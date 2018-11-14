package geschenke.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class WeihnachtenverteilerService {

    @Autowired
    private PersonService personService;

    public List<Person> getParticipants() {
        return personService.getAllPersons();
    }

    public List<PresentTable> getPresentTableList() {
        LinkedList schenkender = new LinkedList();
        LinkedList beschenkter = new LinkedList();

        final List<Person> participants = getParticipants();
        for (int i = 0; i < participants.size(); ++i) {
            schenkender.add(i, participants.get(i));
            beschenkter.add(i, participants.get(i));
        }

        HashMap hm = new HashMap();

        while (!beschenkter.isEmpty() && !schenkender.isEmpty()) {
            Object key = schenkender.get(getRandomIndex(schenkender));
            Object value = beschenkter.get(getRandomIndex(beschenkter));
            if (!key.equals(value) && !isForbidden(getForbiddenList(), key, value)) {
                hm.put(key, value);
                schenkender.remove(key);
                beschenkter.remove(value);
            }
        }

        return printMapAndSaveToPresentTable(hm);
    }

    private static List<PresentTable> printMapAndSaveToPresentTable(Map mp) {
        Iterator it = mp.entrySet().iterator();
        List<PresentTable> presentTableList = new ArrayList<>();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            presentTableList.add(new PresentTable((Person) pair.getKey(), (Person) pair.getValue()));
            it.remove(); // avoids a ConcurrentModificationException
        }
        return presentTableList;
    }

    //TODO: ForbiddenListService has to be input by external interface
    private static HashMap<String, String> getForbiddenList() {
        HashMap simpleForbiddenList = new HashMap();
        simpleForbiddenList.put("Egon", "Paul");
        simpleForbiddenList.put("Hugo", "Hans");
        final HashMap reversedForbiddenList = reverse(simpleForbiddenList);
        HashMap mergedForbiddenMap = new HashMap();
        mergedForbiddenMap.putAll(simpleForbiddenList);
        mergedForbiddenMap.putAll(reversedForbiddenList);
        return mergedForbiddenMap;
    }

    //map must be a bijection in order for this to work properly
    private static <K, V> HashMap<V, K> reverse(Map<K, V> map) {
        HashMap<V, K> rev = new HashMap<V, K>();
        for (Map.Entry<K, V> entry : map.entrySet())
            rev.put(entry.getValue(), entry.getKey());
        return rev;
    }

    private static boolean isForbidden(HashMap<String, String> verboteneListe, Object key, Object value) {
        Iterator var4 = verboteneListe.entrySet().iterator();

        Map.Entry entry;
        do {
            if (!var4.hasNext()) {
                return false;
            }

            entry = (Map.Entry) var4.next();
        } while (entry.getKey() != key || entry.getValue() != value);

        return true;
    }

    private static int getRandomIndex(LinkedList<String> anyLinkedList) {
        int sizeOfLinkedList = anyLinkedList.size();
        Random generator = new Random();
        return generator.nextInt(sizeOfLinkedList);
    }
}
