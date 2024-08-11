package com.africa.semicolon.Data.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class Contact {
    private String firstName;
    private String lastName;
    @Id
    private String id;
    private String email;
    private String address;
    private String gender;
    private String phoneNumber;
}
