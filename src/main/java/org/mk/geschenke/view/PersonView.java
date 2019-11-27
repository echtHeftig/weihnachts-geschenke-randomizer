package org.mk.geschenke.view;

import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.mk.geschenke.domain.Person;
import org.mk.geschenke.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@SpringUI(path = "personview")
public class PersonView extends UI {

    private TextField textField = new TextField("Personen-Name");
    private Button button = new Button("Person hinzufügen");
    private Button deleteButton = new Button("Markierte Person löschen", VaadinIcons.DEL);
    private Grid<Person> personGrid = new Grid<>(Person.class);
    private Binder<Person> binder = new Binder<>(Person.class);

    @Autowired
    private PersonService personService;

    @Override
    protected void init(VaadinRequest request) {
        binder.forField(textField)
                .bind(Person::getName, Person::setName);
        button.addClickListener(e -> addPersonToGrid(new Person()));
        deleteButton.addClickListener(c -> deletePersonFromGrid());
        personGrid.setItems(personService.getAllPersons());
        personGrid.setHeightByRows(12);

        final VerticalLayout verticalLayout = new VerticalLayout(new NavigationHorizontalLayout().getNavigationLayout(), textField, button, personGrid, deleteButton);

        setContent(verticalLayout);
    }

    private void addPersonToGrid(Person person) {
        binder.writeBeanIfValid(person);
        personService.addPerson(person);
        personGrid.setItems(personService.getAllPersons());
    }

    private void deletePersonFromGrid() {
        Set<Person> selectedItems = personGrid.getSelectedItems();
        //TODO: Fix Bug in ForbiddenView / Service / Repo -> If Person is deleted then all forbiddenPairs containing this name have to be removed
        for (Person person : selectedItems) {
            personService.deletePersonByName(person.getName());
        }
        personGrid.setItems(personService.getAllPersons());
    }
}
