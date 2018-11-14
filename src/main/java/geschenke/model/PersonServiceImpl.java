package geschenke.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

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
