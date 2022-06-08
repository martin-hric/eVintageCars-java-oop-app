package com.example.projekt.users;

import com.example.projekt.systems.BasicSystem;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Bohaty uchadzac ktory je serializovatelny
 */
public class RichUser extends User implements HumanBeing, Serializable {
    private final String Name;
    private final int id;
    public RichUser(int ID,String NAME){
        this.id = ID;
        this.Name = NAME;
    }

    @Override
    public String getName() {
        return this.Name;
    }

    @Override
    public String getInfo() {
        return String.format("%d Rich User: %s",(this).id,(this).Name);
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
     * @param basicSystem
     * @return
     */
    @Override
    public LinkedList getMyOffer(BasicSystem basicSystem) {
        return basicSystem.getList(this);
    }

}
