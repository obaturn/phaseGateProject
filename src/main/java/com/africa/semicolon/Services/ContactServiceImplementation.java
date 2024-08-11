package com.africa.semicolon.Services;

import com.africa.semicolon.Data.model.Contact;
import com.africa.semicolon.Data.repository.ContactRepository;
import com.africa.semicolon.Exception.ContactNotFoundException;
import com.africa.semicolon.Dto.CustomerRequest.AddContactRequest;
import com.africa.semicolon.Dto.CustomerRequest.DeleteContactRequest;
import com.africa.semicolon.Dto.CustomerRequest.EditContactRequest;
import com.africa.semicolon.Dto.CustomerRequest.FindContactRequest;
import com.africa.semicolon.Dto.CustomerResponse.AddContactResponse;
import com.africa.semicolon.Dto.CustomerResponse.DeleteContactResponse;
import com.africa.semicolon.Dto.CustomerResponse.EditContactResponse;
import com.africa.semicolon.Dto.CustomerResponse.FindContactResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class ContactServiceImplementation implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public AddContactResponse createContact(AddContactRequest addContactRequest) {
            Contact contact1 = new Contact();
            contact1.setFirstName(addContactRequest.getFirstName());
            contact1.setLastName(addContactRequest.getLastName());
            contact1.setPhoneNumber(addContactRequest.getPhoneNumber());
            contact1.setEmail(addContactRequest.getEmail());
            contactRepository.save(contact1);
            AddContactResponse addContactResponse = new AddContactResponse();
            addContactResponse.setEmail(contact1.getEmail());
            addContactResponse.setFirstName(contact1.getFirstName());
            addContactResponse.setLastName(contact1.getLastName());
            addContactResponse.setPhoneNumber(contact1.getPhoneNumber());
            addContactResponse.setEmail(contact1.getEmail());
            addContactResponse.setMessage("you have successfully create a new contact");
            return addContactResponse;
        }




    @Override
    public EditContactResponse editContact(EditContactRequest edit) {
        Contact contact = new Contact();
        contact.setFirstName(edit.getFirstName());
        contact.setLastName(edit.getLastName());
        contact.setPhoneNumber(edit.getPhoneNumber());
        contactRepository.save(contact);
        EditContactResponse editContactResponse = new EditContactResponse();
        editContactResponse.setMessage("contact edited successfully");

        return editContactResponse;

    }

    @Override
    public FindContactResponse findContact(FindContactRequest findContactRequest) {

        Optional<Contact> contact =contactRepository.findByLastName(findContactRequest.getLastName());
        if (contact.isEmpty()) {
            throw new ContactNotFoundException("Contact cannot be found because it does not exist");

        } else{
            FindContactResponse findContactResponse = new FindContactResponse();
            findContactResponse.setMessage("contact found with last name");
            return findContactResponse;

        }
    }


    @Override
    public DeleteContactResponse deleteContact(DeleteContactRequest delete) {
        System.out.println(" deleting contact with phoneNumber"+delete.getPhoneNumber());
        Optional<Contact>contact = contactRepository.deleteByPhoneNumber(delete.getPhoneNumber());
        if (contact.isPresent()) {
            contactRepository.delete(contact.get());
            DeleteContactResponse deleteContactResponse = new DeleteContactResponse();
            deleteContactResponse.setMessage("contact deleted successfully");
            return deleteContactResponse;
        }
        return null;
    }


}
