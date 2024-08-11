package com.africa.semicolon.Data.repository;


import com.africa.semicolon.Data.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface ContactRepository extends MongoRepository<Contact,String> {
    Optional<Contact> deleteByPhoneNumber(String phoneNumber);
    Optional<Contact> findByPhoneNumber(String phoneNumber);
    Optional<Contact> findByLastName(String lastName);
}
