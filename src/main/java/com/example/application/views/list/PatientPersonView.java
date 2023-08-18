package com.example.application.views.list;

import com.example.application.data.entity.*;
import com.example.application.data.service.*;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "patientPerson", layout = MainLayout.class)
@PageTitle("Relation List | HBYS Cem")

public class PatientPersonView extends VerticalLayout {
    Grid<Patient> grid = new Grid<>(Patient.class);
    TextField filterText = new TextField();
    PatientPersonForm form;
    PatientService service;
    PersonsService pService;

    public PatientPersonView(PatientService service, PersonsService pService) {
        this.pService = pService;
        this.service = service;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        form = new PatientPersonForm(pService);
        form.setWidth("25em");
        form.addListener(PatientPersonForm.SaveEvent.class, this::saveRelation);
        form.addListener(PatientPersonForm.DeleteEvent.class, this::deleteRelation);
        form.addListener(PatientPersonForm.CloseEvent.class, e -> closeEditor());
    }

    private void saveRelation(PatientPersonForm.SaveEvent event) {
        service.saveRelation(event.getPatient());
        updateList();
        closeEditor();
    }

    private void deleteRelation(PatientPersonForm.DeleteEvent event) {
        service.deleteRelation(event.getPatient());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName", "identityNo");
        grid.addColumn(patient -> {
            if (patient.getPersons() != null) {
                return patient.getPersons().getFirstName() + " " + patient.getPersons().getLastName();
            }
            return null;
        }).setHeader("Related Person");

        grid.addColumn(patient -> {
            if (patient.getPersons() != null) {
                return patient.getPersons().getPersID();
            }
            return null;
        }).setHeader("Related Person ID");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event -> {
            editRelation(event.getValue());
            System.out.println("Grid selected");
        });
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addRelationButton = new Button("Add relation");
        addRelationButton.addClickListener(click -> addRelation());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addRelationButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    public void editRelation(Patient patient) {
        if (patient == null) {
            closeEditor();
        } else {
            form.setRelation(patient);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setRelation(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void addRelation() {
        grid.asSingleSelect().clear();
        editRelation(new Patient());
    }

    private void updateList() {
        grid.setItems(service.findAllContacts(filterText.getValue()));
    }
}
