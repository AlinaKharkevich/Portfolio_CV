package com.alinaharkevich.attributeConstarint;

import java.util.regex.Pattern;

public class Doctor {
    private static final int MIN_AGE = 25;
    private static final int MAX_AGE = 60;
    private static final Pattern NAME_PATTERN = Pattern.compile("[A-Za-z]+");

    private String name;
    private int age;
    private int qualificationYears; //years of experience

    public Doctor(String name, int age, int qualificationYears) {
        setName(name);
        setAge(age);
        setQualificationYears(qualificationYears);
    }

    //Static attribute constraints_________________________________________________
    public String getName() {
        return name;
    }

    public void setName(String name){
        if(name == null || "".equals(name.trim())){
            throw new IllegalArgumentException("Name is obligatory.");
        }
        if (!NAME_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException("Invalid name format.");
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age){
        //Not obligatory condition - because our min age = 25, and we will have an error anyway
        //It is just help for user to fix error
        if(age < 0){
            throw new IllegalArgumentException("Doctor age can not be negative number. Check the provided age.");
        }
        //Obligatory condition
        if (age < MIN_AGE || age > MAX_AGE) { //25 || 60
            throw new IllegalArgumentException("Doctor can not be younger then 25 or older then 55.");
        }
        this.age = age;
    }

    //Dynamic attribute constraints_________________________________________________
    public int getQualificationYears() {
        return qualificationYears;
    }

    public void setQualificationYears(int qualificationYears) {
        if(qualificationYears <= 0){
            throw new IllegalArgumentException("Number of qualification years can not zero or less");
        }
        if(qualificationYears < this.qualificationYears){
            throw new IllegalArgumentException("Number of qualification years can not decrease, only rise");
        }
        this.qualificationYears = qualificationYears;
    }

    @Override
    public String toString() {
        return "Doctor [name = " + getName() + ", age = " + getAge() + ", qualification = " + getQualificationYears() + "]";
    }
}