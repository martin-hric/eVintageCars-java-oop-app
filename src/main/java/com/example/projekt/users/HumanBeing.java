package com.example.projekt.users;

import com.example.projekt.systems.BasicSystem;

import java.util.LinkedList;

/**
 * Interface pre vsetkych uzivatelov
 */
public interface HumanBeing {
    String getName();

    String getInfo();

    int getID();

    String getType();

    LinkedList getMyOffer(BasicSystem basicSystem);
}
