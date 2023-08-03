package com.alinaharkevich;

import java.time.LocalDate;

public class Visit { //Association with attribute: Visit, Patient and Laboratory

    private Laboratory lab;
    private Patient patient;

    private LocalDate visitDate;
    private int id;

    public Visit(Laboratory lab, Patient patient, LocalDate visitDate, int id) {
        for (Visit visit : lab.getVisit()) { // Check for duplicate visits
            if (visit.getPatient().equals(patient) &&
                    visit.getVisitDate().equals(visitDate) &&
                    visit.getId() == id) {
                throw new IllegalArgumentException("Duplicate visit not allowed.");
            }
        }
        for (Visit visit : lab.getVisit()) { // Check for duplicate IDs within the same laboratory
            if (visit.getId() == id) {
                throw new IllegalArgumentException("Duplicate ID not allowed within the same laboratory.");
            }
        }
        if (lab == null || patient == null || visitDate == null) { //null check
            throw new IllegalArgumentException("Null value not allowed.");
        }
        if (id <=0){
            throw new IllegalArgumentException("Id can not be zero or less.");
        }

        this.lab = lab;
        this.patient = patient;
        this.visitDate = visitDate;
        this.id = id;

        lab.addVisit(this);
        patient.addVisit(this);
    }

    // Getters__________________________________________________________________
    public Laboratory getLab() {
        return lab;
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public int getId() {
        return id;
    }

    // Remove__________________________________________________________________

    public void remove() {

        // remove references between related objects
        lab.removeVisit(this);
        patient.removeVisit(this);

        // set references to null to make the object eligible for garbage collection
        lab = null;
        patient = null;
    }

    // toString__________________________________________________________________
    @Override
    public String toString() {
        return "Visit [" +
                "Lab=" + lab.getName() +
                ", Patient=" + patient.getSurname() +
                ", VisitDate=" + visitDate.toString() +
                ", Id=" + id +
                ']';
    }
}

