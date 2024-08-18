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
import com.africa.semicolon.Exception.ContactAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
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
        addContactRequest.setFirstName("sam");
        addContactRequest.setLastName("Mikel");
        addContactRequest.setPhoneNumber("08154375142");
        contactService.createContact(addContactRequest);
        AddContactResponse response = new AddContactResponse();
        response.setMessage(addContactRequest.getFirstName());
        assertThat(addContactRequest.getFirstName()).isEqualTo("sam");

    }

    @Test
    void testThatICanEditContact() {

        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setFirstName("Al");
        addContactRequest.setLastName("ajo");
        addContactRequest.setPhoneNumber("08130577871");
        AddContactResponse response = contactService.createContact(addContactRequest);
        assertThat(response.getFirstName()).isEqualTo("Al");
        EditContactRequest editContactRequest = new EditContactRequest();
        editContactRequest.setFirstName("glory");
        editContactRequest.setLastName("chi");
        editContactRequest.setPhoneNumber("08130577871");
        EditContactResponse response2 = contactService.editContact(editContactRequest);
        assertThat(response2.getMessage()).isEqualTo("contact updated successfully");
        assertThat(response2.getFirstName()).isEqualTo("glory");
        assertThat(response2.getLastName()).isEqualTo("chi");
    }


    @Test
   public  void testThatICanFindContact() {
        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setFirstName("jo");
        addContactRequest.setLastName("bo");
        addContactRequest.setPhoneNumber("07043510740");
        AddContactResponse response = contactService.createContact(addContactRequest);
        assertThat(response.getFirstName()).isEqualTo("jo");

        FindContactRequest findContactRequest = new FindContactRequest();
        findContactRequest.setFirstName("jo");
        findContactRequest.setLastName("bo");
        findContactRequest.setPhoneNumber("07043510740");
        FindContactResponse answer = contactService.findContacts(findContactRequest);

        assertNotNull(answer, "Contact found with \" + findContactRequest.getPhoneNumber()");
        assertThat(answer.getFirstName()).isEqualTo("jo");
        assertThat(answer.getLastName()).isEqualTo("bo");


    }


    @Test
    public void testThatICanDeleteContact() {
        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setFirstName("foyin");
        addContactRequest.setLastName("yola");
        addContactRequest.setPhoneNumber("07106458140");
        AddContactResponse response = contactService.createContact(addContactRequest);
        System.out.println("Contact created successfully: " + response.getPhoneNumber());

        DeleteContactRequest deleteContactRequest = new DeleteContactRequest();
        deleteContactRequest.setPhoneNumber("07106458140");
        deleteContactRequest.setFirstName(addContactRequest.getFirstName());
        DeleteContactResponse deleteResponse = contactService.deleteContact(deleteContactRequest);
        deleteResponse.setPhoneNumber(deleteContactRequest.getPhoneNumber());

        assertNotNull(deleteResponse, "Contact deleted successfully with \" + delete.getPhoneNumber()");




    }
    @Test
    public  void testThatICanAllContactByTheirFirstName() {
        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setFirstName("Jom");
        addContactRequest.setLastName("Mickelar");
        addContactRequest.setPhoneNumber("08167214532");
        AddContactResponse response = contactService.createContact(addContactRequest);
        response.setMessage("you have successfully added contact");
        List<Contact> contacts = contactRepository.getAllByFirstName(addContactRequest.getFirstName());
        assertThat(contacts.size()).isEqualTo(1);
        assertThat(contacts.getFirst().getFirstName()).isEqualTo(addContactRequest.getFirstName());
    }
    @Test
    public void testThatICanFindContactByLastName() {
        AddContactRequest addContactRequest = new AddContactRequest();
        addContactRequest.setFirstName("Jo");
        addContactRequest.setLastName("Mi");
        addContactRequest.setPhoneNumber("08105385124");
        AddContactResponse response = contactService.createContact(addContactRequest);
        response.setMessage("you have successfully added contact");
        AddContactRequest addContactRequest2 = new AddContactRequest();
        addContactRequest2.setFirstName("Jo");
        addContactRequest2.setLastName("Mi");
        addContactRequest2.setPhoneNumber("08105385124");
        assertThrows(ContactAlreadyExistsException.class, () -> contactService.createContact(addContactRequest2));
        List<Contact> contacts = contactRepository.findByLastName(addContactRequest.getLastName());
        assertThat(contacts.size()).isEqualTo(1);
        assertThat(contacts.get(0).getLastName()).isEqualTo(addContactRequest.getLastName());

    }
    @Test
    public void testThatICanMyContactDoesNotAcceptDuplicateNames() {

        AddContactRequest initialRequest = new AddContactRequest();
        initialRequest.setFirstName("mohamed");
        initialRequest.setLastName("bolaji");
        initialRequest.setPhoneNumber("09128534560");
        contactService.createContact(initialRequest);
        AddContactRequest duplicateRequest = new AddContactRequest();
        duplicateRequest.setFirstName("mohamed");
        duplicateRequest.setLastName("bolaji");
        duplicateRequest.setPhoneNumber("09128534560");
        assertThrows(ContactAlreadyExistsException.class, () -> contactService.duplicateFirstNameAndLastName(duplicateRequest));

    }
}