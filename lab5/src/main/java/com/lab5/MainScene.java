package com.lab5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;

public class MainScene extends Application {

    private Image image;

    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        Scene scene = new Scene(grid, 650, 480);
        stage.setScene(scene);

        Text textModifImg = new Text("Modificateur d'image");
        textModifImg.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        textModifImg.setUnderline(true);
        grid.add(textModifImg, 0, 0);

        Text textImg = new Text("Ajouter une image à modifier:");
        textImg.setFont(Font.font("Arial", 20));
        grid.add(textImg, 0, 8);

        Button ajoutImg = new Button("Ajouter");
        grid.add(ajoutImg, 0, 10);

        ajoutImg.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                image = new Image(file.toURI().toString());
                ImageView imageView = new ImageView(image);
                grid.add(imageView, 0, 15);
            }

        });

        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
