package it.polimi.se2018.controller;

import it.polimi.se2018.model.ToolCard;
import it.polimi.se2018.utils.message.CVMessage;
import it.polimi.se2018.utils.message.Message;

/**
 *  @author Lorenzo Minto
 *  @author Federico Haag (refactor)
 */
public class ToolCardControllerState extends ControllerState {

    /**
     * Class constructor.
     *
     * @param controller the controller of which this class is going to act as a state.
     */
    public ToolCardControllerState(Controller controller) {
        this.controller = controller;
        this.defaultMessage = TOOLCARD_ONLY;
    }

    @Override
    public Message useToolCard(ToolCard toolcard) {
        if (controller.canUseSpecificToolCard(toolcard)) {
            controller.setActiveToolCard(toolcard);
            controller.setControllerState(controller.stateManager.getNextState(this));
        } else {
            return new CVMessage("Can't use this toolcard.");
        }
        return new CVMessage("Toolcard activated.");
    }
}
