module org.example.labo05 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.labo05 to javafx.fxml;
    exports org.example.labo05;
}