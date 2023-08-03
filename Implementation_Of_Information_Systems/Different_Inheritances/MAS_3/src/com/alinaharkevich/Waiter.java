package com.alinaharkevich;

public class Waiter {
    //Multi-aspect inheritance
    private String name;

    public Waiter(String name) {
        setName(name);
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

    @Override
    public String toString() {
        return "Waiter | Name: " + getName() ;
    }
}
