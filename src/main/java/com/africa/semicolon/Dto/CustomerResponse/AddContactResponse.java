package com.africa.semicolon.Dto.CustomerResponse;

import lombok.Data;

@Data
public class AddContactResponse {

    private String id;
    private String Email;
    private String message;
    private String StatusMessage;
    private String phoneNumber;
    private String firstName;
    private String lastName;
}
