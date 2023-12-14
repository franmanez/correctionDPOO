package edu.uoc.pac4.wrestler.properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class TechniquePropertiesTest {

    TechniqueProperties techniqueProperties;

    @BeforeEach
    void setUp() {
        techniqueProperties = new TechniqueProperties(60);
    }

    @Test
    void getHolds() {
        assertEquals(60, techniqueProperties.getHolds());

        techniqueProperties = new TechniqueProperties(85);
        assertEquals(85, techniqueProperties.getHolds());

        techniqueProperties = new TechniqueProperties(0);
        assertEquals(0, techniqueProperties.getHolds());

        techniqueProperties = new TechniqueProperties(100);
        assertEquals(100, techniqueProperties.getHolds());

        techniqueProperties = new TechniqueProperties(101);
        assertEquals(50, techniqueProperties.getHolds());

        techniqueProperties = new TechniqueProperties(-10);
        assertEquals(50, techniqueProperties.getHolds());
    }

    @Test
    void getOverall() {
        assertEquals(60, techniqueProperties.getHolds());

        techniqueProperties = new TechniqueProperties(85);
        assertEquals(85, techniqueProperties.getOverall());

        techniqueProperties = new TechniqueProperties(0);
        assertEquals(0, techniqueProperties.getOverall());

        techniqueProperties = new TechniqueProperties(100);
        assertEquals(100, techniqueProperties.getOverall());

        techniqueProperties = new TechniqueProperties(101);
        assertEquals(50, techniqueProperties.getOverall());

        techniqueProperties = new TechniqueProperties(-10);
        assertEquals(50, techniqueProperties.getOverall());
    }

    @Test
    void size() {
        assertEquals(1, techniqueProperties.size());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Fields definition")
    public void checkFieldsSanity() {
        final Class<TechniqueProperties> ownClass = TechniqueProperties.class;

        //check attribute fields
        assertEquals(1, ownClass.getDeclaredFields().length);
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    public void checkMethodsSanity() {
        final Class<TechniqueProperties> ownClass = TechniqueProperties.class;
        final Class<WrestlerProperties> wrestlerPropertiesClass = WrestlerProperties.class;

        //TechniqueProperties implements WrestlerProperties interface
        assertTrue(wrestlerPropertiesClass.isAssignableFrom(ownClass));

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);

        assertEquals(4, ownClass.getDeclaredMethods().length);
        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredConstructor(double.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getOverall").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("size").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getHolds").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("setHolds", double.class).getModifiers()));
        }catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }
}