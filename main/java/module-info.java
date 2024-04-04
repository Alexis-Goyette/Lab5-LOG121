module org.example.labo05 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.labo05 to javafx.fxml;
    exports org.labo05;
}