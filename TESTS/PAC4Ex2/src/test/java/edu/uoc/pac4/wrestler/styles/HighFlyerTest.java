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

class HighFlyerTest {

    Wrestler wrestler;

    @BeforeEach
    void setUp() {
        try {
            wrestler = new HighFlyer("Carlos Ruiz", LocalDate.of(1997,5,9),
                    "Madrid, Spain", Gender.MALE,175,75,"Axiom");
            wrestler.setSignature("Spanish Fly");
            wrestler.setFinisher("Springboard Moonsault DDT");

            WrestlerProperties strengthProperties = new StrengthProperties(60,65);
            WrestlerProperties agilityProperties = new AgilityProperties(80,80, 85);
            WrestlerProperties staminaProperties = new StaminaProperties(70,65, 65);
            WrestlerProperties techniqueProperties = new TechniqueProperties(60);
            WrestlerProperties defenseProperties = new DefenseProperties(65,65, 60, 60);

            WrestlerProperties[] properties = {strengthProperties,agilityProperties,staminaProperties,techniqueProperties,defenseProperties};
            wrestler.setProperties(properties);
        } catch (SuperstarException | WrestlerException e) {
            fail("There was an error with the setup: " + e.getMessage());
        }
    }

    @Test
    public void testToString() {
        assertEquals("AXIOM" + System.lineSeparator() +
                        "\tBirth name: Carlos Ruiz" + System.lineSeparator() +
                        "\tBorn: 09-05-1997" + System.lineSeparator() +
                        "\t      Madrid, Spain" + System.lineSeparator() +
                        "\tSignature: " + "Spanish Fly" + System.lineSeparator() +
                        "\tFinisher: " + "Springboard Moonsault DDT" + System.lineSeparator() +
                        "\tStyle: " + "High flyer" + System.lineSeparator() +
                        "\tOverall: 69.0"
                , wrestler.toString());
    }


    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Fields definition")
    public void checkFieldsSanity() {
        final Class<HighFlyer> ownClass = HighFlyer.class;

        //check attribute fields
        assertEquals(0, ownClass.getDeclaredFields().length);
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    public void checkMethodsSanity() {
        final Class<HighFlyer> ownClass = HighFlyer.class;
        final Class<Wrestler> wrestlerClass = Wrestler.class;

        //Highflyer is a Wrestler's subclass
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