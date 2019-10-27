package org.mk.geschenke.forbiddenlist;

import org.mk.geschenke.domain.ForbiddenList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForbiddenListServiceImpl implements ForbiddenListService {

    private ForbiddenListRepository forbiddenListRepository;

    ForbiddenListServiceImpl(ForbiddenListRepository forbiddenListRepository) {
        this.forbiddenListRepository = forbiddenListRepository;
    }

    @Override
    public void saveForbiddenPair(ForbiddenList forbiddenList) {
        forbiddenListRepository.save(forbiddenList);
    }

    @Override
    public List<ForbiddenList> getAllForbiddenPairs() {
        return forbiddenListRepository.findAll();
    }
}
