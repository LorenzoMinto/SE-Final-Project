package it.polimi.se2018.model;

import it.polimi.se2018.utils.BadBehaviourRuntimeException;
import it.polimi.se2018.utils.ValueOutOfBoundsException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static it.polimi.se2018.model.DiceColor.*;
import static org.junit.Assert.*;

/**
 * @author Jacopo Pio Gargano
 */

public class TurnTest {

    private Turn turn;

    private static PrivateObjectiveCard privateObjectiveCard;
    private static Player player;
    private static Dice dice;
    private static ToolCard toolCard;
    private static String playerName;



    @BeforeClass
    public static void initializeVariables() {
        privateObjectiveCard = new PrivateObjectiveCard(null,null, null, RED);
        playerName = "player";
        player = new Player(playerName, privateObjectiveCard);
        dice = new Dice(RED);
        toolCard = ToolCard.createTestInstance();

    }

    @Before
    public void initializeDefaultTurnWithPlayer(){
        turn = new Turn(0, player);
    }


    @Test
    public void testConstructor(){
        turn = new Turn(0,player);
        assertNotNull(turn);
    }

    @Test
    public void testConstructorWithNegativeTurnNumber(){
        try{
            turn = new Turn(-1, player);
            fail();
        }catch (ValueOutOfBoundsException e){}
    }

    @Test
    public void testConstructorWithNullPlayer(){
        try{
            turn = new Turn(0, null);
            fail();
        }catch (IllegalArgumentException e){}
    }

    //to be run with setDraftedDice test
    @Test
    public void testHasActuallyDrafted(){
        turn.setDraftedDice(dice);
        assertTrue(turn.hasDrafted());
    }

    @Test
    public void testHasNotDrafted(){
        assertNull(turn.getDraftedDice());
        assertFalse(turn.hasDrafted());
    }

    @Test
    public void testHasDraftedAndPlaced(){
        turn.setDraftedDice(dice);
        turn.setDraftedAndPlaced();
        assertTrue(turn.hasDraftedAndPlaced());
    }

    @Test
    public void testHasDraftedAndPlacedWithoutDrafting(){
        try{
            assertNull(turn.getDraftedDice());
            turn.setDraftedAndPlaced();
            fail();
        }catch (IllegalStateException e){}
    }

    @Test
    public void testHasActuallyUsedToolCard(){
        turn.setUsedToolCard(toolCard);
        assertTrue(turn.hasUsedToolCard());
    }

    @Test
    public void testHasNotUsedToolCard(){
        assertFalse(turn.hasUsedToolCard());
    }

    @Test
    public void testSetNullUsedToolCard(){
        try {
            turn.setUsedToolCard(null);
            fail();
        }catch (IllegalArgumentException e){}
    }

    @Test
    public void getSlotOfTrackChosenDice(){
        try {
            turn.getSlotOfTrackChosenDice();
            fail();
        }catch (BadBehaviourRuntimeException e){}
    }

    @Test
    public void testSetSlotOfTrackChosenDice(){
        turn.setSlotOfTrackChosenDice(1);
        assertEquals(1,turn.getSlotOfTrackChosenDice());
    }

    @Test
    public void testSetSlotOfTrackChosenDiceWithNegativeValue(){
        try{
            turn.setSlotOfTrackChosenDice(-1);
            fail();
        }catch (ValueOutOfBoundsException e){}
    }

    @Test
    public void testSetDraftedDice(){
        turn.setDraftedDice(dice);
        assertNotNull(turn.getDraftedDice());
    }

    @Test
    public void testSetNullDraftedDice(){
        try {
            turn.setDraftedDice(null);
            fail();
        }catch (IllegalArgumentException e){}
    }

    @Test
    public void testSetTrackChosenDice(){
        turn.setTrackChosenDice(dice);
        assertNotNull(turn.getTrackChosenDice());
    }

    @Test
    public void testSetNullTrackChosenDice(){
        try {
            turn.setTrackChosenDice(null);
            fail();
        }catch (IllegalArgumentException e){}
    }

    @Test
    public void testIsCurrentPlayer(){
        Player playerTest = turn.getPlayer();
        assertTrue(turn.isCurrentPlayer(playerTest.getID()));
    }

    @Test
    public void testIsCurrentPlayerSameName(){
        Player playerTest = new Player(playerName, privateObjectiveCard);
        assertTrue(turn.isCurrentPlayer(playerTest.getID()));
    }

    @Test
    public void testDifferentPlayerIsCurrentPlayer(){
        Player playerTest = new Player( "differentName", privateObjectiveCard);
        assertFalse(turn.isCurrentPlayer(playerTest.getID()));
    }

    @Test
    public void testNullPlayerIsCurrentPlayer(){
        try{
            turn.isCurrentPlayer(null);
            fail();
        }catch (IllegalArgumentException e){}
    }



}