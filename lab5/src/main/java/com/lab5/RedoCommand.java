package com.lab5;

public class RedoCommand extends AbstractCommand {

    public RedoCommand(ModeleImg modele) {
        super(modele);
    }

    public void execute() {
        modele.Redo();
    }

}
