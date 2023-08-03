package com.alinaharkevich.xor;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Person {
    private Set<InsuranceOffice> contract = new HashSet<>();
    private Hospital beTreated;
    private Date contractStartDate;
    private Date contractEndDate;
    private String name;

    public Person(String name) {
        this.beTreated = null;
        this.contractStartDate = null;
        this.contractEndDate = null;
        setName(name);
    }

    public Set<InsuranceOffice> getContract() {
        return Collections.unmodifiableSet(contract);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || "".equals(name.trim())){
            throw new IllegalArgumentException("Name is obligatory.");
        }
        this.name = name;
    }

    public void addContract(InsuranceOffice ins) {
        if (beTreated != null) {
            throw new IllegalStateException("Person cannot make contract and work for a hospital at the same time.");
        }
        contract.add(ins);
    }

    public void setWorksFor(Hospital hospital) {
        if (beTreated != null || !contract.isEmpty()) {
            throw new IllegalStateException("Person cannot work for a hospital while making contract for companies.");
        }
        this.beTreated = hospital;
    }

    public void setContractDates(Date startDate, Date endDate) { // Special restriction
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Contract dates cannot be null.");
        }

        if (startDate.after(endDate)) {
            throw new IllegalArgumentException("Contract end date must be after the contract start date.");
        }

        this.contractStartDate = startDate;
        this.contractEndDate = endDate;
    }

    @Override
    public String toString() {
        return "Person: make contract=" + contract + ", beTreated=" + beTreated + ", contractStartDate=" + contractStartDate + ", contractEndDate=" + contractEndDate;
    }
}
