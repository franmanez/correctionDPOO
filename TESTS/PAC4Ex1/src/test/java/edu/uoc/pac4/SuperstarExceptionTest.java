package edu.uoc.pac4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class SuperstarExceptionTest {

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Fields definition")
    public void checkFieldsSanity() {
        final Class<SuperstarException> ownClass = SuperstarException.class;

        //check attribute fields
        assertEquals(10, ownClass.getDeclaredFields().length);

        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredField("MSG_ERR_BIRTH_NAME_NULL").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("MSG_ERR_BIRTH_NAME_NULL").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("MSG_ERR_BIRTH_NAME_NULL").getModifiers()));

            assertTrue(Modifier.isPublic(ownClass.getDeclaredField("MSG_ERR_BIRTH_NAME_LENGTH").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("MSG_ERR_BIRTH_NAME_LENGTH").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("MSG_ERR_BIRTH_NAME_LENGTH").getModifiers()));

            assertTrue(Modifier.isPublic(ownClass.getDeclaredField("MSG_ERR_BIRTH_NAME_NUMBERS").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("MSG_ERR_BIRTH_NAME_NUMBERS").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("MSG_ERR_BIRTH_NAME_NUMBERS").getModifiers()));

            assertTrue(Modifier.isPublic(ownClass.getDeclaredField("MSG_ERR_BIRTH_DATE").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("MSG_ERR_BIRTH_DATE").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("MSG_ERR_BIRTH_DATE").getModifiers()));

            assertTrue(Modifier.isPublic(ownClass.getDeclaredField("MSG_ERR_BIRTHPLACE_NULL").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("MSG_ERR_BIRTHPLACE_NULL").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("MSG_ERR_BIRTHPLACE_NULL").getModifiers()));

            assertTrue(Modifier.isPublic(ownClass.getDeclaredField("MSG_ERR_BIRTHPLACE_LENGTH").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("MSG_ERR_BIRTHPLACE_LENGTH").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("MSG_ERR_BIRTHPLACE_LENGTH").getModifiers()));

            assertTrue(Modifier.isPublic(ownClass.getDeclaredField("MSG_ERR_HEIGHT").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("MSG_ERR_HEIGHT").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("MSG_ERR_HEIGHT").getModifiers()));

            assertTrue(Modifier.isPublic(ownClass.getDeclaredField("MSG_ERR_WEIGHT").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("MSG_ERR_WEIGHT").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("MSG_ERR_WEIGHT").getModifiers()));


            assertTrue(Modifier.isPublic(ownClass.getDeclaredField("MSG_ERR_RING_NAME_NULL").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("MSG_ERR_RING_NAME_NULL").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("MSG_ERR_RING_NAME_NULL").getModifiers()));

            assertTrue(Modifier.isPublic(ownClass.getDeclaredField("MSG_ERR_RING_NAME_LENGTH").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("MSG_ERR_RING_NAME_LENGTH").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("MSG_ERR_RING_NAME_LENGTH").getModifiers()));
        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    public void checkMethodsSanity() {
        final Class<SuperstarException> ownClass = SuperstarException.class;

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