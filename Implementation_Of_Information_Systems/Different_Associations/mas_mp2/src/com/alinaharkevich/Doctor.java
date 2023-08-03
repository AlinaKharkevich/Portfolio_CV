package com.alinaharkevich;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Doctor {
    // BASIC ASSOCIATION: 1 doctor can have from 1 to Many patients.
    // Has at least 1 patient because work without patients is impossible in hospital

    private Set<Patient> patients = new HashSet<>();
    private String surname;

    public Doctor (String surname, Patient patient){
        setSurname(surname);
        addPatient(patient); //doctor should has at least 1 patient when we create a Doctor
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

    // Patients set getter__________________________________________________________________
    public Set<Patient> getPatients() {
        return Collections.unmodifiableSet(patients);//prevent modification outside the class
    }

    // Add and Remove__________________________________________________________________
    public void addPatient(Patient patient) {
        if ( patient == null) { //check if patient really exist
            throw new IllegalArgumentException("Patient cannot be null");
        }
        if(patients.contains((patient))){ //check if we already has this patient
            return;
        }
        patients.add(patient); //add to collection
        patient.setPatient(this); //back reference
    }

    public void removePatient(Patient patient) {
        if ( patient == null) { //check if patient really exist
            throw new IllegalArgumentException("Patient cannot be null");
        }
        if(this.patients.size() == 0){ //check if if it is the last patient
            throw new IllegalArgumentException("Doctor should have at least 1 patient");
        }
        if(!patients.contains((patient))){ //check if we do not have this patient already
            return;
        }
        patients.remove(patient); //remove from collection
        patient.setPatient(null); //back reference
    }

    // toString__________________________________________________________________
    @Override
    public String toString() {
        String str = "(Doctor Surname:" + this.surname + " | ";
        str += "Patients: [";
        for (Patient patient : this.patients) {
            str += patient.getSurname() + ", ";
        }
        str = str.substring(0, str.length() - 2) + "])";
        return str;
    }
}
