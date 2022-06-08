package com.example.projekt.systems;

import com.example.projekt.users.Admin;
import com.example.projekt.users.Provider;
import java.io.*;

/**
 * Spaja, serializuje a deserializuje oba systemy aut, uctov
 */
public class AuctionSystem {

    /**
     * Agregacia
     */
    private final CarSystem carSystem = new CarSystem();
    private final AccountSystem accountSystem = new AccountSystem();

    public AuctionSystem() throws InterruptedException {

/*
        accountSystem.addAdmin("Martin","xhricm","adminheslo");
        accountSystem.addProvider("Samo","xbagar","lucka");
        accountSystem.addRichUser("Andrej","xkorchy","sandra");
        accountSystem.addCheapUser("Adrian","xbilek","chiarka");
        Provider provider = (Provider)accountSystem.findAccountName("Samo").getOwner();
        carSystem.addExpensiveCar(provider,"Audi",1950,"cierna",3200,3800,true,"Zachovale dobry stav");
        carSystem.addClassicCar(provider,"BMW e46",1998,"strieborna",2000,2500,false,"Detaily o tomto aute: nestartuje");
        carSystem.addVintage(provider,"Mercedes C63",1820,"bordova",65000,98000,true,"Od roku 2005 nepouzivane na SK cestach, len na vystavach publikovane");
        carSystem.addVeteran(provider,"Jaguar s600",1226,"cierna",120000,162000,true);
        carSystem.addClassicCar(provider,"Tesla 3",2012,"biela",20000,20000,false,"Elektricke auto pre nenarocnych pouzivatelov");
        carSystem.addClassicCar(provider,"Skoda Favorit",1992,"zelena",1925,2100,false,"Stara skodovka z roku 92, este ide v pohode");
        carSystem.addClassicCar(provider,"Volkswagen",1999,"cierna metaliza",2100,2100,false,"Výškovo nastavitelné sedadlo vodica, Výškovo nastavitelné sedadlo spolujazdca, Bocné airbagy vpredu, Zásuvka 12 V ");

        serializeSystems();
*/
        deserializeSystems();
    }

    public AccountSystem getAccountSystem(){
        return accountSystem;
    }

    public CarSystem getCarSystem(){
        return carSystem;
    }

    /**
     * pouzitie multithreading na serializaciu oboch systemov
     * @throws InterruptedException
     */
    public void serializeSystems() throws InterruptedException {
        Thread account = new Thread(accountSystem,"System uctov");
        Thread car = new Thread(carSystem,"System aut");
        account.start();
        car.start();
        account.join();
        car.join();
    }

    public void deserializeSystems(){
        try {
            accountSystem.deserialize("accounts.out");
            carSystem.deserialize("cars.out");
        }
        catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
