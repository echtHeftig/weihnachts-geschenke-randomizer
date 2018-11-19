package geschenke.view;

import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

public class NavigationHorizontalLayout extends HorizontalLayout {
    private Button navigateToPersonViewButton = new Button("PersonView");
    private Button navigateToForbiddenViewButton = new Button("ForbiddenView");
    private Button navigateToGenerateViewButton = new Button("GenerateView");

    public NavigationHorizontalLayout() {
        new HorizontalLayout(navigateToPersonViewButton, navigateToForbiddenViewButton, navigateToGenerateViewButton);
    }

    HorizontalLayout getNavigationLayout() {
        HorizontalLayout horizontalNavigationLayout = new HorizontalLayout(navigateToPersonViewButton, navigateToForbiddenViewButton, navigateToGenerateViewButton);
        navigateToPersonViewButton.addClickListener(e -> switchToPage("personview"));
        navigateToForbiddenViewButton.addClickListener(e -> switchToPage("forbiddenview"));
        navigateToGenerateViewButton.addClickListener(e -> switchToPage("presentpairs"));
        return horizontalNavigationLayout;
    }

    private void switchToPage(String path) {
        Page.getCurrent().open("http://localhost:8080/"+path, null);
    }
}
