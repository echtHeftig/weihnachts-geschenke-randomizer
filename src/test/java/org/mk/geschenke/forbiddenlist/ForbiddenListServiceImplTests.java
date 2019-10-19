package org.mk.geschenke.forbiddenlist;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mk.geschenke.domain.ForbiddenList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class ForbiddenListServiceImplTests {

    private ForbiddenListRepository forbiddenListRepository;

    private ForbiddenListService forbiddenListService;

    @Before
    public void setup() {
            forbiddenListRepository = mock(ForbiddenListRepository.class);
            forbiddenListService = new ForbiddenListServiceImpl(forbiddenListRepository);
    }

    @Test
    public void testSaveForbiddenPair() {
        ForbiddenList forbiddenList = new ForbiddenList("Lars", "Timo");

        forbiddenListService.saveForbiddenPair(forbiddenList);

        verify(forbiddenListRepository).save(forbiddenList);
        verifyNoMoreInteractions(forbiddenListRepository);
    }

    @Test
    public void testGetAllForbiddenPairsNoPairs() {
        when(forbiddenListRepository.findAll()).thenReturn(Collections.emptyList());

        List<ForbiddenList> allForbiddenPairs = forbiddenListService.getAllForbiddenPairs();

        verify(forbiddenListRepository).findAll();
        verifyNoMoreInteractions(forbiddenListRepository);
        Assert.assertThat(allForbiddenPairs.size(), CoreMatchers.is(0));
    }

    @Test
    public void testGetAllForbiddenPairsOnePair() {
        ForbiddenList forbiddenList = new ForbiddenList("Lars", "Timo");
        when(forbiddenListRepository.findAll()).thenReturn(Collections.singletonList(forbiddenList));

        List<ForbiddenList> allForbiddenPairs = forbiddenListService.getAllForbiddenPairs();

        verify(forbiddenListRepository).findAll();
        verifyNoMoreInteractions(forbiddenListRepository);
        Assert.assertThat(allForbiddenPairs.size(), CoreMatchers.is(1));
        Assert.assertEquals(forbiddenList, allForbiddenPairs.get(0));
    }

    @Test
    public void testGetAllForbiddenPairsMultiplePairs() {
        ForbiddenList forbiddenList = new ForbiddenList("Lars", "Timo");
        ForbiddenList forbiddenList2 = new ForbiddenList("Hugo", "Paula");
        when(forbiddenListRepository.findAll()).thenReturn(Arrays.asList(forbiddenList, forbiddenList2));

        List<ForbiddenList> allForbiddenPairs = forbiddenListService.getAllForbiddenPairs();

        verify(forbiddenListRepository).findAll();
        verifyNoMoreInteractions(forbiddenListRepository);
        Assert.assertThat(allForbiddenPairs.size(), CoreMatchers.is(2));
        Assert.assertEquals(forbiddenList, allForbiddenPairs.get(0));
        Assert.assertEquals(forbiddenList2, allForbiddenPairs.get(1));
        Assert.assertEquals(forbiddenList.getFirstPersonName(), allForbiddenPairs.get(0).getFirstPersonName());
        Assert.assertEquals(forbiddenList.getSecondPersonName(), allForbiddenPairs.get(0).getSecondPersonName());
        Assert.assertEquals(forbiddenList2.getFirstPersonName(), allForbiddenPairs.get(1).getFirstPersonName());
        Assert.assertEquals(forbiddenList2.getSecondPersonName(), allForbiddenPairs.get(1).getSecondPersonName());
    }
}
