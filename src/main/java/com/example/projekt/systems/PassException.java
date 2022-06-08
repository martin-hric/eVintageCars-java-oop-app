package com.example.projekt.systems;

/**
 * extenduje exception s vypisom pre alerty na osetrovanie vstupu hesla
 */
public class PassException extends Exception {
    private final String output;

    public PassException(){
        this.output = "Nespravne zadane heslo! Heslo musi obsahovat 10 znakov, z toho  minimalne 1 cislo a 1 velke pismeno!";
    }

    public String getOutput(){
        return this.output;
    }
}
