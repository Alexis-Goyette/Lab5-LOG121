package com.lab5;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

public class InterfaceUtilisateur implements IObserver {

    @FXML
    private MenuButton mbFichier;
    @FXML
    private MenuButton mbEdition;
    @FXML
    private MenuButton mbPP;
    @FXML
    private MenuItem miCI;
    @FXML
    private MenuItem miSauvergarderPerspective;
    @FXML
    private MenuItem miCP;
    @FXML
    private MenuItem miUndo;
    @FXML
    private MenuItem miRedo;
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
    private Button btnBas;
    @FXML
    private Button btnHaut;
    @FXML
    private MenuItem miChoisirStrat;

    @FXML
    private Button btnGauche;

    @FXML
    private Button btnDroite;

    @FXML
    private MenuItem mICopierImg1;
    @FXML
    private MenuItem mICopierImg2;
    @FXML
    private MenuItem mICollerImg1;
    @FXML
    private MenuItem mICollerImg2;

    @FXML
    private ScrollPane scrollPane1;
    @FXML
    private ScrollPane scrollPane2;

    private Image image;

    private ControleurImg controleur;

    public InterfaceUtilisateur() {

    }

    @FXML
    public void initialize() {
        miSauvergarderPerspective.setDisable(true);
        miCP.setDisable(true);
        miChoisirStrat.setDisable(true);
        miUndo.setDisable(true);
        miRedo.setDisable(true);
        miCI.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            // Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.png, *.jpeg)",
                    "*.png", "*.jpeg");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(miCI.getParentPopup().getScene().getWindow());
            if (file != null) {
                image = new Image(file.toURI().toString());
                miSauvergarderPerspective.setDisable(false);
                miCP.setDisable(false);
                miChoisirStrat.setDisable(false);
                setImage(imgViewOriginal, image);
                setImage(imgView1, image);
                setImage(imgView2, image);
                controleur = ControleurImg.getInstance(this);
                // appelle les méthodes du controleur pour instancier les eventHandler
                controleur.translateDown();
                controleur.translateUp();
                controleur.translateLeft();
                controleur.translateRight();
                controleur.zoomIn();
                controleur.zoomOut();
                controleur.selectionementImgV1();
                controleur.selectionementImgV2();
                controleur.Undo();
                controleur.Redo();
                controleur.ChoixStrat();
                controleur.saveAs();
                controleur.CopyMilieu();
                controleur.CopyDroite();
                controleur.PasteMilieu();
                controleur.PasteDroite();
            }
        });
        miCP.setOnAction(e -> controleur.chargerPerspective());
    }

    public void setImage(ImageView imageView, Image image) {
        imageView.setImage(image);
        imageView.setFitWidth(535); // Set the width
        imageView.setFitHeight(500);
    }

    public void update(ModeleImg modele) {
        modele.getImageView().setTranslateX(modele.getXTranslationValue());
        modele.getImageView().setTranslateY(modele.getYTranslationValue());
        modele.getImageView().setScaleX(modele.getZoomFactor());
        modele.getImageView().setScaleY(modele.getZoomFactor());
        miRedo.setDisable(modele.getUndoStack().isEmpty());
        miUndo.setDisable(modele.getMementoStack().isEmpty());
    }
    // tout les méthodes get pour que les autres classes aie accèes aux éléments de
    // l'interface
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

    public MenuItem getMiSauvergarderPerspective() {
        return miSauvergarderPerspective;
    }

    public MenuItem getMiCP() {
        return miCP;
    }

    public MenuItem getMiUndo() {
        return miUndo;
    }

    public MenuItem getMiRedo() {
        return miRedo;
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

    public ScrollPane getScrollPane1() {
        return scrollPane1;
    }

    public ScrollPane getScrollPane2() {
        return scrollPane2;
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

    public MenuItem getMiChoisirStrat() {
        return miChoisirStrat;
    }

    public MenuItem getMICopierImg1() {
        return mICopierImg1;
    }

    public MenuItem getMICopierImg2() {
        return mICopierImg2;
    }

    public MenuItem getMICollerImg1() {
        return mICollerImg1;
    }

    public MenuItem getMICollerImg2() {
        return mICollerImg2;
    }
}