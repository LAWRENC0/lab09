package it.unibo.mvc.view;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

public class DrawNumberStdoutView implements DrawNumberView{
    private final String START_MESSAGE = "Game has just started :D !!";
    private static final String NEW_GAME = ": a new game starts!";

    public DrawNumberStdoutView(){ }

    @Override
    public void setController(DrawNumberController observer) { }

    @Override
    public void start() {
        System.out.println(START_MESSAGE);
    }

    @Override
    public void result(DrawResult res) {
        switch (res) {
            case YOURS_HIGH, YOURS_LOW -> {
                System.out.println(res.getDescription());
                return;
            }
            case YOU_WON -> {
                System.out.println(res.getDescription() + NEW_GAME);
            }
            case YOU_LOST -> {
                System.out.println(res.getDescription() + NEW_GAME);
            }
            default -> throw new IllegalStateException("Unknown game state");
        }
    }
    
}
