package com.lab5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RightClickMenu extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage arg0) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItem1 = new MenuItem("Copier");
        MenuItem menuItem2 = new MenuItem("Coller");

        contextMenu.getItems().addAll(menuItem1, menuItem2);

        TextArea textArea = new TextArea();

        textArea.setContextMenu(contextMenu);

        VBox vBox = new VBox(textArea);
        Scene scene = new Scene(vBox);

        arg0.setScene(scene);
        arg0.setTitle("JavaFX App");

        arg0.show();
    }
}
