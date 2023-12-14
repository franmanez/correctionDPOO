package edu.uoc.pac4.manager;

import edu.uoc.pac4.Gender;
import edu.uoc.pac4.Superstar;
import edu.uoc.pac4.SuperstarException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Modifier;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    private Manager manager;

    @BeforeEach
    void setUp() {
        try {
            manager = new Manager();
        } catch (SuperstarException e) {
            fail("There was an error with the setup: " + e.getMessage());
        }
    }

    @Test
    @Tag("minimum")
    public void testSpeak() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        manager.speak();
        assertEquals("I'm a manager!!", outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Fields definition")
    public void checkFieldsSanity() {
        final Class<Manager> ownClass = Manager.class;

        //check attribute fields
        assertEquals(0, ownClass.getDeclaredFields().length);
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    public void checkMethodsSanity() {
        final Class<Manager> ownClass = Manager.class;
        final Class<Superstar> superstarClass = Superstar.class;

        //Manager is a Superstar's subclass
        assertTrue(superstarClass.isAssignableFrom(ownClass));

        //check constructors
        assertEquals(2, ownClass.getDeclaredConstructors().length);

        assertEquals(1, ownClass.getDeclaredMethods().length);
        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredConstructor().getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredConstructor(String.class, LocalDate.class, String.class,
                    Gender.class, double.class, double.class, String.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("speak").getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }

}