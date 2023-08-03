package com.alinaharkevich.unique;


import com.alinaharkevich.ObjectPlus;

import java.util.List;

public class Patient extends ObjectPlus {
    private String name;
    private String NIP; //Unique

    public Patient(String name, String NIP) {
        setName(name);
        setNIP(NIP);
        addFromExtent();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || "".equals(name.trim())) {
            throw new IllegalArgumentException("Name is obligatory.");
        }
        this.name = name;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        if (NIP == null || NIP.trim().isEmpty()) {
            throw new IllegalArgumentException("NIP is obligatory.");
        }
        if(NIP.equals(this.NIP)){ //the same
                return;
        }
        if (NIP.length() != 10) {
            throw new IllegalArgumentException("NIP must consist of exactly 10 digits.");
        }
        validateNipFormat(NIP);
        validateUniqueNIP(NIP);
        this.NIP = NIP;
    }

    private void validateNipFormat(String NIP){
        // Extract the individual digits from String
        int[] digits = new int[10];
        for (int i = 0; i < 10; i++) {
            digits[i] = Character.getNumericValue(NIP.charAt(i));
        }

        // Validate the first three digits (codes provided by tax office)
        if (digits[0] == 0 || digits[0] > 2) {
            throw new IllegalArgumentException("Invalid NIP. The first digit must be in the range of 1-2.");
        }
        if (digits[1] < 0 || digits[1] > 9) {
            throw new IllegalArgumentException("Invalid NIP. The second digit must be in the range of 0-9.");
        }
        if (digits[2] < 0 || digits[2] > 9) {
            throw new IllegalArgumentException("Invalid NIP. The third digit must be in the range of 0-9.");
        }

        // Validate the control digit
        int controlSum = 0;
        for (int i = 0; i < 9; i++) {
            controlSum += digits[i] * (i + 1);
        }
        int controlDigit = controlSum % 11;
        if (controlDigit == 10) {
            controlDigit = 0;
        }
        if (digits[9] != controlDigit) {
            throw new IllegalArgumentException("Invalid NIP. The control digit is incorrect.");
        }
    }

    private void validateUniqueNIP(String NIP) {
        List<Patient> patients = ObjectPlus.getExtent(Patient.class);
        for (Patient patient : patients) {
            if (patient != this && patient.getNIP().equals(NIP)) {
                throw new IllegalArgumentException("NIP must be unique. Another patient with the same NIP already exists.");
            }
        }
    }

    @Override
    public String toString() {
        String formattedNIP = NIP.substring(0, 3) + "-" + NIP.substring(3, 5) + "-" + NIP.substring(5, 7) + "-" + NIP.substring(7, 10);
        return "Patient [name = " + name + ", NIP = " + formattedNIP + "]";
    }
}