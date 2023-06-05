package com.thalesgroup.uk.ttft.repository;

import com.thalesgroup.uk.ttft.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
}
