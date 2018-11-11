package geschenke.view;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import geschenke.model.Person;
import geschenke.model.PresentTable;

@SpringUI(path = "")
public class MyMainView extends UI {

    private Grid<PresentTable> grid = new Grid<>(PresentTable.class);
    private Button generateButton = new Button("Generate!");

    @Override
    protected void init(VaadinRequest request) {
        grid.addColumn(aPresentTable -> aPresentTable.getSchenkender().getName())
                .setCaption("Schenkende Person")
                .setId("schenkendePersonId");
        grid.addColumn(aPresentTable -> aPresentTable.getBeschenkter().getName())
                .setCaption("Beschenkte Person")
                .setId("beschenktePersonId");
        grid.setColumns("schenkendePersonId", "beschenktePersonId");
        VerticalLayout verticalLayout = new VerticalLayout(grid, generateButton);
        generateButton.addClickListener(e -> updatePresentTable());

        setContent(verticalLayout);
    }

    private void updatePresentTable() {
        PresentTable items1 = new PresentTable(new Person("Egon"), new Person("Hans"));
        PresentTable items2 = new PresentTable(new Person("Miau"), new Person("Hans"));
        PresentTable items3 = new PresentTable(new Person("Timo"), new Person("Hanna"));
        grid.setItems(items1, items3, items2);
    }
}
