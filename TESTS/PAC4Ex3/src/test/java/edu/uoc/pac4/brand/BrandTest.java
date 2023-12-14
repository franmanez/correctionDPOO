package edu.uoc.pac4.brand;

import edu.uoc.pac4.Gender;
import edu.uoc.pac4.Superstar;
import edu.uoc.pac4.SuperstarException;
import edu.uoc.pac4.referee.Referee;
import edu.uoc.pac4.wrestler.Wrestler;
import edu.uoc.pac4.wrestler.WrestlerException;
import edu.uoc.pac4.wrestler.properties.*;
import edu.uoc.pac4.wrestler.styles.HighFlyer;
import edu.uoc.pac4.wrestler.styles.Powerhouse;
import edu.uoc.pac4.wrestler.styles.Striker;
import edu.uoc.pac4.wrestler.styles.Technician;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BrandTest {

    private Brand raw;
    private Wrestler bryanDanielson, undertaker, romanReigns, axiom, thunderRosa;
    private Referee aubreyEdwards, mikeChioda;

    @BeforeEach
    void setUp() {
        raw = new Brand("Raw", Day.MONDAY, "USA Network");

        try {
            //BRYAN DANIELSON
            bryanDanielson = new Technician("Bryan Lloyd Danielson", LocalDate.of(1981, 5, 22),
                    "Aberdeen, Washington, U.S.", Gender.MALE, 178, 95, "Bryan Danielson");
            bryanDanielson.setSignature("Busaiku Knee");
            bryanDanielson.setFinisher("YES! Lock");
            WrestlerProperties strengthProperties = new StrengthProperties(75, 95);
            WrestlerProperties agilityProperties = new AgilityProperties(75, 75, 65);
            WrestlerProperties staminaProperties = new StaminaProperties(80, 75, 90);
            WrestlerProperties techniqueProperties = new TechniqueProperties(98);
            WrestlerProperties defenseProperties = new DefenseProperties(75, 75, 60, 83);
            bryanDanielson.setProperties(new WrestlerProperties[]{strengthProperties, agilityProperties, staminaProperties, techniqueProperties, defenseProperties});


            //THE UNDERTAKER
            undertaker = new Striker("Mark William Calaway", LocalDate.of(1965, 3, 24),
                    "Houston, Texas, U.S.", Gender.MALE, 208, 140, "The Undertaker");
            undertaker.setSignature("Chokeslam");
            undertaker.setFinisher("Tombstone");
            strengthProperties = new StrengthProperties(98, 95);
            agilityProperties = new AgilityProperties(70, 60, 50);
            staminaProperties = new StaminaProperties(80, 80, 80);
            techniqueProperties = new TechniqueProperties(85);
            defenseProperties = new DefenseProperties(89, 85, 80, 97);
            undertaker.setProperties(new WrestlerProperties[]{strengthProperties, agilityProperties, staminaProperties, techniqueProperties, defenseProperties});

            //ROMAN REIGNS
            romanReigns = new Powerhouse("Leati Joseph \"Joe\" Anoa'i", LocalDate.of(1985, 5, 25),
                    "Pensacola, Florida, U.S.", Gender.MALE, 191, 130, "Roman Reigns");
            romanReigns.setSignature("Spear");
            romanReigns.setFinisher("Superman Punch");
            strengthProperties = new StrengthProperties(98, 95);
            agilityProperties = new AgilityProperties(80, 80, 60);
            staminaProperties = new StaminaProperties(95, 95, 95);
            techniqueProperties = new TechniqueProperties(85);
            defenseProperties = new DefenseProperties(80, 85, 80, 97);
            romanReigns.setProperties(new WrestlerProperties[]{strengthProperties, agilityProperties, staminaProperties, techniqueProperties, defenseProperties});

            //AXIOM
            axiom = new HighFlyer("Carlos Ruiz", LocalDate.of(1997, 5, 9),
                    "Madrid, Spain", Gender.MALE, 175, 75, "Axiom");
            axiom.setSignature("Spanish Fly");
            axiom.setFinisher("Springboard Moonsault DDT");
            strengthProperties = new StrengthProperties(60, 65);
            agilityProperties = new AgilityProperties(80, 80, 85);
            staminaProperties = new StaminaProperties(70, 65, 65);
            techniqueProperties = new TechniqueProperties(60);
            defenseProperties = new DefenseProperties(65, 65, 60, 60);
            axiom.setProperties(new WrestlerProperties[]{strengthProperties, agilityProperties, staminaProperties, techniqueProperties, defenseProperties});

            //THUNDER ROSA
            thunderRosa = new Technician("Melissa Cervantes", LocalDate.of(1986, 7, 22), "Tijuana, Mexico",
                    Gender.FEMALE, 160, 54, "Thunder Rosa");
            thunderRosa.setSignature("Diving Crossbody");
            thunderRosa.setFinisher("Fire Thunder Driver");

            strengthProperties = new StrengthProperties(75, 95);
            agilityProperties = new AgilityProperties(85, 85, 80);
            staminaProperties = new StaminaProperties(75, 70, 70);
            techniqueProperties = new TechniqueProperties(82);
            defenseProperties = new DefenseProperties(75, 75, 65, 85);
            thunderRosa.setProperties(new WrestlerProperties[]{strengthProperties, agilityProperties, staminaProperties, techniqueProperties, defenseProperties});

            //AUBREY EDWARDS
            aubreyEdwards = new Referee("Brittany Aubert", LocalDate.of(197, 3, 9),
                    "Seattle, Washington, U.S.", Gender.FEMALE, 170, 68, "Aubrey Edwards");

            mikeChioda = new Referee("Michael Joseph Chioda", LocalDate.of(1966, 8, 1),
                    "Willingboro Township, New Jersey, U.S.", Gender.MALE, 183, 79, "Mike Chioda");

        } catch (SuperstarException | WrestlerException e) {
            fail("There was an error with the setup: " + e.getMessage());
        }
    }


    @Test
    void getName() {
        assertEquals("Raw", raw.getName());
    }

    @Test
    void setName() {
        assertEquals("Raw", raw.getName());

        raw.setName("Smackdown");
        assertEquals("Smackdown", raw.getName());

        raw.setName("Dynamite");
        assertEquals("Dynamite", raw.getName());

        raw.setName(null);
        assertEquals("Dynamite", raw.getName());
    }

    @Test
    void getDay() {
        assertEquals(Day.MONDAY, raw.getDay());
    }

    @Test
    void setDay() {
        assertEquals(Day.MONDAY, raw.getDay());

        raw.setDay(Day.FRIDAY);
        assertEquals(Day.FRIDAY, raw.getDay());

        raw.setDay(null);
        assertEquals(Day.FRIDAY, raw.getDay());
    }

    @Test
    void getTvChannel() {
        assertEquals("USA Network", raw.getTvChannel());
    }

    @Test
    void setTvChannel() {
        assertEquals("USA Network", raw.getTvChannel());

        raw.setTvChannel("DAZN");
        assertEquals("DAZN", raw.getTvChannel());

        raw.setTvChannel(null);
        assertEquals("DAZN", raw.getTvChannel());
    }

    @Test
    void getRoster(){
        raw.addSuperstar(bryanDanielson);
        raw.addSuperstar(aubreyEdwards);
        raw.addSuperstar(undertaker);

        List<Superstar> list = raw.getRoster();
        assertTrue(list instanceof List);
        assertTrue(list instanceof ArrayList);
        assertTrue(list.getClass().getSimpleName().equals("ArrayList"));
        assertEquals(3,list.size());
        assertTrue(list.indexOf(bryanDanielson) >= 0);
        assertTrue(list.indexOf(aubreyEdwards) >= 0);
        assertTrue(list.indexOf(undertaker) >= 0);
    }

    @Test
    void addSuperstar() {
        assertTrue(raw.addSuperstar(undertaker));
        assertTrue(raw.isSuperstar(undertaker));

        assertTrue(raw.addSuperstar(aubreyEdwards));
        assertTrue(raw.isSuperstar(undertaker));
        assertTrue(raw.isSuperstar(aubreyEdwards));

        //Repeated wrestler
        assertFalse(raw.addSuperstar(undertaker));

        //Cannot add null
        assertFalse(raw.addSuperstar(null));
    }

    @Test
    void removeSuperstar() {
        raw.addSuperstar(bryanDanielson);
        raw.addSuperstar(aubreyEdwards);
        raw.addSuperstar(undertaker);

        assertTrue(raw.isSuperstar(aubreyEdwards));
        assertTrue(raw.removeSuperstar(aubreyEdwards));
        assertFalse(raw.isSuperstar(aubreyEdwards));

        //No null (because addSuperstar doesn't allow it)
        assertFalse(raw.removeSuperstar(null));

        //Superstar is not in the set
        assertFalse(raw.removeSuperstar(romanReigns));

        //Superstar is not in the set
        assertFalse(raw.removeSuperstar(aubreyEdwards));
    }

    @Test
    void isEmpty() {
        assertTrue(raw.isEmpty());
        raw.addSuperstar(bryanDanielson);
        assertFalse(raw.isEmpty());
    }

    @Test
    void testToString() {
        assertEquals("Raw every Monday on USA Network", raw.toString());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Fields definition")
    public void checkFieldsSanity() {
        final Class<Brand> ownClass = Brand.class;

        //check attribute fields
        assertEquals(4, ownClass.getDeclaredFields().length);

        try {
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("name").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("day").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("tvChannel").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("roster").getModifiers()));
        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    public void checkMethodsSanity() {
        final Class<Brand> ownClass = Brand.class;

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);

        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredConstructor(String.class, Day.class, String.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getName").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setName", String.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getDay").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setDay", Day.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getTvChannel").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setTvChannel", String.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getRoster").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("addSuperstar", Superstar.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("removeSuperstar", Superstar.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("isEmpty").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("isSuperstar", Superstar.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getNumReferees").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getWeightAverage").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getTopWrestlers", int.class).getModifiers()));
             assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setDefaultMoves").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("toString").getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }


}