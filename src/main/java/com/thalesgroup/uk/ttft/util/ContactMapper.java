package com.thalesgroup.uk.ttft.util;

import com.thalesgroup.uk.ttft.dto.ContactDto;
import com.thalesgroup.uk.ttft.dto.CreateContactDto;
import com.thalesgroup.uk.ttft.dto.UpdateContactDto;
import com.thalesgroup.uk.ttft.model.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    Contact toEntity(CreateContactDto createContactDTO);
    Contact toEntity(UpdateContactDto updateContactDTO);

    ContactDto toDto(Contact contact);

    List<ContactDto> toDTOs(List<Contact> contacts);
}
