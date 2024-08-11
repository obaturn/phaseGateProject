package com.africa.semicolon.Services;

import com.africa.semicolon.Dto.CustomerRequest.AddContactRequest;
import com.africa.semicolon.Dto.UserRequest.*;
import com.africa.semicolon.Dto.UserResponse.*;
import com.africa.semicolon.Dto.UserRequest.UserRegisterRequest;
import com.africa.semicolon.Dto.UserResponse.UserRegisterResponse;

public interface UserService {
    UserRegisterResponse registerUser(UserRegisterRequest registrationUser);
    UserFindByIdResponse userFindById(UserFindByIdRequest userFindById);
    UserLoginResponse userLogin(UserLoginRequest login);
    UserUpdateResponse updateUser(UserUpdateRequest updateUser);
    UserAddContactResponse userCannotAddAContactWithTheSamePhoneNumber(AddContactRequest addingContact);
    UserAddContactResponse addContactByUser(AddContactRequest addContact);

}
