package com.lab5;

public class SaveCommand extends AbstractCommand {


    public SaveCommand(ModeleImg modele) {
        super(modele);
        
    }

    public void execute() {
        modele.save();
    }
}
