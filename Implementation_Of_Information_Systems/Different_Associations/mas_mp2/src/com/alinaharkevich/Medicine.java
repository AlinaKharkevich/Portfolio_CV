package com.alinaharkevich;

public class Medicine extends ObjectPlus{
    //COMPOSITION ASSOCIATION
    private String medName;
    private int number;
    private final Prescription prescription;

    public Medicine(String medName, int number, Prescription prescription) {
        setMedName(medName);
        setNumber(number);
        if (prescription == null) {
            throw new IllegalArgumentException("Prescription cannot be null.");
        }
        this.prescription = prescription;
    }

    // Getters and Setters__________________________________________________________________
    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        if( medName == null || "".equals(medName.trim())){
            throw new IllegalArgumentException("Medicine name is obligatory.");
        }
        this.medName = medName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Number of medicine units can not be zero or less.");
        }
        this.number = number;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    // Remove__________________________________________________________________
    public void remove() {
        getExtent().remove(this);
    }

    // toString__________________________________________________________________
    @Override
    public String toString() {
        return "Medicine [" +
                "medName='" + medName + '\'' +
                ", quantity=" + number +
                ", prescription id=" + prescription.getPrescriptionId() +
                ']';
    }
}
