package com.africa.semicolon.Dto.CustomerResponse;

import lombok.Data;

@Data
public class EditContactResponse {
    private String  message;
    private String  firstName;
    private String  lastName;
    private String  email;
    private String  phoneNumber;
    private String statusMessage;
}
