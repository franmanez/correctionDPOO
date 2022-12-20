package edu.uoc.pac4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class TheropodaTest {

    private final Position position;

    private Theropoda dino;

    public TheropodaTest() {
        position = mock(Position.class);
        try {
            dino = new Theropoda("T Rex", 3300, 4500, Gender.MALE, position);
        } catch (Exception e) {
            e.printStackTrace();
            fail("TheropodaTest failed");
        }
    }

    @Test
    void attackKOTheropoda() {
        Theropoda target;
        try{
             target = new Theropoda("Spinosaurus", 5400, 5000,Gender.FEMALE, position);
            Exception ex = assertThrows(Exception.class, () -> dino.attack(target));
            assertEquals(DinosaurException.MSG_ERR_ATTACKING_SAME_CLASS, ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            fail("attackKOTheropoda failed");
        }
    }

    @Test
    void attackKONotClose(){
        Ornithischian target;
        try{
            //this.getPosition().isCloseTo(target.getPosition(),SPEED)
            target = new Ornithischian("Spinosaurus", 5400, 5000,Gender.FEMALE, position);
            Mockito.when(dino.getPosition().isCloseTo(target.getPosition(),5)).thenReturn(false);
            Exception ex = assertThrows(Exception.class, () -> dino.attack(target));
            assertEquals(DinosaurException.MSG_ERR_ATTACKING_NOT_CLOSE, ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            fail("attackKONotClose failed");
        }
    }

    @Test
    void attackKONoStamina(){
        Ornithischian target;
        try{
            target = new Ornithischian("Triceraptors", 5400, 5000,Gender.FEMALE, position);
            Mockito.when(dino.getPosition().isCloseTo(target.getPosition(),5)).thenReturn(true);
            Exception ex = assertThrows(Exception.class, () -> dino.attack(target));
            assertEquals(DinosaurException.MSG_ERR_ATTACKING_NO_STAMINA, ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            fail("attackKONotClose failed");
        }
    }

    @Test
    void attackOK(){
        Ornithischian target;
        try{
            dino.incStamina(50);
            target = new Ornithischian("Triceraptors", 5400, 5000,Gender.FEMALE, position);
            Mockito.when(dino.getPosition().isCloseTo(target.getPosition(),5)).thenReturn(true);
            dino.attack(target);
            assertTrue(target.isDead());
            assertEquals(44.44,dino.getStamina(),0.1);
        } catch (Exception e) {
            e.printStackTrace();
            fail("attackOK failed");
        }
    }

    @Test
    public void testEatOKWater() {
        Food food = mock(Food.class);
        Mockito.when(food.getType()).thenReturn(FoodType.WATER); //mocking the result
        Mockito.when(food.getEnergy()).thenReturn(10); //mocking the result

        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            System.setOut(new PrintStream(output));
            dino.eat(food);
            assertEquals("Yum, yum", output.toString());
            assertEquals(10, dino.getStamina());
        } catch (DinosaurException e) {
            e.printStackTrace();
            fail("testEatOK failed");
        }
    }

    @Test
    public void testEatOKMeat() {
        Food food = mock(Food.class);
        Mockito.when(food.getType()).thenReturn(FoodType.MEAT); //mocking the result
        Mockito.when(food.getEnergy()).thenReturn(100); //mocking the result

        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            System.setOut(new PrintStream(output));
            dino.eat(food);
            assertEquals("Yum, yum", output.toString());
            assertEquals(100, dino.getStamina());
        } catch (DinosaurException e) {
            e.printStackTrace();
            fail("testEatOK failed");
        }
    }

    @Test
    public void testEatOKFish() {

        Food food = mock(Food.class);
        Mockito.when(food.getType()).thenReturn(FoodType.FISH); //mocking the result
        Mockito.when(food.getEnergy()).thenReturn(85); //mocking the result

        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            System.setOut(new PrintStream(output));
            dino.eat(food);
            assertEquals("Yum, yum", output.toString());
            assertEquals(85, dino.getStamina());
        } catch (DinosaurException e) {
            e.printStackTrace();
            fail("testEatOK failed");
        }
    }

    @Test
    public void testEatKO() {

        Food food = mock(Food.class);
        Mockito.when(food.getType()).thenReturn(FoodType.VEGETABLE); //mocking the result

        Exception ex = assertThrows(Exception.class, () -> dino.eat(food));
        assertEquals(DinosaurException.MSG_ERR_EATING, ex.getMessage());
        assertEquals(0, dino.getStamina());
    }

    @Test
    public void testWalkTrue() {
        Position positionTarget = mock(Position.class);

        Mockito.when(positionTarget.isCloseTo(any(Position.class), anyInt())).thenReturn(true);

        dino.walk(positionTarget);
        assertEquals(positionTarget, dino.getPosition());
    }

    @Test
    public void testWalkFalse() {
        dino.setPosition(new Position(1,2));
        dino.walk(new Position(6,13));
        assertEquals(new Position(6,7), dino.getPosition());

        dino.setPosition(new Position(1,2));
        dino.walk(new Position(0,1));
        assertEquals(new Position(0,1), dino.getPosition());

        dino.setPosition(new Position(1,2));
        dino.walk(new Position(4,6));
        assertEquals(new Position(4,6), dino.getPosition());

        dino.setPosition(new Position(1,2));
        dino.walk(new Position(-4,-6));
        assertEquals(new Position(-4,-3), dino.getPosition());
    }

    @Test
    void roar() {
        assertEquals("Mic mic!!! Grrr!!!", dino.roar());
    }

    @Test
    void testToString() {
        Mockito.when(position.toString()).thenReturn("(2,4)");

        assertEquals("I'm a Theropoda:" + System.lineSeparator() +
                "\tName: T Rex" + System.lineSeparator() +
                        "\tHeight: 3300 cm." + System.lineSeparator() +
                        "\tWeight: 4500.0 kg." + System.lineSeparator() +
                        "\t#Legs: 2" + System.lineSeparator() +
                        "\tPosition: (2,4)" + System.lineSeparator() +
                        "\tLife expectancy: 29.17 years"
                , dino.toString());
    }
}
