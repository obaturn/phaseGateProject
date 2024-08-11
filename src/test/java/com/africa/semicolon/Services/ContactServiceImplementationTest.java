package com.africa.semicolon.Services;

import com.africa.semicolon.Data.model.Contact;
import com.africa.semicolon.Data.model.User;
import com.africa.semicolon.Data.repository.ContactRepository;
import com.africa.semicolon.Dto.CustomerRequest.AddContactRequest;
import com.africa.semicolon.Dto.CustomerRequest.DeleteContactRequest;
import com.africa.semicolon.Dto.CustomerRequest.EditContactRequest;
import com.africa.semicolon.Dto.CustomerRequest.FindContactRequest;
import com.africa.semicolon.Dto.CustomerResponse.AddContactResponse;
import com.africa.semicolon.Dto.CustomerResponse.DeleteContactResponse;
import com.africa.semicolon.Dto.CustomerResponse.EditContactResponse;
import com.africa.semicolon.Dto.CustomerResponse.FindContactResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ContactServiceImplementationTest {
@Autowired
private ContactService contactService;
@Autowired
private ContactRepository contactRepository;

    @Test
    void testThatICanCreateContact() {
        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setFirstName("Daniel");
        addContactRequest.setLastName("Michael");
        addContactRequest.setPhoneNumber("08104375140");
        contactService.createContact(addContactRequest);
        AddContactResponse response = new AddContactResponse();
        response.setMessage(addContactRequest.getFirstName());
        assertThat(addContactRequest.getFirstName()).isEqualTo("Daniel");

    }

    @Test
    void testThatICanEditContact() {
        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setFirstName("Dan");
        addContactRequest.setLastName("Mic");
        addContactRequest.setPhoneNumber("08134577809");
        AddContactResponse response = contactService.createContact(addContactRequest);
        assertThat(response.getFirstName()).isEqualTo("Dan");
        FindContactRequest findContactRequest = new FindContactRequest();
        findContactRequest.setFirstName("Dan");
        findContactRequest.setLastName("Mic");
        findContactRequest.setPhoneNumber("08134577809");
        EditContactRequest editContactRequest = new EditContactRequest();
        editContactRequest.setFirstName("Tonia");
        contactService.editContact(editContactRequest);
        EditContactResponse response2 = new EditContactResponse();
        assertNotEquals(response2.getMessage(),"contact edited successfully");
    }


    @Test
   public  void testThatICanFindContact() {
        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setFirstName("jo");
        addContactRequest.setLastName("Mich");
        addContactRequest.setPhoneNumber("08104375143");
        AddContactResponse response = contactService.createContact(addContactRequest);
        assertThat(response.getFirstName()).isEqualTo("jo");
        FindContactRequest findContactRequest = new FindContactRequest();
         findContactRequest.setFirstName("jo");
         findContactRequest.setLastName("Mich");
        FindContactResponse answer = contactService.findContact(findContactRequest);
        assertNotNull(answer, "contact found with last name");
        assertThat(findContactRequest.getFirstName()).isEqualTo("jo");
    }

    @Test
    public void testThatICanDeleteContact() {
        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setFirstName("Daniel");
        addContactRequest.setLastName("Michael");
        addContactRequest.setPhoneNumber("08106378142");
        AddContactResponse response = contactService.createContact(addContactRequest);
        System.out.println("Contact created successfully: " + response.getPhoneNumber());
        DeleteContactRequest deleteContactRequest = new DeleteContactRequest();
        deleteContactRequest.setPhoneNumber(response.getPhoneNumber());
        deleteContactRequest.setFirstName(response.getFirstName());
        DeleteContactResponse deleteResponse = contactService.deleteContact(deleteContactRequest);
        assertNotNull(deleteResponse, "contact deleted successfully");


    }
}