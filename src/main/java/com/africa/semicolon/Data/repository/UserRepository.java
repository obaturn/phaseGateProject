package com.africa.semicolon.Data.repository;


import com.africa.semicolon.Data.model.Contact;
import com.africa.semicolon.Data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByPhoneNumber(String phoneNumber);

    User findByUsername(String username);
    Optional<User> findByEmail(String email);

    Optional<Contact> findByLastNameAndFirstName(String lastName, String firstName);
}
