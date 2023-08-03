package com.alinaharkevich.bag;

public class Internship {
    private String name;

    public Internship(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || "".equals(name.trim())){
            throw new IllegalArgumentException("Internship name cannot be null or empty.");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name: " + name;
    }
}
