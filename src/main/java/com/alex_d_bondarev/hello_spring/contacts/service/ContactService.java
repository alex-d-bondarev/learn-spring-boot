package com.alex_d_bondarev.hello_spring.contacts.service;

import com.alex_d_bondarev.hello_spring.contacts.exception.NoContactException;
import com.alex_d_bondarev.hello_spring.contacts.pojo.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> getContacts();
    Contact getContactById(String id) throws NoContactException;
    void saveContact(Contact contact);
    void updateContact(String id, Contact contact) throws NoContactException;
    void deleteContact(String id) throws NoContactException;
}
