package com.lab5;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.scene.control.ScrollPane;

public class ModeleImg {

    private ImageView imgView;
    private ArrayList<InterfaceUtilisateur> observateurs;
    private final int translationValue = 10;
    private final float zoomTranlationValue = 0.1f;

    private Stack<ImgMemento> mementoStack;
    private Stack<ImgMemento> undoStack;

    private float x, y, zoomFactor;

    public ModeleImg(ImageView imageView, InterfaceUtilisateur interfaceUtilisateur) {
        imgView = imageView;
        observateurs = new ArrayList<InterfaceUtilisateur>();
        zoomFactor = 1;
        mementoStack = new Stack<ImgMemento>();
        undoStack = new Stack<ImgMemento>();
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

    public void sauvegarderPerspective(ScrollPane scrollPane) {
        // Prepare the snapshot parameters
        SnapshotParameters params = new SnapshotParameters();
        params.setViewport(new Rectangle2D(
                scrollPane.getViewportBounds().getMinX(),
                scrollPane.getViewportBounds().getMinY(),
                scrollPane.getViewportBounds().getWidth(),
                scrollPane.getViewportBounds().getHeight()));
        WritableImage snapshot = scrollPane.getContent().snapshot(params, null);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image as Perspective File");
        fileChooser.setInitialFileName("savedImage.perspective");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Perspective Files", "*.perspective"));
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try {
                BufferedImage bImage = SwingFXUtils.fromFXImage(snapshot, null);
                ImageIO.write(bImage, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    public void undo() {
        var image = mementoStack.pop();
        undoStack.push(créerMemento());
        this.x = (float) image.getX();
        this.y = (float) image.getY();
        this.zoomFactor = (float) image.getZoom();
        notifyObservers();
    }

    public void redo() {
        var image = undoStack.pop();
        mementoStack.push(créerMemento());
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

    public void resetValues() {
        this.x = 0;
        this.y = 0;
        this.zoomFactor = 1;
        notifyObservers();
    }

    public ImageView getImgView() {
        return imgView;
    }

    public Stack<ImgMemento> getMementoStack() {
        return mementoStack;
    }

    public Stack<ImgMemento> getUndoStack() {
        return undoStack;
    }

}