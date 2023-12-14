package edu.uoc.pac4.wrestler;

import edu.uoc.pac4.Gender;
import edu.uoc.pac4.Speakable;
import edu.uoc.pac4.Superstar;
import edu.uoc.pac4.SuperstarException;
import edu.uoc.pac4.wrestler.properties.*;
import edu.uoc.pac4.wrestler.styles.Powerhouse;
import edu.uoc.pac4.wrestler.styles.Technician;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Modifier;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WrestlerTest {


    Technician wrestler;

    @BeforeEach
    void setUp() {
        try {
            wrestler = new Technician("Melissa Cervantes", LocalDate.of(1986, 7, 22), "Tijuana, Mexico",
                    Gender.FEMALE, 160, 54, "Thunder Rosa");
            wrestler.setSignature("Diving Crossbody");
            wrestler.setFinisher("Fire Thunder Driver");

            WrestlerProperties strengthProperties = new StrengthProperties(75, 95);
            WrestlerProperties agilityProperties = new AgilityProperties(85, 85, 80);
            WrestlerProperties staminaProperties = new StaminaProperties(75, 70, 70);
            WrestlerProperties techniqueProperties = new TechniqueProperties(82);
            WrestlerProperties defenseProperties = new DefenseProperties(75, 75, 65, 85);

            WrestlerProperties[] properties = {strengthProperties, agilityProperties, staminaProperties, techniqueProperties, defenseProperties};
            wrestler.setProperties(properties);
        } catch (SuperstarException | WrestlerException e) {
            fail("There was an error with the setup: " + e.getMessage());
        }
    }

    @Test
    void getStrength() {
        assertEquals(10, wrestler.getStrength());
    }

    @Test
    void getAgility() {
        assertEquals(20, wrestler.getAgility());
    }

    @Test
    void getStamina() {
        assertEquals(20, wrestler.getStamina());
    }

    @Test
    void getTechnique() {
        assertEquals(30, wrestler.getTechnique());
    }

    @Test
    void getDefense() {
        assertEquals(20, wrestler.getDefense());
    }

    @Test
    void getEnergy() {
        assertEquals(100, wrestler.getEnergy());
    }

    @Test
    void setEnergy() {
        assertEquals(100, wrestler.getEnergy());

        wrestler.setEnergy(90);
        assertEquals(90, wrestler.getEnergy());

        wrestler.setEnergy(55.6);
        assertEquals(55.6, wrestler.getEnergy());

        wrestler.setEnergy(0);
        assertEquals(0, wrestler.getEnergy());

        wrestler.setEnergy(100);
        assertEquals(100, wrestler.getEnergy());

        wrestler.setEnergy(-90);
        assertEquals(0, wrestler.getEnergy());

        wrestler.setEnergy(100.01);
        assertEquals(100, wrestler.getEnergy());
    }

    @Test
    void getSignature() {
        assertEquals("Diving Crossbody", wrestler.getSignature());
    }

    @Test
    void setSignature() {
        wrestler.setSignature("Cannonball");
        assertEquals("Cannonball", wrestler.getSignature());

        wrestler.setSignature(null);
        assertEquals("Superkick", wrestler.getSignature());
    }

    @Test
    void getFinisher() {
        assertEquals("Fire Thunder Driver", wrestler.getFinisher());
    }

    @Test
    void setFinisher() {
        wrestler.setFinisher("RKO");
        assertEquals("RKO", wrestler.getFinisher());

        wrestler.setFinisher(null);
        assertEquals("Canadian Destroyer", wrestler.getFinisher());
    }

    @Test
    void isFace() {
        assertTrue(wrestler.isFace());
    }

    @Test
    void setFace() {
        assertTrue(wrestler.isFace());

        wrestler.setFace(false);
        assertFalse(wrestler.isFace());

        wrestler.setFace(true);
        assertTrue(wrestler.isFace());
    }

    @Test
    void setProperties() {
        WrestlerProperties strengthProperties = new StrengthProperties(75, 95);
        WrestlerProperties agilityProperties = new AgilityProperties(85, 85, 80);
        WrestlerProperties staminaProperties = new StaminaProperties(75, 70, 70);
        WrestlerProperties techniqueProperties = new TechniqueProperties(82);
        WrestlerProperties defenseProperties = new DefenseProperties(75, 75, 65, 85);

        //Whole NULL
        WrestlerException ex = assertThrows(WrestlerException.class, () -> wrestler.setProperties(null));
        assertEquals(WrestlerException.MSG_ERR_PROPERTIES_NULL, ex.getMessage());


        //OK
        WrestlerProperties[] properties = {strengthProperties, agilityProperties, staminaProperties,
                techniqueProperties, defenseProperties};

        try {
            wrestler.setProperties(properties);
        } catch (WrestlerException e) {
            fail("There was an error with setProperties: " + e.getMessage());
        }

        assertEquals(79, wrestler.getOverall());

        //Some null
        ex = assertThrows(WrestlerException.class, () -> wrestler.setProperties(new WrestlerProperties[]{null, agilityProperties, staminaProperties, techniqueProperties, defenseProperties}));
        assertEquals(WrestlerException.MSG_ERR_PROPERTIES_NULL, ex.getMessage());

        ex = assertThrows(WrestlerException.class, () -> wrestler.setProperties(new WrestlerProperties[]{strengthProperties, null, staminaProperties, techniqueProperties, defenseProperties}));
        assertEquals(WrestlerException.MSG_ERR_PROPERTIES_NULL, ex.getMessage());

        ex = assertThrows(WrestlerException.class, () -> wrestler.setProperties(new WrestlerProperties[]{strengthProperties, agilityProperties, null, techniqueProperties, defenseProperties}));
        assertEquals(WrestlerException.MSG_ERR_PROPERTIES_NULL, ex.getMessage());

        ex = assertThrows(WrestlerException.class, () -> wrestler.setProperties(new WrestlerProperties[]{strengthProperties, agilityProperties, staminaProperties, null, defenseProperties}));
        assertEquals(WrestlerException.MSG_ERR_PROPERTIES_NULL, ex.getMessage());

        ex = assertThrows(WrestlerException.class, () -> wrestler.setProperties(new WrestlerProperties[]{strengthProperties, agilityProperties, staminaProperties, techniqueProperties, null}));
        assertEquals(WrestlerException.MSG_ERR_PROPERTIES_NULL, ex.getMessage());
    }

    @Test
    void getOverall() {
        assertEquals(79, wrestler.getOverall());
    }

    @Test
    void getWeightClass() {
        assertEquals(WeightClass.LIGHT_HEAVYWEIGHT, wrestler.getWeightClass());
    }

    @Test
    public void testSpeak() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        wrestler.speak();
        assertEquals("I'm the best wrestler in the world!!", outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    public void WrestlerWithAttributesNotInRange() {


        WrestlerException ex = assertThrows(WrestlerException.class, () -> new WrestlerForTesting("Melissa Cervantes", LocalDate.of(1986, 7, 22), "Tijuana, Mexico",
                Gender.FEMALE, 160, 54, "Thunder Rosa", 101, 10, 5, 5, 1));
        assertEquals(WrestlerException.MSG_ERR_ATTRIBUTES_VALUES + "strength", ex.getMessage());

        ex = assertThrows(WrestlerException.class, () -> new WrestlerForTesting("Melissa Cervantes", LocalDate.of(1986, 7, 22), "Tijuana, Mexico",
                Gender.FEMALE, 160, 54, "Thunder Rosa", 0, 10, 5, 5, 1));
        assertEquals(WrestlerException.MSG_ERR_ATTRIBUTES_VALUES + "strength", ex.getMessage());

        ex = assertThrows(WrestlerException.class, () -> new WrestlerForTesting("Melissa Cervantes", LocalDate.of(1986, 7, 22), "Tijuana, Mexico",
                Gender.FEMALE, 160, 54, "Thunder Rosa", 10, -10, 5, 5, 1));
        assertEquals(WrestlerException.MSG_ERR_ATTRIBUTES_VALUES + "agility", ex.getMessage());

        ex = assertThrows(WrestlerException.class, () -> new WrestlerForTesting("Melissa Cervantes", LocalDate.of(1986, 7, 22), "Tijuana, Mexico",
                Gender.FEMALE, 160, 54, "Thunder Rosa", 90, 10, 105, 5, 1));
        assertEquals(WrestlerException.MSG_ERR_ATTRIBUTES_VALUES + "stamina", ex.getMessage());

        ex = assertThrows(WrestlerException.class, () -> new WrestlerForTesting("Melissa Cervantes", LocalDate.of(1986, 7, 22), "Tijuana, Mexico",
                Gender.FEMALE, 160, 54, "Thunder Rosa", 90, 10, 5, -35, 1));
        assertEquals(WrestlerException.MSG_ERR_ATTRIBUTES_VALUES + "technique", ex.getMessage());

        ex = assertThrows(WrestlerException.class, () -> new WrestlerForTesting("Melissa Cervantes", LocalDate.of(1986, 7, 22), "Tijuana, Mexico",
                Gender.FEMALE, 160, 54, "Thunder Rosa", 10, 10, 5, 5, -1));
        assertEquals(WrestlerException.MSG_ERR_ATTRIBUTES_VALUES + "defense", ex.getMessage());

        ex = assertThrows(WrestlerException.class, () -> new WrestlerForTesting("Melissa Cervantes", LocalDate.of(1986, 7, 22), "Tijuana, Mexico",
                Gender.FEMALE, 160, 54, "Thunder Rosa", 10, 10, 5, 5, 131));
        assertEquals(WrestlerException.MSG_ERR_ATTRIBUTES_VALUES + "defense", ex.getMessage());
    }

    @Test
    public void WrestlerWithAttributesSumOutRange() {
        WrestlerException ex = assertThrows(WrestlerException.class, () -> new WrestlerForTesting("Melissa Cervantes", LocalDate.of(1986, 7, 22), "Tijuana, Mexico",
                Gender.FEMALE, 160, 54, "Thunder Rosa", 80, 10, 5, 5, 1));
        assertEquals(WrestlerException.MSG_ERR_ATTRIBUTES_MAX_VALUE, ex.getMessage());

        ex = assertThrows(WrestlerException.class, () -> new WrestlerForTesting("Melissa Cervantes", LocalDate.of(1986, 7, 22), "Tijuana, Mexico",
                Gender.FEMALE, 160, 54, "Thunder Rosa", 10, 10, 5, 5, 1));
        assertEquals(WrestlerException.MSG_ERR_ATTRIBUTES_MAX_VALUE, ex.getMessage());
    }

    @Test
    public void testToString() {
        try {
            WrestlerForTesting thunderRosa = new WrestlerForTesting("Melissa Cervantes", LocalDate.of(1986, 7, 22), "Tijuana, Mexico",
                    Gender.FEMALE, 160, 54, "Thunder Rosa", 70, 10, 5, 5, 10);

            assertEquals("THUNDER ROSA" + System.lineSeparator() +
                    "\tBirth name: Melissa Cervantes" + System.lineSeparator() +
                    "\tBorn: 22-07-1986" + System.lineSeparator() +
                    "\t      Tijuana, Mexico" + System.lineSeparator() +
                    "\tSignature: Superkick" + System.lineSeparator() +
                    "\tFinisher: Canadian Destroyer" + System.lineSeparator() +
                    "\tOverall: 0.0", thunderRosa.toString());

        } catch (SuperstarException | WrestlerException ex) {
            fail("There was an error with toString: " + ex.getMessage());
        }
    }

    @Test
    public void testCompareTo() {
        try {
            Wrestler romanReigns = new Powerhouse("Leati Joseph \"Joe\" Anoa'i", LocalDate.of(1985, 5, 25),
                    "Pensacola, Florida, U.S.", Gender.MALE, 191, 130, "Roman Reigns");
            romanReigns.setSignature("Spear");
            romanReigns.setFinisher("Superman Punch");

            WrestlerProperties strengthProperties = new StrengthProperties(98, 95);
            WrestlerProperties agilityProperties = new AgilityProperties(80, 80, 60);
            WrestlerProperties staminaProperties = new StaminaProperties(95, 95, 95);
            WrestlerProperties techniqueProperties = new TechniqueProperties(85);
            WrestlerProperties defenseProperties = new DefenseProperties(80, 85, 80, 97);

            WrestlerProperties[] properties = {strengthProperties, agilityProperties, staminaProperties, techniqueProperties, defenseProperties};
            romanReigns.setProperties(properties);

            assertEquals(0, wrestler.compareTo(wrestler));
            assertEquals(0, romanReigns.compareTo(romanReigns));
            assertEquals(-11, wrestler.compareTo(romanReigns));
            assertEquals(11, romanReigns.compareTo(wrestler));

            assertThrows(NullPointerException.class, () -> wrestler.compareTo(null));


        } catch (SuperstarException | WrestlerException ex) {
            fail("There was an error with compareTo: " + ex.getMessage());
        }
    }


    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Class definition")
    public void checkClassSanity() {
        final Class<Wrestler> ownClass = Wrestler.class;
        final Class<Superstar> superstarClass = Superstar.class;
        final Class<Speakable> speakableClass = Speakable.class;
        final Class<Comparable> comparableClass = Comparable.class;

        assertTrue(Modifier.isAbstract(ownClass.getModifiers()));
        assertTrue(superstarClass.isAssignableFrom(ownClass));
        assertTrue(speakableClass.isAssignableFrom(ownClass));
        assertTrue(comparableClass.isAssignableFrom(ownClass));
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Fields definition")
    public void checkFieldsSanity() {
        final Class<Wrestler> ownClass = Wrestler.class;

        //check attribute fields
        assertEquals(15, ownClass.getDeclaredFields().length);

        try {
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("strength").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("agility").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("stamina").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("technique").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("defense").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("energy").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("signature").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("finisher").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("isFace").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("properties").getModifiers()));

            assertTrue(Modifier.isPublic(ownClass.getDeclaredField("ATTRIBUTES_MIN_VALUE").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("ATTRIBUTES_MIN_VALUE").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("ATTRIBUTES_MIN_VALUE").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredField("ATTRIBUTES_MAX_VALUE").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("ATTRIBUTES_MAX_VALUE").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("ATTRIBUTES_MAX_VALUE").getModifiers()));

            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("ATTRIBUTES_SUM_VALUE").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("ATTRIBUTES_SUM_VALUE").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("ATTRIBUTES_SUM_VALUE").getModifiers()));

            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("DEFAULT_SIGNATURE").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("DEFAULT_SIGNATURE").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("DEFAULT_SIGNATURE").getModifiers()));

            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("DEFAULT_FINISHER").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("DEFAULT_FINISHER").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("DEFAULT_FINISHER").getModifiers()));
        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    public void checkMethodsSanity() {
        final Class<Wrestler> ownClass = Wrestler.class;

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);

        assertEquals(27, ownClass.getDeclaredMethods().length);
        try {
            assertTrue(Modifier.isProtected(ownClass.getDeclaredConstructor(String.class, LocalDate.class, String.class,
                    Gender.class, double.class, double.class, String.class, double.class, double.class, double.class, double.class, double.class).getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("isAttributeInRange", double.class).getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("areAttributesSumInRange").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getStrength").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("setStrength", double.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getAgility").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("setAgility", double.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getStamina").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("setStamina", double.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getTechnique").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("setTechnique", double.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getDefense").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("setDefense", double.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getEnergy").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setEnergy", double.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getSignature").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setSignature", String.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getFinisher").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setFinisher", String.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("isFace").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setFace", boolean.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setProperties", WrestlerProperties[].class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getOverall").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getWeightClass").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("speak").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("toString").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("compareTo", Wrestler.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }


    class WrestlerForTesting extends Wrestler {


        protected WrestlerForTesting(String birthName, LocalDate birthDate, String birthplace, Gender gender, double height, double weight, String ringName, double strength, double agility, double stamina, double technique, double defense) throws SuperstarException, WrestlerException {
            super(birthName, birthDate, birthplace, gender, height, weight, ringName, strength, agility, stamina, technique, defense);
        }
    }


}