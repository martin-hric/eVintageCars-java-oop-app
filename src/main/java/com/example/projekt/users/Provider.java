package com.example.projekt.users;

import com.example.projekt.cars.Car;
import com.example.projekt.systems.BasicSystem;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Trieda poskytovatela, ktory dokaze pridavat auta
 */
public class Provider extends User implements Serializable {
    private final int id;
    private final String name;

    public Provider(int ID, String NAME) {
        this.id = ID;
        this.name = NAME;
    }

    public void addCar(LinkedList<Car> carList, Car car){
        carList.add(car);
    }
    @Override
    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getInfo() {
        return String.format("Poskytovatel ID:%d Meno: %s", this.id, this.name);
    }
    public String getType(){
        return "Provider";
    }

    /**
     * Visitor pattern
     * @param basicSystem basicsys
     * @return vracia linked list danych aut danemu uzivatelov
     */
    @Override
    public LinkedList getMyOffer(BasicSystem basicSystem) {
        return basicSystem.getList(this);
    }
}
