package com.alinaharkevich;

class Antibiotic extends Medicine {
    //Abstract class / polymorphism
    private double milligramsPerTablet;

    public Antibiotic(String name, double price, double milligramsPerTablet) {
        super(name, price);
        setMilligramsPerTablet(milligramsPerTablet);
    }

    public double getMilligramsPerTablet() {
        return milligramsPerTablet;
    }

    public void setMilligramsPerTablet(double milligramsPerTablet) {
        if (milligramsPerTablet <= 0) {
            throw new IllegalArgumentException("Number of doses can not be zero or less.");
        }
        this.milligramsPerTablet = milligramsPerTablet;
    }

    //Overrided methods__________________________________________________________________
    @Override
    public String isPrescriptionRequired() {
        if( milligramsPerTablet >= 10.5){
            return "Prescription is required";
        }else {
            return "Prescription is not required";
        }
    }

    @Override
    public String finalPrice( double numDoses){
        if( milligramsPerTablet >= 10.5) {
            double fee = (getPricePerOne() * numDoses) * 0.1;
            return "Final price with fee = " + ((getPricePerOne() * numDoses) + fee);
        }else{
            return "Final price = " + (getPricePerOne() * numDoses);
        }
    }

    @Override
    public String toString() {
        return "Antibiotic{" +
                "name=" + getName() +
                ", price=" + getPricePerOne() +
                ", milligrams per tablet=" + milligramsPerTablet +
                '}';
    }
}
