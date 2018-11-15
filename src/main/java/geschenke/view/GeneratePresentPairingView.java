package geschenke.view;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import geschenke.model.PresentTable;
import geschenke.model.SchenkenderBeschenkenderException;
import geschenke.model.WeihnachtenverteilerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringUI(path = "presentpairs")
public class GeneratePresentPairingView extends UI {

    private static Logger LOGGER = LoggerFactory.getLogger(GeneratePresentPairingView.class);

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
        List<PresentTable> presentTableList = null;
        try {
            presentTableList = weihnachtenverteilerService.getPresentTableList();
        } catch (SchenkenderBeschenkenderException e) {
            Notification.show(e.getNewMessage());
            return;
        }

        for (PresentTable presentTable : presentTableList) {
            LOGGER.info("Schenkender wird sein: {} und der Beschenkte wird sein: {}",
                    presentTable.getSchenkender().getName(), presentTable.getBeschenkter().getName());
        }
        grid.setItems(presentTableList);
    }
}
