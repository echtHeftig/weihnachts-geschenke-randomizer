package org.mk.geschenke.model;

import java.util.List;

public interface ForbiddenListService {
    void saveForbiddenPair(ForbiddenList forbiddenList);

    List<ForbiddenList> getAllForbiddenPairs();
}
