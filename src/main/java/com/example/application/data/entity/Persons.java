package com.example.application.data.entity;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "persons")
public class Persons {

    @OneToMany(mappedBy = "persons", fetch = FetchType.EAGER)
    private Set<Patient> patient;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer persID;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String phoneNumber;

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public Integer getPersID() {
        return persID;
    }

    public void setPersID(Integer persID) {
        this.persID = persID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Patient> getPatient() {
        return patient;
    }

    public void setPatient(Set<Patient> patient) {
        this.patient = patient;
    }

    public int getPatientCount() {
        return patient.size();
    }
}
