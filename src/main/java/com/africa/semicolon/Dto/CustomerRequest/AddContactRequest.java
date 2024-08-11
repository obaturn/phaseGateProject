package com.africa.semicolon.Dto.CustomerRequest;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class AddContactRequest { 
    private String userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

}
