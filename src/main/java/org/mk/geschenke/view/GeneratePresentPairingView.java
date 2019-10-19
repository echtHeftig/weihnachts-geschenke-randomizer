package org.mk.geschenke.view;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.mk.geschenke.domain.PresentTable;
import org.mk.geschenke.presenttable.SchenkenderBeschenkenderException;
import org.mk.geschenke.presenttable.PresentTableService;
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
    private PresentTableService presentTableService;

    @Override
    protected void init(VaadinRequest request) {
        grid.addColumn(aPresentTable -> aPresentTable.getSchenkender().getName())
                .setCaption("Schenkende Person")
                .setId("schenkendePersonId");
        grid.addColumn(aPresentTable -> aPresentTable.getBeschenkter().getName())
                .setCaption("Beschenkte Person")
                .setId("beschenktePersonId");
        grid.setColumns("schenkendePersonId", "beschenktePersonId");
        grid.setHeightByRows(12);
        VerticalLayout verticalLayout = new VerticalLayout(new NavigationHorizontalLayout().getNavigationLayout(), grid, generateButton);
        generateButton.addClickListener(e -> updatePresentTable());

        setContent(verticalLayout);
    }

    private void updatePresentTable() {
        List<PresentTable> presentTableList = null;
        try {
            presentTableList = presentTableService.getPresentTableList();
        } catch (SchenkenderBeschenkenderException e) {
            Notification.show(e.getNewMessage(), Notification.Type.ERROR_MESSAGE);
            return;
        }

        for (PresentTable presentTable : presentTableList) {
            LOGGER.info("Schenkender wird sein: {} und der Beschenkte wird sein: {}",
                    presentTable.getSchenkender().getName(), presentTable.getBeschenkter().getName());
        }
        grid.setItems(presentTableList);
    }
}
