package com.lab5;


import javafx.scene.image.Image;

public class ImgMemento {

    private Image etat;

    public ImgMemento(Image i) {
        etat = i;
    }

    public void SetEtat(Image i) {
        etat = i;
    }

    public Image GetEtat() {
        return etat;
    }

}