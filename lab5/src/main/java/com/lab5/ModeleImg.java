package com.lab5;

import java.util.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ModeleImg {
    
    private Image image;

    private ImageView imgView;
    private ArrayList<ImageView> observateurs;
    private final int translationValue =10;
    private int scaleFactor;
    private Stack<ImgMemento> mementoStack;

    public ModeleImg(ImageView imageView) {
        imgView = imageView;
        observateurs = new ArrayList<ImageView>();
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
//
//    public void NotifyObservers() {
//        for (var observer : observateurs) {
//            observer.update();
//        }
//    }
    public void translateLeft() {
       // this.image.setTranslateX(this.image.getTranslateX() - translationValue);
        this.imgView.setTranslateX(-translationValue);
    }

    public void translateRight() {
        this.imgView.setTranslateX(-translationValue);
    }

    public void translateUp() {
        this.imgView.setTranslateY(translationValue);
    }

    public void translateDown() {
        this.imgView.setTranslateY(-translationValue);
    }

    public void zoomIn() {
    }

    public void zoomOut() {
    }

    public void save() {
    }

    public void saveAs() {
    }

    public ImgMemento créerMemento() {
        return new ImgMemento(image);
    }

    public void setMemento(ImgMemento m) {
        mementoStack.push(m);
    }

    public int getScaleFactor() {
        return scaleFactor;
    }

    public int getTranslationValue() {
        return translationValue;
    }
}