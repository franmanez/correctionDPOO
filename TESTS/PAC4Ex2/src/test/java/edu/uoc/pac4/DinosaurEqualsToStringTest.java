package edu.uoc.pac4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class DinosaurEqualsToStringTest {

    private final Position position;

    private Dinosaur dino1;
    private Dinosaur dino2;

    public DinosaurEqualsToStringTest() {
        position = mock(Position.class); // Mock object

        try {
            dino1 = new Ornithischian("Triceraptors", 1300, 6000, Gender.FEMALE, position);
            dino2 = new Ornithischian("Iguanodon", 900, 5000.4, Gender.MALE, position);
        } catch (Exception e) {
            e.printStackTrace();
            fail("DinosaurTest failed");
        }
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
                , dino1.toString());
    }

    @Test
    public void testEqualsOK1() {
        assertEquals(dino1, dino1);
        assertEquals(dino2, dino2);
        assertNotEquals(dino1, dino2);
        assertNotEquals(dino2, dino1);

        dino2 = dino1;
        assertEquals(dino1, dino2);
        assertEquals(dino2, dino1);
    }

    @Test
    public void testEqualsOK2() {
        Dinosaur dino3, dino4;

        try {
            dino3 = new Ornithischian("Triceraptors", 1300, 6000, Gender.MALE, position);
            dino4 = new Ornithischian("Triceraptors", 1300, 6000, Gender.FEMALE, position);

            assertEquals(dino3, dino3);
            assertEquals(dino4, dino4);

            assertEquals(dino1, dino3);
            assertEquals(dino3, dino1);

            assertEquals(dino1, dino4);
            assertEquals(dino4, dino1);

            assertEquals(dino3, dino4);
            assertEquals(dino4, dino3);
        } catch (DinosaurException e) {
            e.printStackTrace();
            fail("testEqualsOK2 failed");
        }
    }

    @Test
    public void testEqualsKO1() {
        assertNotEquals(dino1, dino2);
        assertNotEquals(dino2, dino1);
    }

    @Test
    public void testEqualsKO2() {
        Dinosaur dino3, dino4, dino5;

        try {
            dino3 = new Ornithischian("Triceraptors 1", 1300, 6000, Gender.MALE, position);
            dino4 = new Ornithischian("Triceraptors", 2300, 6000, Gender.FEMALE, position);
            dino5 = new Ornithischian("Triceraptors", 1300, 6050, Gender.FEMALE, position);
            assertNotEquals(dino1, dino3);
            assertNotEquals(dino1, dino4);
            assertNotEquals(dino1, dino5);
        } catch (DinosaurException e) {
            e.printStackTrace();
            fail("testEqualsKO2 failed");
        }
    }
}
