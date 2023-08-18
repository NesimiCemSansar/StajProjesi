package com.example.application.views.list;

import com.example.application.data.entity.Persons;
import com.example.application.data.service.PersonsService;
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

@Route(value = "persons", layout = MainLayout.class)
@PageTitle("Persons List | HBYS Cem")

public class PersonsView extends VerticalLayout {
    Grid<Persons> grid = new Grid<>(Persons.class);
    TextField filterText = new TextField();
    PersonsForm form;
    PersonsService service;

    public PersonsView(PersonsService service) {
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
        form = new PersonsForm();
        form.setWidth("25em");
        form.addListener(PersonsForm.SaveEvent.class, this::savePersons);
        form.addListener(PersonsForm.DeleteEvent.class, this::deletePersons);
        form.addListener(PersonsForm.CloseEvent.class, e -> closeEditor());
    }

    private void savePersons(PersonsForm.SaveEvent event) {
        service.savePersons(event.getPersons());
        updateList();
        closeEditor();
    }

    private void deletePersons(PersonsForm.DeleteEvent event) {
        service.deletePersons(event.getPersons());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("persID", "firstName", "lastName", "phoneNumber");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event -> {
            editPersons(event.getValue());
            System.out.println("Grid selected");

        });
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addPersonsButton = new Button("Add person");
        addPersonsButton.addClickListener(click -> addPersons());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addPersonsButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    public void editPersons(Persons persons) {
        // System.out.println(persons.getPersID());
        if (persons == null) {
            closeEditor();
        } else {
            form.setPersons(persons);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setPersons(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void addPersons() {
        grid.asSingleSelect().clear();
        editPersons(new Persons());
    }

    private void updateList() {
        grid.setItems(service.findAllPersons(filterText.getValue()));
    }
}
