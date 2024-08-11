package com.africa.semicolon.Dto.UserRequest;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String Gender;
    private String UserName;
    private String phoneNumber;
}
