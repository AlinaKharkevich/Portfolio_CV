package com.alinaharkevich;

public class Supervisor extends Person{
    //Dynemic inheritance
    private String department;

    public Supervisor(String name, String department) {
        super(name);
        setDepartment(department);
    }

    public Supervisor(Person oldRole, String studentNumber) {
        super(oldRole);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if( department == null || "".equals(department.trim())){
            throw new IllegalArgumentException("Name is obligatory.");
        }
        this.department = department;
    }

    @Override
    public void removeFromExtent(){
        super.removeFromExtent();
    }

    @Override
    public String toString() {
        return "Supervisor{" +
                "name='" + getName() + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
