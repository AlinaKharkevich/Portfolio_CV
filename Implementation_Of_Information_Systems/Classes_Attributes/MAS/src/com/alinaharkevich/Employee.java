package com.alinaharkevich;

public class Employee {
    private Company employer;
    public Company getEmployer(){
        return employer;
    }
    public void  setEmployer(Company employer){
        if (this.employer ==null && employer != null){
        }else if (this.employer != null && employer == null){
        }-
    }
}
