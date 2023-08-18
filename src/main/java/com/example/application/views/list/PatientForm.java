package com.example.application.views.list;

import com.example.application.data.entity.Patient;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.renderer.ComponentRenderer;

public class PatientForm extends FormLayout {
    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");
    EmailField email = new EmailField("E-mail");
    TextField phoneNumber = new TextField("Phone Number");
    TextField identityNo = new TextField("Turkish Citizenship Number");

    ComboBox<String> gender = new ComboBox<>("Gender");
    Binder<Patient> binder = new BeanValidationBinder<>(Patient.class);
    private Patient patient;

    public void setPatient(Patient patient) {
        System.out.println("Set patient");
        this.patient = patient;
        binder.readBean(patient);
    }

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    public PatientForm() {
        binder.bindInstanceFields(this);

        gender.setItems("Male", "Female", "Other");

        gender.setRenderer(new ComponentRenderer<>(item -> {
            Icon icon;
            switch (item) {
                case "Male":
                    icon = VaadinIcon.MALE.create();
                    break;
                case "Female":
                    icon = VaadinIcon.FEMALE.create();
                    break;
                case "Other":
                    icon = VaadinIcon.QUESTION.create();
                    break;
                default:
                    icon = new Icon();
                    break;
            }
            return icon;
        }));

        add(firstName,
                lastName,
                email,
                phoneNumber,
                identityNo,
                gender,
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

    public static abstract class ContactFormEvent extends ComponentEvent<PatientForm> {
        private Patient patient;

        protected ContactFormEvent(PatientForm source, Patient patient) {
            super(source, false);
            this.patient = patient;
        }

        public Patient getPatient() {
            return patient;
        }
    }

    public static class SaveEvent extends ContactFormEvent {
        SaveEvent(PatientForm source, Patient patient) {
            super(source, patient);
        }
    }

    public static class DeleteEvent extends ContactFormEvent {
        DeleteEvent(PatientForm source, Patient patient) {
            super(source, patient);
        }

    }

    public static class CloseEvent extends ContactFormEvent {
        CloseEvent(PatientForm source) {
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
}