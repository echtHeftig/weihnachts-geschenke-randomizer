package geschenke.view;

import com.vaadin.data.Binder;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import geschenke.model.Person;
import geschenke.model.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

@SpringUI(path = "forbiddenview")
public class ForbiddenView extends UI {

    private Grid<Person> personGrid = new Grid<>(Person.class);
    private ComboBox<String> personComboBox = new ComboBox<>();
    private Binder<Person> personBinder = new Binder<>(Person.class);
    @Autowired
    private PersonService personService;

    @Override
    protected void init(VaadinRequest request) {
        final List<Person> allPersons = personService.getAllPersons();
        final List<String> personNames = new LinkedList<>();
        for (Person person : allPersons) {
            personNames.add(person.getName());
        }
        personComboBox.setItems(personNames);
        setContent(personComboBox);
    }
}
