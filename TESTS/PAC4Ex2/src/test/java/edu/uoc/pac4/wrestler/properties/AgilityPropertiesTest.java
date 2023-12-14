package edu.uoc.pac4.wrestler.properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class AgilityPropertiesTest {

    AgilityProperties agilityProperties;

    @BeforeEach
    void setUp() {
        agilityProperties = new AgilityProperties(50, 60, 70);
    }

    @Test
    void getSpeed() {
        assertEquals(50, agilityProperties.getSpeed());

        agilityProperties = new AgilityProperties(85, 60, 70);
        assertEquals(85, agilityProperties.getSpeed());

        agilityProperties = new AgilityProperties(0, 60, 70);
        assertEquals(0, agilityProperties.getSpeed());

        agilityProperties = new AgilityProperties(100, 60, 70);
        assertEquals(100, agilityProperties.getSpeed());

        agilityProperties = new AgilityProperties(-85, 60, 70);
        assertEquals(50, agilityProperties.getSpeed());

        agilityProperties = new AgilityProperties(185, 60, 70);
        assertEquals(50, agilityProperties.getSpeed());
    }

    @Test
    void getJump() {
        assertEquals(60, agilityProperties.getJump());

        agilityProperties = new AgilityProperties(50, 96, 70);
        assertEquals(96, agilityProperties.getJump());

        agilityProperties = new AgilityProperties(50, 0, 70);
        assertEquals(0, agilityProperties.getJump());

        agilityProperties = new AgilityProperties(50, 100, 70);
        assertEquals(100, agilityProperties.getJump());

        agilityProperties = new AgilityProperties(50, -96, 70);
        assertEquals(50, agilityProperties.getJump());

        agilityProperties = new AgilityProperties(50, 196, 70);
        assertEquals(50, agilityProperties.getJump());
    }

    @Test
    void getAcrobatics() {
        assertEquals(70, agilityProperties.getAcrobatics());

        agilityProperties = new AgilityProperties(50, 60, 60);
        assertEquals(60, agilityProperties.getAcrobatics());

        agilityProperties = new AgilityProperties(50, 60, 0);
        assertEquals(0, agilityProperties.getAcrobatics());

        agilityProperties = new AgilityProperties(50, 60, 100);
        assertEquals(100, agilityProperties.getAcrobatics());

        agilityProperties = new AgilityProperties(50, 60, -60);
        assertEquals(50, agilityProperties.getAcrobatics());

        agilityProperties = new AgilityProperties(50, 60, 260);
        assertEquals(50, agilityProperties.getAcrobatics());
    }

    @Test
    void getOverall() {
        assertEquals(60, agilityProperties.getOverall());

        agilityProperties = new AgilityProperties(40, 80, 60);
        assertEquals(60, agilityProperties.getOverall());

        agilityProperties = new AgilityProperties(100, 0, 80);
        assertEquals(60, agilityProperties.getOverall());

        agilityProperties = new AgilityProperties(65, 80, 90);
        assertEquals(78.33, agilityProperties.getOverall(), 0.1);
    }

    @Test
    void size() {
        assertEquals(3, agilityProperties.size());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Fields definition")
    public void checkFieldsSanity() {
        final Class<AgilityProperties> ownClass = AgilityProperties.class;

        //check attribute fields
        assertEquals(3, ownClass.getDeclaredFields().length);
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    public void checkMethodsSanity() {
        final Class<AgilityProperties> ownClass = AgilityProperties.class;
        final Class<WrestlerProperties> wrestlerPropertiesClass = WrestlerProperties.class;

        //AgilityProperties implements WrestlerProperties interface
        assertTrue(wrestlerPropertiesClass.isAssignableFrom(ownClass));

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);

        assertEquals(8, ownClass.getDeclaredMethods().length);
        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredConstructor(double.class, double.class, double.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getOverall").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("size").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getSpeed").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("setSpeed", double.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getJump").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("setJump", double.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getAcrobatics").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("setAcrobatics", double.class).getModifiers()));
        }catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }
}