package edu.uoc.pac4;

import edu.uoc.pac4.manager.Manager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SuperstarTest {

    Superstar superstar;

    @BeforeEach
    void setUp() {
        try {
            superstar = new Manager();
        } catch (SuperstarException e) {
            fail("There was an error with the setup: " + e.getMessage());
        }
    }

    @Test
    @Tag("minimum")
    void getBirthName() {
        assertEquals("Anonymous", superstar.getBirthName());
    }

    @Test
    @Tag("minimum")
    void setBirthName() {
        assertEquals("Anonymous", superstar.getBirthName());
        try {
            //Regular name
            superstar.setBirthName("Dylan Mark Postl");
            assertEquals("Dylan Mark Postl", superstar.getBirthName());

            //Regular name
            superstar.setBirthName("Mark William Calaway");
            assertEquals("Mark William Calaway", superstar.getBirthName());

            //1-char name
            superstar.setBirthName("D");
            assertEquals("D", superstar.getBirthName());

            //60-char name
            superstar.setBirthName("Mark William Calaway The Undertaker American Badass Punisher");
            assertEquals("Mark William Calaway The Undertaker American Badass Punisher", superstar.getBirthName());

            //Name with trail
            superstar.setBirthName("Mark William Calaway ");
            assertEquals("Mark William Calaway", superstar.getBirthName());

            //Name with white space in the beginning
            superstar.setBirthName(" Mark William Calaway");
            assertEquals("Mark William Calaway", superstar.getBirthName());

            //Name with white spaces in the beginning and in the end
            superstar.setBirthName(" Mark William Calaway ");
            assertEquals("Mark William Calaway", superstar.getBirthName());

            //Name with white spaces in the beginning and in the end
            superstar.setBirthName("                 Mark William Calaway           ");
            assertEquals("Mark William Calaway", superstar.getBirthName());
        } catch (SuperstarException e) {
            fail("There was an error with setBirthName: " + e.getMessage());
        }

        //Null
        SuperstarException ex = assertThrows(SuperstarException.class, () -> superstar.setBirthName(null));
        assertEquals(SuperstarException.MSG_ERR_BIRTH_NAME_NULL, ex.getMessage());

        //Empty
        ex = assertThrows(SuperstarException.class, () -> superstar.setBirthName(""));
        assertEquals(SuperstarException.MSG_ERR_BIRTH_NAME_LENGTH, ex.getMessage());

        //No chars
        ex = assertThrows(SuperstarException.class, () -> superstar.setBirthName("             "));
        assertEquals(SuperstarException.MSG_ERR_BIRTH_NAME_LENGTH, ex.getMessage());

        //Over 60 chars (=64)
        ex = assertThrows(SuperstarException.class, () -> superstar.setBirthName("Mark William Calaway The Undertaker American Badass The Punisher"));
        assertEquals(SuperstarException.MSG_ERR_BIRTH_NAME_LENGTH, ex.getMessage());

        //61 chars
        ex = assertThrows(SuperstarException.class, () -> superstar.setBirthName("Mark William Calaway The Undertaker American Badass  Punisher"));
        assertEquals(SuperstarException.MSG_ERR_BIRTH_NAME_LENGTH, ex.getMessage());

        //Name with numbers
        ex = assertThrows(SuperstarException.class, () -> superstar.setBirthName("EC3"));
        assertEquals(SuperstarException.MSG_ERR_BIRTH_NAME_NUMBERS, ex.getMessage());

        //Name with numbers
        ex = assertThrows(SuperstarException.class, () -> superstar.setBirthName("E34C"));
        assertEquals(SuperstarException.MSG_ERR_BIRTH_NAME_NUMBERS, ex.getMessage());

        //Name with numbers
        ex = assertThrows(SuperstarException.class, () -> superstar.setBirthName("3EC"));
        assertEquals(SuperstarException.MSG_ERR_BIRTH_NAME_NUMBERS, ex.getMessage());

        //Name with numbers
        ex = assertThrows(SuperstarException.class, () -> superstar.setBirthName("55E34C66"));
        assertEquals(SuperstarException.MSG_ERR_BIRTH_NAME_NUMBERS, ex.getMessage());
    }

    @Test
    @Tag("minimum")
    void getBirthDate() {
        assertEquals(LocalDate.now().minusDays(1), superstar.getBirthDate());
    }

    @Test
    @Tag("minimum")
    void setBirthDate() {
        assertEquals(LocalDate.now().minusDays(1), superstar.getBirthDate());

        try {
            superstar.setBirthDate(LocalDate.of(2000, 8, 1));
            assertEquals(LocalDate.of(2000, 8, 1), superstar.getBirthDate());
        } catch (SuperstarException e) {
            fail("There was an error with setBirthDate: " + e.getMessage());
        }

        //Null
        SuperstarException ex = assertThrows(SuperstarException.class, () -> superstar.setBirthDate(null));
        assertEquals(SuperstarException.MSG_ERR_BIRTH_DATE, ex.getMessage());

        //Today
        ex = assertThrows(SuperstarException.class, () -> superstar.setBirthDate(LocalDate.now()));
        assertEquals(SuperstarException.MSG_ERR_BIRTH_DATE, ex.getMessage());

        //Tomorrow
        ex = assertThrows(SuperstarException.class, () -> superstar.setBirthDate(LocalDate.now().plusDays(1)));
        assertEquals(SuperstarException.MSG_ERR_BIRTH_DATE, ex.getMessage());

        //Future
        ex = assertThrows(SuperstarException.class, () -> superstar.setBirthDate(LocalDate.of(2101, 9, 1)));
        assertEquals(SuperstarException.MSG_ERR_BIRTH_DATE, ex.getMessage());
    }

    @Test
    @Tag("minimum")
    void getBirthplace() {
        assertEquals("New York", superstar.getBirthplace());
    }

    @Test
    @Tag("minimum")
    void setBirthplace() {

        try {
            superstar.setBirthplace("Barcelona, 13 Percebe St., Spain");
            assertEquals("Barcelona, 13 Percebe St., Spain", superstar.getBirthplace());

            //Birthplace with white spaces
            superstar.setBirthplace("         Barcelona, 13 Percebe St., Spain          ");
            assertEquals("Barcelona, 13 Percebe St., Spain", superstar.getBirthplace());

            //1-char
            superstar.setBirthplace("A");
            assertEquals("A", superstar.getBirthplace());

            //80-char
            superstar.setBirthplace("12345678901234567890123456789012345678901234567890123456789012345678901234567890");
            assertEquals("12345678901234567890123456789012345678901234567890123456789012345678901234567890", superstar.getBirthplace());
        } catch (SuperstarException e) {
            fail("There was an error with setBirthplace: " + e.getMessage());
        }

        //Null
        SuperstarException ex = assertThrows(SuperstarException.class, () -> superstar.setBirthplace(null));
        assertEquals(SuperstarException.MSG_ERR_BIRTHPLACE_NULL, ex.getMessage());

        //Empty
        ex = assertThrows(SuperstarException.class, () -> superstar.setBirthplace(""));
        assertEquals(SuperstarException.MSG_ERR_BIRTHPLACE_LENGTH, ex.getMessage());

        //No chars
        ex = assertThrows(SuperstarException.class, () -> superstar.setBirthplace("       "));
        assertEquals(SuperstarException.MSG_ERR_BIRTHPLACE_LENGTH, ex.getMessage());

        //Over 80 chars
        ex = assertThrows(SuperstarException.class, () -> superstar.setBirthplace("Horta, Horta-Guinardo, Barcelona, Barcelones, Catalonia, Spain, Europe, World, Earth"));
        assertEquals(SuperstarException.MSG_ERR_BIRTHPLACE_LENGTH, ex.getMessage());
    }

    @Test
    @Tag("minimum")
    void getGender() {
        assertEquals(Gender.FEMALE, superstar.getGender());
    }

    @Test
    @Tag("minimum")
    void setGender() {
        assertEquals(Gender.FEMALE, superstar.getGender());
        superstar.setGender(Gender.MALE);
        assertEquals(Gender.MALE, superstar.getGender());
        superstar.setGender(Gender.FEMALE);
        assertEquals(Gender.FEMALE, superstar.getGender());
    }

    @Test
    @Tag("minimum")
    void getHeight() {
        assertEquals(168, superstar.getHeight());
    }

    @Test
    @Tag("minimum")
    void setHeight() {
        try {
            superstar.setHeight(200.67);
            assertEquals(200.67, superstar.getHeight());

            superstar.setHeight(150);
            assertEquals(150, superstar.getHeight());

            superstar.setHeight(100.01);
            assertEquals(100.01, superstar.getHeight());
        } catch (SuperstarException e) {
            fail("There was an error with setHeight: " + e.getMessage());
        }

        //Less than 100 cm
        SuperstarException ex = assertThrows(SuperstarException.class, () -> superstar.setHeight(50));
        assertEquals(SuperstarException.MSG_ERR_HEIGHT, ex.getMessage());

        //Less than or equal to 100 cm
        ex = assertThrows(SuperstarException.class, () -> superstar.setHeight(100));
        assertEquals(SuperstarException.MSG_ERR_HEIGHT, ex.getMessage());
    }

    @Test
    @Tag("minimum")
    void getWeight() {
        assertEquals(54, superstar.getWeight());
    }

    @Test
    @Tag("minimum")
    void setWeight() {
        try {
            superstar.setWeight(200.67);
            assertEquals(200.67, superstar.getWeight());

            superstar.setWeight(134.6);
            assertEquals(134.6, superstar.getWeight());

            superstar.setWeight(84.85);
            assertEquals(84.85, superstar.getWeight());
        } catch (SuperstarException e) {
            fail("There was an error with setWeight: " + e.getMessage());
        }

        //Less than 30 kg
        SuperstarException ex = assertThrows(SuperstarException.class, () -> superstar.setWeight(29.9));
        assertEquals(SuperstarException.MSG_ERR_WEIGHT, ex.getMessage());

        //Less than or equal to 30 kg
        ex = assertThrows(SuperstarException.class, () -> superstar.setWeight(30));
        assertEquals(SuperstarException.MSG_ERR_WEIGHT, ex.getMessage());
    }

    @Test
    @Tag("minimum")
    void getRingName() {
        assertEquals("Superstar", superstar.getRingName());
    }

    @Test
    @Tag("minimum")
    void setRingName() {
        try {
            //Regular ring name
            superstar.setRingName("A-Kid");
            assertEquals("A-Kid", superstar.getRingName());

            //Ring name with white spaces
            superstar.setRingName(" A-Kid ");
            assertEquals("A-Kid", superstar.getRingName());

            //Regular ring name
            superstar.setRingName("EC3");
            assertEquals("EC3", superstar.getRingName());

            //1-char
            superstar.setRingName("A");
            assertEquals("A", superstar.getRingName());

            //60-char
            superstar.setRingName("123456789012345678901234567890123456789012345678901234567890");
            assertEquals("123456789012345678901234567890123456789012345678901234567890", superstar.getRingName());
        } catch (SuperstarException e) {
            fail("There was an error with setRingName: " + e.getMessage());
        }

        //Null
        SuperstarException ex = assertThrows(SuperstarException.class, () -> superstar.setRingName(null));
        assertEquals(SuperstarException.MSG_ERR_RING_NAME_NULL, ex.getMessage());

        //Empty
        ex = assertThrows(SuperstarException.class, () -> superstar.setRingName(""));
        assertEquals(SuperstarException.MSG_ERR_RING_NAME_LENGTH, ex.getMessage());

        //No chars
        ex = assertThrows(SuperstarException.class, () -> superstar.setRingName("       "));
        assertEquals(SuperstarException.MSG_ERR_RING_NAME_LENGTH, ex.getMessage());

        //Over 60 chars
        ex = assertThrows(SuperstarException.class, () -> superstar.setRingName("EC32EC32EC32EC32EC32EC32EC32EC32EC32EC32EC32EC32EC32EC32EC32E"));
        assertEquals(SuperstarException.MSG_ERR_RING_NAME_LENGTH, ex.getMessage());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Class definition")
    public void checkClassSanity() {
        final Class<Superstar> ownClass = Superstar.class;
        assertTrue(Modifier.isAbstract(ownClass.getModifiers()));
    }


    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Fields definition")
    public void checkFieldsSanity() {
        final Class<Superstar> ownClass = Superstar.class;

        //check attribute fields
        assertEquals(15, ownClass.getDeclaredFields().length);

        try {
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("birthName").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("birthDate").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("birthplace").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("gender").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("height").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("weight").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("ringName").getModifiers()));

            assertTrue(Modifier.isPublic(ownClass.getDeclaredField("BIRTH_NAME_MIN_LENGTH").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("BIRTH_NAME_MIN_LENGTH").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("BIRTH_NAME_MIN_LENGTH").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredField("BIRTH_NAME_MAX_LENGTH").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("BIRTH_NAME_MAX_LENGTH").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("BIRTH_NAME_MAX_LENGTH").getModifiers()));

            assertTrue(Modifier.isPublic(ownClass.getDeclaredField("BIRTHPLACE_MIN_LENGTH").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("BIRTHPLACE_MIN_LENGTH").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("BIRTHPLACE_MIN_LENGTH").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredField("BIRTHPLACE_MAX_LENGTH").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("BIRTHPLACE_MAX_LENGTH").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("BIRTHPLACE_MAX_LENGTH").getModifiers()));

            assertTrue(Modifier.isPublic(ownClass.getDeclaredField("RING_NAME_MIN_LENGTH").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("RING_NAME_MIN_LENGTH").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("RING_NAME_MIN_LENGTH").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredField("RING_NAME_MAX_LENGTH").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("RING_NAME_MAX_LENGTH").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("RING_NAME_MAX_LENGTH").getModifiers()));

            assertTrue(Modifier.isPublic(ownClass.getDeclaredField("HEIGHT_MIN_VALUE").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("HEIGHT_MIN_VALUE").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("HEIGHT_MIN_VALUE").getModifiers()));

            assertTrue(Modifier.isPublic(ownClass.getDeclaredField("WEIGHT_MIN_VALUE").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("WEIGHT_MIN_VALUE").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("WEIGHT_MIN_VALUE").getModifiers()));

        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    public void checkMethodsSanity() {
        final Class<Superstar> ownClass = Superstar.class;

        //check constructors
        assertEquals(2, ownClass.getDeclaredConstructors().length);

        assertEquals(16, ownClass.getDeclaredMethods().length);
        try {
            assertTrue(Modifier.isProtected(ownClass.getDeclaredConstructor().getModifiers()));
            assertTrue(Modifier.isProtected(ownClass.getDeclaredConstructor(String.class, LocalDate.class, String.class,
                    Gender.class, double.class, double.class, String.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getBirthName").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setBirthName", String.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getBirthDate").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setBirthDate", LocalDate.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getBirthplace").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setBirthplace", String.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getGender").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setGender", Gender.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getHeight").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setHeight", double.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getWeight").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setWeight", double.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getRingName").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setRingName", String.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("equals", Object.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("toString").getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }
}
