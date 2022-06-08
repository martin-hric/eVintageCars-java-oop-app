package com.example.projekt.systems;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerts {
    /**
     * Jednoduchy alert system na zobrazovanie alertov pre uzivatelov rozneho typu
     * @param title titul aky dany alert bude mat
     * @param message samotny obsah alertu
     */
    public Alerts(String title,String message){
        Alert a = new Alert(AlertType.WARNING);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(message);
        a.show();
    }
}
