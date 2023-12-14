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

class PowerhouseTest {

    Wrestler wrestler;

    @BeforeEach
    void setUp() {
        try {
            wrestler = new Powerhouse("Leati Joseph \"Joe\" Anoa'i", LocalDate.of(1985,5,25),
                    "Pensacola, Florida, U.S.", Gender.MALE,191,130,"Roman Reigns");
            wrestler.setSignature("Spear");
            wrestler.setFinisher("Superman Punch");

            WrestlerProperties strengthProperties = new StrengthProperties(98,95);
            WrestlerProperties agilityProperties = new AgilityProperties(80,80, 60);
            WrestlerProperties staminaProperties = new StaminaProperties(95,95, 95);
            WrestlerProperties techniqueProperties = new TechniqueProperties(85);
            WrestlerProperties defenseProperties = new DefenseProperties(80,85, 80, 97);

            WrestlerProperties[] properties = {strengthProperties,agilityProperties,staminaProperties,techniqueProperties,defenseProperties};
            wrestler.setProperties(properties);
        } catch (SuperstarException | WrestlerException e) {
            fail("There was an error with the setup: " + e.getMessage());
        }
    }

    @Test
    public void testToString() {
        assertEquals("ROMAN REIGNS" + System.lineSeparator() +
                        "\tBirth name: Leati Joseph \"Joe\" Anoa'i" + System.lineSeparator() +
                        "\tBorn: 25-05-1985" + System.lineSeparator() +
                        "\t      Pensacola, Florida, U.S." + System.lineSeparator() +
                        "\tSignature: " + "Spear" + System.lineSeparator() +
                        "\tFinisher: " + "Superman Punch" + System.lineSeparator() +
                        "\tStyle: " + "Powerhouse" + System.lineSeparator() +
                        "\tOverall: 90.0"
                , wrestler.toString());
    }


    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Fields definition")
    public void checkFieldsSanity() {
        final Class<Powerhouse> ownClass = Powerhouse.class;

        //check attribute fields
        assertEquals(0, ownClass.getDeclaredFields().length);
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    public void checkMethodsSanity() {
        final Class<Powerhouse> ownClass = Powerhouse.class;
        final Class<Wrestler> wrestlerClass = Wrestler.class;

        //Powerhouse is a Wrestler's subclass
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