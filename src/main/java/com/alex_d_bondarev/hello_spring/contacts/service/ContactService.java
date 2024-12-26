package com.alex_d_bondarev.hello_spring.contacts.service;

import com.alex_d_bondarev.hello_spring.contacts.pojo.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> getContacts();
    Contact getContactById(String id);
    void saveContact(Contact contact);
    void updateContact(String id, Contact contact);
    void deleteContact(String id);
}
