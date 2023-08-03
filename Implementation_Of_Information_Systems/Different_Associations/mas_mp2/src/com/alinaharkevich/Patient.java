package com.alinaharkevich;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Patient {
    //BASIC ASSOCIATION: Doctor, Patient. 1 patient can has from 0 to 1 doctor.
    // Can has no doctor because patients sometimes bring analyzes, not only get treatment from a specific doctor

    private String surname;
    private Doctor patient;

    //ASSOCIATION WITH ATTRIBUTE: Visit, Patient and Laboratory
    private Set<Visit> visits = new HashSet<>();

    public Patient (String surname){
        setSurname(surname);
    }

    //Mandatory attribute__________________________________________________________________
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) { //check validation for mandatory attribute String
        if( surname == null || "".equals(surname.trim())){
            throw new IllegalArgumentException("Surname is obligatory.");
        }
        this.surname = surname;
    }

    //Visit set getter__________________________________________________________________
    public Set<Visit> getVisit() {
        return Collections.unmodifiableSet(visits); // safe getter
    }

    //Basic association__________________________________________________________________
    public void setPatient(Doctor patient) {

        if(this.patient == patient){ //to avoid recursion
            return;
        }

        if( this.patient == null && patient != null){ //add
            this.patient = patient;
            patient.addPatient(this);//back reference

        }else if (this.patient != null && patient == null) { //remove
            Doctor tmp = this.patient;
            this.patient = null;
            tmp.removePatient(this);//back reference

        } else if (this.patient != null && patient !=  null){ //replace
            Doctor tmp = this.patient;
            this.patient = null;
            tmp.removePatient(this);
            this.patient = patient;
            patient.addPatient(this);

        }
    }

    //Add and Remove Visit__________________________________________________________________
    public void addVisit (Visit visit){
        if (visit == null) {
            throw new IllegalArgumentException("Null value not allowed.");
        }
        if (visit.getPatient() != this) { //check if given visit is related to this
            throw new IllegalArgumentException("Visit association not related to this laboratory.");
        }
        visits.add(visit); // no need in back reference
    }

    public void removeVisit (Visit visit){
        if (visit == null) {
            throw new IllegalArgumentException("Null value not allowed.");
        }
        visits.remove(visit); // no need in back reference
    }

    // toString__________________________________________________________________
    @Override
    public String toString() {
        String str = "Patient [Surname:" + this.surname + ", ";
        if (this.patient == null) {
            str += "No assigned doctor)";
        } else {
            str += "Doctor:" + this.patient.getSurname() + ", ";
        }
        str += "Visits:" + visits.size() + ", Labs:";
        for (Visit visit : visits) {
            str += visit.getLab().getName() + ", ";
        }
        if (!visits.isEmpty()) {
            str = str.substring(0, str.length() - 2); // remove the last comma and space
        }
        str += "]";
        return str;
    }
}
