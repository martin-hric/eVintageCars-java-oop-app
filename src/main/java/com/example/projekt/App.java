package com.example.projekt;

import com.example.projekt.systems.AuctionSystem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Objects;


public class App extends Application{
    /**
     * Zakladne spustenie aplikacie a nacitanie LogInScreenu
     * @param stage window
     * @throws IOException ..
     */
    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LogInView.fxml"));
        Parent root = fxmlLoader.load();
        LogInController logInController = fxmlLoader.getController();
        logInController.transfer(new AuctionSystem(),stage);

        stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("logo.jpg"))));
        stage.setTitle("eVintageCars");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
