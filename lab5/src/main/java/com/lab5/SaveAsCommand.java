package com.lab5;

import java.awt.*;
import javafx.scene.control.ScrollPane;

public class SaveAsCommand extends AbstractCommand{

    private ScrollPane scrollPane;
    public SaveAsCommand(ModeleImg modele, ScrollPane scrollPane) {
        super(modele);
        this.scrollPane = scrollPane;
    }

    public void execute() {
        modele.sauvegarderPerspective(this.scrollPane);
    }
}
