package com.lab5;

import javafx.scene.image.ImageView;

public class ImgMemento {

    private float x, y, zoomFactor;

    public ImgMemento(float x, Float y, Float zoomFactor) {
        this.x = x;
        this.y = y;
        this.zoomFactor = zoomFactor;
    }

    public void SetEtat(float x, Float y, Float zoomFactor) {
        this.x = x;
        this.y = y;
        this.zoomFactor = zoomFactor;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZoom() {
        return zoomFactor;
    }

}
