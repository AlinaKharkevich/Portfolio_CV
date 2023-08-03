package com.alinaharkevich.subset;

import java.util.HashSet;
import java.util.Set;

public class HeadTeam {
    private int teamNum;
    private Set<Supervisor> supervisors = new HashSet<>();

    public HeadTeam(int teamNum){
        setTeamNum(teamNum);
    }

    public int getTeamNum() {
        return teamNum;
    }

    public void setTeamNum(int teamNum) {
        if(teamNum <=0){
            throw new IllegalArgumentException("Group number can not be zero or less.");
        }
        this.teamNum = teamNum;
    }

    public void addSupervisor(Supervisor supervisor) {
        if( supervisor == null){
            throw new IllegalArgumentException("Supervisor can not be null.");
        }
        supervisors.add(supervisor);
    }

    public void removeSupervisor(Supervisor supervisor) {
        if( supervisor == null){
            throw new IllegalArgumentException("Supervisor can not be null.");
        }
        if (supervisors.size() <= 1) {
            throw new IllegalStateException("At least one supervisor must exist in the team.");
        }
        supervisors.remove(supervisor);
    }

    @Override
    public String toString() {
        String result = "Team Number: " + teamNum + "\n";
         result += "Supervisors:\n";
         for (Supervisor supervisor : supervisors) {
             result += " - " + supervisor.getName() + "\n";
         }
        return result;
    }
}
