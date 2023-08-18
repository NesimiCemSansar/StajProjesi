package com.example.application.views.list;

import com.example.application.data.entity.Persons;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class PersonsForm extends FormLayout {
    TextField persID = new TextField("ID");
    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");
    TextField phoneNumber = new TextField("Phone Number");

    Binder<Persons> binder = new BeanValidationBinder<>(Persons.class);
    private Persons persons;

    public void setPersons(Persons persons) {
        System.out.println("Set person");
        this.persons = persons;
        binder.readBean(persons);
    }

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    public PersonsForm() {
        binder.bindInstanceFields(this);
        add(persID,
                firstName,
                lastName,
                phoneNumber,
                createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, persons)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    public static abstract class ContactFormEvent extends ComponentEvent<PersonsForm> {
        private Persons persons;

        protected ContactFormEvent(PersonsForm source, Persons persons) {
            super(source, false);
            this.persons = persons;
        }

        public Persons getPersons() {
            return persons;
        }
    }

    public static class SaveEvent extends ContactFormEvent {
        SaveEvent(PersonsForm source, Persons persons) {
            super(source, persons);
        }
    }

    public static class DeleteEvent extends ContactFormEvent {
        DeleteEvent(PersonsForm source, Persons persons) {
            super(source, persons);
        }

    }

    public static class CloseEvent extends ContactFormEvent {
        CloseEvent(PersonsForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
            ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(persons);
            fireEvent(new SaveEvent(this, persons));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }
}