package org.mk.geschenke.forbiddenlist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mk.geschenke.domain.ForbiddenList;
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
@WebMvcTest(ForbiddenListController.class)
public class ForbiddenListControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ForbiddenListServiceImpl forbiddenListService;

    @Test
    public void testAddForbiddenEntry() throws Exception {
         mockMvc.perform(post("/forbidden")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content("{ \"firstPersonName\": \"Helga\", \"secondPersonName\": \"Ernst\" }"))
                 .andExpect(status().isCreated());

         verify(forbiddenListService).saveForbiddenPair(any(ForbiddenList.class));
         verifyNoMoreInteractions(forbiddenListService);
    }
}
