package geschenke.view;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import geschenke.model.PresentTable;
import geschenke.model.WeihnachtenverteilerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringUI(path = "presentpairs")
public class GeneratePresentPairingView extends UI {

    private Grid<PresentTable> grid = new Grid<>(PresentTable.class);
    private Button generateButton = new Button("Generate!");

    @Autowired
    private WeihnachtenverteilerService weihnachtenverteilerService;

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
        final List<PresentTable> presentTableList = weihnachtenverteilerService.getPresentTableList();

        for (PresentTable presentTable : presentTableList) {
            System.out.println("Schenkender wird sein: '" + presentTable.getSchenkender().getName()
                    + "' und Beschenkter wird sein '" + presentTable.getBeschenkter().getName() + "'.");
        }
        grid.setItems(presentTableList);
    }
}
