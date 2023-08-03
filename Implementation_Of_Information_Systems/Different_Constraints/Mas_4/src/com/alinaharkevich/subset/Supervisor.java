package com.alinaharkevich.subset;

import java.util.*;
import java.util.stream.Collectors;

public class Supervisor {
    private Set<HeadTeam> belongsTo = new HashSet<>();
    private Set<HeadTeam> manages = new HashSet<>();

    private String name;

    public Supervisor(String name){
        setName(name);
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

    public List<HeadTeam> getBelongsTo() { //Ordered constraint
        return Collections.unmodifiableList(
                belongsTo.stream()
                        .sorted(Comparator.comparingInt(HeadTeam::getTeamNum))
                        .collect(Collectors.toList())
        );
    }

    public void addBelongsTo(HeadTeam team) {
        if (manages.contains(team)) {
            throw new IllegalArgumentException("Supervisor already exists in this group.");
        }
        belongsTo.add(team);
        team.addSupervisor(this);
    }

    public void addManages(HeadTeam team) {
        if (!belongsTo.contains(team)) {
            throw new IllegalArgumentException("Team has not been created yet.");
        }
        if (manages.contains(team)) {
            throw new IllegalArgumentException("Team is already being managed.");
        }
        manages.add(team);
    }

    public void removeBelongsTo(HeadTeam team) {
        if( team == null){
            throw new IllegalArgumentException("Team can not be null.");
        }
        if (manages.contains(team)) {
            throw new IllegalArgumentException("Team already exist.");
        }
        belongsTo.remove(team);
        team.removeSupervisor(this);
    }

    public void removeManages(HeadTeam team) {
        if( team == null){
            throw new IllegalArgumentException("Team can not be null.");
        }
        if (!belongsTo.contains(team)) {
            throw new IllegalArgumentException("Team does not created yet.");
        }
        manages.remove(team);
    }

    public Set<HeadTeam> getManages() {
        return manages.stream()
                .sorted(Comparator.comparingInt(HeadTeam::getTeamNum))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public String toString() {
        String result = "Supervisor: " + name + "\n";

        if (!belongsTo.isEmpty()) {
            result += "Belongs to teams:\n";
            for (HeadTeam team : getBelongsTo()) {
                result += "Team number: " + team.getTeamNum() + "\n";
            }
        } else {
            result += "Does not belong to any teams.\n";
        }

        return result;
    }
}

