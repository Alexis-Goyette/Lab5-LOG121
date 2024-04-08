package com.lab5;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Stack;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;

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
        // setMemento(créerMemento());
        mementoStack.push(créerMemento());
        this.zoomFactor -= zoomTranlationValue;
        notifyObservers();
    }

    public void save() {
        notifyObservers();
    }

    public void saveAs() throws MalformedURLException, IOException {
        DirectoryChooser fileChooser = new DirectoryChooser();

        fileChooser.setTitle("Open Resource Folder");

        File selectedFile = fileChooser.showDialog(null);
        if (selectedFile != null) {

            var wi = imgView.snapshot(null, null);
            File file = new File(selectedFile.getAbsolutePath() + "/image.png");
            RenderedImage renderedImage = SwingFXUtils.fromFXImage(wi, null);
            ImageIO.write(
                    renderedImage,
                    "png",
                    file);
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