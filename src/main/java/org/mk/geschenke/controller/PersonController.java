package org.mk.geschenke.controller;

import org.mk.geschenke.person.Person;
import org.mk.geschenke.person.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("person")
public class PersonController {
    @Autowired
    private PersonServiceImpl personServiceImpl;

    @PostMapping
    public ResponseEntity<Void> addPerson(@RequestBody Person person) {
        personServiceImpl.addPerson(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
