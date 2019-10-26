package org.mk.geschenke.person;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mk.geschenke.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonServiceImpl personService;

    @Test
    public void testAddPerson() throws Exception {
        List<Person> allPersonsBefore = personService.getAllPersons();
        Assert.assertTrue(allPersonsBefore.isEmpty());

        String personName = "Klaus";
        given(personService.getAllPersons()).willReturn(Collections.singletonList(new Person(personName)));

        mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\":  \""+personName+"\" }"))
                .andExpect(status().isCreated());

        List<Person> allPersonsAfter = personService.getAllPersons();
        Assert.assertFalse(allPersonsAfter.isEmpty());
        Assert.assertEquals(1, allPersonsAfter.size());
        Assert.assertEquals(personName, allPersonsAfter.get(0).getName());
    }

}
