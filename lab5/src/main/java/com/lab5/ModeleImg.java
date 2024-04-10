package com.lab5;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Stack;

import javafx.scene.image.ImageView;

public class ModeleImg {
    private IObserver interfaceUtilisateur;

    private ImageView imgView;
    private ArrayList<InterfaceUtilisateur> observateurs;
    private final int translationValue = 10;
    private final float zoomTranlationValue = 0.1f;

    private Stack<ImgMemento> mementoStack;
    private Stack<ImgMemento> UndoStack;

    private float x, y, zoomFactor;

    public ModeleImg(ImageView imageView, InterfaceUtilisateur interfaceUtilisateur) {
        imgView = imageView;
        observateurs = new ArrayList<InterfaceUtilisateur>();
        zoomFactor = 1;
        mementoStack = new Stack<ImgMemento>();
        UndoStack = new Stack<ImgMemento>();
        this.interfaceUtilisateur = interfaceUtilisateur;
        addObserver(interfaceUtilisateur);
    }

    public ImageView getImageView() {
        return imgView;
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
        mementoStack.push(créerMemento());
        this.x -= translationValue;
        notifyObservers();
    }

    public void translateRight() {
        mementoStack.push(créerMemento());
        this.x += translationValue;
        notifyObservers();
    }

    public void translateUp() {
        mementoStack.push(créerMemento());
        this.y -= translationValue;
        notifyObservers();
    }

    public void translateDown() {
        mementoStack.push(créerMemento());
        this.y += translationValue;
        notifyObservers();
    }

    public void zoomIn() {
        mementoStack.push(créerMemento());
        this.zoomFactor += zoomTranlationValue;
        notifyObservers();
    }

    public void zoomOut() {
        mementoStack.push(créerMemento());
        this.zoomFactor -= zoomTranlationValue;
        notifyObservers();
    }

    public void save() {
        notifyObservers();
    }

    public void Undo() {
        var image = mementoStack.pop();
        UndoStack.push(créerMemento());
        this.x = (float) image.getX();
        this.y = (float) image.getY();
        this.zoomFactor = (float) image.getZoom();
        notifyObservers();
    }

    public void Redo() {
        var image = UndoStack.pop();
        this.x = (float) image.getX();
        this.y = (float) image.getY();
        this.zoomFactor = (float) image.getZoom();
        notifyObservers();
    }

    public void chargerPerspective(ImgMemento memento, String elementsACopier) {
        if (elementsACopier.contains("Grandissement")) {
            this.zoomFactor = (float) memento.getZoom();
        }
        if (elementsACopier.contains("Translation")) {
            this.x = (float) memento.getX();
            this.y = (float) memento.getY();
        }

        notifyObservers();
    }

    public ImgMemento créerMemento() {
        return new ImgMemento(x, y, zoomFactor);
    }

    public void setMemento(ImgMemento m) {
        mementoStack.push(m);
    }

    public float getZoomFactor() {
        return zoomFactor;
    }

    public float getXTranslationValue() {
        return x;
    }

    public float getYTranslationValue() {
        return y;
    }

    public void setZoomFactor(Float factor) {
        zoomFactor = factor;
    }

    public void setXTranslationValue(Float x) {
        this.x = x;
    }

    public void setYTranslationValue(Float y) {
        this.y = y;
    }
}