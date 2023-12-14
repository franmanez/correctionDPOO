package edu.uoc.pac4.wrestler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class WrestlerExceptionTest {

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Fields definition")
    public void checkFieldsSanity() {
        final Class<WrestlerException> ownClass = WrestlerException.class;

        //check attribute fields
        assertEquals(3, ownClass.getDeclaredFields().length);

        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredField("MSG_ERR_ATTRIBUTES_VALUES").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("MSG_ERR_ATTRIBUTES_VALUES").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("MSG_ERR_ATTRIBUTES_VALUES").getModifiers()));

            assertTrue(Modifier.isPublic(ownClass.getDeclaredField("MSG_ERR_ATTRIBUTES_MAX_VALUE").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("MSG_ERR_ATTRIBUTES_MAX_VALUE").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("MSG_ERR_ATTRIBUTES_MAX_VALUE").getModifiers()));

            assertTrue(Modifier.isPublic(ownClass.getDeclaredField("MSG_ERR_PROPERTIES_NULL").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("MSG_ERR_PROPERTIES_NULL").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("MSG_ERR_PROPERTIES_NULL").getModifiers()));

        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    public void checkMethodsSanity() {
        final Class<WrestlerException> ownClass = WrestlerException.class;

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);

        try {

            assertTrue(Modifier.isPublic(ownClass.getDeclaredConstructor(String.class).getModifiers()));

        }catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }

}