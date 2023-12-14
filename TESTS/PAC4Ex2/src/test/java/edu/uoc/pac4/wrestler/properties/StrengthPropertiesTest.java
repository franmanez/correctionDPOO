package edu.uoc.pac4.wrestler.properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class StrengthPropertiesTest {

    StrengthProperties strengthProperties;

    @BeforeEach
    void setUp() {
        strengthProperties = new StrengthProperties(50, 60);
    }

    @Test
    void getArmPower() {
        assertEquals(50, strengthProperties.getArmPower());

        strengthProperties = new StrengthProperties(85, 60);
        assertEquals(85, strengthProperties.getArmPower());

        strengthProperties = new StrengthProperties(0, 60);
        assertEquals(0, strengthProperties.getArmPower());

        strengthProperties = new StrengthProperties(100, 60);
        assertEquals(100, strengthProperties.getArmPower());

        strengthProperties = new StrengthProperties(-85, 60);
        assertEquals(50, strengthProperties.getArmPower());

        strengthProperties = new StrengthProperties(185, 60);
        assertEquals(50, strengthProperties.getArmPower());
    }

    @Test
    void getLegPower() {
        assertEquals(60, strengthProperties.getLegPower());

        strengthProperties = new StrengthProperties(50, 96);
        assertEquals(96, strengthProperties.getLegPower());

        strengthProperties = new StrengthProperties(50, 0);
        assertEquals(0, strengthProperties.getLegPower());

        strengthProperties = new StrengthProperties(50, 100);
        assertEquals(100, strengthProperties.getLegPower());

        strengthProperties = new StrengthProperties(50, -96);
        assertEquals(50, strengthProperties.getLegPower());

        strengthProperties = new StrengthProperties(50, 196);
        assertEquals(50, strengthProperties.getLegPower());
    }

    @Test
    void getOverall() {
        assertEquals(55, strengthProperties.getOverall());

        strengthProperties = new StrengthProperties(40, 80);
        assertEquals(60, strengthProperties.getOverall());

        strengthProperties = new StrengthProperties(100, 0);
        assertEquals(50, strengthProperties.getOverall());

        strengthProperties = new StrengthProperties(65, 80);
        assertEquals(72.5, strengthProperties.getOverall(), 0.1);
    }

    @Test
    void size() {
        assertEquals(2, strengthProperties.size());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Fields definition")
    public void checkFieldsSanity() {
        final Class<StrengthProperties> ownClass = StrengthProperties.class;

        //check attribute fields
        assertEquals(2, ownClass.getDeclaredFields().length);
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    public void checkMethodsSanity() {
        final Class<StrengthProperties> ownClass = StrengthProperties.class;
        final Class<WrestlerProperties> wrestlerPropertiesClass = WrestlerProperties.class;

        //StrengthProperties implements WrestlerProperties interface
        assertTrue(wrestlerPropertiesClass.isAssignableFrom(ownClass));

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);

        assertEquals(6, ownClass.getDeclaredMethods().length);
        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredConstructor(double.class, double.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getOverall").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("size").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getArmPower").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("setArmPower", double.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getLegPower").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("setLegPower", double.class).getModifiers()));
        }catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }
}