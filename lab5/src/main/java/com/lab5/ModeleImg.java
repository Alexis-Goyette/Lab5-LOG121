package com.lab5;

import java.util.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ModeleImg {

    private IObserver interfaceUtilisateur;
    
    private Image image;

    private ImageView imgView;
    private ArrayList<InterfaceUtilisateur> observateurs;
    private final int translationValue =10;
    private final float zoomTranlationValue = 0.1f;
    private Stack<ImgMemento> mementoStack;

    private float x,y, zoomFactor;

    public ModeleImg(ImageView imageView, InterfaceUtilisateur interfaceUtilisateur){
        imgView = imageView;
        observateurs = new ArrayList<InterfaceUtilisateur>();
        zoomFactor = 1;
        mementoStack = new Stack<ImgMemento>();
        this.interfaceUtilisateur = interfaceUtilisateur;
        addObserver(interfaceUtilisateur);
    }

    public ImageView getImageView() {
        return imgView;
    }

    public void setImage(Image i) {
        image = i;
    }

    public void addObserver(InterfaceUtilisateur observer) {
        observateurs.add(observer);
    }

    public void removeObserver(ImageView observer) {
        int index = observateurs.indexOf(observer);
        if (index > 0) {
            observateurs.remove(index);
        }
    }

    public void notifyObservers() {
        for (var observer : observateurs) {
            observer.update(this);
        }
   }
    public void translateLeft() {
       // this.image.setTranslateX(this.image.getTranslateX() - translationValue);
        // this.imgView.setTranslateX(this.imgView.getTranslateX() - translationValue);
        this.x-=translationValue;
        notifyObservers();
    }

    public void translateRight() {
//        this.imgView.setTranslateX(this.imgView.getTranslateX() + translationValue);
        this.x+=translationValue;
        notifyObservers();
    }

    public void translateUp() {
//        this.imgView.setTranslateY(this.imgView.getTranslateY() - translationValue);
        this.y-=translationValue;
        notifyObservers();
    }

    public void translateDown() {
//        this.imgView.setTranslateY(this.imgView.getTranslateY() + translationValue);
        this.y+=translationValue;
        notifyObservers();
    }

    public void zoomIn() {
        this.zoomFactor += zoomTranlationValue;
        notifyObservers();
    }

    public void zoomOut() {
        this.zoomFactor -= zoomTranlationValue;
        notifyObservers();
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

    public float getScaleFactor() {
        return zoomFactor;
    }

    public float getXTranslationValue() {
        return x;
    }

    public float getYTranslationValue() {
        return y;
    }
}