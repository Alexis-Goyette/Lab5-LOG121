package com.lab5;

public abstract class AbstractCommand implements ICommand{

    protected ModeleImg modele;


    public AbstractCommand(ModeleImg modele ) {
        this.modele = modele;
    }


}
