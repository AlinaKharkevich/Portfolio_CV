package com.alinaharkevich;

public class Building {
    //Multi-inheritance:
    private int id;

    public Building(int id) {
        setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id<=0){
            throw new IllegalArgumentException("Building number can not be zero or less");
        }
        this.id = id;
    }

    @Override
    public String toString() {
        return "Building ID: " + getId();
    }
}