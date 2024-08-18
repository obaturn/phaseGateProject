package com.africa.semicolon.Services;

import com.africa.semicolon.Data.model.Contact;
import com.africa.semicolon.Data.repository.ContactRepository;
import com.africa.semicolon.Exception.ContactAlreadyExistsException;
import com.africa.semicolon.Exception.ContactDigit;
import com.africa.semicolon.Exception.ContactNotFoundException;
import com.africa.semicolon.Dto.CustomerRequest.AddContactRequest;
import com.africa.semicolon.Dto.CustomerRequest.DeleteContactRequest;
import com.africa.semicolon.Dto.CustomerRequest.EditContactRequest;
import com.africa.semicolon.Dto.CustomerRequest.FindContactRequest;
import com.africa.semicolon.Dto.CustomerResponse.AddContactResponse;
import com.africa.semicolon.Dto.CustomerResponse.DeleteContactResponse;
import com.africa.semicolon.Dto.CustomerResponse.EditContactResponse;
import com.africa.semicolon.Dto.CustomerResponse.FindContactResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ContactServiceImplementation implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public AddContactResponse createContact(AddContactRequest addContactRequest) {
        Optional<Contact> duplicateContact = contactRepository.findByFirstNameAndLastName(
                addContactRequest.getFirstName(), addContactRequest.getLastName());
        if (duplicateContact.isPresent()) {
            throw new ContactAlreadyExistsException("A contact already exists with first name "
                    + addContactRequest.getFirstName() + " and last name " + addContactRequest.getLastName());
        }
          if(addContactRequest.getPhoneNumber().length() != 11) {
             throw new ContactDigit("sorry sir or ma your phone number must be up to 11 digits");

            }
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
        Optional<Contact> exisingContact = contactRepository.findByPhoneNumber(edit.getPhoneNumber());
        if (exisingContact.isEmpty()) {
            throw new ContactNotFoundException("Contact not found");
        } else {
            Contact contactToUpdate = exisingContact.get();
            contactToUpdate.setFirstName(edit.getFirstName());
            contactToUpdate.setLastName(edit.getLastName());
            contactRepository.save(contactToUpdate);
            EditContactResponse editContactResponse = new EditContactResponse();
            editContactResponse.setFirstName(contactToUpdate.getFirstName());
            editContactResponse.setLastName(contactToUpdate.getLastName());
            editContactResponse.setMessage("contact updated successfully");
            return editContactResponse;
        }
    }

    @Override
     public FindContactResponse findContacts(@NotNull FindContactRequest findContactRequest) {
        Optional<Contact> contact = contactRepository.findByPhoneNumber(findContactRequest.getPhoneNumber());
        if (contact.isEmpty()) {
            throw new ContactNotFoundException("Contact cannot be found because it does not exist");
        } else {
            FindContactResponse findContactResponse = new FindContactResponse();
            Contact foundContact = contact.get();
            findContactResponse.setFirstName(foundContact.getFirstName());
            findContactResponse.setLastName(foundContact.getLastName());
            findContactResponse.setPhoneNumber(foundContact.getPhoneNumber());
            findContactResponse.setMessage("Contact found with " + findContactRequest.getPhoneNumber());
            return findContactResponse;
        }
    }


    @Override
    public DeleteContactResponse deleteContact(DeleteContactRequest delete) {
        Optional<Contact> contact = contactRepository.findByPhoneNumber(delete.getPhoneNumber());
        if (contact.isPresent()) {
            contactRepository.delete(contact.get());
            DeleteContactResponse deleteContactResponse = new DeleteContactResponse();
            deleteContactResponse.setMessage("Contact deleted successfully with " + delete.getPhoneNumber());
            return deleteContactResponse;
        } else {
            throw new ContactNotFoundException("Contact with phone number " + delete.getPhoneNumber() + " not found");
        }

    }

    @Override
    public List<Contact> getAllTheFirstName(String firstName) {
        System.out.println("contact find by firstName " + firstName);
        return contactRepository.getAllByFirstName(firstName);

    }

    @Override
    public List<Contact> getAllTheLastName(String lastName) {
        System.out.println("contact find by lastName " + lastName);
        return contactRepository.findByLastName(lastName);

    }

    @Override
    public void duplicateFirstNameAndLastName(AddContactRequest addContactRequest) {
        Optional<Contact> duplicateContact = contactRepository.findByFirstNameAndLastName(
                addContactRequest.getFirstName(), addContactRequest.getLastName());
        if (duplicateContact.isPresent()) {
            throw new ContactAlreadyExistsException("A contact already exists with first name "
                    + addContactRequest.getFirstName() + ", last name " + addContactRequest.getLastName());


        } else{
            addContactRequest.setFirstName(addContactRequest.getFirstName());
            addContactRequest.setLastName(addContactRequest.getLastName());
            addContactRequest.setPhoneNumber(addContactRequest.getPhoneNumber());
            addContactRequest.setEmail(addContactRequest.getEmail());
            AddContactResponse addContactResponse = new AddContactResponse();
            addContactResponse.setFirstName(addContactRequest.getFirstName());
            addContactResponse.setLastName(addContactRequest.getLastName());
            addContactResponse.setPhoneNumber(addContactRequest.getPhoneNumber());
            addContactResponse.setEmail(addContactRequest.getEmail());
        }

    }


}
