package it.polimi.se2018.controller;

import it.polimi.se2018.model.*;
import it.polimi.se2018.view.View;

public class StartControllerState implements ControllerState {
    Controller controller;

    public StartControllerState(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void draftDiceFromDraftPool(Dice dice, View view) {
        Game game = controller.game;
        Round currentRound = game.currentRound;
        if (currentRound.draftPool.draftDice(dice)) {
            currentRound.currentTurn.setDraftedDice(dice);
            controller.hasDrafted = true;
        }
        controller.setControllerState(controller.getPlaceState());
    }

    @Override
    public void placeDice(int row, int col, View view) {
        view.showMessage("Can't do. You have to choose a dice from the draft pool first.");
    }

    @Override
    public boolean useToolCard(Player player, ToolCard toolcard, View view) {
        if (controller.canUseSpecificToolCard(player, toolcard)) {
            controller.setActiveToolCard(toolcard);
            controller.setControllerState(toolcard.nextState(this));
            return true;
        }
        return false;
    }

    @Override
    public void draftDiceFromTrack(Dice dice, View view) {
        view.showMessage("Can't do. Choose a dice from the draft pool or activate a ToolCard");
    }

    @Override
    public void moveDice(int rowFrom, int colFrom, int rowTo, int colTo, View view) {
        view.showMessage("Can't do. Choose a dice from the draft pool or activate a ToolCard");
    }

    @Override
    public void incrementDice(View view) {
        view.showMessage("Can't do. Choose a dice from the draft pool or activate a ToolCard");
    }

    @Override
    public void decrementDice(View view) {
        view.showMessage("Can't do. Choose a dice from the draft pool or activate a ToolCard");
    }

    @Override
    public void chooseDiceValue(int value, View view) {
        view.showMessage("Can't do. Choose a dice from the draft pool or activate a ToolCard");
    }

    @Override
    public void executeImplicitBehaviour(View view) {
        //TODO: initialize commented variables below only when at beginning of turn
//        controller.hasDrafted = false;
//        controller.hasToolCardBeenUsed = false;
//        controller.movesCounter = 0;
    }
}