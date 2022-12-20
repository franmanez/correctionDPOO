package edu.uoc.pac4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class OrnithischianTest {

    private final Position position;

    private Ornithischian dino;

    public OrnithischianTest() {
        position = mock(Position.class);

        try {
            dino = new Ornithischian("Triceraptors", 1300, 6000, Gender.FEMALE, position);
        } catch (Exception e) {
            e.printStackTrace();
            fail("OrnithischianTest failed");
        }
    }

    @Test
    public void testRoar() {
        assertEquals("Orrr!!!", dino.roar());
    }

    @Test
    public void testEatOKVegetable() {
        try {
            dino = new Ornithischian("Triceraptors", 1300, 6000, Gender.FEMALE, position);
        } catch (Exception e) {
            e.printStackTrace();
            fail("testEatOK failed");
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
            fail("testEatOK failed");
        }
    }

    @Test
    public void testEatOKWater() {
        try {
            dino = new Ornithischian("Triceraptors", 1300, 6000, Gender.FEMALE, position);
        } catch (Exception e) {
            e.printStackTrace();
            fail("testEatOK failed");
        }

        Food food = mock(Food.class);
        Mockito.when(food.getType()).thenReturn(FoodType.WATER); //mocking the result
        Mockito.when(food.getEnergy()).thenReturn(10); //mocking the result

        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            System.setOut(new PrintStream(output));
            dino.eat(food);
            assertEquals("Mmmm", output.toString());
            assertEquals(10, dino.getStamina());
        } catch (DinosaurException e) {
            e.printStackTrace();
            fail("testEatOK failed");
        }
    }


    @Test
    public void testEatKO() {
        try {
            dino = new Ornithischian("Triceraptors", 1300, 6000, Gender.FEMALE, position);
        } catch (Exception e) {
            e.printStackTrace();
            fail("testEatKO failed");
        }

        Food food = mock(Food.class);
        Mockito.when(food.getType()).thenReturn(FoodType.FISH); //mocking the result

        Exception ex = assertThrows(Exception.class, () -> dino.eat(food));
        assertEquals(DinosaurException.MSG_ERR_EATING, ex.getMessage());
        assertEquals(0, dino.getStamina());
    }

    @Test
    public void testFindNull() {
        try {
            dino = new Ornithischian("Triceraptors", 1300, 6000, Gender.FEMALE, position);
        } catch (Exception e) {
            e.printStackTrace();
            fail("testFindNull failed");
        }

        dino.incStamina(10);

        assertNull(dino.find(new Food[2]));

        assertEquals(5,dino.getStamina());
    }

    @Test
    public void testFind(){
        try {
            dino = new Ornithischian("Triceraptors", 1300, 6000, Gender.FEMALE, position);
        } catch (Exception e) {
            e.printStackTrace();
            fail("testFindNull failed");
        }



        Mockito.when(position.getX()).thenReturn(0);
        Mockito.when(position.getY()).thenReturn(0);

        Food food1 = mock(Food.class);
        Food food2 = mock(Food.class);

        Mockito.when(food1.getPosition()).thenReturn(new Position(1,2));

        Mockito.when(food2.getPosition()).thenReturn(new Position(5,10));

        assertNull(dino.find(new Food[]{food1,food2}));

        dino.incStamina(20);
        Position result = dino.find(new Food[]{food1,food2});
        assertEquals(1,result.getX());
        assertEquals(2,result.getY());
        assertEquals(15,dino.getStamina());

        result = dino.find(new Food[]{food2,food1});
        assertEquals(1,result.getX());
        assertEquals(2,result.getY());
        assertEquals(10,dino.getStamina());
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
       assertEquals(new Position(2,3), dino.getPosition());

        dino.setPosition(new Position(1,2));
        dino.walk(new Position(0,1));
        assertEquals(new Position(0,1), dino.getPosition());

        dino.setPosition(new Position(1,2));
        dino.walk(new Position(4,6));
        assertEquals(new Position(2,3), dino.getPosition());

        dino.setPosition(new Position(1,2));
        dino.walk(new Position(-4,-6));
        assertEquals(new Position(0,1), dino.getPosition());
    }

    @Test
    public void testToString() {
        Mockito.when(position.toString()).thenReturn("(2,4)");

        assertEquals("I'm an Ornithischian:"+System.lineSeparator() +
                "\tName: Triceraptors" + System.lineSeparator() +
                        "\tHeight: 1300 cm." + System.lineSeparator() +
                        "\tWeight: 6000.0 kg." + System.lineSeparator() +
                        "\t#Legs: 4" + System.lineSeparator() +
                        "\tPosition: (2,4)" + System.lineSeparator() +
                        "\tLife expectancy: 22.9 years"
                , dino.toString());
    }

}
