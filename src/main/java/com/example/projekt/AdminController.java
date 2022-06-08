package com.example.projekt;

import com.example.projekt.cars.Car;
import com.example.projekt.systems.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.Iterator;

/**
 *  Kontroller pre AdminView.fxml, poskytuje zakladne cinnosti admina
 */
public class AdminController extends SwitchingController{
    @FXML
    private ListView AllOffers;
    @FXML
    private Label signedBar;

    /**
     * Horna lista, kde je uvedene ID, Meno a Typ kto je prihlaseny
     */
    public void setSignedBar(){
        signedBar.setText("PRIHLASENY ID:" + auctionSystem.getAccountSystem().getUser().getOwner().getInfo());
    }

    /**
     * Upravuje vypis objektov na Text ktory sa v nich nachadza
     */
    @FXML
    public void initialize() {
        try{
            AllOffers.setCellFactory(param -> new ListCell<Object>() {
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
        } catch (NullPointerException exception){
            System.out.println("Osetreny error pri prehadzovani stages");
        }
    }

    /**
     * Refresh v listView dostupnych aukcii aut
     */
    public void showMyOffer(){
        AllOffers.getItems().clear();
        AllOffers.getItems().addAll(auctionSystem.getAccountSystem().getUser().getOwner().getMyOffer(auctionSystem.getCarSystem()));
    }

    /**
     * Zobrazenie detailov o aute v alerte, pokial boli poskytnute
     */
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

    /**
     * Zmazanie zaznamu z LinkedListu a serializacia systemu
     * @throws InterruptedException exception
     */
    public void delete() throws InterruptedException {
        if (AllOffers.getSelectionModel().getSelectedItems().isEmpty()) {
            new Alerts("Nevybrany model", "Nevybral si ziadny model auta aky sa ma zmazat");
        } else{
            for(Iterator iterator = auctionSystem.getCarSystem().getListAdmin().iterator(); iterator.hasNext();){
                Object curr = iterator.next();
                if(curr.equals(AllOffers.getSelectionModel().getSelectedItems().get(0))){
                    iterator.remove();
                    auctionSystem.serializeSystems();
                    new Alerts("Uspech","Uspesne zmazana aukcia");
                }
            }
        }
    }

    /**
     * Odhlasenie uzivatela
     * @param e actionevent
     * @throws InterruptedException
     */
    public void logOut(ActionEvent e) throws InterruptedException {
        auctionSystem.serializeSystems();
        auctionSystem.getAccountSystem().getUser().logOutUser();
        changeScene("LogInView.fxml",e);
    }
}
