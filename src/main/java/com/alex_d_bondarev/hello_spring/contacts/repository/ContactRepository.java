package com.alex_d_bondarev.hello_spring.contacts.repository;

import java.util.ArrayList;
import java.util.List;

import com.alex_d_bondarev.hello_spring.contacts.pojo.Contact;
import org.springframework.stereotype.Repository;


@Repository
public class ContactRepository {

    private final List<Contact> contacts = new ArrayList<>();

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
