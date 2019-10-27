package org.mk.geschenke.forbiddenlist;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mk.geschenke.WeihnachtenApplication;
import org.mk.geschenke.domain.ForbiddenList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WeihnachtenApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ForbiddenListControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ForbiddenListService forbiddenListService;

    @Autowired
    private ForbiddenListRepository forbiddenListRepository;

    @Before
    public void setup() {
        forbiddenListRepository.deleteAll();
    }

    @Test
    public void testAddOneForbiddenEntryWithoutGeneratedId() throws Exception {
        List<ForbiddenList> allForbiddenPairsBeforeTest = forbiddenListService.getAllForbiddenPairs();
        Assert.assertEquals(0, allForbiddenPairsBeforeTest.size());
        mockMvc
                .perform(post("/forbidden")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                " \"firstPersonName\" : \"Paula\", " +
                                " \"secondPersonName\" : \"Samson\" " +
                                "}"
                        )
                )
                .andExpect(status().isCreated());

        List<ForbiddenList> allForbiddenPairsAfterTest = forbiddenListService.getAllForbiddenPairs();
        Assert.assertEquals(1, allForbiddenPairsAfterTest.size());
        Assert.assertEquals("Paula", allForbiddenPairsAfterTest.get(0).getFirstPersonName());
        Assert.assertEquals("Samson", allForbiddenPairsAfterTest.get(0).getSecondPersonName());
    }

    @Test
    public void testAddTwoForbiddenEntriesWithoutGeneratedId() throws Exception {
        List<ForbiddenList> allForbiddenPairsBeforeTest = forbiddenListService.getAllForbiddenPairs();
        Assert.assertEquals(0, allForbiddenPairsBeforeTest.size());
        List<ForbiddenList> expectedForbiddenList = Arrays.asList(
                new ForbiddenList("A", "B"),
                new ForbiddenList("C", "D")
        );

        for(ForbiddenList forbiddenList : expectedForbiddenList) {
            mockMvc
                    .perform(post("/forbidden")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{" +
                                    " \"firstPersonName\" : \"" + forbiddenList.getFirstPersonName() + "\", " +
                                    " \"secondPersonName\" : \"" + forbiddenList.getSecondPersonName() + "\" " +
                                    "}"
                            )
                    )
                    .andExpect(status().isCreated());
        }

        List<ForbiddenList> allForbiddenPairsAfterTest = forbiddenListService.getAllForbiddenPairs();
        Assert.assertEquals(2, allForbiddenPairsAfterTest.size());
        Assert.assertEquals("A", allForbiddenPairsAfterTest.get(0).getFirstPersonName());
        Assert.assertEquals("B", allForbiddenPairsAfterTest.get(0).getSecondPersonName());
        Assert.assertEquals("C", allForbiddenPairsAfterTest.get(1).getFirstPersonName());
        Assert.assertEquals("D", allForbiddenPairsAfterTest.get(1).getSecondPersonName());
    }

    @Test
    public void testAddTwoEqualForbiddenEntriesWithoutGeneratedIdTwoTimesSavedDuplicate() throws Exception {
        List<ForbiddenList> allForbiddenPairsBeforeTest = forbiddenListService.getAllForbiddenPairs();
        Assert.assertEquals(0, allForbiddenPairsBeforeTest.size());
        List<ForbiddenList> expectedForbiddenList = Arrays.asList(
                new ForbiddenList("A", "B"),
                new ForbiddenList("A", "B")
        );

        for(ForbiddenList forbiddenList : expectedForbiddenList) {
            mockMvc
                    .perform(post("/forbidden")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{" +
                                    " \"firstPersonName\" : \"" + forbiddenList.getFirstPersonName() + "\", " +
                                    " \"secondPersonName\" : \"" + forbiddenList.getSecondPersonName() + "\" " +
                                    "}"
                            )
                    )
                    .andExpect(status().isCreated());
        }

        List<ForbiddenList> allForbiddenPairsAfterTest = forbiddenListService.getAllForbiddenPairs();
        Assert.assertEquals(2, allForbiddenPairsAfterTest.size());
        Assert.assertEquals("A", allForbiddenPairsAfterTest.get(0).getFirstPersonName());
        Assert.assertEquals("B", allForbiddenPairsAfterTest.get(0).getSecondPersonName());
        Assert.assertEquals("A", allForbiddenPairsAfterTest.get(1).getFirstPersonName());
        Assert.assertEquals("B", allForbiddenPairsAfterTest.get(1).getSecondPersonName());
    }
}
