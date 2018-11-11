package geschenke.view;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import geschenke.WeihnachtenVerteiler;
import geschenke.model.PresentTable;

import java.util.List;

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
        final List<PresentTable> presentTableList = WeihnachtenVerteiler.getPresentTableList();

        for (PresentTable presentTable : presentTableList) {
            System.out.println("Schenkender wird sein: '" + presentTable.getSchenkender().getName()
                    + "' und Beschenkter wird sein '" + presentTable.getBeschenkter().getName() + "'.");
        }
        grid.setItems(presentTableList);
    }
}
