package com.alinaharkevich.xor;

public class InsuranceOffice {
    private String name;

    public InsuranceOffice(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || "".equals(name.trim())) {
            throw new IllegalArgumentException("Insurance Office name cannot be null or empty.");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return "Insurance Office: name=" + name;
    }
}
