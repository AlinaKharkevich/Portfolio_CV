package com.alinaharkevich;

public abstract class Person extends ObjectPlus {
    //Dynemic inheritance
    private String name;

    public Person(String name) {
        setName(name);
    }

    public Person(Person oldRole){
        setName(oldRole.name);
        oldRole.removeFromExtent();
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
    protected void removeFromExtent() {
        super.removeFromExtent();

        // Remove all the associations of this Person object
        for (Object key : associations.keySet()) {
            if (associations.get(key) == this) {
                associations.remove(key);
            }
        }
    }
}
