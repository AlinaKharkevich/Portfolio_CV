package com.alinaharkevich;

public class Hospital extends Building implements IHospital{
    //Multi-inheritance:
    private int cabinetsNum;

    public Hospital(int id, int cabinetsNum) {
        super(id); //from building
        setCabinetsNum(cabinetsNum);
    }

    @Override
    public int getCabinetsNum() {
        return cabinetsNum;
    }

    @Override
    public void setCabinetsNum(int cabinetsNum) {
        if(cabinetsNum<=0){
            throw new IllegalArgumentException("Cabinets number can not be zero or less");
        }
        this.cabinetsNum = cabinetsNum;
    }

    @Override
    public String toString() {
        return "Hospital | ID: " + getId() + " | Number of Cabinets: " + getCabinetsNum();
    }
}