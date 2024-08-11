package com.africa.semicolon.Dto.UserResponse;

import lombok.Data;

@Data
public class UserUpdateResponse {
    private String id;
    private String message;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
