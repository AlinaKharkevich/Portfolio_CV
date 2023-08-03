package com.alinaharkevich;

public class MedicalCard { // QUALIFIED ASSOCIATION: MedicalCard and Storage. Qualifier: id (of medical card)
    private long id;
    private Storage storage;

    public MedicalCard(long id) {
        setId(id);
    }

    // Getter__________________________________________________________________
    public long getId() {
        return id;
    }

    public Storage getStorage() {
        return storage;
    }

    // Setter__________________________________________________________________
    public void setId(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID cannot be zero or less");
        }
        this.id = id;
    }

    public void setStorage(Storage storage) {
        //one to many check
        // Check that this MedicalCard is not already associated with another Storage

        if(this.storage == storage){ //to avoid recursion
            return;
        }

        if( this.storage == null && storage != null){ //add
            this.storage = storage;
            storage.addMedicalCard(this);//back reference

        }else if (this.storage != null && storage == null) { //remove
            Storage tmp = this.storage;
            this.storage = null;
            tmp.removeMedicalCard(this);//back reference

        } else if (this.storage != null && storage !=  null){ //replace
            Storage tmp = this.storage;
            this.storage = null;
            tmp.removeMedicalCard(this);
            this.storage = storage;
            storage.addMedicalCard(this);

        }
    }

    // toString__________________________________________________________________
    @Override
    public String toString() {
        return "Medical Card [ID: " + this.id + ", Storage Type: " + this.storage.getType() + "]";
    }
}
