package geschenke.view;

import com.vaadin.data.Binder;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import geschenke.model.Person;
import geschenke.model.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI(path = "personview")
public class PersonView extends UI {

    private TextField textField = new TextField("Personen-Name");
    private Button button = new Button("Person hinzufügen");
    private Grid<Person> personGrid = new Grid<>(Person.class);
    private Binder<Person> binder = new Binder<>(Person.class);
    private Person person;

    @Autowired
    private PersonService personService;

    @Override
    protected void init(VaadinRequest request) {
        binder.forField(textField)
                .bind(Person::getName, Person::setName);
        button.addClickListener(e -> addPersonToGrid(new Person()));
        personGrid.setItems(personService.getAllPersons());
        setContent(new VerticalLayout(textField, button, personGrid));
    }

    private void addPersonToGrid(Person person) {
        this.person = person;
        binder.writeBeanIfValid(this.person);
        personService.addPerson(this.person);
        personGrid.setItems(personService.getAllPersons());
    }
}
