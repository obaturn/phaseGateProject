package com.africa.semicolon.Dto.CustomerRequest;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class EditContactRequest {

    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
