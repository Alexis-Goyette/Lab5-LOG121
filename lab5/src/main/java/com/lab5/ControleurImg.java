package com.lab5;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class ControleurImg {
    public ControleurImg() {

    }

    private static ControleurImg instanceUnique;

    private Button btnGauche;
    private Button btnDroite;
    private Button btnHaut;
    private Button btnBas;
    private Button btnZoomIn;
    private Button btnZoomOut;
    private MenuItem miSauvergarderPerspective;
    private MenuItem miUndo;
    private MenuItem miRedo;
    private MenuItem miCP;
    private MenuItem miChoisirStrat;

    private MenuItem mICopierImg1;
    private MenuItem mICopierImg2;
    private MenuItem mICollerImg1;
    private MenuItem mICollerImg2;

    private ModeleImg modeleImgMilieu;
    private ModeleImg modeleImgDroite;

    private static ImageView imgView1;

    private static ImageView imgView2;

    private ScrollPane scrollPane1;

    private ScrollPane scrollPane2;

    InterfaceUtilisateur interfaceUtilisateur;

    private ModeleImg modeleImgSelectionne;

    private ICommand translateCommandLeftModImg1;
    private ICommand translateCommandRightModImg1;
    private ICommand translateCommandUpModImg1;
    private ICommand translateCommandDownModImg1;

    private ICommand zoomCommandInModImg1;
    private ICommand zoomCommandOutModImg1;

    private ICommand translateCommandLeftModImg2;
    private ICommand translateCommandRightModImg2;
    private ICommand translateCommandUpModImg2;
    private ICommand translateCommandDownModImg2;

    private ICommand zoomCommandInModImg2;
    private ICommand zoomCommandOutModImg2;

    private ICommand undoCommandImg1;
    private ICommand undoCommandImg2;
    private ICommand redoCommandImg1;
    private ICommand redoCommandImg2;
    private ICommand saveAsCommandImg1;
    private ICommand saveAsCommandImg2;

    private ImgMemento perspectiveSauvegardé;

    private String[] elementACopierChoix = { "Translation seulement", "Grandissement seulement",
            "Translation et Grandissement" };
    private String elementsACopier = "Translation seulement";

    private ControleurImg(InterfaceUtilisateur interfaceUtilisateur) {
        // Aller chercher les rfrances aux diffrents boutons et modèles des images
        this.interfaceUtilisateur = interfaceUtilisateur;
        btnGauche = interfaceUtilisateur.getBtnGauche();
        btnDroite = interfaceUtilisateur.getBtnDroite();
        btnHaut = interfaceUtilisateur.getBtnHaut();
        btnBas = interfaceUtilisateur.getBtnBas();
        btnZoomIn = interfaceUtilisateur.getBtnZoomIn();
        btnZoomOut = interfaceUtilisateur.getBtnZoomOut();
        miSauvergarderPerspective = interfaceUtilisateur.getMiSauvergarderPerspective();
        miUndo = interfaceUtilisateur.getMiUndo();
        miRedo = interfaceUtilisateur.getMiRedo();
        miCP = interfaceUtilisateur.getMiCP();
        miChoisirStrat = interfaceUtilisateur.getMiChoisirStrat();
        perspectiveSauvegardé = new ImgMemento(1f, 1f, 1f);
        mICopierImg1 = interfaceUtilisateur.getMICopierImg1();
        mICopierImg2 = interfaceUtilisateur.getMICopierImg2();
        mICollerImg1 = interfaceUtilisateur.getMICollerImg1();
        mICollerImg2 = interfaceUtilisateur.getMICollerImg2();

        imgView1 = interfaceUtilisateur.getImgView1();
        imgView2 = interfaceUtilisateur.getImgView2();

        scrollPane1 = interfaceUtilisateur.getScrollPane1();
        scrollPane2 = interfaceUtilisateur.getScrollPane2();

        this.modeleImgMilieu = new ModeleImg(imgView1, interfaceUtilisateur);
        this.modeleImgDroite = new ModeleImg(imgView2, interfaceUtilisateur);

        // instanciation des commandes
        translateCommandLeftModImg1 = new TranslateCommand(modeleImgMilieu, TranslationDirection.LEFT);
        translateCommandRightModImg1 = new TranslateCommand(modeleImgMilieu, TranslationDirection.RIGHT);
        translateCommandUpModImg1 = new TranslateCommand(modeleImgMilieu, TranslationDirection.UP);
        translateCommandDownModImg1 = new TranslateCommand(modeleImgMilieu, TranslationDirection.DOWN);

        zoomCommandInModImg1 = new ZoomCommand(modeleImgMilieu, ZoomDirection.IN);
        zoomCommandOutModImg1 = new ZoomCommand(modeleImgMilieu, ZoomDirection.OUT);

        undoCommandImg1 = new UndoCommand(modeleImgMilieu);
        redoCommandImg1 = new RedoCommand(modeleImgMilieu);

        translateCommandLeftModImg2 = new TranslateCommand(modeleImgDroite, TranslationDirection.LEFT);
        translateCommandRightModImg2 = new TranslateCommand(modeleImgDroite, TranslationDirection.RIGHT);
        translateCommandUpModImg2 = new TranslateCommand(modeleImgDroite, TranslationDirection.UP);
        translateCommandDownModImg2 = new TranslateCommand(modeleImgDroite, TranslationDirection.DOWN);

        zoomCommandInModImg2 = new ZoomCommand(modeleImgDroite, ZoomDirection.IN);
        zoomCommandOutModImg2 = new ZoomCommand(modeleImgDroite, ZoomDirection.OUT);

        undoCommandImg2 = new UndoCommand(modeleImgDroite);
        redoCommandImg2 = new RedoCommand(modeleImgDroite);
        saveAsCommandImg1 = new SaveAsCommand(modeleImgMilieu, scrollPane1);
        saveAsCommandImg2 = new SaveAsCommand(modeleImgDroite, scrollPane2);

        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItem1 = new MenuItem("Copier");
        MenuItem menuItem2 = new MenuItem("Coller");
        contextMenu.getItems().addAll(menuItem1, menuItem2);

    }

    public static ControleurImg getInstance(InterfaceUtilisateur interfaceUtilisateur) {
        if (instanceUnique == null) {
            instanceUnique = new ControleurImg(interfaceUtilisateur);
        }
        return instanceUnique;
    }

    @FXML
    public void selectionementImgV1() {
        imgView1.setOnMouseClicked(e -> {
            modeleImgSelectionne = modeleImgMilieu;
        });
    }

    @FXML
    public void selectionementImgV2() {
        imgView2.setOnMouseClicked(e -> {
            modeleImgSelectionne = modeleImgDroite;
        });
    }

    @FXML
    public void translateLeft() {
        btnGauche.setOnAction(e -> {
            if (modeleImgSelectionne == modeleImgMilieu)
                translateCommandLeftModImg1.execute();
            else
                translateCommandLeftModImg2.execute();
        });
    }

    @FXML
    public void translateRight() {
        btnDroite.setOnAction(e -> {
            if (modeleImgSelectionne == modeleImgMilieu)
                translateCommandRightModImg1.execute();
            else
                translateCommandRightModImg2.execute();
        });
    }

    @FXML
    public void translateDown() {
        btnBas.setOnAction(e -> {
            if (modeleImgSelectionne == modeleImgMilieu)
                translateCommandDownModImg1.execute();
            else
                translateCommandDownModImg2.execute();
        });
    }

    @FXML
    public void translateUp() {
        btnHaut.setOnAction(e -> {
            if (modeleImgSelectionne == modeleImgMilieu)
                translateCommandUpModImg1.execute();
            else
                translateCommandUpModImg2.execute();
        });
    }

    @FXML
    public void zoomIn() {
        btnZoomIn.setOnAction(e -> {
            if (modeleImgSelectionne == modeleImgMilieu)
                zoomCommandInModImg1.execute();
            else
                zoomCommandInModImg2.execute();
        });
    }

    @FXML
    public void zoomOut() {
        btnZoomOut.setOnAction(e -> {
            if (modeleImgSelectionne == modeleImgMilieu)
                zoomCommandOutModImg1.execute();
            else
                zoomCommandOutModImg2.execute();
        });
    }

    @FXML
    public void saveAs() {
        miSauvergarderPerspective.setOnAction(e -> {
            if (modeleImgSelectionne == modeleImgMilieu)
                saveAsCommandImg1.execute();
            else
                saveAsCommandImg2.execute();
        });
    }

    public void chargerPerspective() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Perspective File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Perspective Files", "*.perspective"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            modeleImgMilieu.getImgView().setImage(image);
            modeleImgDroite.getImgView().setImage(image);
            modeleImgMilieu.resetValues();
            modeleImgDroite.resetValues();
        }
    }

    public void Undo() {
        miUndo.setOnAction(e -> {
            if (modeleImgSelectionne == modeleImgMilieu)
                undoCommandImg1.execute();
            else
                undoCommandImg2.execute();
        });
    }

    public void Redo() {
        miRedo.setOnAction(e -> {
            if (modeleImgSelectionne == modeleImgMilieu)
                redoCommandImg1.execute();
            else
                redoCommandImg2.execute();
        });
    }

    public void ChoixStrat() {
        miChoisirStrat.setOnAction(e -> {
            ChoiceDialog choixDialog = new ChoiceDialog<>(elementACopierChoix[0], elementACopierChoix);
            choixDialog.setTitle("Élements à copier");
            choixDialog.show();
            choixDialog.setOnCloseRequest(ev -> {
                if (choixDialog.getResult() != null) {
                    elementsACopier = choixDialog.getResult().toString();
                }
            });
        });
    }

    public void CopyMilieu() {
        mICopierImg1.setOnAction(e -> {
            perspectiveSauvegardé = modeleImgMilieu.créerMemento();
        });
    }

    public void CopyDroite() {
        mICopierImg2.setOnAction(e -> {
            perspectiveSauvegardé = modeleImgDroite.créerMemento();
        });
    }

    public void PasteMilieu() {
        mICollerImg1.setOnAction(e -> {
            modeleImgMilieu.chargerPerspective(perspectiveSauvegardé, elementsACopier);
        });
    }

    public void PasteDroite() {
        mICollerImg2.setOnAction(e -> {
            modeleImgDroite.chargerPerspective(perspectiveSauvegardé, elementsACopier);
        });
    }
}