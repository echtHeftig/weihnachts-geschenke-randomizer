package org.mk.geschenke.person;

import org.mk.geschenke.domain.Person;

import java.util.List;

public interface PersonService {
    void addPerson(Person person);
    List<Person> getAllPersons();
    void deletePersonByName(String personName);
}
