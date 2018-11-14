package geschenke;

import geschenke.model.Person;
import geschenke.model.PersonRepository;
import geschenke.model.PresentTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class WeihnachtenVerteiler implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(WeihnachtenVerteiler.class, args);
    }

    public static List<PresentTable> getPresentTableList() {
        LinkedList schenkender = new LinkedList();
        LinkedList beschenkter = new LinkedList();

        for (int i = 0; i < getParticipants().length; ++i) {
            schenkender.add(i, getParticipants()[i]);
            beschenkter.add(i, getParticipants()[i]);
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
            presentTableList.add(new PresentTable(new Person(pair.getKey().toString()), new Person(pair.getValue().toString())));
            it.remove(); // avoids a ConcurrentModificationException
        }
        return presentTableList;
    }

    //TODO: Participants has to be input by external interface
    private static String[] getParticipants() {
        return new String[]{"Hugo", "Hans", "Egon", "Paul"};
    }

    //TODO: ForbiddenList has to be input by external interface
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

    @Override
    public void run(String... args) throws Exception {
        Person personA = new Person("Klara");
        personRepository.save(personA);
    }
}
