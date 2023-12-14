package edu.uoc.pac4.wrestler.properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class StaminaPropertiesTest {

    StaminaProperties staminaProperties;

    @BeforeEach
    void setUp() {
        staminaProperties = new StaminaProperties(50, 60, 70);
    }

    @Test
    void getBodyDurability() {
        assertEquals(50, staminaProperties.getBodyDurability());

        staminaProperties = new StaminaProperties(85, 60, 70);
        assertEquals(85, staminaProperties.getBodyDurability());

        staminaProperties = new StaminaProperties(0, 60, 70);
        assertEquals(0, staminaProperties.getBodyDurability());

        staminaProperties = new StaminaProperties(100, 60, 70);
        assertEquals(100, staminaProperties.getBodyDurability());

        staminaProperties = new StaminaProperties(-85, 60, 70);
        assertEquals(50, staminaProperties.getBodyDurability());

        staminaProperties = new StaminaProperties(185, 60, 70);
        assertEquals(50, staminaProperties.getBodyDurability());
    }

    @Test
    void getArmDurability() {
        assertEquals(60, staminaProperties.getArmDurability());

        staminaProperties = new StaminaProperties(50, 96, 70);
        assertEquals(96, staminaProperties.getArmDurability());

        staminaProperties = new StaminaProperties(50, 0, 70);
        assertEquals(0, staminaProperties.getArmDurability());

        staminaProperties = new StaminaProperties(50, 100, 70);
        assertEquals(100, staminaProperties.getArmDurability());

        staminaProperties = new StaminaProperties(50, -96, 70);
        assertEquals(50, staminaProperties.getArmDurability());

        staminaProperties = new StaminaProperties(50, 196, 70);
        assertEquals(50, staminaProperties.getArmDurability());
    }

    @Test
    void getLegDurability() {
        assertEquals(70, staminaProperties.getLegDurability());

        staminaProperties = new StaminaProperties(50, 60, 60);
        assertEquals(60, staminaProperties.getLegDurability());

        staminaProperties = new StaminaProperties(50, 60, 0);
        assertEquals(0, staminaProperties.getLegDurability());

        staminaProperties = new StaminaProperties(50, 60, 100);
        assertEquals(100, staminaProperties.getLegDurability());

        staminaProperties = new StaminaProperties(50, 60, -60);
        assertEquals(50, staminaProperties.getLegDurability());

        staminaProperties = new StaminaProperties(50, 60, 260);
        assertEquals(50, staminaProperties.getLegDurability());
    }

    @Test
    void getOverall() {
        assertEquals(60, staminaProperties.getOverall());

        staminaProperties = new StaminaProperties(40, 80, 60);
        assertEquals(60, staminaProperties.getOverall());

        staminaProperties = new StaminaProperties(100, 0, 80);
        assertEquals(60, staminaProperties.getOverall());

        staminaProperties = new StaminaProperties(65, 80, 90);
        assertEquals(78.33, staminaProperties.getOverall(), 0.1);
    }

    @Test
    void size() {
        assertEquals(3, staminaProperties.size());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Fields definition")
    public void checkFieldsSanity() {
        final Class<StaminaProperties> ownClass = StaminaProperties.class;

        //check attribute fields
        assertEquals(3, ownClass.getDeclaredFields().length);
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    public void checkMethodsSanity() {
        final Class<StaminaProperties> ownClass = StaminaProperties.class;
        final Class<WrestlerProperties> wrestlerPropertiesClass = WrestlerProperties.class;

        //StaminaProperties implements WrestlerProperties interface
        assertTrue(wrestlerPropertiesClass.isAssignableFrom(ownClass));

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);

        assertEquals(8, ownClass.getDeclaredMethods().length);
        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredConstructor(double.class, double.class, double.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getOverall").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("size").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getBodyDurability").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("setBodyDurability", double.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getArmDurability").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("setArmDurability", double.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getLegDurability").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("setLegDurability", double.class).getModifiers()));
        }catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }
}