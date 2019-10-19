package org.mk.geschenke.presenttable;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mk.geschenke.domain.ForbiddenList;
import org.mk.geschenke.forbiddenlist.ForbiddenListService;
import org.mk.geschenke.person.PersonService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;

public class PresentTableServiceImplTests {

    private PersonService personService;
    private ForbiddenListService forbiddenListService;

    private PresentTableService presentTableService;

    @Before
    public void setUp() {
        personService = mock(PersonService.class);
        forbiddenListService = mock(ForbiddenListService.class);
        presentTableService = new PresentTableServiceImpl(personService, forbiddenListService);
    }

    @Test
    public void givenSimpleForbiddenList_whenGetForbiddenList_thenGetMergedForbiddenListReturned() {

        //Given
        final List<ForbiddenList> mockedSimpleForbiddenList = new LinkedList<>();
        mockedSimpleForbiddenList.add(new ForbiddenList("Lukas", "Samson"));
        when(forbiddenListService.getAllForbiddenPairs()).thenReturn(mockedSimpleForbiddenList);

        //When
        final HashMap<String, String> forbiddenList = presentTableService.getForbiddenList();

        //Then
        verify(forbiddenListService, times(1)).getAllForbiddenPairs();
        verifyNoMoreInteractions(forbiddenListService);
        final HashMap<String, String> expectedMergedForbiddenList = new HashMap<>();
        expectedMergedForbiddenList.put("Lukas", "Samson");
        expectedMergedForbiddenList.put("Samson", "Lukas");
        Assert.assertThat(forbiddenList, is(expectedMergedForbiddenList));
    }

    @Test
    public void givenSimpleForbiddenListBiDirectional_whenGetForbiddenList_thenGetMergedForbiddenListReturned() {

        //Given
        final List<ForbiddenList> mockedSimpleForbiddenList = new LinkedList<>();
        mockedSimpleForbiddenList.add(new ForbiddenList("Thilo", "Herbert"));
        mockedSimpleForbiddenList.add(new ForbiddenList("Herbert", "Thilo"));
        mockedSimpleForbiddenList.add(new ForbiddenList("Samson", "Grobi"));
        when(forbiddenListService.getAllForbiddenPairs()).thenReturn(mockedSimpleForbiddenList);

        //When
        final HashMap<String, String> forbiddenList = presentTableService.getForbiddenList();

        //Then
        verify(forbiddenListService, times(1)).getAllForbiddenPairs();
        verifyNoMoreInteractions(forbiddenListService);
        final HashMap<String, String> expectedMergedForbiddenList = new HashMap<>();
        expectedMergedForbiddenList.put("Thilo", "Herbert");
        expectedMergedForbiddenList.put("Herbert", "Thilo");
        expectedMergedForbiddenList.put("Grobi", "Samson");
        expectedMergedForbiddenList.put("Samson", "Grobi");
        Assert.assertThat(forbiddenList, is(expectedMergedForbiddenList));
    }
}
