package geschenke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class WeihnachtenVerteiler {

    public static void main(String[] args) {
        SpringApplication.run(WeihnachtenVerteiler.class, args);
    }

    private void anyMethod() {
        LinkedList<String> schenkender = new LinkedList();
        LinkedList<String> beschenkter = new LinkedList();

        for (int i = 0; i < getTeilnehmer().length; ++i) {
            schenkender.add(i, getTeilnehmer()[i]);
            beschenkter.add(i, getTeilnehmer()[i]);
        }

        HashMap hm = new HashMap();

        while (!beschenkter.isEmpty() && !schenkender.isEmpty()) {
            String key = schenkender.get(getRandomIndex(schenkender));
            String value = beschenkter.get(getRandomIndex(beschenkter));
            if (!key.equals(value) && !isForbidden(getVerboteneListe(), key, value)) {
                hm.put(key, value);
                schenkender.remove(key);
                beschenkter.remove(value);
            }
        }

        final String[][] twoDimensionalStringArray = writeHashMapValuesIntoTwoDimensionalStringArray(hm);
        for(int i = 0; i < twoDimensionalStringArray.length; i++) {
            System.out.println("Person '" + twoDimensionalStringArray[i][0] + "' beschenkt Person '" + twoDimensionalStringArray[i][1] + "'");
        }
    }

    private static String[] getTeilnehmer() {
        return new String[]{"Hugo", "Hans", "Egon", "Paul"};
    }

    private static HashMap<String, String> getVerboteneListe() {
        //TODO: Aktuell ist es moeglich, dass Hans auch Hugo etwas schenkt (aber nicht umgekehrt). Es muss einfach sein, dass bidirektional nicht geschenkt werden darf (ohne beide Richtungen explizit anzugeben)
        HashMap verboteneListe = new HashMap();
        verboteneListe.put("Hugo", "Hans");
        verboteneListe.put("Egon", "Paul");
        return verboteneListe;
    }

    private static String[][] writeHashMapValuesIntoTwoDimensionalStringArray(HashMap<String, String> hm) {
        Object[] arrayKeys = hm.keySet().toArray();
        Object[] arrayValues = hm.values().toArray();
        String[][] GeschenkeArray = new String[hm.size()][hm.size()];

        for (int i = 0; i < GeschenkeArray.length; ++i) {
            GeschenkeArray[i][0] = arrayKeys[i].toString();
            GeschenkeArray[i][1] = arrayValues[i].toString();
        }

        return GeschenkeArray;
    }

    private static boolean isForbidden(HashMap<String, String> verboteneListe, String key, String value) {
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
