package org.mk.geschenke.person;

import org.mk.geschenke.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping(value = "/{personName}")
    public ResponseEntity<Void> deletePerson(@PathVariable(value = "personName") String personNameAsString) {
        personServiceImpl.deletePersonByName(personNameAsString);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
