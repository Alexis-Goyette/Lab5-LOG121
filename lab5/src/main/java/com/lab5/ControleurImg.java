package com.lab5;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;

public class ControleurImg {
    public ControleurImg() {

    }

    private static ControleurImg instanceUnique;

    private int indexCommand;
    private CopyPasteMediator mediator;
    private static InterfaceUtilisateur interfaceUtilisateur;
    private Button btnGauche;
    private Button btnDroite;
    private Button btnHaut;
    private Button btnBas;
    private Button btnZoomIn;
    private Button btnZoomOut;
    private MenuButton mbFichier;
    private MenuItem miSauvegarderImage;

    private ModeleImg modeleImgMilieu;
    private ModeleImg modeleImgDroite;

    private static ImageView imgView1;

    private static ImageView imgView2;

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

    private ICommand saveAsCommandImg1;
    private ICommand saveAsCommandImg2;

    private ControleurImg(InterfaceUtilisateur interfaceUtilisateur) {
        this.interfaceUtilisateur = interfaceUtilisateur;
        btnGauche = interfaceUtilisateur.getBtnGauche();
        btnDroite = interfaceUtilisateur.getBtnDroite();
        btnHaut = interfaceUtilisateur.getBtnHaut();
        btnBas = interfaceUtilisateur.getBtnBas();
        btnZoomIn = interfaceUtilisateur.getBtnZoomIn();
        btnZoomOut = interfaceUtilisateur.getBtnZoomOut();
        mbFichier = interfaceUtilisateur.getMbFichier();
        miSauvegarderImage = interfaceUtilisateur.getMiSauvegarderImage();

        imgView1 = interfaceUtilisateur.getImgView1();
        imgView2 = interfaceUtilisateur.getImgView2();

        this.modeleImgMilieu = new ModeleImg(imgView1, interfaceUtilisateur);
        this.modeleImgDroite = new ModeleImg(imgView2, interfaceUtilisateur);

        translateCommandLeftModImg1 = new TranslateCommand(modeleImgMilieu, TranslationDirection.LEFT);
        translateCommandRightModImg1 = new TranslateCommand(modeleImgMilieu, TranslationDirection.RIGHT);
        translateCommandUpModImg1 = new TranslateCommand(modeleImgMilieu, TranslationDirection.UP);
        translateCommandDownModImg1 = new TranslateCommand(modeleImgMilieu, TranslationDirection.DOWN);

        zoomCommandInModImg1 = new ZoomCommand(modeleImgMilieu, ZoomDirection.IN);
        zoomCommandOutModImg1 = new ZoomCommand(modeleImgMilieu, ZoomDirection.OUT);

        saveAsCommandImg1 = new SaveAsCommand(modeleImgMilieu);

        translateCommandLeftModImg2 = new TranslateCommand(modeleImgDroite, TranslationDirection.LEFT);
        translateCommandRightModImg2 = new TranslateCommand(modeleImgDroite, TranslationDirection.RIGHT);
        translateCommandUpModImg2 = new TranslateCommand(modeleImgDroite, TranslationDirection.UP);
        translateCommandDownModImg2 = new TranslateCommand(modeleImgDroite, TranslationDirection.DOWN);

        zoomCommandInModImg2 = new ZoomCommand(modeleImgDroite, ZoomDirection.IN);
        zoomCommandOutModImg2 = new ZoomCommand(modeleImgDroite, ZoomDirection.OUT);

        saveAsCommandImg2 = new SaveAsCommand(modeleImgDroite);
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
                try {
                    translateCommandLeftModImg1.execute();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            else
                try {
                    translateCommandLeftModImg2.execute();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
        });
    }

    @FXML
    public void translateRight() {
        btnDroite.setOnAction(e -> {
            if (modeleImgSelectionne == modeleImgMilieu)
                try {
                    translateCommandRightModImg1.execute();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            else
                try {
                    translateCommandRightModImg2.execute();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
        });
    }

    @FXML
    public void translateDown() {
        btnBas.setOnAction(e -> {
            if (modeleImgSelectionne == modeleImgMilieu)
                try {
                    translateCommandDownModImg1.execute();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            else
                try {
                    translateCommandDownModImg2.execute();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
        });
    }

    @FXML
    public void translateUp() {
        btnHaut.setOnAction(e -> {
            if (modeleImgSelectionne == modeleImgMilieu)
                try {
                    translateCommandUpModImg1.execute();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            else
                try {
                    translateCommandUpModImg2.execute();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
        });
    }

    @FXML
    public void zoomIn() {
        btnZoomIn.setOnAction(e -> {
            if (modeleImgSelectionne == modeleImgMilieu)
                try {
                    zoomCommandInModImg1.execute();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            else
                try {
                    zoomCommandInModImg2.execute();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
        });
    }

    @FXML
    public void zoomOut() {
        btnZoomOut.setOnAction(e -> {
            if (modeleImgSelectionne == modeleImgMilieu)
                try {
                    zoomCommandOutModImg1.execute();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            else
                try {
                    zoomCommandOutModImg2.execute();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
        });
    }

    public void SaveImage() {
    }

    public void SaveAs() {
        miSauvegarderImage.setOnAction(e -> {
            if (modeleImgSelectionne == modeleImgMilieu)
                try {
                    saveAsCommandImg1.execute();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            else
                try {
                    saveAsCommandImg2.execute();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
        });
    }

    public void Undo() {
    }

    public void CopyParams(ModeleImg source, ICopyStrategy strategy) {
    }

    public void PasteParams(ModeleImg source, ModeleImg destination) {
    }

    // je suis pas sûr si le type de "source" et "destination" est bon,
    // il était pas marqué dans l'uml, à changer au besoin

}
