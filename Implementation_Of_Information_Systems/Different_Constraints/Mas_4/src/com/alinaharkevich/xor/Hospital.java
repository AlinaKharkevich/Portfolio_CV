package com.alinaharkevich.xor;

public class Hospital {
    private String name;

    public Hospital(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || "".equals(name.trim())) {
            throw new IllegalArgumentException("Hospital name cannot be null or empty.");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hospital: name=" + name;
    }
}

