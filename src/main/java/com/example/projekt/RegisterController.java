package com.example.projekt;

import com.example.projekt.systems.PassCheck;
import com.example.projekt.systems.PassException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import com.example.projekt.systems.Alerts;

/**
 * Handler pre registraciu
 */
public class RegisterController extends SwitchingController {
    @FXML
    private TextField name , login, pass;
    @FXML
    private ChoiceBox<String> type;

    /**
     * inicializovanie choiceboxu
     */
    @FXML
    public void initialize() {
        type.getItems().addAll("Zadavatel", "Administrator", "Uchadzac","Bohaty uchadzac");

    }

    /**
     * zaregistrovanie uzivatela s osetrenim vstupu a debug vypismi
     * @param actionEvent
     * @throws InterruptedException
     */
    public void clicked_registerButton(ActionEvent actionEvent) throws InterruptedException{
        System.out.println("bol stlaceny register s udajmi:");
        System.out.println(name.getText());
        System.out.println(login.getText());
        System.out.println(pass.getText());
        System.out.println(type.getSelectionModel().getSelectedItem());
        if (name.getText().equals("") || login.getText().equals("") || pass.getText().equals("") || type.getSelectionModel().isEmpty()) {
            new Alerts("Nevyplnene udaje!","Neboli vyplnene vsetky potrebne udaje k registracii");
        } else {
            if (auctionSystem.getAccountSystem().existsUser(login.getText())){
                new Alerts("Login uz existuje","Prihlasovacie meno uz existuje, zvolte prosim ine!");
                return;
            }
            PassCheck passCheck = new PassCheck();
            try {
                passCheck.passCheck(pass.getText());
            }catch (PassException passException){
                new Alerts("Nespravne zadane heslo!",passException.getOutput());
                return;
            }
            switch (type.getSelectionModel().getSelectedItem()) {
                case ("Zadavatel") -> {
                    auctionSystem.getAccountSystem().addProvider(name.getText(), login.getText(), pass.getText());
                }
                case ("Administrator") -> {
                    auctionSystem.getAccountSystem().addAdmin(name.getText(), login.getText(), pass.getText());
                }
                case ("Uchadzac") -> {
                    auctionSystem.getAccountSystem().addCheapUser(name.getText(), login.getText(), pass.getText());
                }
                case ("Bohaty uchadzac") -> {
                    auctionSystem.getAccountSystem().addRichUser(name.getText(), login.getText(), pass.getText());
                }
            }
            auctionSystem.serializeSystems();
            new Alerts("Konto vytvorene","Vase konto bolo uspesne vytvorene!");
            changeScene("LogInView.fxml",actionEvent);

        }
    }
    public void back(ActionEvent actionEvent){
        changeScene("LogInView.fxml",actionEvent);
    }
}


