package com.alinaharkevich;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Laboratory {//Association with attribute: Visit, Patient and Laboratory

    private Set<Visit> visits = new HashSet<>();
    private String name;

    public Laboratory (String name){
        setName(name);
    }

    //Mandatory attribute__________________________________________________________________
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if( name == null || "".equals(name.trim())){
            throw new IllegalArgumentException("Name is obligatory.");
        }
        this.name = name;
    }

    //Visits set getter__________________________________________________________________
    public Set<Visit> getVisit() {
        return Collections.unmodifiableSet(visits); // safe getter
    }

    //Add and Remove__________________________________________________________________
    public void addVisit (Visit visit){
        if (visit == null) {
            throw new IllegalArgumentException("Null value not allowed.");
        }
        if (visit.getLab() != this) { //check if given visit is related to this
            throw new IllegalArgumentException("Visit association not related to this laboratory.");
        }
        visits.add(visit); // no need in back reference
    }

    public void removeVisit (Visit visit){
        if (visit == null) {
            throw new IllegalArgumentException("Null value not allowed.");
        }
        visits.remove(visit);// no need in back reference
    }

    // toString__________________________________________________________________
    @Override
    public String toString() {
        String str =  "Laboratory [Name=" + name + ", Visits=" + visits.size() + ", Patients=";
        for (Visit visit : visits) {
            str += visit.getPatient().getSurname() + ", ";
        }
        if (!visits.isEmpty()) {
            str = str.substring(0, str.length() - 2); // remove the last comma and space
        }
        str += "]";
        return  str;
    }
}
