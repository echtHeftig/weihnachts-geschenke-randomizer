package org.mk.geschenke.person;

import org.mk.geschenke.domain.Person;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void addPerson(Person person) {
        personRepository.save(person);
    }

    public List<Person> getAllPersons() {
        final Iterable<Person> allPersonsIterable = personRepository.findAll();
        List<Person> personList = new LinkedList<>();
        for(Person person : allPersonsIterable) {
            personList.add(person);
        }
        return personList;
    }
}
