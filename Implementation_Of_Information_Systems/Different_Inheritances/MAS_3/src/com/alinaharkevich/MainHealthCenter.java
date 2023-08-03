package com.alinaharkevich;

public class MainHealthCenter extends  Laboratory implements IHospital {
    //Multi-inheritance:
    private int maxCapacity;
    private int cabinetsNum;

    public MainHealthCenter(int id, int labRoomsNum, int cabinetsNum, int maxCapacity) {
        super(id, labRoomsNum);
        setCabinetsNum(cabinetsNum);
        setMaxCapacity(maxCapacity);
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        if(maxCapacity<=0){
            throw new IllegalArgumentException("Capacity number can not be zero or less");
        }
        this.maxCapacity = maxCapacity;
    }

    @Override
    public int getCabinetsNum() {
        return cabinetsNum;
    }

    @Override
    public void setCabinetsNum(int cabinetsNum) {
        if (cabinetsNum <= 0) {
            throw new IllegalArgumentException("Number of cabinets must be greater than zero.");
        }
        this.cabinetsNum = cabinetsNum;
    }

    @Override
    public String toString() {
        return "Main Health Center | ID: " + getId() + " | Number of Labs: " + getLabRoomsNum() +
                " | Number of Cabinets: " + getCabinetsNum() + " | Max Capacity: " + getMaxCapacity();
    }
}
