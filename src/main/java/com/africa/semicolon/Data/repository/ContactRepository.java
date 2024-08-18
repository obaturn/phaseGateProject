package com.africa.semicolon.Data.repository;


import com.africa.semicolon.Data.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ContactRepository extends MongoRepository<Contact,String> {
    Optional<Contact> findByPhoneNumber(String phoneNumber);
    List<Contact> findByLastName (String lastName);
    List<Contact> getAllByFirstName(String firstName);
   Optional< Contact> findByFirstNameAndLastName(String firstName, String lastName);

}
