package com.alinaharkevich.bag;

public class Intern {
    private String name;

    public Intern(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || "".equals(name.trim())) {
            throw new IllegalArgumentException("Intern name cannot be null or empty.");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name: " + name;
    }
}