package geschenke.view;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import geschenke.model.PresentTable;

@SpringUI(path = "")
public class MyMainView extends UI {

    private Grid<PresentTable> grid = new Grid<>(PresentTable.class);
    private Button generateButton = new Button("Generate!");

    @Override
    protected void init(VaadinRequest request) {
        grid.setItems(new PresentTable());
        VerticalLayout verticalLayout = new VerticalLayout(grid, generateButton);
        generateButton.addClickListener(e -> updatePresentTable());

        setContent(verticalLayout);
    }

    private void updatePresentTable() {
        System.out.println("mach mal nichts");
    }
}
