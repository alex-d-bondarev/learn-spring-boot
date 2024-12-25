package com.alex_d_bondarev.hello_spring.module2.contacts.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ltp.contacts.service.ContactService;

@Controller
public class ContactController {
    
    @Autowired
    private ContactService contactService;

}
