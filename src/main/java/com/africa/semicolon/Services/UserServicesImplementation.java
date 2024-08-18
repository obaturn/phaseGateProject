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
        Optional<Contact> duplicateNames = userRepository.findByLastNameAndFirstName(registrationUser.getLastName(), registrationUser.getFirstName());
        if (duplicateNames.isPresent()) {
            throw new UserNotFoundException("User with last name " + registrationUser.getLastName()+"And user with first name" +registrationUser.getFirstName()+ " already exists");
        }

        if (registrationUser.getPhoneNumber().length() != 11) {
            throw new UserAlreadyExistException("Dear Sir or Ma, your phone number must be exactly 11 digits long");
        }

        if (registrationUser.getPassword().length() != 6) {
            throw new LoginUserException("Password must be exactly 6 digits long");
        }

        User user = new User();
        user.setEmail(registrationUser.getEmail());
        user.setFirstName(registrationUser.getFirstName());
        user.setLastName(registrationUser.getLastName());
        user.setPhoneNumber(registrationUser.getPhoneNumber());
        user.setPassword(registrationUser.getPassword());
        user.setUsername(registrationUser.getUsername());

        userRepository.save(user);

        UserRegisterResponse response = new UserRegisterResponse();
        response.setMessage(" you have been successfully registered");
        response.setUserName(user.getUsername());
        response.setFirstname(user.getFirstName());
        response.setLastname(user.getLastName());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setEmail(user.getEmail());
        response.setStatusMessage("registration successful");

        return response;

    }
    @Override
    public UserFindByEmailResponse userFindByEmail(UserFindByEmailRequest emailRequest) {

        Optional<User> user = userRepository.findByEmail(emailRequest.getEmail());
        if (user.isPresent()) {
            UserFindByEmailResponse response = new UserFindByEmailResponse();
            response.setMessage("user found with email"+user.get().getEmail());
            return response;
        } else {
            throw new UserNotFoundException("user not found with email"+emailRequest.getEmail());
        }

    }

    @Override
    public UserLoginResponse userLogin(UserLoginRequest login) {
        User user = userRepository.findByUsername(login.getUserName());
        if (!user.getPassword().equals(login.getPassword())) {
            throw new LoginUserException("incorrect password");
        }
        if(login.getPassword().length() != 6){
            throw new LoginUserException("password too short it must be 6 digit");
        }


        else {
            user.setUsername(login.getUserName());
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
        Optional<User> optionalUser = userRepository.findByPhoneNumber(updateUser.getPhoneNumber());
        if (optionalUser.isEmpty()) {
            throw new UpdateCannotBeDone("user not found");
        } else {
            User user = optionalUser.get();
            user.setFirstName(updateUser.getFirstName());
            user.setLastName(updateUser.getLastName());
            user.setPhoneNumber(updateUser.getPhoneNumber());
            userRepository.save(user);

            UserUpdateResponse response = new UserUpdateResponse();
            response.setMessage("user updated successfully");
            response.setFirstName("you have updated the first name");
            response.setLastName("you have updated the last name");
            return response;
        }
        }



    @Override
    public UserAddContactResponse userCannotAddAContactWithTheSamePhoneNumber(AddContactRequest addingContact) {
        Optional<User> existingUser = userRepository.findByPhoneNumber(addingContact.getPhoneNumber());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistException("User already exists with this phone number");
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
