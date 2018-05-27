package it.polimi.se2018.controller;

import it.polimi.se2018.model.*;
import it.polimi.se2018.utils.message.CVMessage;

import static it.polimi.se2018.utils.message.CVMessage.types.ACKNOWLEDGMENT_MESSAGE;
import static it.polimi.se2018.utils.message.CVMessage.types.ERROR_MESSAGE;

/**
 *  @author Lorenzo Minto
 *  @author Federico Haag (refactor)
 */
public class StartControllerState extends ControllerState {

    /**
     * Class constructor.
     *
     * @param controller the controller of which this class is going to act as a state.
     */
    public StartControllerState(Controller controller) {
        if (controller==null) { throw new IllegalArgumentException("Can't create a State Controller without a Controller");}
        this.controller = controller;
        this.defaultMessage = NO_DICE_DRAFTED;
    }

    @Override
    public CVMessage draftDiceFromDraftPool(Dice dice) {
        Round currentRound = controller.game.getCurrentRound();

        if (currentRound.getDraftPool().draftDice(dice)) {
            currentRound.getCurrentTurn().setDraftedDice(dice);
            controller.setControllerState(controller.stateManager.getPlaceState());
            return new CVMessage(ACKNOWLEDGMENT_MESSAGE,"Dice drafted.");
        } else {
            return new CVMessage(ERROR_MESSAGE,"Dice not in the draft pool.");
        }
    }

    @Override
    public CVMessage useToolCard(ToolCard toolcard) {
        if (controller.canUseSpecificToolCard(toolcard)) {
            controller.setActiveToolCard(toolcard);
            controller.setControllerState(controller.stateManager.getNextState(this));
        } else {
            return new CVMessage(ERROR_MESSAGE,"Can't use this toolcard.");
        }
        return new CVMessage(ACKNOWLEDGMENT_MESSAGE,"Toolcard activated.");
    }
}
