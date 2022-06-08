module com.example.projekt {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.projekt to javafx.fxml;
    exports com.example.projekt;
    exports com.example.projekt.users;
    opens com.example.projekt.users to javafx.fxml;
    exports com.example.projekt.systems;
    opens com.example.projekt.systems to javafx.fxml;
    exports com.example.projekt.cars;
    opens com.example.projekt.cars to javafx.fxml;
}