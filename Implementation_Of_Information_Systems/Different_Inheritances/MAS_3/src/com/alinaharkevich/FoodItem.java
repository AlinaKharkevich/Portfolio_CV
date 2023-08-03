package com.alinaharkevich;

public class FoodItem extends MenuItem {
    //Multi-aspect inheritance
    private String cuisine;

    public FoodItem(String name, int price, String cuisine) {
        super(name, price);
        setCuisine(cuisine);
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        if( cuisine == null || "".equals(cuisine.trim())){
            throw new IllegalArgumentException("Cuisine is obligatory.");
        }
        this.cuisine = cuisine;
    }

    @Override
    public String toString() {
        return "Food | Name: " + getName() + " |  Price: " + getPrice() + " |  Cuisine: " + getCuisine() ;
    }
}
