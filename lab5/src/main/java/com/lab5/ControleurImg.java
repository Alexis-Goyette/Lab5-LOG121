package com.lab5;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    private ModeleImg modeleImgMilieu;
    private ModeleImg modeleImgDroite;




    private ModeleImg modImg;
    private int indexCommand;
    private ICommand command;
    private static ControleurImg instanceUnique;
    private CopyPasteMediator mediator;
    private Image image;

    private ICommand translateCommandLeft = new TranslateCommand(modImg, TranslationDirection.LEFT);
    private ICommand translateCommandRight = new TranslateCommand(modImg, TranslationDirection.RIGHT);
    private ICommand translateCommandUp = new TranslateCommand(modImg, TranslationDirection.UP);
    private ICommand translateCommandDown = new TranslateCommand(modImg, TranslationDirection.DOWN);

    private ICommand zoomCommandIn = new ZoomCommand(modImg, ZoomDirection.IN);
    private ICommand zoomCommandOut = new ZoomCommand(modImg, ZoomDirection.OUT);
private void translateLeft() {
    btnGauche.setOnAction(e -> {
       translateCommandLeft.execute();

    });
    }
    private void translateRight() {
        btnGauche.setOnAction(e -> {
            translateCommandRight.execute();

        });
    }private void translateUp() {
        btnGauche.setOnAction(e -> {
            translateCommandUp.execute();

        });
    }private void translateDown() {
        btnGauche.setOnAction(e -> {
            translateCommandDown.execute();

        });
    }


    private ControleurImg(ModeleImg model) {
        modImg = model;
        instanceUnique = this;
    }

    public void TranslateImage(TranslationDirection.translationDirection direction) {
        switch (direction) {
            case LEFT:
                modImg.translateLeft();
                break;
            case RIGHT:
                modImg.translateRight();
                break;
            case UP:
                modImg.translateUp();
                break;
            case DOWN:
                modImg.translateDown();
                break;
        }
    }

    public void ZoomImage(ZoomDirection.zoomDirection direction) {
        switch (direction) {
            case IN:
                modImg.zoomIn();
                break;
            case OUT:
                modImg.zoomOut();
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
                new ModeleImg(imgViewOriginal);
                modeleImgMilieu = new ModeleImg(imgView1);
            }
        });
    }


}
