package com.africa.semicolon.Dto.CustomerRequest;

import lombok.Data;

@Data
public class DeleteContactRequest {
    private String DeleteId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

}
