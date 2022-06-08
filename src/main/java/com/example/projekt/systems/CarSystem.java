package com.example.projekt.systems;

import com.example.projekt.cars.*;
import com.example.projekt.users.Provider;
import com.example.projekt.users.RichUser;
import com.example.projekt.users.User;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * Zakladny system aut na vracanie linkedlistu s danymi autami pre daneho uzivatela,
 * pridavanie aut na zaklade odlisnych parametrov
 */
public class CarSystem extends BasicSystem implements Serializable {

    /**
     * vratenie linkelistu pre cheap usera
     * @param person cheap user
     * @return linkedlist
     */
    public LinkedList getList(User person) {
        LinkedList<Car> cheapCars = new LinkedList<Car>();
        for (Car car : ((LinkedList<Car>)list)) {
            if(car instanceof ClassicCar) cheapCars.add(car);
        }
        return cheapCars;
    }
    public LinkedList getList(RichUser person) {
        return ((LinkedList<Car>)list);
    }

    public LinkedList getList(Provider person) {
        return ((LinkedList<Car>)list);
    }

    public void addClassicCar(Provider Owner, String MODEL, int YEAR, String COLOR, int START_PRICE, int CURRENT_PRICE, boolean EXPENSIVE){
        Owner.addCar((LinkedList<Car>)list , new ClassicCar(MODEL,YEAR,COLOR,START_PRICE,CURRENT_PRICE,false));
    }
    public void addClassicCar(Provider Owner, String MODEL, int YEAR, String COLOR, int START_PRICE, int CURRENT_PRICE, boolean EXPENSIVE,String details){
        Owner.addCar((LinkedList<Car>)list , new ClassicCar(MODEL,YEAR,COLOR,START_PRICE,CURRENT_PRICE,false,details));
    }
    public void addExpensiveCar(Provider Owner, String MODEL, int YEAR, String COLOR, int START_PRICE, int CURRENT_PRICE, boolean EXPENSIVE){
        Owner.addCar((LinkedList<Car>)list , new ExpensiveCar(MODEL,YEAR,COLOR,START_PRICE,CURRENT_PRICE,true));
    }
    public void addExpensiveCar(Provider Owner, String MODEL, int YEAR, String COLOR, int START_PRICE, int CURRENT_PRICE, boolean EXPENSIVE,String details){
        Owner.addCar((LinkedList<Car>)list , new ExpensiveCar(MODEL,YEAR,COLOR,START_PRICE,CURRENT_PRICE,true,details));
    }
    public void addVintage(Provider Owner, String MODEL, int YEAR, String COLOR, int START_PRICE, int CURRENT_PRICE, boolean EXPENSIVE){
        Owner.addCar((LinkedList<Car>)list , new Vintage(MODEL,YEAR,COLOR,START_PRICE,CURRENT_PRICE,true));
    }
    public void addVintage(Provider Owner, String MODEL, int YEAR, String COLOR, int START_PRICE, int CURRENT_PRICE, boolean EXPENSIVE, String details){
        Owner.addCar((LinkedList<Car>)list , new Vintage(MODEL,YEAR,COLOR,START_PRICE,CURRENT_PRICE,true,details));
    }
    public void addVeteran(Provider Owner, String MODEL, int YEAR, String COLOR, int START_PRICE, int CURRENT_PRICE, boolean EXPENSIVE){
        Owner.addCar((LinkedList<Car>)list , new Veteran(MODEL,YEAR,COLOR,START_PRICE,CURRENT_PRICE,true));
    }
    public void addVeteran(Provider Owner, String MODEL, int YEAR, String COLOR, int START_PRICE, int CURRENT_PRICE, boolean EXPENSIVE,String details){
        Owner.addCar((LinkedList<Car>)list , new Veteran(MODEL,YEAR,COLOR,START_PRICE,CURRENT_PRICE,true,details));
    }

    @Override
    public void run() {
        try {
            serialize("cars.out");
        } catch (IOException exception){
            exception.printStackTrace();
        }

    }
}
