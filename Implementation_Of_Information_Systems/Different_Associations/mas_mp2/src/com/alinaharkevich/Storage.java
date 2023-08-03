package com.alinaharkevich;

import java.util.*;

public class Storage { // QUALIFIED ASSOCIATION: MedicalCard and Storage. Qualifier: id (of medical card)

    private Map<Long, MedicalCard> cards = new HashMap<>();
    private String type;

    public Storage(String type) {
        setType(type);
    }

    //Mandatory attribute__________________________________________________________________
    public String getType() {
        return type;
    }

    public void setType(String type) { //check validation for mandatory attribute String
        if( type == null || "".equals(type.trim())){
            throw new IllegalArgumentException("Type is obligatory.");
        }
        this.type = type;
    }

    //Medical Card Set getters__________________________________________________________________
    public Map<Long, MedicalCard> getCards() {
        return Collections.unmodifiableMap(cards); //safe getter
    }

    public Set<MedicalCard> getCardsSet() { //method to return a collection
        return Collections.unmodifiableSet(new HashSet(cards.values())); //safe getter + casting
    }

    public MedicalCard getCardById(long id){ // Qualifier method
        return cards.get(id);
    }

    //Add and Remove__________________________________________________________________
    public void addMedicalCard (MedicalCard card){
        if (card == null) { //null check
            throw new IllegalArgumentException("Medical card cannot be null");
        }
        if (cards.containsKey(card.getId())) { //duplicates check
            return;
        }
        cards.put(card.getId(), card);
        card.setStorage(this); //back reference
    }

    public void removeMedicalCard (MedicalCard card){
        if (card == null) { //null check
            throw new IllegalArgumentException("Card cannot be null");
        }
        if (!cards.containsValue(card)) { //check if do not have this card already
            return;
        }
        cards.remove(card.getId(), card);
        card.setStorage(null); //back reference
    }

    // toString__________________________________________________________________
    @Override
    public String toString() {
        String str = "Storage [Type: " + this.type + ", Cards ID: ";

        if (cards.isEmpty()) {
            str += "No medical cards in storage]";
        } else {
            for (MedicalCard card : cards.values()) {
                str += card.getId() + ",";
            }
            str = str.substring(0, str.length() - 1) + "]"; // remove the last comma and add a closing bracket
        }

        return str;
    }
}
