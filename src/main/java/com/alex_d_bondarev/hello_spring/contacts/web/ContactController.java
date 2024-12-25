package com.alex_d_bondarev.hello_spring.contacts.web;

import com.alex_d_bondarev.hello_spring.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/contact/{id}")
    public void getContact(@PathVariable String id) {
        System.out.println(id);
    }
}
