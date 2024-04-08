package com.lab5;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.awt.image.BufferedImage;
import java.util.*;

import javax.imageio.ImageIO;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class ModeleImg {
    private IObserver interfaceUtilisateur;

    private ImageView imgView;
    private ArrayList<InterfaceUtilisateur> observateurs;
    private final int translationValue = 10;
    private final float zoomTranlationValue = 0.1f;

    private Stack<ImgMemento> mementoStack;

    private float x, y, zoomFactor;

    public ModeleImg(ImageView imageView, InterfaceUtilisateur interfaceUtilisateur) {
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
        mementoStack.push(créerMemento());
        this.x -= translationValue;
        notifyObservers();
    }

    public void translateRight() {
        // this.imgView.setTranslateX(this.imgView.getTranslateX() + translationValue);
        mementoStack.push(créerMemento());
        this.x += translationValue;
        notifyObservers();
    }

    public void translateUp() {
        // this.imgView.setTranslateY(this.imgView.getTranslateY() - translationValue);
        mementoStack.push(créerMemento());
        this.y -= translationValue;
        notifyObservers();
    }

    public void translateDown() {
        // this.imgView.setTranslateY(this.imgView.getTranslateY() + translationValue);
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
    }

    public void saveAs() throws MalformedURLException, IOException {
        DirectoryChooser fileChooser = new DirectoryChooser();

        fileChooser.setTitle("Open Resource Folder");

        File selectedFile = fileChooser.showDialog(null);
        if (selectedFile != null) {
            String urlString = imgView.getImage().getUrl();
            InputStream inputStream = new URL(urlString).openStream();
            File path = new File(selectedFile.getAbsolutePath() + "/image.jpg");

            Files.copy(inputStream, path.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

    }

    public void Undo() {
        var image = mementoStack.pop();
        this.x = (float) image.getX();
        this.y = (float) image.getY();
        this.zoomFactor = (float) image.getZoom();
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
}