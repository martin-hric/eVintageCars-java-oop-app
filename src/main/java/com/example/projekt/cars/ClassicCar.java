package com.example.projekt.cars;

import java.io.Serializable;

/**
 * Trieda aut dostupna aj pre Cheap usera
 */
public class ClassicCar extends Car implements Serializable,ProductBeing {

    public ClassicCar(String Model, int Year, String Color, int Starting_price, int Current_price, boolean Expensive) {
        super(Model, Year, Color, Starting_price, Current_price, Expensive);
    }

    public ClassicCar(String Model, int Year, String Color, int Starting_price, int Current_price, boolean Expensive,String details ) {
        super(Model, Year, Color, Starting_price, Current_price, Expensive,details);
    }

    @Override
    public String getDetails() {
        return super.getDetails();
    }

    @Override
    public String getInfo() {
        return String.format("Klasicke auto MODEL-> %s ROK VYROBY-> %d FARBA-> %s POCIATOCNA CENA-> %d MOMENTALNE NASTAVENA CENA-> %d",this.model,this.year,this.color,this.starting_price,this.current_price);
    }
}
