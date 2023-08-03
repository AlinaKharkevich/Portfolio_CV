package com.alinaharkevich;

public class MenuItem {
    //Multi-aspect inheritance
    private String name;
    private int price;

    public MenuItem(String name, int price) {
        setName(name);
        setPrice(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if( name == null || "".equals(name.trim())){
            throw new IllegalArgumentException("Name is obligatory.");
        }
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if(price<=0){
            throw new IllegalArgumentException("Menu item price can not be zero or less");
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return "Menu Name: " + getName() + " |  Price: " + getPrice();
    }
}
