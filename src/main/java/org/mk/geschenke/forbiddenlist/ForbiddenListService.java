package org.mk.geschenke.forbiddenlist;

import org.mk.geschenke.domain.ForbiddenList;

import java.util.List;

public interface ForbiddenListService {
    void saveForbiddenPair(ForbiddenList forbiddenList);

    List<ForbiddenList> getAllForbiddenPairs();
}
