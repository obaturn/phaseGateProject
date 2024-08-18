package com.africa.semicolon.Dto.UserRequest;

import lombok.Data;

@Data
public class UserFindByEmailRequest {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
}
