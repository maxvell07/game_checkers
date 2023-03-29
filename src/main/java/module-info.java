module com.example.game_dem1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.game_dem1 to javafx.fxml;
    exports com.example.game_dem1;
}