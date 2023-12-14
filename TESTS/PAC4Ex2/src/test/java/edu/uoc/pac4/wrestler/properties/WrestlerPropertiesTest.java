package edu.uoc.pac4.wrestler.properties;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class WrestlerPropertiesTest {

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Fields definition")
    public void checkFieldsSanity() {
        final Class<WrestlerProperties> ownClass = WrestlerProperties.class;

        //check attribute fields
        assertEquals(0, ownClass.getDeclaredFields().length);
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    public void checkMethodsSanity() {
        final Class<WrestlerProperties> ownClass = WrestlerProperties.class;

        //check constructors
        assertEquals(0, ownClass.getDeclaredConstructors().length);

        assertEquals(2, ownClass.getDeclaredMethods().length);
        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getOverall").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("size").getModifiers()));
        }catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }

}