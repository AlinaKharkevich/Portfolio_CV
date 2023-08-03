package com.alinaharkevich;

public class DrinkItem extends MenuItem {
    //Multi-aspect inheritance
    private double alcoholPersentage;

    public DrinkItem(String name, int price, double alcoholPersentage) {
        super(name, price);
        setAlcoholPersentage(alcoholPersentage);
    }

    public double getAlcoholPersentage() {
        return alcoholPersentage;
    }

    public void setAlcoholPersentage(double alcoholPersentage) {
        if(alcoholPersentage<0){//can be zero
            throw new IllegalArgumentException("Alco persentage can not be less then zero");
        }
        this.alcoholPersentage = alcoholPersentage;
    }

    @Override
    public String toString() {
        return "Drink | Name: " + getName() + " |  Price: " + getPrice() + " | Acho %: " + getAlcoholPersentage();
    }
}
