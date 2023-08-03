package com.alinaharkevich;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Prescription extends ObjectPlus{
    //COMPOSITION ASSOCIATION
    private int prescriptionId;
    private LocalDate date;
    private Set<Medicine> medicines = new HashSet<>();
    private static Set<Integer> allIds = new HashSet<>(); //new field to keep track of all used ids

    public Prescription(int prescriptionId, LocalDate date) {
        setPrescriptionId(prescriptionId);
        if (date == null) { //null check
            throw new IllegalArgumentException("Null date not allowed.");
        }
        this.date = date;
    }

    // Getters and Setters__________________________________________________________________
    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int id) {
        if (allIds.contains(id)) {
            throw new IllegalArgumentException("Prescription id already exists.");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("Prescription id can not be zero or less.");
        }
        allIds.remove(this.prescriptionId);  // Remove the old ID from allIds
        allIds.add(id);  // Add the new ID to allIds
        this.prescriptionId = id;
    }

    public LocalDate getPrescriptionDate() {
        return date;
    }

    // Medicine set getter__________________________________________________________________
    public Set<Medicine> getMedicines() {
        return Collections.unmodifiableSet(medicines); // safe getter
    }

    public Set<Integer> getAllIds() {
        return Collections.unmodifiableSet(allIds); // safe getter
    }

    // Add and Remove__________________________________________________________________
    public void addMedicine (String medName, int number) {
        if (medName == null || medName.trim().isEmpty()) {
            throw new IllegalArgumentException("Medicine name cannot be null or empty.");
        }
        if (number <= 0) {
            throw new IllegalArgumentException("Number of medicine must be greater than zero.");
        }
        Medicine medicine = new Medicine(medName, number, this);
        medicines.add(medicine); //no back reference
    }

    public void removeMedicine(Medicine medicine) {
        if (medicine == null) {
            throw new IllegalArgumentException("Medicine cannot be null.");
        }

        if (!medicines.contains(medicine)) {
            throw new IllegalArgumentException("Medicine is not in the collection.");
        }
        medicines.remove(medicine);
        medicine.remove();
    }

    public void removeMedicine(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Medicine name cannot be null or empty.");
        }
        for (Medicine medicine : medicines) {
            if (medicine.getMedName().equals(name)) {
                medicines.remove(medicine);
                medicine.remove();
                break;
            }
        }
    }

    public void removePrescription (){
        Set<Medicine> medicinesCopy = new HashSet<>(medicines);
        for (Medicine medicine : medicinesCopy) {
            removeMedicine(medicine);
        }
        getExtent().remove(this);
    }

    // toString__________________________________________________________________
    @Override
    public String toString() {
         String str = "Prescription [" +
                "date=" + date +
                ", med=";
        for (Medicine medicine : medicines) {
            str += medicine + ", ";
        }
        if (!medicines.isEmpty()) {
            str = str.substring(0, str.length() - 2);
        }
        str += "]";
        return str;
    }
}

