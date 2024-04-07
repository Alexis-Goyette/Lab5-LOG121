package com.lab5;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class ControleurImg {
    public ControleurImg() {

    }

     private static ControleurImg instanceUnique ;

    private int indexCommand;
    private CopyPasteMediator mediator;
    private static InterfaceUtilisateur interfaceUtilisateur ;
    private Button btnGauche;
    private Button btnDroite;
    private Button btnHaut;
    private  Button btnBas;
    private Button btnZoomIn ;
    private Button btnZoomOut;

    private ModeleImg modeleImgMilieu;
    private ModeleImg modeleImgDroite ;


    private static ImageView imgView1;

    private static ImageView imgView2;


   private ModeleImg modeleImgSelectionne;



    private ICommand translateCommandLeftModImg1 ;
    private ICommand translateCommandRightModImg1 ;
    private ICommand translateCommandUpModImg1;
    private ICommand translateCommandDownModImg1 ;

    private ICommand zoomCommandInModImg1;
    private ICommand zoomCommandOutModImg1 ;



    private ICommand translateCommandLeftModImg2;
    private ICommand translateCommandRightModImg2 ;
    private ICommand translateCommandUpModImg2 ;
    private ICommand translateCommandDownModImg2 ;

    private ICommand zoomCommandInModImg2;
    private ICommand zoomCommandOutModImg2 ;

    private ControleurImg(InterfaceUtilisateur interfaceUtilisateur) {
        this.interfaceUtilisateur = interfaceUtilisateur;
        btnGauche = interfaceUtilisateur.getBtnGauche();
        btnDroite = interfaceUtilisateur.getBtnDroite();
        btnHaut = interfaceUtilisateur.getBtnHaut();
        btnBas= interfaceUtilisateur.getBtnBas();
        btnZoomIn = interfaceUtilisateur.getBtnZoomIn();
        btnZoomOut = interfaceUtilisateur.getBtnZoomOut();

        imgView1= interfaceUtilisateur.getImgView1();
        imgView2 = interfaceUtilisateur.getImgView2();

        this.modeleImgMilieu = new ModeleImg(imgView1);
        this.modeleImgDroite = new ModeleImg(imgView2);

        translateCommandLeftModImg1 =  new TranslateCommand(modeleImgMilieu, TranslationDirection.LEFT);
        translateCommandRightModImg1 =  new TranslateCommand(modeleImgMilieu, TranslationDirection.RIGHT);
        translateCommandUpModImg1=  new TranslateCommand(modeleImgMilieu, TranslationDirection.UP);
        translateCommandDownModImg1 =  new TranslateCommand(modeleImgMilieu, TranslationDirection.DOWN);

        zoomCommandInModImg1=  new ZoomCommand(modeleImgMilieu, ZoomDirection.IN);
        zoomCommandOutModImg1 =  new ZoomCommand(modeleImgMilieu, ZoomDirection.OUT);



        translateCommandLeftModImg2=  new TranslateCommand(modeleImgDroite, TranslationDirection.LEFT);
        translateCommandRightModImg2 =  new TranslateCommand(modeleImgDroite, TranslationDirection.RIGHT);
        translateCommandUpModImg2 =  new TranslateCommand(modeleImgDroite, TranslationDirection.UP);
        translateCommandDownModImg2 =  new TranslateCommand(modeleImgDroite, TranslationDirection.DOWN);

        zoomCommandInModImg2=  new ZoomCommand(modeleImgDroite, ZoomDirection.IN);
        zoomCommandOutModImg2 =  new ZoomCommand(modeleImgDroite, ZoomDirection.OUT);
    }
    public static ControleurImg getInstance(InterfaceUtilisateur interfaceUtilisateur) {
        if (instanceUnique == null) {
            instanceUnique = new ControleurImg(interfaceUtilisateur );
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
            if(modeleImgSelectionne == modeleImgMilieu)
                translateCommandLeftModImg1.execute();
            else
                translateCommandLeftModImg2.execute();
        });
    }
    @FXML
    public void translateRight() {
        btnDroite.setOnAction(e -> {
            if(modeleImgSelectionne == modeleImgMilieu)
                translateCommandRightModImg1.execute();
            else
                translateCommandRightModImg2.execute();
        });
    }

    @FXML
    public void translateDown() {
        btnBas.setOnAction(e -> {
            if(modeleImgSelectionne == modeleImgMilieu)
                translateCommandDownModImg1.execute();
            else
                translateCommandDownModImg2.execute();
        });
    }

    @FXML
    public void translateUp() {
        btnHaut.setOnAction(e -> {
            if(modeleImgSelectionne == modeleImgMilieu)
                translateCommandUpModImg1.execute();
            else
                translateCommandUpModImg2.execute();
        });
    }

    @FXML
    public void ZoomIn() {
        btnZoomIn.setOnAction(e -> {
            if(modeleImgSelectionne == modeleImgMilieu)
                zoomCommandInModImg1.execute();
            else
                zoomCommandInModImg2.execute();
        });
    }

    @FXML
    public void ZoomOut() {
        btnZoomOut.setOnAction(e -> {
            if(modeleImgSelectionne == modeleImgMilieu)
                zoomCommandOutModImg1.execute();
            else
                zoomCommandOutModImg2.execute();
        });
    }




//    public void TranslateImage(TranslationDirection.translationDirection direction) {
//        switch (direction) {
//            case LEFT:
//                modImg.translateLeft();
//                break;
//            case RIGHT:
//                modImg.translateRight();
//                break;
//            case UP:
//                modImg.translateUp();
//                break;
//            case DOWN:
//                modImg.translateDown();
//                break;
//        }
//    }
//
//    public void ZoomImage(ZoomDirection.zoomDirection direction) {
//        switch (direction) {
//            case IN:
//                modImg.zoomIn();
//                break;
//            case OUT:
//                modImg.zoomOut();
//                break;
//        }
//    }

    public void SaveImage() {
    }

    public void SaveAs() {
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
