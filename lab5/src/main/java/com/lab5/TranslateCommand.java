package com.lab5;

public class TranslateCommand extends AbstractCommand {


    TranslationDirection direction;
    public TranslateCommand(ModeleImg modele,  TranslationDirection direction) {
        super(modele);
        this.direction = direction;
    }

    public void execute() {
//        modele.translate(direction);

        switch (direction) {
            case LEFT:
                modele.translateLeft();
                break;
            case RIGHT:
                modele.translateRight();
                break;
            case UP:
                modele.translateUp();
                break;
            case DOWN:
                modele.translateDown();
                break;
        }
    }
}
