import geschenke.model.ForbiddenList;
import geschenke.model.ForbiddenListService;
import geschenke.model.WeihnachtenverteilerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;

public class WeihnachtenVerteilerServiceTests {

    @Mock
    private ForbiddenListService forbiddenListServiceMock;
    @InjectMocks
    private WeihnachtenverteilerService weihnachtenverteilerService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenSimpleForbiddenList_whenGetForbiddenList_thenGetMergedForbiddenListReturned() {

        //Given
        final List<ForbiddenList> mockedSimpleForbiddenList = new LinkedList<>();
        mockedSimpleForbiddenList.add(new ForbiddenList("Lukas", "Samson"));
        when(forbiddenListServiceMock.getAllForbiddenPairs()).thenReturn(mockedSimpleForbiddenList);

        //When
        final HashMap<String, String> forbiddenList = weihnachtenverteilerService.getForbiddenList();

        //Then
        verify(forbiddenListServiceMock, times(1)).getAllForbiddenPairs();
        verifyNoMoreInteractions(forbiddenListServiceMock);
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
        when(forbiddenListServiceMock.getAllForbiddenPairs()).thenReturn(mockedSimpleForbiddenList);

        //When
        final HashMap<String, String> forbiddenList = weihnachtenverteilerService.getForbiddenList();

        //Then
        verify(forbiddenListServiceMock, times(1)).getAllForbiddenPairs();
        verifyNoMoreInteractions(forbiddenListServiceMock);
        final HashMap<String, String> expectedMergedForbiddenList = new HashMap<>();
        expectedMergedForbiddenList.put("Thilo", "Herbert");
        expectedMergedForbiddenList.put("Herbert", "Thilo");
        expectedMergedForbiddenList.put("Grobi", "Samson");
        expectedMergedForbiddenList.put("Samson", "Grobi");
        Assert.assertThat(forbiddenList, is(expectedMergedForbiddenList));
    }
}
