package com.example.projekt.users;

import com.example.projekt.systems.BasicSystem;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Obycajny uzivatel
 */
public class CheapUser extends User implements HumanBeing, Serializable {
    private final String Name;
    private final int id;

    public CheapUser(String name,int ID){
        this.Name = name;
        this.id = ID;
    }

    @Override
    public String getName(){
        return this.Name;
    }

    @Override
    public String getInfo() {
        return String.format("%d Cheap User: %s",(this).id,(this).Name);
    }

    public String getType(){
        return "User";
    }

    @Override
    public int getID() {
        return this.id;
    }

    /**
     * Visitor pattern
     * @param basicSystem basicsystem
     * @return vracia linkedlist jemu dany
     */
    @Override
    public LinkedList getMyOffer(BasicSystem basicSystem) {
        return basicSystem.getList(this);
    }
}
