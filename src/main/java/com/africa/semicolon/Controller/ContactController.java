package com.africa.semicolon.Controller;

import com.africa.semicolon.Dto.CustomerRequest.AddContactRequest;
import com.africa.semicolon.Dto.CustomerRequest.DeleteContactRequest;
import com.africa.semicolon.Dto.CustomerRequest.EditContactRequest;
import com.africa.semicolon.Dto.CustomerRequest.FindContactRequest;
import com.africa.semicolon.Dto.CustomerResponse.AddContactResponse;
import com.africa.semicolon.Dto.CustomerResponse.ApiResponse;
import com.africa.semicolon.Dto.CustomerResponse.FindContactResponse;
import com.africa.semicolon.Services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/Contact")
public class ContactController {
    @Autowired
    private ContactService contactService;
    @PostMapping("/createContact")
    public ResponseEntity<?> createContact(@RequestBody AddContactRequest request) {
        try{
            AddContactResponse response = contactService.createContact(request);
            return new ResponseEntity<>(new ApiResponse(true, response),CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()),BAD_REQUEST);
        }

    }
    @GetMapping("findContacts")
    public ResponseEntity<?> findContacts(@RequestBody FindContactRequest findContactRequest) {
        try {
            FindContactResponse response = contactService.findContacts(findContactRequest);
            return  new ResponseEntity<>(new ApiResponse(true,response),FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),NOT_FOUND);
        }
    }
    @DeleteMapping("deleteContact")
    public ResponseEntity<?> deleteContact(@RequestBody DeleteContactRequest deleteContactRequest) {
        try{
        return new ResponseEntity<>(new ApiResponse(true,contactService.deleteContact(deleteContactRequest)),NO_CONTENT);

    }catch (Exception e){

        return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),NO_CONTENT);
        }
    }
    @PatchMapping("editContact")
   public ResponseEntity<?> editContact(@RequestBody EditContactRequest editContactRequest) {
        try{
        return new ResponseEntity<>(new ApiResponse(true,contactService.editContact(editContactRequest)),NO_CONTENT);
    }
        catch(Exception e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),NOT_FOUND);
        }
    }
    @GetMapping("getAllTheFirstName")
    public ResponseEntity<?> getAllTheFirstName(@RequestBody String firstName) {
        try{
            return new ResponseEntity<>(new ApiResponse(true,contactService.getAllTheFirstName(firstName)),CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),NOT_FOUND);
        }
        
    }
    @GetMapping("getAllTheLastName")
    public ResponseEntity<?> getAllTheLastName(@RequestBody String lastName) {
        try{
            return new ResponseEntity<>(new ApiResponse(true,contactService.getAllTheLastName(lastName)),CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),NOT_FOUND);
        }
    }

}
