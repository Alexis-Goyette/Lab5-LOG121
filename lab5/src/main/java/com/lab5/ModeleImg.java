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
        this.image = image;
        observateurs = new ArrayList<ImageView>();
        translationValue = 0;
        scaleFactor = 1;
        mementoStack = new Stack<ImgMemento>();

    }

    public Image GetImage() {
        return image;
    }

    public void SetImage(Image i) {
        image = i;
    }

    public void AddObserver(ImageView observer) {
        observateurs.add(observer);
    }

    public void RemoveObserver(ImageView observer) {
        int index = observateurs.indexOf(observer);
        if (index > 0) {
            observateurs.remove(index);
        }
    }

    public void NotifyObservers() {
        for (var observer : observateurs) {
            observer.Update();
        }
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
        return new ImgMemento(image);
    }

    public void SetMemento(ImgMemento m) {
        mementoStack.push(m);
    }

    public int GetScaleFactor() {
        return scaleFactor;
    }

    public int GetTranslationValue() {
        return translationValue;
    }
}