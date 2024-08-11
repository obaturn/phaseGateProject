package com.africa.semicolon.Data.repository;


import com.africa.semicolon.Data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByPhoneNumber(String phoneNumber);

    User findByUserName(String username);
}
