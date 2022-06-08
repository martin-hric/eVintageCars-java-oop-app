package com.example.projekt.cars;

import java.io.Serializable;

/**
 * Trieda aut uz nedostupna cheap userovi
 */
public class ExpensiveCar extends Car implements ProductBeing, Serializable {

    public ExpensiveCar(String Model, int Year, String Color, int Starting_price, int Current_price, boolean Expensive) {
        super(Model, Year, Color, Starting_price, Current_price, Expensive);
    }

    public ExpensiveCar(String Model, int Year, String Color, int Starting_price, int Current_price, boolean Expensive,String details ) {
        super(Model, Year, Color, Starting_price, Current_price, Expensive,details);
    }

    @Override
    public String getInfo() {
        return String.format("Drahe auto : MODEL-> %s ROK VYROBY-> %d FARBA-> %s POCIATOCNA CENA-> %d MOMENTALNE NASTAVENA CENA-> %d",this.model,this.year,this.color,this.starting_price,this.current_price);
    }

    @Override
    public String getDetails() {
        return super.getDetails();
    }
}
