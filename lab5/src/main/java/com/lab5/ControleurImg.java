package com.lab5;

public class ControleurImg {

    private ModeleImg modImg;
    private int indexCommand;
    private ICommand command;
    private static ControleurImg instanceUnique;
    private CopyPasteMediator mediator;

    private ControleurImg(ModeleImg model) {
    }

    public void TranslateImage(TranslationDirection direction) {
    }

    public void ZoomImage(ZoomDirection direction) {
    }

    public void SaveImage() {
    }

    public void SaveAs() {
    }

    public void Undo() {
    }

    public ControleurImg GetInstance() {
        return instanceUnique;
    }

    public void CopyParams(ModeleImg source, ICopyStrategy strategy) {
    }

    public void PasteParams(ModeleImg source, ModeleImg destination) {
    }
    // je suis pas sûr si le type de "source" et "destination" est bon,
    // il était pas marqué dans l'uml, à changer au besoin
}
