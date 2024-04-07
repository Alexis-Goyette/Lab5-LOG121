package com.lab5;

public class ZoomCommand extends AbstractCommand {
ZoomDirection direction;
    public ZoomCommand(ModeleImg modele, ZoomDirection direction) {
        super(modele);
       this.direction = direction;
    }

    public void execute() {

    }

}
