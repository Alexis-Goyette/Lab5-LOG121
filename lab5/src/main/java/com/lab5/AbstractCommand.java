package com.lab5;

import javafx.event.ActionEvent;

public abstract class AbstractCommand extends ActionEvent {

    protected ModeleImg modele;

    public AbstractCommand(ModeleImg modele) {}
}
