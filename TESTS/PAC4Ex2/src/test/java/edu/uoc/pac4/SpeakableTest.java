package edu.uoc.pac4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import static org.junit.jupiter.api.Assertions.*;

class SpeakableTest {

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    public void checkMethodsSanity() {
        final Class<Speakable> ownClass = Speakable.class;

        try {
           assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("speak").getModifiers()));
        }catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }

}