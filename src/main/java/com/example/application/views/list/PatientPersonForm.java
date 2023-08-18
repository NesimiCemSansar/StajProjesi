package com.example.application.views.list;

import java.util.List;
import com.example.application.data.entity.*;
import com.example.application.data.service.PatientService;
import com.example.application.data.service.PersonsService;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class PatientPersonForm extends FormLayout {
    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");
    TextField identityNo = new TextField("Turkish Citizenship Number");
    ComboBox<Persons> persons = new ComboBox<>("Select Related Person");

    Binder<Patient> binder = new BeanValidationBinder<>(Patient.class);
    private Patient patient;

    private PersonsService pService;
    private PatientService service;

    public void setRelation(Patient patient) {

        System.out.println("Set person");
        this.patient = patient;
        binder.readBean(patient);
    }

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    private Patient currentPatient;

    public PatientPersonForm(PersonsService pService) {
        this.pService = pService;
        binder.bindInstanceFields(this);
        List<Integer> personId = pService.findAllPersons("").stream().map(p -> p.getPersID()).toList();
        persons.setItems(pService.findAllPersons(""));
        add(firstName,
                lastName,
                identityNo,
                persons,
                createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, patient)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    public static abstract class ContactFormEvent extends ComponentEvent<PatientPersonForm> {
        private Patient patient;

        protected ContactFormEvent(PatientPersonForm source, Patient patient) {
            super(source, false);
            this.patient = patient;
        }

        public Patient getPatient() {
            return patient;
        }
    }

    public static class SaveEvent extends ContactFormEvent {
        SaveEvent(PatientPersonForm source, Patient patient) {
            super(source, patient);
        }
    }

    public static class DeleteEvent extends ContactFormEvent {
        DeleteEvent(PatientPersonForm source, Patient patient) {
            super(source, patient);
        }

    }

    public static class CloseEvent extends ContactFormEvent {
        CloseEvent(PatientPersonForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
            ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(patient);
            fireEvent(new SaveEvent(this, patient));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public void setPatient(Patient patient) {
        currentPatient = patient;
        binder.setBean(currentPatient);
    }
}