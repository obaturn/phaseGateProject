package com.africa.semicolon.Dto.CustomerResponse;

import lombok.Data;

@Data
public class FindContactResponse {
    private String message;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String statusMessage;
}
