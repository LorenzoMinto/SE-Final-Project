package it.polimi.se2018.model;

import it.polimi.se2018.controller.NoMoreTurnsAvailableException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class TestRound {


    private Round round;
    private Turn turn;

    private static DraftPool draftPool;
    private static User user;
    private static PrivateObjectiveCard privateObjectiveCard;

    private static List<Player> players;
    private static Player p1;
    private static Player p2;
    private static Player p3;
    private static Player p4;
    private static Dice dice;
    private static ToolCard toolCard;

    @BeforeClass
    public static void initializeVariables(){
        user = new User(1,"username");
        privateObjectiveCard = new PrivateObjectiveCard(null,null,null,DiceColors.RED);

        p1 = new Player(user, "p1", privateObjectiveCard);
        p2 = new Player(user, "p2", privateObjectiveCard);
        p3 = new Player(user, "p3", privateObjectiveCard);
        p4 = new Player(user, "p4", privateObjectiveCard);

        draftPool = new DraftPool();

    }


    @Before
    public void initializePlayersList(){
        players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
    }

    @Test
    public void testConstructor(){
        round = new Round(0,players.size(), players, draftPool);
        assertNotNull(round);
    }

    @Test
    public void testConstructorWithNegativeRoundNumber(){
        try{
            round = new Round(-1, players.size()*2, players, draftPool);
            fail();
        }catch (IllegalArgumentException e){}
    }

    @Test
    public void testConstructorWithNegativeNumberOfTurns(){
        try{
            round = new Round(0, -2, players, draftPool);
            fail();
        }catch (IllegalArgumentException e){}
    }

    @Test
    public void testConstructorWithNullListOfPlayers(){
        try{
            round = new Round(0, 8, null, draftPool);
            fail();
        }catch (IllegalArgumentException e){}
    }

    @Test
    public void testConstructorWithEmptyListOfPlayers(){
        try{
            round = new Round(0, 8, new ArrayList<>(), draftPool);
            fail();
        }catch (IllegalArgumentException e){}
    }

    @Test
    public void testConstructorWithNullDraftPool(){
        try{
            round = new Round(0, 8, players, null);
            fail();
        }catch (IllegalArgumentException e){}
    }


    @Test
    public void testGetCurrentTurn(){
        round = new Round(0,players.size()*2, players, draftPool);

        turn = round.getCurrentTurn();
        assertNotNull(turn);
    }

    @Test
    public void testGetPlayersByReverseTurnOrderOfLastRound(){
        List<Player> expectedPlayers = new ArrayList<>();
        expectedPlayers.add(p2);
        expectedPlayers.add(p3);
        expectedPlayers.add(p4);
        expectedPlayers.add(p1);

        round = new Round(9,players.size()*2, players, draftPool);
        List<Player> playersByReverseTurnOrder = round.getPlayersByReverseTurnOrder();

        assertEquals(expectedPlayers, playersByReverseTurnOrder);
    }

    @Test
    public void testGetPlayersByReverseTurnOrderWithOnePlayer(){
        List<Player> expectedPlayers = new ArrayList<>();
        expectedPlayers.add(p2);

        players = new ArrayList<>();
        players.add(p2);

        round = new Round(9,players.size()*2, players, draftPool);
        List<Player> playersByReverseTurnOrder = round.getPlayersByReverseTurnOrder();

        assertEquals(expectedPlayers, playersByReverseTurnOrder);
    }

    @Test
    public void testGetPlayerForFirstTurn(){
        Player expectedPlayer = p1;

        round = new Round(0,players.size()*2, players, draftPool);
        List<Player> playersByReverseTurnOrder = round.getPlayersByReverseTurnOrder();
        Player firstPlayer = playersByReverseTurnOrder.get(0);

        assertEquals(expectedPlayer, firstPlayer);
    }

    @Test
    public void testGetPlayerForLastTurn(){
        Player expectedPlayer = p2;

        round = new Round(9,players.size()*2, players, draftPool);
        List<Player> playersByReverseTurnOrder = round.getPlayersByReverseTurnOrder();
        Player lastPlayer = playersByReverseTurnOrder.get(0);

        assertEquals(expectedPlayer, lastPlayer);
    }

    @Test
    public void testFirstTurnOfRoundHasZeroIndex(){
        round = new Round(0,players.size()*2, players, draftPool);

        int currentTurnNumber = round.getCurrentTurn().getNumber();

        assertEquals(0,currentTurnNumber);
    }

    @Test
    public void testNextTurnFromFirstTurnOfRound(){
        round = new Round(0,players.size()*2, players, draftPool);

        try {
            round.nextTurn();
        } catch (NoMoreTurnsAvailableException e) {
            fail();
        }

        int currentTurnNumber = round.getCurrentTurn().getNumber();

        assertEquals(1,currentTurnNumber);
    }

    @Test
    public void testNextTurnWhenNoMoreTurnsAvailable(){
        int numberOfTurnsPerRound = players.size()*2;

        round = new Round(0,numberOfTurnsPerRound, players, draftPool);

        try {
            for(int i = 0; i < numberOfTurnsPerRound-1; i++) {
                round.nextTurn();
            }
        } catch (NoMoreTurnsAvailableException e) {
            fail();
        }

        try{
            round.nextTurn();
            fail();
        }catch (NoMoreTurnsAvailableException e){}
    }

    @Test
    public void getNumber(){
        int roundNumber = 7;
        round = new Round(roundNumber,players.size()*2, players, draftPool);

        assertEquals(roundNumber,round.getNumber());
    }

    @Test
    public void getDraftPool(){
        draftPool = new DraftPool();
        round = new Round(0,players.size()*2, players, draftPool);

        assertEquals(draftPool, round.getDraftPool());
    }





}