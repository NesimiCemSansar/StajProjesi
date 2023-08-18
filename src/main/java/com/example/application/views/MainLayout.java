package com.example.application.views;

import com.example.application.security.SecurityService;
import com.example.application.views.list.PatientPersonView;
import com.example.application.views.list.PatientView;
import com.example.application.views.list.PersonsView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;

@Theme(themeFolder = "flowcrmtutorial")
public class MainLayout extends AppLayout {
    private final SecurityService securityService;

    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("HBYS Cem");
        logo.addClassNames("text-l", "m-m");

        Button logout = new Button("Logout", e -> securityService.logout());

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logout);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);

    }

    private void createDrawer() {
        RouterLink patientLink = new RouterLink("Patients List", PatientView.class);
        patientLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink personsLink = new RouterLink("Persons List", PersonsView.class);
        personsLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink patientPersonLink = new RouterLink("Relation List", PatientPersonView.class);
        patientPersonLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink dashboardLink = new RouterLink("Stats", DashboardView.class);
        dashboardLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(patientLink, personsLink, patientPersonLink, dashboardLink));
    }

}