package com.lab5;

import java.util.*;
import javafx.scene.image.Image;

public class ModeleImg {
    private Image image;
    private ArrayList<ImageView> observateurs;
    private int translationValue;
    private int scaleFactor;
    private Stack<ImgMemento> mementoStack;

    public ModeleImg(Image image) {
    }

    public Image GetImage() {
        return image;
    }

    public void SetImage(Image image) {
    }

    public void AddObserver(ImageView observer) {
    }

    public void RemoveObserver(ImageView observer) {
    }

    public void NotifyObservers() {
    }

    public void TranslateLeft() {
    }

    public void TranslateRight() {
    }

    public void TranslateUp() {
    }

    public void TranslateDown() {
    }

    public void ZoomIn() {
    }

    public void ZoomOut() {
    }

    public void Save() {
    }

    public void SaveAs() {
    }

    public ImgMemento CréerMemento() {
        return new ImgMemento();
    }

    public void SetMemento() {
    }

    public int GetScaleFactor() {
        return scaleFactor;
    }

    public int GetTranslationValue() {
        return translationValue;
    }
}