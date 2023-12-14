package edu.uoc.pac4.wrestler.styles;

import edu.uoc.pac4.Gender;
import edu.uoc.pac4.SuperstarException;
import edu.uoc.pac4.wrestler.Wrestler;
import edu.uoc.pac4.wrestler.WrestlerException;
import edu.uoc.pac4.wrestler.properties.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StrikerTest {

    Wrestler wrestler;

    @BeforeEach
    void setUp() {
        try {
            wrestler = new Striker("Mark William Calaway", LocalDate.of(1965,3,24),
                    "Houston, Texas, U.S.", Gender.MALE,208,140,"The Undertaker");
            wrestler.setSignature("Chokeslam");
            wrestler.setFinisher("Tombstone");

            WrestlerProperties strengthProperties = new StrengthProperties(98,95);
            WrestlerProperties agilityProperties = new AgilityProperties(70,60, 50);
            WrestlerProperties staminaProperties = new StaminaProperties(80,80, 80);
            WrestlerProperties techniqueProperties = new TechniqueProperties(85);
            WrestlerProperties defenseProperties = new DefenseProperties(89,85, 80, 97);

            WrestlerProperties[] properties = {strengthProperties,agilityProperties,staminaProperties,techniqueProperties,defenseProperties};
            wrestler.setProperties(properties);
        } catch (SuperstarException | WrestlerException e) {
            fail("There was an error with the setup: " + e.getMessage());
        }
    }

    @Test
    public void testToString() {
        assertEquals("THE UNDERTAKER" + System.lineSeparator() +
                        "\tBirth name: Mark William Calaway" + System.lineSeparator() +
                        "\tBorn: 24-03-1965" + System.lineSeparator() +
                        "\t      Houston, Texas, U.S." + System.lineSeparator() +
                        "\tSignature: " + "Chokeslam" + System.lineSeparator() +
                        "\tFinisher: " + "Tombstone" + System.lineSeparator() +
                        "\tStyle: " + "Striker" + System.lineSeparator() +
                        "\tOverall: 82.0"
                , wrestler.toString());
    }


    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Fields definition")
    public void checkFieldsSanity() {
        final Class<Striker> ownClass = Striker.class;

        //check attribute fields
        assertEquals(0, ownClass.getDeclaredFields().length);
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    public void checkMethodsSanity() {
        final Class<Striker> ownClass = Striker.class;
        final Class<Wrestler> wrestlerClass = Wrestler.class;

        //Striker is a Wrestler's subclass
        assertTrue(wrestlerClass.isAssignableFrom(ownClass));

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);

        assertEquals(1, ownClass.getDeclaredMethods().length);
        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredConstructor(String.class, LocalDate.class, String.class,
                    Gender.class, double.class, double.class, String.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("toString").getModifiers()));
        }catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }



}