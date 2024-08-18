package com.africa.semicolon.Dto.UserResponse;

import lombok.Data;

@Data
public class UserRegisterResponse {
    private String message;
    private String UserName;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String statusMessage;
    private String email;


}
