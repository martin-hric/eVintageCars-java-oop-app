package com.example.projekt;

import com.example.projekt.cars.Car;
import com.example.projekt.systems.Alerts;
import com.example.projekt.users.Provider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Kontroller pre poskytovatela
 */
public class ProviderController extends SwitchingController{
    @FXML
    private ListView AllOffers;
    @FXML
    private Label signedBar;
    @FXML
    private TextField newPrice , model , year , start_price , details, color;
    @FXML
    private ChoiceBox<String> type;

    /**
     * setup hornej listy s info
     */
    public void setSignedBar(){
        signedBar.setText("PRIHLASENY->" + auctionSystem.getAccountSystem().getUser().getOwner().getInfo());
    }

    /**
     * Upravuje vypis objektov na Text ktory sa v nich nachadza
     */
    @FXML
    public void initialize() {
        try {
            type.getItems().addAll("Klasicke auto","Drahe auto","Vintage","Veteran");
        } catch (NullPointerException e){
            System.out.println("Osetreny error pri prehadzovani stages");
        }
        try {
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
     * pomocna funkcia pri urcovani spravnosti zadanej ceny, osetruje sa ci cena je rovnaka alebo mensia ako je zadana pri konkretnom aute
     * @param price cena auta ktoru pridelil uchadzac
     * @return true alebo false na zaklade spravnosti
     */
    boolean checkPrice(int price){
        int curr_price = ((Car)AllOffers.getSelectionModel().getSelectedItem()).getCurrent_price();
        if(price <= 0 || price <= curr_price) {return false;}
        return true;
    }

    /**
     * Zaevidovanie novej current ceny pre auto
     */
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

    /**
     * Zmena screenu na pridanie noveho auta
     * @param event event
     */
    public void addNewAuction(ActionEvent event){
        changeScene("NewBidView.fxml", event);
    }
    /**
     * Pridanie noveho auta do aukcie
     * @param event event
     */
    public void addAuction(ActionEvent event) throws InterruptedException {
        if (model.getText().equals("") || year.getText().equals("") || start_price.getText().equals("") || type.getSelectionModel().isEmpty() || color.getText().equals("")) {
            new Alerts("Nevyplnene udaje!", "Neboli vyplnene vsetky potrebne udaje k pridaniu novej aukcie");
        } else {
            try {
                int my_id = auctionSystem.getAccountSystem().getUser().getOwner().getID();
                int price = Integer.parseInt(start_price.getText());
                int rok = Integer.parseInt(year.getText());
                switch (type.getSelectionModel().getSelectedItem()) {
                    case ("Klasicke auto") -> {
                        if(details.getText().equals("")){
                            auctionSystem.getCarSystem().addClassicCar(((Provider)auctionSystem.getAccountSystem().findAccountID(my_id).getOwner()),model.getText(),rok,color.getText(),price,price,false);
                        } else{
                            auctionSystem.getCarSystem().addClassicCar(((Provider)auctionSystem.getAccountSystem().findAccountID(my_id).getOwner()),model.getText(),rok,color.getText(),price,price,false,details.getText());
                        }}
                    case ("Drahe auto") -> {
                        if(details.getText().equals("")){
                            auctionSystem.getCarSystem().addExpensiveCar(((Provider)auctionSystem.getAccountSystem().findAccountID(my_id).getOwner()),model.getText(),rok,color.getText(),price,price,false);
                        } else{
                            auctionSystem.getCarSystem().addExpensiveCar(((Provider)auctionSystem.getAccountSystem().findAccountID(my_id).getOwner()),model.getText(),rok,color.getText(),price,price,false,details.getText());
                        }}
                    case ("Vintage") -> {
                        if(details.getText().equals("")){
                            auctionSystem.getCarSystem().addVintage(((Provider)auctionSystem.getAccountSystem().findAccountID(my_id).getOwner()),model.getText(),rok,color.getText(),price,price,false);
                        } else{
                            auctionSystem.getCarSystem().addVintage(((Provider)auctionSystem.getAccountSystem().findAccountID(my_id).getOwner()),model.getText(),rok,color.getText(),price,price,false,details.getText());
                        }}
                    case ("Veteran") -> {
                        if(details.getText().equals("")){
                            auctionSystem.getCarSystem().addVeteran(((Provider)auctionSystem.getAccountSystem().findAccountID(my_id).getOwner()),model.getText(),rok,color.getText(),price,price,false);
                        } else{
                            auctionSystem.getCarSystem().addVeteran(((Provider)auctionSystem.getAccountSystem().findAccountID(my_id).getOwner()),model.getText(),rok,color.getText(),price,price,false,details.getText());
                        }}}
                auctionSystem.serializeSystems();
                new Alerts("Uspesne pridana aukcia!","Vasa aukcia bola uspesne pridana!");
                changeScene("ProviderView.fxml",event);
            } catch (NumberFormatException e){
                new Alerts("Nespravny format","Zadali ste udaje ROK alebo CENA v nespravnom formate");
            }
        }
    }

    /**
     * tlacidlo spat
     * @param actionEvent event
     */
    public void back(ActionEvent actionEvent){
        changeScene("ProviderView.fxml",actionEvent);
    }

    /**
     * odhlasenie uzivatela
     * @param e event
     * @throws InterruptedException exception
     */
    public void logOut(ActionEvent e) throws InterruptedException {
        auctionSystem.serializeSystems();
        auctionSystem.getAccountSystem().getUser().logOutUser();
        changeScene("LogInView.fxml",e);
    }
}
