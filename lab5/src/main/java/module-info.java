module com.lab5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;

    opens com.lab5 to javafx.fxml;

    exports com.lab5;
}
