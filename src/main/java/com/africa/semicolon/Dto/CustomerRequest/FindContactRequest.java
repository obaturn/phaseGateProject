package com.africa.semicolon.Dto.CustomerRequest;

import lombok.Data;

@Data
public class FindContactRequest {
    private String findUserId;
    private String contactId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
