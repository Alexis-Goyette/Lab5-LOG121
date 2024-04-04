package com.lab5;

public class ControleurImg {

    private ModeleImg modImg;
    private int indexCommand;
    private ICommand command;
    private static ControleurImg instanceUnique;
    private CopyPasteMediator mediator;

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
}
