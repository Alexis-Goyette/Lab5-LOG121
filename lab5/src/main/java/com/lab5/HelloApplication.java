package com.lab5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItem1 = new MenuItem("Copier");
        MenuItem menuItem2 = new MenuItem("Coller");
        contextMenu.getItems().addAll(menuItem1, menuItem2);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1650, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
       
    }

    public static void main(String[] args) {
        launch();
    }
}