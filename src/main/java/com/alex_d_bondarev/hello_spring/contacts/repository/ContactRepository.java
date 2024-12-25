package com.alex_d_bondarev.hello_spring.contacts.repository;

import java.util.Arrays;
import java.util.List;

import com.alex_d_bondarev.hello_spring.contacts.pojo.Contact;
import org.springframework.stereotype.Repository;


@Repository
public class ContactRepository {

    private List<Contact> contacts = Arrays.asList(
            new Contact("123", "John Snow", "41329123"),
            new Contact("456", "Tyrion Lannister", "52430234"),
            new Contact("789", "The Hound", "63541345")
    );

    public List<Contact> getContacts() {
        return contacts;
    }

    public Contact getContact(int index) {
        return contacts.get(index);
    }

    public void saveContact(Contact contact) {
        contacts.add(contact);
    }

    public void updateContact(int index, Contact contact) {
        contacts.set(index, contact);
    }

    public void deleteContact(int index) {
        contacts.remove(index);
    }

}
