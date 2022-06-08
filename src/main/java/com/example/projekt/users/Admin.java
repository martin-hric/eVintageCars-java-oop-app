package com.example.projekt.users;

import com.example.projekt.systems.BasicSystem;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Trieda administratora, ktory dokaze mazat aukcie
 */
public class Admin extends Provider implements Serializable {
    public Admin(int ID, String NAME) {
        super(ID,NAME);
    }
    public String getInfo() {
        return String.format("Administrator ID: %d , meno: %s", this.getID(), this.getName());
    }

    public String getType(){
        return "Admin";
    }

    /**
     * Visitor pattern
     * @param basicSystem
     * @return
     */
    @Override
    public LinkedList getMyOffer(BasicSystem basicSystem) {
        return basicSystem.getList(this);
    }

}
