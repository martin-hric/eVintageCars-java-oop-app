package com.example.projekt;

import com.example.projekt.systems.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Kontroller pre prihlasovanie
 */
public class LogInController extends SwitchingController {
    @FXML
    private TextField login;
    @FXML
    private TextField password;

    /**
     * Osetrenie udajov a zmena screenu na zaklade typu uzivatela
     * @param event event
     */
    @FXML
    private void clicked_logIn(ActionEvent event){
        System.out.println("bol stlaceny login s udajmi:");
        System.out.println("Meno:" + login.getText());
        System.out.println("Heslo:"+ password.getText());
        if(login.getText().equals("") || password.getText().equals("")){
            new Alerts("Nevyplnene udaje","Neboli vyplnene vsetky potrebne udaje k prihlaseniu");
        }
        if (auctionSystem.getAccountSystem().logInUser(login.getText(),password.getText())) {
            switch (auctionSystem.getAccountSystem().getUser().getOwner().getType()) {
                case ("Admin") -> {
                    changeScene("AdminView.fxml", event);
                }
                case ("Provider") -> {
                    changeScene("ProviderView.fxml", event);
                }
                case ("User") -> {
                    changeScene("BidView.fxml", event);
                }
            }
        } else{
            new Alerts("Nespravne zadane udaje","Prihlasovacie meno alebo heslo su nespravne");
        }

    }

    /**
     * zmena screenu na registraciu
     * @param event event
     */
    @FXML
    private void clicked_register(ActionEvent event){
        changeScene("RegisterView.fxml",event);

    }

    /**
     * ukoncenie programu
     */
    @FXML
    private void exit(){
        window.close();
        System.exit(0);
    }

}
