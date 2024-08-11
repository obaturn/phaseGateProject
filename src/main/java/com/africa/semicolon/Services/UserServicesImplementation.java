package com.africa.semicolon.Services;

import com.africa.semicolon.Data.model.Contact;
import com.africa.semicolon.Data.model.User;
import com.africa.semicolon.Data.repository.ContactRepository;
import com.africa.semicolon.Dto.CustomerRequest.AddContactRequest;
import com.africa.semicolon.Dto.UserRequest.*;
import com.africa.semicolon.Dto.UserResponse.*;
import com.africa.semicolon.Data.repository.UserRepository;

import com.africa.semicolon.Exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServicesImplementation implements UserService {
    @Autowired
    ContactService contactService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public UserRegisterResponse registerUser(UserRegisterRequest registrationUser) {
        User user = new User();
        user.setEmail(registrationUser.getEmail());
        user.setFirstName(registrationUser.getFirstName());
        user.setLastName(registrationUser.getLastName());
        user.setPhoneNumber(registrationUser.getPhoneNumber());
          user.setPassword(registrationUser.getPassword());
        user.setUserName(registrationUser.getUserName());
        userRepository.save(user);
        UserRegisterResponse response = new UserRegisterResponse();
        response.setMessage(" you have been successfully registered");
        response.setUser_id(user.getId());
        response.setUserName(registrationUser.getUserName());
        return response;
    }

    @Override
    public UserFindByIdResponse userFindById(UserFindByIdRequest userFindByIdRequest) {
        Optional<User> user = userRepository.findById(userFindByIdRequest.getId());
        if (user.isPresent()) {
            UserFindByIdResponse response = new UserFindByIdResponse();
            response.setMessage("User found with Id"+userFindByIdRequest.getId());
            return response;
        } else {
            throw new UserNotFoundException(userFindByIdRequest.getId());
        }

    }

    @Override
    public UserLoginResponse userLogin(UserLoginRequest login) {
        User user = userRepository.findByUserName(login.getUserName());
        if (!user.getPassword().equals(login.getPassword())) {
            throw new LoginUserException("incorrect password");
        }else {
            user.setUserName(login.getUserName());
            user.setPassword(login.getPassword());
            user.setLogin(true);
            userRepository.save(user);
        }
        UserLoginResponse response = new UserLoginResponse();
        response.setMessage("login successful");
        return response;
    }

    @Override
    public UserUpdateResponse updateUser(UserUpdateRequest updateUser) {
        User user = userRepository.findById(updateUser.getUpdateId()).orElse(null);
        if (user == null) {
            throw new UpdateCannotBeDone("user not found");

        } else{
            user.setFirstName(updateUser.getFirstName());
            user.setLastName(updateUser.getLastName());
            user.setPhoneNumber(updateUser.getPhoneNumber());
            userRepository.save(user);
            UserUpdateResponse response = new UserUpdateResponse();
            response.setMessage("user updated successfully");
            response.setId(user.getId());
            response.setFirstName("you have updated the first name");
            return response;

        }

    }

    @Override
    public UserAddContactResponse userCannotAddAContactWithTheSamePhoneNumber(AddContactRequest addingContact) {
        Optional<User> existingUser = userRepository.findByPhoneNumber(addingContact.getPhoneNumber());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistException("User already exist with this phoneNumber");
        } else {
            User user = new User();
            user.setFirstName(addingContact.getFirstName());
            user.setLastName(addingContact.getLastName());
            user.setPhoneNumber(addingContact.getPhoneNumber());
            userRepository.save(user);
            UserAddContactResponse response = new UserAddContactResponse();
            response.setMessage("Contact added successfully");
            return response;
        }
    }

    @Override
    public UserAddContactResponse addContactByUser(AddContactRequest addContact) {
        Contact contact = new Contact();
        contact.setFirstName(addContact.getFirstName());
        contact.setLastName(addContact.getLastName());
        contact.setEmail(addContact.getEmail());
        contact.setPhoneNumber(addContact.getPhoneNumber());
        contactRepository.save(contact);
        User user = new User();
        user.setContactList(contactRepository.findAll());
        userRepository.save(user);
        UserAddContactResponse response = new UserAddContactResponse();
        response.setMessage("Contact added successfully");

        return response;
    }
}
