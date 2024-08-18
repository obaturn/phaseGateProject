package com.africa.semicolon.Services;
import com.africa.semicolon.Data.model.Contact;
import com.africa.semicolon.Dto.CustomerRequest.AddContactRequest;
import com.africa.semicolon.Dto.CustomerRequest.DeleteContactRequest;
import com.africa.semicolon.Dto.CustomerRequest.EditContactRequest;
import com.africa.semicolon.Dto.CustomerRequest.FindContactRequest;
import com.africa.semicolon.Dto.CustomerResponse.AddContactResponse;
import com.africa.semicolon.Dto.CustomerResponse.DeleteContactResponse;
import com.africa.semicolon.Dto.CustomerResponse.EditContactResponse;
import com.africa.semicolon.Dto.CustomerResponse.FindContactResponse;

import java.util.List;

public interface ContactService {
    AddContactResponse createContact(AddContactRequest addContactRequest);
    EditContactResponse editContact(EditContactRequest edit);
    FindContactResponse findContacts(FindContactRequest findContactRequest);
    DeleteContactResponse deleteContact(DeleteContactRequest delete);
    List<Contact> getAllTheFirstName(String firstName);
    List<Contact> getAllTheLastName(String lastName);
    void duplicateFirstNameAndLastName(AddContactRequest addContactRequest);

}
