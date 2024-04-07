package com.lab5;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class InterfaceUtilisateur implements IObserver {
    public MenuButton getMbFichier() {
        return mbFichier;
    }

    public MenuButton getMbEdition() {
        return mbEdition;
    }

    public MenuButton getMbPP() {
        return mbPP;
    }

    public MenuItem getMiCI() {
        return miCI;
    }

    public ImageView getImgViewOriginal() {
        return imgViewOriginal;
    }

    public ImageView getImgView1() {
        return imgView1;
    }

    public ImageView getImgView2() {
        return imgView2;
    }

    public Button getBtnZoomIn() {
        return btnZoomIn;
    }

    public Button getBtnZoomOut() {
        return btnZoomOut;
    }

    public Button getBtnBas() {
        return btnBas;
    }

    public Button getBtnHaut() {
        return btnHaut;
    }

    public Button getBtnGauche() {
        return btnGauche;
    }

    public Button getBtnDroite() {
        return btnDroite;
    }



    @FXML
    private MenuButton mbFichier;
    @FXML
    private MenuButton mbEdition;
    @FXML
    private MenuButton mbPP;
    @FXML
    private MenuItem miCI;
    @FXML
    private ImageView imgViewOriginal;
    @FXML
    private ImageView imgView1;
    @FXML
    private ImageView imgView2;
    @FXML
    private Button btnZoomIn;
    @FXML
    private Button btnZoomOut;
    @FXML
    private Button  btnBas;
    @FXML
    private Button  btnHaut;

    @FXML
    private Button  btnGauche;

    @FXML
    private Button  btnDroite;


    private Image image;


    private ControleurImg controleur;
    public InterfaceUtilisateur() {
        ControleurImg controleur = ControleurImg.getInstance(this);
    }
    @FXML
    public void initialize() {
        miCI.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");

            // Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.png, *.jpeg)", "*.png", "*.jpeg");
            fileChooser.getExtensionFilters().add(extFilter);

            File file = fileChooser.showOpenDialog(miCI.getParentPopup().getScene().getWindow());
            if (file != null) {
                image = new Image(file.toURI().toString());
                imgViewOriginal.setImage(image);
                imgViewOriginal.setFitWidth(535); // Set the width
                imgViewOriginal.setFitHeight(500);
                imgView1.setImage(image);
                imgView1.setFitWidth(535); // Set the width
                imgView1.setFitHeight(500);
                imgView2.setImage(image);
                imgView2.setFitWidth(535); // Set the width
                imgView2.setFitHeight(500);
            }
        });
    }



    public void update() {
    }

    public void DisplayImage() {
    }

    public void Start(Stage primaryStage) {
    }
}