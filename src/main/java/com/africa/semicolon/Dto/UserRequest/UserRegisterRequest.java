package com.africa.semicolon.Dto.UserRequest;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserRegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private String username;
    private String phoneNumber;
    private String address;
    private String confirmPassword;
}
