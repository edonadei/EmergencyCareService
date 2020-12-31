package com.efrei.emergency.care.entities;

import java.util.UUID;

public class Patient {
    private String id;
    private String firstName;
    private String lastName;
    private Boolean checkedIn;
    private Paperwork paperwork;
    private Boolean examined;

    public Patient(String firstName, String lastName) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.checkedIn = false;
        this.examined = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Boolean getCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(Boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    public Paperwork getPaperwork() {
        return paperwork;
    }

    public void setPaperwork(Paperwork paperwork) {
        this.paperwork = paperwork;
    }

    public Boolean getExamined() {
        return examined;
    }

    public void setExamined(Boolean examined) {
        this.examined = examined;
    }
}
