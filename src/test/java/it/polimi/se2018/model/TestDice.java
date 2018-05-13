package it.polimi.se2018.model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestDice {

    private Dice dice;

    @Before
    public  void initializeDice(){
        dice = new Dice(DiceColors.GREEN,2);
    }

    @Test
    public void testSetValue() {
        int value = 5;
        dice.setValue(value);

        assertEquals(value,dice.getValue());
    }

    @Test
    public void testSetIllegalValue(){
        try{
            dice.setValue(7);
            fail();
        }catch (IllegalArgumentException e){}
    }

    @Test
    public void testSetNegativeValue(){
        try{
            dice.setValue(-3);
            fail();
        }catch (IllegalArgumentException e){}
    }


    @Test
    public void testRollOverFromOne() {
        dice.setValue(1);
        dice.rollOver();

        assertEquals(6,dice.getValue());
    }

    @Test
    public void testRollOverFromTwo() {
        dice.setValue(2);
        dice.rollOver();

        assertEquals(5,dice.getValue());
    }

    @Test
    public void testRollOverFromThree() {
        dice.setValue(3);
        dice.rollOver();

        assertEquals(4,dice.getValue());
    }

    @Test
    public void testRollOverFromFour() {
        dice.setValue(4);
        dice.rollOver();

        assertEquals(3,dice.getValue());
    }

    @Test
    public void testRollOverFromFive() {
        dice.setValue(5);
        dice.rollOver();

        assertEquals(2,dice.getValue());
    }

    @Test
    public void testRollOverFromSix() {
        dice.setValue(6);
        dice.rollOver();

        assertEquals(1,dice.getValue());
    }

    @Test
    public void testIncrementValue() {
        dice.setValue(5);
        dice.incrementValue();

        assertEquals(6,dice.getValue());
    }

    @Test
    public void testIncrementValueFromMaxValue() {
        dice.setValue(6);

        assertFalse(dice.incrementValue());
    }

    @Test
    public void testDecrementValue() {
        dice.setValue(2);
        dice.decrementValue();

        assertEquals(1,dice.getValue());
    }

    @Test
    public void testDecrementValueFromMin() {
        dice.setValue(1);

        assertFalse(dice.decrementValue());
    }

    @Test
    public void testToString() {
        dice.setValue(2);
        assertEquals("2:G",dice.toString());
    }

    @Test
    public void testEquals() {
        assertTrue(dice.equals(dice));
        assertTrue(dice.equals(dice.copy()));
        assertFalse(dice.equals(new Cell()));
    }

    @Test
    public void testHashCode() {
    }
}