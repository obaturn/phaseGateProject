package com.africa.semicolon.Dto.UserRequest;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String updateId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String message;
}
