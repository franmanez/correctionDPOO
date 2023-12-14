package edu.uoc.pac4.wrestler.properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class DefensePropertiesTest {

    DefenseProperties defenseProperties;

    @BeforeEach
    void setUp() {
        defenseProperties = new DefenseProperties(50, 60, 70, 90);
    }

    @Test
    void getStrikeReversal() {
        assertEquals(50, defenseProperties.getStrikeReversal());

        defenseProperties = new DefenseProperties(85, 60, 70, 90);
        assertEquals(85, defenseProperties.getStrikeReversal());

        defenseProperties = new DefenseProperties(0, 60, 70, 90);
        assertEquals(0, defenseProperties.getStrikeReversal());

        defenseProperties = new DefenseProperties(100, 60, 70, 90);
        assertEquals(100, defenseProperties.getStrikeReversal());

        defenseProperties = new DefenseProperties(-85, 60, 70, 90);
        assertEquals(50, defenseProperties.getStrikeReversal());

        defenseProperties = new DefenseProperties(185, 60, 70, 90);
        assertEquals(50, defenseProperties.getStrikeReversal());
    }

    @Test
    void getGrappleReversal() {
        assertEquals(60, defenseProperties.getGrappleReversal());

        defenseProperties = new DefenseProperties(50, 96, 70, 90);
        assertEquals(96, defenseProperties.getGrappleReversal());

        defenseProperties = new DefenseProperties(50, 0, 70, 90);
        assertEquals(0, defenseProperties.getGrappleReversal());

        defenseProperties = new DefenseProperties(50, 100, 70, 90);
        assertEquals(100, defenseProperties.getGrappleReversal());

        defenseProperties = new DefenseProperties(50, -96, 70, 90);
        assertEquals(50, defenseProperties.getGrappleReversal());

        defenseProperties = new DefenseProperties(50, 196, 70, 90);
        assertEquals(50, defenseProperties.getGrappleReversal());
    }

    @Test
    void getAerialReversal() {
        assertEquals(70, defenseProperties.getAerialReversal());

        defenseProperties = new DefenseProperties(50, 60, 60, 90);
        assertEquals(60, defenseProperties.getAerialReversal());

        defenseProperties = new DefenseProperties(50, 60, 0, 90);
        assertEquals(0, defenseProperties.getAerialReversal());

        defenseProperties = new DefenseProperties(50, 60, 100, 90);
        assertEquals(100, defenseProperties.getAerialReversal());

        defenseProperties = new DefenseProperties(50, 60, -60, 90);
        assertEquals(50, defenseProperties.getAerialReversal());

        defenseProperties = new DefenseProperties(50, 60, 260, 90);
        assertEquals(50, defenseProperties.getAerialReversal());
    }

    @Test
    void getOverall() {
        assertEquals(67.5, defenseProperties.getOverall());

        defenseProperties = new DefenseProperties(40, 80, 60, 70);
        assertEquals(62.5, defenseProperties.getOverall());

        defenseProperties = new DefenseProperties(100, 0, 80, 20);
        assertEquals(50, defenseProperties.getOverall());

        defenseProperties = new DefenseProperties(65, 80, 80, 55);
        assertEquals(70, defenseProperties.getOverall(), 0.1);
    }

    @Test
    void size() {
        assertEquals(4, defenseProperties.size());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Fields definition")
    public void checkFieldsSanity() {
        final Class<DefenseProperties> ownClass = DefenseProperties.class;

        //check attribute fields
        assertEquals(4, ownClass.getDeclaredFields().length);
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    public void checkMethodsSanity() {
        final Class<DefenseProperties> ownClass = DefenseProperties.class;
        final Class<WrestlerProperties> wrestlerPropertiesClass = WrestlerProperties.class;

        //DefenseProperties implements WrestlerProperties interface
        assertTrue(wrestlerPropertiesClass.isAssignableFrom(ownClass));

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);

        assertEquals(10, ownClass.getDeclaredMethods().length);
        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredConstructor(double.class, double.class, double.class, double.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getOverall").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("size").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getStrikeReversal").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("setStrikeReversal", double.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getGrappleReversal").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("setGrappleReversal", double.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getAerialReversal").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("setAerialReversal", double.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getSubmissionDefense").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("setSubmissionDefense", double.class).getModifiers()));
        }catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }
}