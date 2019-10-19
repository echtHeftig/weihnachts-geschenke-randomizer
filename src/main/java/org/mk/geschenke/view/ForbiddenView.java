package org.mk.geschenke.view;

import com.vaadin.data.Binder;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.mk.geschenke.domain.ForbiddenList;
import org.mk.geschenke.domain.Person;
import org.mk.geschenke.forbiddenlist.ForbiddenListService;
import org.mk.geschenke.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

@SpringUI(path = "forbiddenview")
public class ForbiddenView extends UI {

    private Grid<ForbiddenList> personGrid = new Grid<>(ForbiddenList.class);
    private ComboBox<String> firstPersonComboBox = new ComboBox<>();
    private ComboBox<String> secondPersonComboBox = new ComboBox<>();
    private Button addForbiddenPersonPairButton = new Button("Hinzufügen zur ForbiddenPair List");
    private Binder<Person> firstPersonBinder = new Binder<>(Person.class);
    private Binder<Person> secondPersonBinder = new Binder<>(Person.class);
    private ForbiddenList forbiddenList;
    @Autowired
    private PersonService personService;
    @Autowired
    private ForbiddenListService forbiddenListService;

    @Override
    protected void init(VaadinRequest request) {
        final List<Person> allPersons = personService.getAllPersons();
        final List<String> personNames = new LinkedList<>();
        for (Person person : allPersons) {
            personNames.add(person.getName());
        }
        firstPersonComboBox.setItems(personNames);
        secondPersonComboBox.setItems(personNames);
        firstPersonBinder.forField(firstPersonComboBox)
                .bind(Person::getName, Person::setName);
        secondPersonBinder.forField(secondPersonComboBox)
                .bind(Person::getName, Person::setName);
        addForbiddenPersonPairButton.addClickListener(e -> addPairToList(new Person(), new Person()));
        personGrid.setItems(forbiddenListService.getAllForbiddenPairs());
        personGrid.setHeightByRows(12);
        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout(firstPersonComboBox, secondPersonComboBox, addForbiddenPersonPairButton);
        verticalLayout.addComponents(new NavigationHorizontalLayout().getNavigationLayout(), horizontalLayout, personGrid);
        setContent(verticalLayout);
    }

    private void addPairToList(Person firstPerson1, Person secondPerson1) {
        firstPersonBinder.writeBeanIfValid(firstPerson1);
        secondPersonBinder.writeBeanIfValid(secondPerson1);
        ForbiddenList forbiddenList = new ForbiddenList(firstPerson1.getName(), secondPerson1.getName());
        forbiddenListService.saveForbiddenPair(forbiddenList);
        personGrid.setItems(forbiddenListService.getAllForbiddenPairs());
    }
}
