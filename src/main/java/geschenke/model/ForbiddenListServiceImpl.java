package geschenke.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForbiddenListServiceImpl implements ForbiddenListService {

    @Autowired
    ForbiddenListRepository forbiddenListRepository;

    @Override
    public void saveForbiddenPair(ForbiddenList forbiddenList) {
        forbiddenListRepository.save(forbiddenList);
    }
}
