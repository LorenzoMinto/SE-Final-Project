package it.polimi.se2018.controller;

import it.polimi.se2018.model.*;
import it.polimi.se2018.view.View;

public class PlaceControllerState implements ControllerState {
    Controller controller;

    public PlaceControllerState(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void draftDiceFromDraftPool(Dice dice, View view) {
        view.showMessage("Can't do. You have to place the drafted dice first");
    }

    @Override
    public void placeDice(int row, int col, View view) {
        Game game = controller.game;
        Turn currentTurn = game.currentRound.currentTurn;
        //NOTE: checking for currentTurn.hasDrafted might be redundant in the context of the State pattern
        if (currentTurn.hasDrafted()) {
            if (currentTurn.currentPlayer.getWindowPattern().putDiceOnCell(currentTurn.getDraftedDice(), row, col)) {
                //TODO: checking with PlacementRule of correctness of placement
                view.showMessage("Dice placed!");

                if (controller.getActiveToolCard() != null) {
                    controller.setControllerState(controller.getActiveToolCard().nextState(this));
                } else {
                    //TODO: change to ToolcardState
                    controller.setControllerState(controller.getToolCardState());
                }
            } else {
                view.showMessage("Move is illegal. There's another dice in that position.");
            }
        } else {
            view.showMessage("No drafted dice.");
        }

    }

    @Override
    public void useToolCard(Player player, ToolCard toolcard, View view) {
        view.showMessage("Can't do. You have to place the drafted dice first");
    }

    //TOOLCARD ACTIVATED

    @Override
    public void chooseDiceFromTrack(Dice dice, View view) {
        view.showMessage("Can't do. You have to place the drafted dice first");
    }

    @Override
    public void moveDice(int rowFrom, int colFrom, int rowTo, int colTo, View view) {
        view.showMessage("Can't do. You have to place the drafted dice first");
    }

    @Override
    public void incrementDice(View view) {
        view.showMessage("Can't do. You have to place the drafted dice first");
    }

    @Override
    public void decrementDice(View view) {
        view.showMessage("Can't do. You have to place the drafted dice first");
    }

    @Override
    public void chooseDiceValue(int value, View view) {
        view.showMessage("Can't do. You have to place the drafted dice first");
    }

    @Override
    public void executeImplicitBehaviour(View view) {

    }
}
