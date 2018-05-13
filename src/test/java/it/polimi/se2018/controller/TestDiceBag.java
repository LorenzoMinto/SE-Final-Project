package it.polimi.se2018.controller;

import it.polimi.se2018.model.Dice;
import it.polimi.se2018.model.DiceColors;
import it.polimi.se2018.utils.BadBehaviourRuntimeException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TestDiceBag {

    @Test
    public void testGetDices1() {
        DiceBag diceBag = new DiceBag(10);
        try{
            diceBag.getDices(51);
        } catch(BadBehaviourRuntimeException e){
            return;
        }
        fail();
    }

    @Test
    public void testGetDices2() {
        DiceBag diceBag = new DiceBag(1);
        List<Dice> dices = diceBag.getDices(5);
        for(Dice dice : dices){
            dice.setValue(3);
        }
        assertTrue(
                dices.contains(new Dice(DiceColors.RED,3))&&
                        dices.contains(new Dice(DiceColors.GREEN,3))&&
                        dices.contains(new Dice(DiceColors.BLUE,3))&&
                        dices.contains(new Dice(DiceColors.PURPLE,3))&&
                        dices.contains(new Dice(DiceColors.YELLOW,3))
        );
    }

    @Test
    public void testAddDice() {
        DiceBag diceBag = new DiceBag(0);
        Dice dice = new Dice(DiceColors.RED,4);
        diceBag.addDice(dice);
        assertTrue(diceBag.getDices(1).get(0).getColor()==DiceColors.RED);
    }
}