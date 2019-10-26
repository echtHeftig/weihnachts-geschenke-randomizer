package org.mk.geschenke.person;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mk.geschenke.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
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

        String personName = "Klaus";

        mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\":  \""+personName+"\" }"))
                .andExpect(status().isCreated());

        verify(personService).addPerson(any(Person.class));
        verifyNoMoreInteractions(personService);
    }

}
