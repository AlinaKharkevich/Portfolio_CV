package com.alinaharkevich;

abstract class Medicine {
    //Abstract class / polymorphism
    private String name;
    private double pricePerOne;

    public Medicine(String name, double pricePerOne) {
        setName(name);
        setPricePerOne(pricePerOne);
    }

    //Getters and Setters__________________________________________________________________
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if( name == null || "".equals(name.trim())){
            throw new IllegalArgumentException("Name is obligatory.");
        }
        this.name = name;
    }

    public double getPricePerOne() {
        return pricePerOne;
    }

    public void setPricePerOne(double pricePerOne) {
        if (pricePerOne <= 0) {
            throw new IllegalArgumentException("Price can not be zero or less.");
        }
        this.pricePerOne = pricePerOne;
    }

    //Polymorphic method__________________________________________________________________
    abstract String isPrescriptionRequired(); // polymorphic method
    abstract String finalPrice( double numDoses); // polymorphic method
}