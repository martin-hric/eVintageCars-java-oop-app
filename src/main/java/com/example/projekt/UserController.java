package com.example.projekt;

import com.example.projekt.cars.Car;
import com.example.projekt.systems.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Handler pre uzivatela
 */
public class UserController extends SwitchingController {
    @FXML
    private ListView AllOffers;
    @FXML
    private Label signedBar;

    @FXML
    private TextField newPrice;

    public void setSignedBar(){
        signedBar.setText("Prihlaseny pouzivatel ID:" + auctionSystem.getAccountSystem().getUser().getOwner().getInfo());
    }
    @FXML
    public void initialize() {
        AllOffers.setCellFactory(px -> new ListCell<Object>() {
            @Override
            protected void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) {
                    setText(null);
                } else {
                    setText(((Car) item).getInfo());
                }
            }
        });
    }

    public void showMyOffer(){
        AllOffers.getItems().clear();
        AllOffers.getItems().addAll(auctionSystem.getAccountSystem().getUser().getOwner().getMyOffer(auctionSystem.getCarSystem()));
    }

    public void showDetails(){
        if(AllOffers.getSelectionModel().getSelectedItems().isEmpty()){
            new Alerts("Nevybrany model","Nevybral si ziadny model auta k akemu sa maju zobrazit detaily");
        } else{
            if(((Car)AllOffers.getSelectionModel().getSelectedItem()).getDetails() == null){
                new Alerts("Detaily o aute niesu uvedene v databaze", "Detaily o tomto aute neboli poskytnute od poskytovatela");
            } else{
                new Alerts("Detaily o aute",((Car)AllOffers.getSelectionModel().getSelectedItem()).getDetails());
            }
        }
    }

    boolean checkPrice(int price){
        int curr_price = ((Car)AllOffers.getSelectionModel().getSelectedItem()).getCurrent_price();
        if(price <= 0 || price <= curr_price) {return false;}
        return true;
    }
    public void setNewPrice() {
        if (AllOffers.getSelectionModel().getSelectedItems().isEmpty()) {
            new Alerts("Nevybrany model", "Nevybral si ziadny model auta k akemu chces nastavit aukcnu hodnotu");
        } else {
            try {
                int price = Integer.parseInt(newPrice.getText());
                if (checkPrice(price)) {
                    ((Car)AllOffers.getSelectionModel().getSelectedItem()).SetCurrentPrice(price);
                    auctionSystem.serializeSystems();
                    new Alerts("Uspech","Uspesne sme zaevidovali tvoju ponuku");
                } else {
                    new Alerts("Neuspech","Tvoja ponuka je prilis nizka");
                }
            } catch (NumberFormatException | InterruptedException e) {
                new Alerts("Nespravny format", "Zadaj cislo v spravnom formate [1 - MaxINT]");
            }
        }
    }
    public void logOut(ActionEvent e) throws InterruptedException {
        auctionSystem.serializeSystems();
        auctionSystem.getAccountSystem().getUser().logOutUser();
        changeScene("LogInView.fxml",e);
    }
}
