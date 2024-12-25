package com.alex_d_bondarev.hello_spring.module2.contacts.web;

import com.alex_d_bondarev.hello_spring.module2.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

}
