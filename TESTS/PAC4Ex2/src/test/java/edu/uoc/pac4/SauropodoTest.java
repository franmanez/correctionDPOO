package edu.uoc.pac4;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;

class SauropodoTest {

    private final Position position;

    private Sauropodo dino;

    public SauropodoTest() {
        position = mock(Position.class);

        try {
            dino = new Sauropodo("Diplodocus", 2000, 9000, Gender.MALE, position);
        } catch (Exception e) {
            e.printStackTrace();
            fail("SauropodoTest failed");
        }
    }

    @Test
    void testRoar() {
        assertEquals("Mic mic!!! Brrr!!!", dino.roar());
    }

    @Test
    public void testEatOKWater() {
        try {
            dino = new Sauropodo("Diplodocus", 2000, 9000, Gender.MALE, position);
        } catch (Exception e) {
            e.printStackTrace();
            fail("SauropodoTest failed");
        }

        Food food = mock(Food.class);
        Mockito.when(food.getType()).thenReturn(FoodType.WATER); //mocking the result
        Mockito.when(food.getEnergy()).thenReturn(10); //mocking the result

        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            System.setOut(new PrintStream(output));
            dino.eat(food);
            assertEquals("Mmmm", output.toString());
            System.setOut(System.out);

            assertEquals(10, dino.getStamina());
        } catch (DinosaurException e) {
            e.printStackTrace();
            fail("testEatOKWater failed");
        }
    }

    @Test
    public void testEatOKVegetable() {
        try {
            dino = new Sauropodo("Diplodocus", 2000, 9000, Gender.MALE, position);
        } catch (Exception e) {
            e.printStackTrace();
            fail("SauropodoTest failed");
        }

        Food food = mock(Food.class);
        Mockito.when(food.getType()).thenReturn(FoodType.VEGETABLE); //mocking the result
        Mockito.when(food.getEnergy()).thenReturn(50); //mocking the result

        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            System.setOut(new PrintStream(output));
            dino.eat(food);
            assertEquals("Mmmm", output.toString());
            assertEquals(50, dino.getStamina());
        } catch (DinosaurException e) {
            e.printStackTrace();
            fail("testEatOKVegetable failed");
        }
    }

    @Test
    public void testEatKO() {
        try {
            dino = new Sauropodo("Diplodocus", 2000, 9000, Gender.MALE, position);
        } catch (Exception e) {
            e.printStackTrace();
            fail("testEatKO failed");
        }

        Food food = mock(Food.class);
        Mockito.when(food.getType()).thenReturn(FoodType.MEAT); //mocking the result

        Exception ex = assertThrows(Exception.class, () -> dino.eat(food));
        assertEquals(DinosaurException.MSG_ERR_EATING, ex.getMessage());
        assertEquals(0, dino.getStamina());
    }

    @Test
    public void testFindNull() {
        try {
            dino = new Sauropodo("Diplodocus", 2000, 9000, Gender.MALE, position);
        } catch (Exception e) {
            e.printStackTrace();
            fail("testFindNull failed");
        }

        assertNull(dino.find(new Food[2]));

        dino.incStamina(20);
        assertNull(dino.find(new Food[2]));
        assertEquals(17,dino.getStamina());
    }

    @Test
    public void testFind(){
        try {
            dino = new Sauropodo("Diplodocus", 2000, 9000, Gender.MALE, position);
        } catch (Exception e) {
            e.printStackTrace();
            fail("testFind failed");
        }

        dino.incStamina(30);

        List<Food> foods = mock(List.class);
        Iterator<Food> foodIterator = mock(Iterator.class);
        Mockito.when(foods.iterator()).thenReturn(foodIterator);
        Mockito.when(foodIterator.hasNext()).thenReturn(true, true, false);

        Food food1 = mock(Food.class);
        Food food2 = mock(Food.class);
        Mockito.when(foodIterator.next()).thenReturn(food1,food2);
        Mockito.when(food1.getPosition()).thenReturn(new Position(1,2));
        Mockito.when(food2.getPosition()).thenReturn(new Position(2,2));

        Mockito.when(position.isCloseTo(food1.getPosition(), 3)).thenReturn(true);
        Food[] f = {food1,food2};
        Position result = dino.find(f);

        assertEquals(1,result.getX());
        assertEquals(2,result.getY());
        assertEquals(27,dino.getStamina());

        Mockito.when(foodIterator.next()).thenReturn(food1,food2);
        Mockito.when(food1.getPosition()).thenReturn(new Position(2,2));
        Mockito.when(food2.getPosition()).thenReturn(new Position(1,2));
        Mockito.when(position.isCloseTo(food1.getPosition(), 3)).thenReturn(true);
        f = new Food[]{food1, food2};
        result = dino.find(f);
        assertEquals(2,result.getX());
        assertEquals(2,result.getY());
        assertEquals(24,dino.getStamina());
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
        assertEquals(new Position(4,5), dino.getPosition());

        dino.setPosition(new Position(1,2));
        dino.walk(new Position(0,1));
        assertEquals(new Position(0,1), dino.getPosition());

        dino.setPosition(new Position(1,2));
        dino.walk(new Position(4,6));
        assertEquals(new Position(4,5), dino.getPosition());

        dino.setPosition(new Position(1,2));
        dino.walk(new Position(-4,-6));
        assertEquals(new Position(-2,-1), dino.getPosition());
    }

    @Test
    void testToString() {
        Mockito.when(position.toString()).thenReturn("(2,4)");

        assertEquals("I'm a Sauropodo:" + System.lineSeparator() +
                "\tName: Diplodocus" + System.lineSeparator() +
                "\tHeight: 2000 cm." + System.lineSeparator() +
                "\tWeight: 9000.0 kg." + System.lineSeparator() +
                "\t#Legs: 4" + System.lineSeparator() +
                "\tPosition: (2,4)" + System.lineSeparator() +
                "\tLife expectancy: 25.5 years", dino.toString());
    }


}
