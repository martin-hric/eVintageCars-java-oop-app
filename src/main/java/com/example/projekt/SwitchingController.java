package com.example.projekt;

import com.example.projekt.systems.AuctionSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * zakladny kontroller na transfer dat a zmenu sceny
 */
public class SwitchingController {

    protected AuctionSystem auctionSystem;
    protected Stage window;

    public void transfer(AuctionSystem system,Stage new_window){
        this.auctionSystem = system;
        this.window = new_window;
    }

    public void changeScene(String scene,ActionEvent actionEvent){
        URL fxmlLocation = getClass().getResource(scene);
        FXMLLoader fxmlloader = new FXMLLoader(fxmlLocation);
        Parent root = null;
        try {
            root = fxmlloader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SwitchingController controller = fxmlloader.getController();
        controller.transfer(this.auctionSystem,this.window);

        switch (scene) {
            case ("BidView.fxml") -> {
                ((UserController) controller).setSignedBar();
            }
            case ("AdminView.fxml") -> {
                ((AdminController) controller).setSignedBar();
            }
            case ("ProviderView.fxml") -> {
                ((ProviderController) controller).setSignedBar();
            }
            default -> {
            }
        }

        assert root != null;
        Scene scena = new Scene(root);
        window.setScene(scena);
        window.show();
}
}
