package org.mk.geschenke.person;

import java.util.List;

public interface PersonService {
    void addPerson(Person person);
    List<Person> getAllPersons();
}
