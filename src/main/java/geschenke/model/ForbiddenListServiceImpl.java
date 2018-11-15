package geschenke.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class ForbiddenListServiceImpl implements ForbiddenListService {

    @Autowired
    ForbiddenListRepository forbiddenListRepository;

    @Override
    public void saveForbiddenPair(ForbiddenList forbiddenList) {
        forbiddenListRepository.save(forbiddenList);
    }

    @Override
    public List<ForbiddenList> getAllForbiddenPairs() {
        Iterable<ForbiddenList> forbiddenListIterable = forbiddenListRepository.findAll();
        Iterator<ForbiddenList> iterator = forbiddenListIterable.iterator();
        LinkedList<ForbiddenList> forbiddenLists = new LinkedList<>();
        while(iterator.hasNext()) {
            forbiddenLists.add(iterator.next());
        }
        return forbiddenLists;
    }
}
