package geschenke.model;

import geschenke.person.Person;
import geschenke.person.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class WeihnachtenverteilerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeihnachtenverteilerService.class);

    @Autowired
    private PersonService personService;
    @Autowired
    private ForbiddenListService forbiddenListService;

    public List<Person> getParticipants() {
        return personService.getAllPersons();
    }

    public List<PresentTable> getPresentTableList() throws SchenkenderBeschenkenderException {
        LinkedList schenkender = new LinkedList();
        LinkedList beschenkter = new LinkedList();

        final List<Person> participants = getParticipants();
        for (int i = 0; i < participants.size(); ++i) {
            LOGGER.info("For Schleife im Durchlauf {} fuer Participant {}", i, participants.get(i));
            schenkender.add(i, participants.get(i));
            beschenkter.add(i, participants.get(i));
        }

        HashMap hm = new HashMap();

        final HashMap<String, String> forbiddenList = getForbiddenList();
        for (int i = 0; i < 100; i++) {
            if (!beschenkter.isEmpty() && !schenkender.isEmpty()) {
                LOGGER.info("Beschenkter Array size {}", beschenkter.size());
                LOGGER.info("Schenkender Array size {}", schenkender.size());
                Person key = null;
                Person value = null;
                if(!schenkender.isEmpty()) {
                    key = (Person) schenkender.get(getRandomIndex(schenkender));
                }
                if(!beschenkter.isEmpty()) {
                    value = (Person) beschenkter.get(getRandomIndex(beschenkter));
                }
                LOGGER.info("Key is {} and value is {}", key.getName(), value.getName());
                if (!key.equals(value) && !isForbidden(forbiddenList, key, value)) {
                    hm.put(key, value);
                    schenkender.remove(key);
                    beschenkter.remove(value);
                }
            }
        }
        if (!schenkender.isEmpty()) {
            LOGGER.info("Schenkender nicht empty, enthaehlt noch {} Elemente und als erstes Element {}", beschenkter.size(), beschenkter.getFirst().toString());
        }
        if (!beschenkter.isEmpty()) {
            LOGGER.info("Schenkender nicht empty, enthaehlt noch {} Elemente und als erstes Element {}", schenkender.size(), schenkender.getFirst().toString());
        }

        if (!schenkender.isEmpty() || !beschenkter.isEmpty()) {
            LOGGER.error("Schenkender oder Beschenkter nicht beruecksichtigt. Bitte nochmal generieren!!");
            throw new SchenkenderBeschenkenderException("Schenkender oder Beschenkter nicht beruecksichtigt. Bitte nochmal generieren!!");
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

    public HashMap<String, String> getForbiddenList() {
        List<ForbiddenList> allForbiddenPairs = forbiddenListService.getAllForbiddenPairs();
        HashMap simpleForbiddenList = new HashMap();
        for (ForbiddenList forbiddenList : allForbiddenPairs) {
            simpleForbiddenList.put(forbiddenList.getFirstPersonName(), forbiddenList.getSecondPersonName());
        }
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
        LOGGER.info("Start des isForbbiden-Methode");
        Iterator var4 = verboteneListe.entrySet().iterator();

        Map.Entry entry;
        do {
            if (!var4.hasNext()) {
                return false;
            }

            entry = (Map.Entry) var4.next();
            LOGGER.info("The current entry key is {} and the parameter key is {}", entry.getKey(), ((Person) key).getName());
            LOGGER.info("The current entry value is {} and the parameter value is {}", entry.getValue(), ((Person) value).getName());
        } while (entry.getKey() != ((Person) key).getName() || entry.getValue() != ((Person) value).getName());

        return true;
    }

    private static int getRandomIndex(LinkedList<String> anyLinkedList) {
        if (!anyLinkedList.isEmpty()) {
            int sizeOfLinkedList = anyLinkedList.size();
            Random generator = new Random();
            return generator.nextInt(sizeOfLinkedList);
        } else {
            return 0;
        }
    }
}
