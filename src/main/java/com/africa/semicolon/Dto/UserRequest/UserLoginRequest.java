package com.africa.semicolon.Dto.UserRequest;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String UserName;
    private String password;
}
