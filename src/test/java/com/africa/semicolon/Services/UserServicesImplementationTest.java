package com.africa.semicolon.Services;

import com.africa.semicolon.Data.model.User;
import com.africa.semicolon.Data.repository.UserRepository;
import com.africa.semicolon.Dto.CustomerRequest.AddContactRequest;
import com.africa.semicolon.Dto.UserRequest.UserFindByEmailRequest;
import com.africa.semicolon.Dto.UserRequest.UserLoginRequest;
import com.africa.semicolon.Dto.UserRequest.UserRegisterRequest;
import com.africa.semicolon.Dto.UserRequest.UserUpdateRequest;
import com.africa.semicolon.Dto.UserResponse.*;
import com.africa.semicolon.Exception.UserAlreadyExistException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServicesImplementationTest {
@Autowired
private UserServicesImplementation userServicesImplementation;
@Autowired
private UserRepository userRepository;

    @Test
    public void testThatICanRegisterAUser() {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setFirstName("Totm");
        userRegisterRequest.setLastName("Oma");
        userRegisterRequest.setPhoneNumber("08246554250");
        userRegisterRequest.setEmail("obaturna@gmail.com");
        userRegisterRequest.setPassword("123435");
        userRegisterRequest.setUsername("oban");
        UserRegisterResponse response = userServicesImplementation.registerUser(userRegisterRequest);
        assertNotNull(response, "Response is successfully");
        assertThat(response.getMessage()).isEqualTo(" you have been successfully registered");
     assertThat(response.getUserName()).isEqualTo(userRegisterRequest.getUsername());


    }

    @Test
    public void testThatICanFindUserByEmail() {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setFirstName("Tolu");
        userRegisterRequest.setLastName("Obaturn");
        userRegisterRequest.setPhoneNumber("08026554250");
        userRegisterRequest.setEmail("obaturn@gmail.com");
        userRegisterRequest.setPassword("123456");
        userRegisterRequest.setUsername("obasco");
        UserRegisterResponse registerResponse = userServicesImplementation.registerUser(userRegisterRequest);
        assertNotNull(registerResponse, "you have been registered");
        UserFindByEmailRequest userFindByEmailRequestRequest = new UserFindByEmailRequest();
        userFindByEmailRequestRequest.setEmail(registerResponse.getEmail());
        assertThat(userFindByEmailRequestRequest.getEmail()).isEqualTo(registerResponse.getEmail());
        UserFindByEmailResponse response = new UserFindByEmailResponse();
        response.setMessage(userFindByEmailRequestRequest.getEmail());
        assertThat(registerResponse.getMessage()).isEqualTo(" you have been successfully registered");
        assertNotNull(registerResponse.getEmail(), "user found with email\"+user.get().getEmail()");


    }

    @Test
     public void testThatMyUserCanLoginToThereContactApp() {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setFirstName("Toluwalase");
        userRegisterRequest.setLastName("Emmanuel");
        userRegisterRequest.setPhoneNumber("08028554250");
        userRegisterRequest.setEmail("akin@gmail.com");
        userRegisterRequest.setPassword("123456");
        userRegisterRequest.setUsername("emmanuel2901");
        UserRegisterResponse registerResponse = userServicesImplementation.registerUser(userRegisterRequest);
        assertThat(registerResponse.getMessage()).isEqualTo(" you have been successfully registered");

        UserLoginRequest loginRequest = new UserLoginRequest();
        loginRequest.setUserName("emmanuel2901");
        loginRequest.setPassword("123456");
        UserLoginResponse loginResponse = userServicesImplementation.userLogin(loginRequest);
        assertThat(loginResponse.getMessage()).isEqualTo("login successful");

    }

    @Test
    public void testThatMyUserCanUpdate() {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setFirstName("ari");
        userRegisterRequest.setLastName("opeyemi");
        userRegisterRequest.setPhoneNumber("08026554251");
        userRegisterRequest.setEmail("ireoluwa@gmail.com");
        userRegisterRequest.setPassword("123455");
        userRegisterRequest.setUsername("john5671");
        UserRegisterResponse registerResponse = userServicesImplementation.registerUser(userRegisterRequest);
        assertThat(registerResponse.getMessage()).isEqualTo(" you have been successfully registered");
        UserUpdateRequest updateRequest = new UserUpdateRequest();
        updateRequest.setUpdateId(registerResponse.getEmail());
        updateRequest.setFirstName("Tolu");
        updateRequest.setLastName("abimbola");
        updateRequest.setPhoneNumber("08104375142");
        UserUpdateResponse updateResponse = userServicesImplementation.updateUser(updateRequest);
        assertThat(updateResponse.getMessage()).isEqualTo("user updated successfully");
        assertThat(updateResponse.getFirstName()).isEqualTo("you have updated the first name");
    }

    @Test
    public void test_That_A_User_Cant_AddContact_With_The_Same_PhoneNumber() {
        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setFirstName("obaturn");
        addContactRequest.setLastName("ibro");
        addContactRequest.setPhoneNumber("08127554342");

        User existingUser = new User();
        existingUser.setFirstName("obaturn");
        existingUser.setLastName("ibro");
        existingUser.setPhoneNumber("08127554342");
        userRepository.save(existingUser);

        assertThrows(UserAlreadyExistException.class, () -> {userServicesImplementation.userCannotAddAContactWithTheSamePhoneNumber(addContactRequest);
        });

    }

    @Test
    public void addContactByUser() {
        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setFirstName("oba");
        addContactRequest.setLastName("Obaturn");
        addContactRequest.setPhoneNumber("08127554352");
        addContactRequest.setEmail("ob132@gmail.com");
        UserAddContactResponse response = userServicesImplementation.addContactByUser(addContactRequest);
        assertNotNull(response, "Response is successfully");
        assertThat(response.getMessage()).isEqualTo("Contact added successfully");


    }
}