package com.thalesgroup.uk.ttft.service;

import com.thalesgroup.uk.ttft.dto.ContactDto;
import com.thalesgroup.uk.ttft.dto.CreateContactDto;
import com.thalesgroup.uk.ttft.dto.UpdateContactDto;
import com.thalesgroup.uk.ttft.exception.ContactNotFoundException;
import com.thalesgroup.uk.ttft.model.Contact;
import com.thalesgroup.uk.ttft.model.ContactPosition;
import com.thalesgroup.uk.ttft.repository.ContactRepository;
import com.thalesgroup.uk.ttft.util.ContactMapper;
import org.geotools.referencing.GeodeticCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    @Autowired
    public ContactService(ContactRepository contactRepository, ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    public Optional<ContactDto> getContactById(String id) {
        Optional<Contact> contact = contactRepository.findById(id);
        return contact.map(contactMapper::toDto);
    }

    public List<ContactDto> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return contactMapper.toDTOs(contacts);
    }

    public ContactDto createContact(CreateContactDto createContactDto) {
        Contact contact = contactMapper.toEntity(createContactDto);
        contact.setDataSource("TTFT");
        contact.setStale(false);
        Contact savedContact = contactRepository.save(contact);
        return contactMapper.toDto(savedContact);
    }

    public ContactDto updateContact(UpdateContactDto updateContactDto) {
        Contact existingContact = contactRepository.findById(updateContactDto.getTrackId())
                .orElseThrow(ContactNotFoundException::new);

        Contact updatedContact = contactMapper.toEntity(updateContactDto);
        updatedContact.setTrackId(existingContact.getTrackId()); // Retain the ID of the existing contact

        Contact savedContact = contactRepository.save(updatedContact);
        return contactMapper.toDto(savedContact);
    }

    @Scheduled(fixedDelay = 1000) // Runs every second
    public void updateTrackPositions() {
        List<Contact> contacts = contactRepository.findAll();
        for (Contact contact : contacts) {
            double speed = contact.getSpeed();
            double course = contact.getCourse();
            double latitude = contact.getPosition().getLatitude();
            double longitude = contact.getPosition().getLongitude();

            // Create a GeodeticCalculator
            GeodeticCalculator calc = new GeodeticCalculator();
            calc.setStartingGeographicPoint(longitude, latitude);
            calc.setDirection(course, speed);

            // Calculate the new position
            Point2D newPosition = calc.getDestinationGeographicPoint();

            // Update the contact's position
            ContactPosition position = contact.getPosition();
            position.setLatitude(newPosition.getY());
            position.setLongitude(newPosition.getX());
            contactRepository.save(contact);
        }
    }

    public void deleteContact(String id) {
        contactRepository.findById(id)
                .orElseThrow(ContactNotFoundException::new);
        contactRepository.deleteById(id);
    }

    public void deleteAllContacts() {
        contactRepository.deleteAll();
    }
}
