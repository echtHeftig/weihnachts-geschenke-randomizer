package org.mk.geschenke.person;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mk.geschenke.domain.Person;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class PersonServiceImplTests {

    private PersonRepository personRepository;

    private PersonService personService;

    @Before
    public void setup() {
        personRepository = mock(PersonRepository.class);
        personService = new PersonServiceImpl(personRepository);
    }

    @Test
    public void testAddOnePerson() {
        Person person = new Person("Paula");

        personService.addPerson(person);

        verify(personRepository, times(1)).save(person);
        verifyNoMoreInteractions(personRepository);
    }

    @Test
    public void testGetAllPersonsReturnsNoPerson() {
        when(personRepository.findAll())
                .thenReturn(Collections.emptyList());

        List<Person> allPersons = personService.getAllPersons();

        verify(personRepository, times(1)).findAll();
        verifyNoMoreInteractions(personRepository);
        Assert.assertThat(allPersons.size(), CoreMatchers.is(0));
    }

    @Test
    public void testGetAllPersonsReturnsOnePerson() {
        final String expectedPerson = "Klara";
        when(personRepository.findAll())
                .thenReturn(Collections.singletonList(new Person(expectedPerson)));

        List<Person> allPersons = personService.getAllPersons();

        verify(personRepository, times(1)).findAll();
        verifyNoMoreInteractions(personRepository);
        Assert.assertThat(allPersons.size(), CoreMatchers.is(1));
        Assert.assertEquals(expectedPerson, allPersons.get(0).getName());
    }

    @Test
    public void testGetAllPersonsReturnsMultiplePersons() {
        final String expectedPerson1 = "Timo";
        final String expectedPerson2 = "Elsa";
        when(personRepository.findAll())
                .thenReturn(Arrays.asList(new Person(expectedPerson1), new Person(expectedPerson2)));

        List<Person> allPersons = personService.getAllPersons();

        verify(personRepository, times(1)).findAll();
        verifyNoMoreInteractions(personRepository);
        Assert.assertThat(allPersons.size(), CoreMatchers.is(2));
        Assert.assertEquals(expectedPerson1, allPersons.get(0).getName());
        Assert.assertEquals(expectedPerson2, allPersons.get(1).getName());
    }

    @Test
    public void testDeletePerson() {
        personService.deletePersonByName("Lukas");

        verify(personRepository).deleteByName("Lukas");
        verifyNoMoreInteractions(personRepository);
    }
}
