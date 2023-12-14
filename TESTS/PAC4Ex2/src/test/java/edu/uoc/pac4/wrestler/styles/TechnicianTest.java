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

class TechnicianTest {

    Wrestler wrestler;

    @BeforeEach
    void setUp() {
        try {
            wrestler = new Technician("Bryan Lloyd Danielson", LocalDate.of(1981, 5, 22),
                    "Aberdeen, Washington, U.S.", Gender.MALE, 178, 95, "Bryan Danielson");
            wrestler.setSignature("Busaiku Knee");
            wrestler.setFinisher("YES! Lock");

            WrestlerProperties strengthProperties = new StrengthProperties(75,95);
            WrestlerProperties agilityProperties = new AgilityProperties(75,75, 65);
            WrestlerProperties staminaProperties = new StaminaProperties(80,75, 90);
            WrestlerProperties techniqueProperties = new TechniqueProperties(98);
            WrestlerProperties defenseProperties = new DefenseProperties(75,75, 60, 83);

            WrestlerProperties[] properties = {strengthProperties,agilityProperties,staminaProperties,techniqueProperties,defenseProperties};
            wrestler.setProperties(properties);
        } catch (SuperstarException | WrestlerException e) {
            fail("There was an error with the setup: " + e.getMessage());
        }
    }

    @Test
    public void testToString() {
        assertEquals("BRYAN DANIELSON" + System.lineSeparator() +
                        "\tBirth name: Bryan Lloyd Danielson" + System.lineSeparator() +
                        "\tBorn: 22-05-1981" + System.lineSeparator() +
                        "\t      Aberdeen, Washington, U.S." + System.lineSeparator() +
                        "\tSignature: " + "Busaiku Knee" + System.lineSeparator() +
                        "\tFinisher: " + "YES! Lock" + System.lineSeparator() +
                        "\tStyle: " + "Technician" + System.lineSeparator() +
                        "\tOverall: 83.0"
                , wrestler.toString());
    }


    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Fields definition")
    public void checkFieldsSanity() {
        final Class<Technician> ownClass = Technician.class;

        //check attribute fields
        assertEquals(0, ownClass.getDeclaredFields().length);
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    public void checkMethodsSanity() {
        final Class<Technician> ownClass = Technician.class;
        final Class<Wrestler> wrestlerClass = Wrestler.class;

        //Technician is a Wrestler's subclass
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