package it.polimi.se2018.controller;

import it.polimi.se2018.model.*;
import it.polimi.se2018.utils.Move;
import it.polimi.se2018.utils.Message;

import java.util.EnumSet;

import static it.polimi.se2018.utils.ViewBoundMessageType.ACKNOWLEDGMENT_MESSAGE;
import static it.polimi.se2018.utils.ViewBoundMessageType.ERROR_MESSAGE;

/**
 *
 *  @author Lorenzo Minto
 *  @author Federico Haag (refactor)
 */
public class DraftControllerState extends ControllerState {


    /**
     * Class constructor.
     *
     * @param controller the controller of which this class is going to act as a state.
     */
    public DraftControllerState(Controller controller) {
        this.controller = controller;
        this.defaultMessage = ONLY_DRAFT_AND_PLACE;
    }

    @Override
    public Message draftDiceFromDraftPool(Dice dice) {
        Game game = controller.game;
        Round currentRound = game.getCurrentRound();

        if (currentRound.getDraftPool().draftDice(dice)) {
            currentRound.getCurrentTurn().setDraftedDice(dice);
        }

        if (controller.getActiveToolCard() != null) {
            controller.setControllerState(controller.stateManager.getNextState(this));
        } else {
            controller.setControllerState(controller.stateManager.getPlaceState());
        }

        return new Message(ACKNOWLEDGMENT_MESSAGE,"Dice drafted.");
    }

    @Override
    public Message placeDice(int row, int col) {
        return new Message(ERROR_MESSAGE, NO_DICE_DRAFTED);
    }

    @Override
    public EnumSet<Move> getStatePermissions() {
        return EnumSet.of(Move.DRAFT_DICE_FROM_DRAFTPOOL, Move.END_TURN);
    }
}
