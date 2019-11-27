package org.mk.geschenke.person;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mk.geschenke.WeihnachtenApplication;
import org.mk.geschenke.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WeihnachtenApplication.class, webEnvironment = MOCK)
@AutoConfigureMockMvc
public class PersonControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository personRepository;

    @Before
    public void setup() {
        personRepository.deleteAll();
    }

    @Test
    public void testAddPersonOnce() throws Exception {
        Iterable<Person> personIterableBeforeTest = personRepository.findAll();
        List<Person> personListBeforeTest = Lists.newArrayList(personIterableBeforeTest);
        Assert.assertEquals(0, personListBeforeTest.size());

        String personName = "Lars";

        mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \""+personName+"\" }")
        ).andExpect(status().isCreated());

        Iterable<Person> personIterableAfterTest = personRepository.findAll();
        List<Person> personListAfterTest = Lists.newArrayList(personIterableAfterTest);
        Assert.assertEquals(1, personListAfterTest.size());
        Assert.assertEquals(personName, personListAfterTest.get(0).getName());
    }

    @Test
    public void testAddPersonTwiceJustOneTimeSaved() throws Exception {
        Iterable<Person> personIterableBeforeTest = personRepository.findAll();
        List<Person> personListBeforeTest = Lists.newArrayList(personIterableBeforeTest);
        Assert.assertEquals(0, personListBeforeTest.size());

        String personName = "Lars";

        for(int i = 0; i < 2; i++) {
            mockMvc.perform(post("/person")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{ \"name\": \"" + personName + "\" }")
            ).andExpect(status().isCreated());
        }

        Iterable<Person> personIterableAfterTest = personRepository.findAll();
        List<Person> personListAfterTest = Lists.newArrayList(personIterableAfterTest);
        Assert.assertEquals(1, personListAfterTest.size());
        Assert.assertEquals(personName, personListAfterTest.get(0).getName());
    }

    @Test
    public void testAddDifferentPersons() throws Exception {
        Iterable<Person> personIterableBeforeTest = personRepository.findAll();
        List<Person> personListBeforeTest = Lists.newArrayList(personIterableBeforeTest);
        Assert.assertEquals(0, personListBeforeTest.size());

        String[] personNames = {"Lars", "Hugo"};

        for(String personName : personNames) {
            mockMvc.perform(post("/person")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{ \"name\": \"" + personName + "\" }")
            ).andExpect(status().isCreated());
        }

        Iterable<Person> personIterableAfterTest = personRepository.findAll();
        List<Person> personListAfterTest = Lists.newArrayList(personIterableAfterTest);
        Assert.assertEquals(2, personListAfterTest.size());
        Assert.assertEquals("Hugo", personListAfterTest.get(0).getName());
        Assert.assertEquals("Lars", personListAfterTest.get(1).getName());
    }

    @Test
    @Transactional
    public void testDeletePerson() throws Exception {
        String personName = "Olga";
        personRepository.save(new Person(personName));

        Assert.assertTrue(personRepository.findAll().iterator().hasNext());

        mockMvc.perform(delete("/person/{personName}", personName))
                .andExpect(status().isNoContent());

        Assert.assertFalse(personRepository.findAll().iterator().hasNext());
    }
}
