package com.alinaharkevich;

public class Intern extends Person{
    //Dynemic inheritance
    private String sphere;

    public Intern(String name, String sphere) {
        super(name);
        setSphere(sphere);
    }

    public Intern(Person oldRole, String sphere) {
        super(oldRole);
        this.sphere = sphere;
    }

    public String getSphere() {
        return sphere;
    }

    public void setSphere(String sphere) {
        if( sphere == null || "".equals(sphere.trim())){
            throw new IllegalArgumentException("Sphere is obligatory.");
        }
        this.sphere = sphere;
    }

    @Override
    public void removeFromExtent(){
        super.removeFromExtent();
    }

    @Override
    public String toString() {
        return "Intern{name='" + getName() + "', sphere='" + sphere + "'}";
    }
}
