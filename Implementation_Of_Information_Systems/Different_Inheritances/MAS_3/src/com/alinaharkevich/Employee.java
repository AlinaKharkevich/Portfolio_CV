package com.alinaharkevich;

import java.util.EnumSet;

public class Employee {
    //Overlapping inheritance
    private EnumSet<EmployeeType> types;
    private String surname;

    //ASSISTANT
    private String operation; //optional attribute
    //NURSE
    private Integer patient; // optional attribute
    //HEAD
    private String field; // optional attribute
    //DOCTOR
    private Integer officeNum; // optional attribute

    public Employee(EnumSet<EmployeeType> types, String surname,//universal CONSTRUCTOR
                    String operation, Integer patient, String field, Integer officeNum) {
        setTypes(types);
        setSurname(surname);////base class attribute

        // attributes for ASSISTANT
        if(types.contains(EmployeeType.ASSISTANT)){
            setOperation(operation);
        }
        // attributes for NURSE
        if(types.contains(EmployeeType.NURSE)){
            setPatient(patient);
        }
        // attributes for HEAD
        if(types.contains(EmployeeType.HEAD)){
            setField(field);
        }
        // attributes for DOCTOR
        if(types.contains(EmployeeType.DOCTOR)){
            setOfficeNum(officeNum);
        }
    }

    public EnumSet<EmployeeType> getTypes() {
        return EnumSet.copyOf(types);
    }

    //ENUM Set__________________________________________________________________
    //private to avoid changes
    private void setTypes(EnumSet<EmployeeType> types) {
        if (types == null) { //// null check
            throw new IllegalArgumentException("Types cannot be null");
        }
        if (types.size() < 1 || types.size() > 4) { // check set size , at least 1 at most 4
            throw new IllegalArgumentException("Types should contain at least 1 and at most 4 elements");
        }
        if (!types.containsAll(types)){ // Contains correct types
            throw new IllegalArgumentException("Types should be ASSISTANT,NURSE,SUPERVISOR,DOCTOR");
        }
        this.types = types;
    }

    //Mandatory attribute__________________________________________________________________
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if( surname == null || "".equals(surname.trim())){
            throw new IllegalArgumentException("Surname is obligatory.");
        }
        this.surname = surname;
    }

    //Getters and setters doe optional attributes_________________________________________________
    public String getOperation() {
        if(this.types.contains(EmployeeType.ASSISTANT)) {
            return operation;
        }else{
            throw new IllegalArgumentException("Wrong type");
        }
    }

    public void setOperation(String operation) {
        if(types.contains(EmployeeType.ASSISTANT)){
            this.operation = operation;
        }else {
            throw new IllegalArgumentException("Wrong type");
        }
    }

    public Integer getPatient() {
        if(this.types.contains(EmployeeType.NURSE)) {
            return patient;
        }else{
            throw new IllegalArgumentException("Wrong type");
        }
    }

    public void setPatient(Integer patient) {
        if(types.contains(EmployeeType.NURSE)){
            this.patient = patient;
        }else {
            throw new IllegalArgumentException("Wrong type");
        }
    }

    public String getField() {
        if(this.types.contains(EmployeeType.HEAD)) {
            return field;
        }else{
            throw new IllegalArgumentException("Wrong type");
        }
    }

    public void setField(String field) {
        if(types.contains(EmployeeType.HEAD)){
            this.field = field;
        }else {
            throw new IllegalArgumentException("Wrong type");
        }
    }

    public Integer getOfficeNum() {
        if(this.types.contains(EmployeeType.DOCTOR)) {
            return officeNum;
        }else{
            throw new IllegalArgumentException("Wrong type");
        }
    }

    public void setOfficeNum(Integer officeNum) {
        if(types.contains(EmployeeType.DOCTOR)){
            this.officeNum = officeNum;
        }else {
            throw new IllegalArgumentException("Wrong type");
        }
    }

    @Override
    public String toString() {
        String optionalAttributes = "";
        if (types.contains(EmployeeType.ASSISTANT)) {
            optionalAttributes += ", operation='" + operation + "'";
        }
        if (types.contains(EmployeeType.NURSE)) {
            optionalAttributes += ", patients=" + patient;
        }
        if (types.contains(EmployeeType.HEAD)) {
            optionalAttributes += ", field='" + field + "'";
        }
        if (types.contains(EmployeeType.DOCTOR)) {
            optionalAttributes += ", officeNum=" + officeNum;
        }
        return "Employee{" +
                "types=" + types +
                ", surname='" + surname + '\'' +
                optionalAttributes +
                '}';
    }
}
