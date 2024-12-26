package com.alex_d_bondarev.hello_spring.contacts.web;

import com.alex_d_bondarev.hello_spring.contacts.exception.ErrorResponse;
import com.alex_d_bondarev.hello_spring.contacts.pojo.Contact;
import com.alex_d_bondarev.hello_spring.contacts.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Tag(name = "Contact Controller", description = "Contacts CRUD")
@RestController
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping(value = "/contact/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get All", description = "List all created contacts")
    @ApiResponse(
            responseCode = "200",
            description = "Successful retrieval of contacts",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Contact.class))))
    public ResponseEntity<List<Contact>> getContacts() {
        List<Contact> contacts = contactService.getContacts();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @GetMapping(value = "/contact/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get by id", description = "Get a specific contact")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Contact doesn't exist",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful retrieval of contact",
                    content = @Content(schema = @Schema(implementation = Contact.class))),
    })

    public ResponseEntity<Contact> getContact(@PathVariable String id) {
        Contact contact = contactService.getContactById(id);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @PostMapping(value = "/contact", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create", description = "Add a new contact")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Successful creation of contact",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request: unsuccessful submission",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })

    public ResponseEntity<Contact> createContact(@RequestBody @Valid Contact contact) {
        contactService.saveContact(contact);
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

    @PutMapping(value = "/contact/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update", description = "Update existing contact")
    public ResponseEntity<Contact> updateContact(@PathVariable String id, @RequestBody @Valid Contact contact) {
        contactService.updateContact(id, contact);
        return new ResponseEntity<>(contactService.getContactById(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/contact/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Delete", description = "Delete existing contact")
    public ResponseEntity<HttpStatus> deleteContact(@PathVariable String id) {
        contactService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
