package com.africa.semicolon.Dto.UserRequest;

import lombok.Data;

@Data
public class UserAddContactRequest {
    private String Id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
