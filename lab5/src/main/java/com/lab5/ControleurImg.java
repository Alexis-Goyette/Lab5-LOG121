package com.lab5;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.scene.image.ImageView;
import java.io.File;

public class ControleurImg {
    public ControleurImg() {

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

    private ModeleImg modImg;
    private int indexCommand;
    private ICommand command;
    private static ControleurImg instanceUnique;
    private CopyPasteMediator mediator;
    private Image image;

    private ControleurImg(ModeleImg model) {
        modImg = model;
        instanceUnique = this;
    }

    public void TranslateImage(TranslationDirection.translationDirection direction) {
        switch (direction) {
            case LEFT:
                modImg.TranslateLeft();
                break;
            case RIGHT:
                modImg.TranslateRight();
                break;
            case UP:
                modImg.TranslateUp();
                break;
            case DOWN:
                modImg.TranslateDown();
                break;
        }
    }

    public void ZoomImage(ZoomDirection.zoomDirection direction) {
        switch (direction) {
            case IN:
                modImg.ZoomIn();
                break;
            case OUT:
                modImg.ZoomOut();
                break;
        }
    }

    public void SaveImage() {
    }

    public void SaveAs() {
    }

    public void Undo() {
    }

    public static ControleurImg GetInstance() {
        return instanceUnique;
    }

    public void CopyParams(ModeleImg source, ICopyStrategy strategy) {
    }

    public void PasteParams(ModeleImg source, ModeleImg destination) {
    }
    // je suis pas sûr si le type de "source" et "destination" est bon,
    // il était pas marqué dans l'uml, à changer au besoin

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


}
