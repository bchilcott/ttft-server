package com.thalesgroup.uk.ttft.controller;

import com.thalesgroup.uk.ttft.dto.ContactDto;
import com.thalesgroup.uk.ttft.dto.CreateContactDto;
import com.thalesgroup.uk.ttft.dto.UpdateContactDto;
import com.thalesgroup.uk.ttft.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contacts")
@CrossOrigin(origins = "http://localhost:3000")
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<List<ContactDto>> getAllContacts() {
        List<ContactDto> contacts = contactService.getAllContacts();
        if (contacts.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        } else {
            return ResponseEntity.ok(contacts);
        }
    }

    @PostMapping
    public ResponseEntity<ContactDto> createContact(@RequestBody CreateContactDto createContactDto) {
        ContactDto createdContact = contactService.createContact(createContactDto);
        return ResponseEntity.created(URI.create("/contacts/" + createdContact.getSystemId())).body(createdContact);
    }

    @GetMapping("/{trackId}")
    public ResponseEntity<ContactDto> getContactById(@PathVariable String trackId) {
        Optional<ContactDto> contact = contactService.getContactById(trackId);
        return contact.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{trackId}")
    public ResponseEntity<ContactDto> updateContact(@RequestBody UpdateContactDto updateContactDto) {
        ContactDto updatedContact = contactService.updateContact(updateContactDto);
        return ResponseEntity.ok(updatedContact);
    }

    @DeleteMapping("/{trackId}")
    public ResponseEntity<ContactDto> deleteContact(@PathVariable String trackId) {
        contactService.deleteContact(trackId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<ContactDto> deleteAllContacts() {
        contactService.deleteAllContacts();
        return ResponseEntity.noContent().build();
    }
}