package com.lab5;

import java.io.IOException;
import java.net.MalformedURLException;

public class SaveAsCommand extends AbstractCommand {

    public SaveAsCommand(ModeleImg modele) {
        super(modele);
    }

    public void execute() throws MalformedURLException, IOException {
        modele.saveAs();
    }
}
