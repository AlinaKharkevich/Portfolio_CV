package com.alinaharkevich;

class Painkiller extends Medicine {
    //Abstract class / polymorphism
    private boolean isNarcotic;

    public Painkiller(String name, double price, boolean isNarcotic) {
        super(name, price);
        setNarcotic(isNarcotic);
    }

    public boolean isNarcotic() {
        return isNarcotic;
    }

    public void setNarcotic(boolean isNarcotic) {
        this.isNarcotic = isNarcotic;
    }

    //Overrided methods__________________________________________________________________
    @Override
    public String isPrescriptionRequired() {
        if( isNarcotic == true){
            return "Prescription is required";
        }else {
            return "Prescription is not required";
        }
    }

    @Override
    public String finalPrice( double numDoses){
        if( isNarcotic == true) {
            double fee = (getPricePerOne() * numDoses) * 0.15;
            return "Final price with fee = " + ((getPricePerOne() * numDoses) + fee);
        }else{
            return "Final price = " + (getPricePerOne() * numDoses);
        }
    }

    @Override
    public String toString() {
        return "Painkiller{" +
                "name=" + getName() +
                ", price=" + getPricePerOne() +
                ", isNarcotic=" + isNarcotic +
                '}';
    }
}