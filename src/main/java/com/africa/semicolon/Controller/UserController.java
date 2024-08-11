package com.africa.semicolon.Controller;

import com.africa.semicolon.Data.model.User;
import com.africa.semicolon.Dto.CustomerRequest.AddContactRequest;
import com.africa.semicolon.Dto.CustomerResponse.ApiResponse;
import com.africa.semicolon.Dto.UserRequest.UserFindByIdRequest;
import com.africa.semicolon.Dto.UserRequest.UserLoginRequest;
import com.africa.semicolon.Dto.UserRequest.UserRegisterRequest;
import com.africa.semicolon.Dto.UserRequest.UserUpdateRequest;
import com.africa.semicolon.Dto.UserResponse.UserFindByIdResponse;
import com.africa.semicolon.Dto.UserResponse.UserLoginResponse;
import com.africa.semicolon.Dto.UserResponse.UserRegisterResponse;
import com.africa.semicolon.Dto.UserResponse.UserUpdateResponse;
import com.africa.semicolon.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("api/v1")
public class UserController {
    @Autowired
    private UserService userService;

@PostMapping("/registerUser")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequest userRegisterRequest) {
    try {
        UserRegisterResponse userRegisterResponse = userService.registerUser(userRegisterRequest);
        return new ResponseEntity<>(new ApiResponse(true,userRegisterResponse),CREATED);
    }catch (Exception e){
        return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),NON_AUTHORITATIVE_INFORMATION);
    }
  }
  @GetMapping("/userFindById")
    public ResponseEntity<?> userFindById(@RequestBody UserFindByIdRequest  findId) {
    try{
      UserFindByIdResponse response = userService.userFindById(findId);
      return new ResponseEntity<>(new ApiResponse(true,response),FOUND);

  }catch (Exception e){
        return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),NOT_FOUND);
    }
  }
  @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
    try{
      UserUpdateResponse response = userService.updateUser(userUpdateRequest);
      return new ResponseEntity<>(new ApiResponse(true,response),FOUND);

  }catch (Exception e){
        return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),FAILED_DEPENDENCY);
    }
  }
  @PostMapping("userLogin")
    public ResponseEntity<?> userLogin(@RequestBody UserLoginRequest userLoginRequest) {
        try{
      UserLoginResponse loginResponse = userService.userLogin(userLoginRequest);
      return new ResponseEntity<>(new ApiResponse(true,loginResponse),OK);

  }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),FAILED_DEPENDENCY);
        }
    }
  @PutMapping("/addContactByUser")
  public ResponseEntity<?> addContactByUser(@RequestBody AddContactRequest addContactRequest) {
    try{
    return new ResponseEntity<>(new ApiResponse(true,userService.addContactByUser(addContactRequest)),OK);
  } catch (Exception e){
        return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),REQUEST_TIMEOUT);
    }
  }

}
