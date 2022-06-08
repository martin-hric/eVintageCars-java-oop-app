package com.example.projekt.cars;

import java.io.Serializable;

/**
 * Abstraktna trieda auta, ktora ma v sebe vsetky potrebne udaje
 */
public abstract class Car implements ProductBeing, Serializable {
    protected String model;
    protected int year;
    protected String color;
    protected int starting_price;
    protected int current_price;
    protected boolean expensive;

    protected Details details;

    /**
     * Vhniezdena trieda s informaciami o aute
     */
    protected static class Details implements Serializable{
        private final String info;

        public Details(String INFO){
            this.info = INFO;
        }
        public String getDetails(){
            return info;
        }
    }

    protected Car(String Model,int Year,String Color,int Starting_price,int Current_price,boolean Expensive){
        this.model = Model;
        this.year = Year;
        this.color = Color;
        this.starting_price = Starting_price;
        this.current_price = Current_price;
        this.expensive = Expensive;
    }

    /**
     * overloading s inymi parametrami
     * @param Model model
     * @param Year rok
     * @param Color farba
     * @param Starting_price zac.cena
     * @param Current_price curr.cena
     * @param Expensive boolean drahost
     * @param details detaily
     */
    protected Car(String Model,int Year,String Color,int Starting_price,int Current_price,boolean Expensive,String details){
        this.model = Model;
        this.year = Year;
        this.color = Color;
        this.starting_price = Starting_price;
        this.current_price = Current_price;
        this.expensive = Expensive;
        this.details = new Details(details);
    }

    public String getDetails() {
        if (details != null)
            return details.getDetails();
        else
            return null;
    }

    public int getCurrent_price(){
        return current_price;
    }

    public void SetCurrentPrice(int price){
        this.current_price = price;
    }
}
