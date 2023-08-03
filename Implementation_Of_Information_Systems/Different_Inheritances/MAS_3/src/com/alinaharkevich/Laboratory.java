package com.alinaharkevich;

public class Laboratory extends Building{
    //Multi-inheritance:
    private int labRoomsNum;

    public Laboratory(int id, int labRoomsNum) {
        super(id); //from building
        setLabRoomsNum(labRoomsNum);
    }

    public int getLabRoomsNum() {
        return labRoomsNum;
    }

    public void setLabRoomsNum(int labRoomsNum) {
        if(labRoomsNum<=0){
            throw new IllegalArgumentException("Number of labs rooms can not be zero or less");
        }
        this.labRoomsNum = labRoomsNum;
    }

    @Override
    public String toString() {
        return "Laboratory | ID: " + getId() + " | Number of Labs: " + getLabRoomsNum();
    }
}
