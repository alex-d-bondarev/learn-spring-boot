package com.alex_d_bondarev.hello_spring.contacts.service;

import java.util.List;
import java.util.stream.IntStream;

import com.alex_d_bondarev.hello_spring.contacts.exception.ContactNotFoundException;
import com.alex_d_bondarev.hello_spring.contacts.pojo.Contact;
import com.alex_d_bondarev.hello_spring.contacts.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    private int findIndexById(String id) {
        return IntStream.range(0, contactRepository.getContacts().size())
                .filter(index -> contactRepository.getContacts().get(index).getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ContactNotFoundException(id));
    }

    @Override
    public List<Contact> getContacts() {
        return contactRepository.getContacts();
    }

    @Override
    public Contact getContactById(String id) {
        return contactRepository.getContact(findIndexById(id));
    }

    @Override
    public void saveContact(Contact contact) {
        contactRepository.saveContact(contact);
    }

    @Override
    public void updateContact(String id, Contact contact) {
        contact.setId(id);
        contactRepository.updateContact(findIndexById(id), contact);
    }

    @Override
    public void deleteContact(String id) {
        contactRepository.deleteContact(findIndexById(id));
    }
}
