package edu.uoc.pac4.brand;

import edu.uoc.pac4.Gender;
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
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StreamTest {

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
    void getNumReferees0() {
        assertEquals(0, raw.getNumReferees());
        raw.addSuperstar(bryanDanielson);
        raw.addSuperstar(undertaker);
        raw.addSuperstar(romanReigns);
        raw.addSuperstar(axiom);
        raw.addSuperstar(thunderRosa);
        assertEquals(0, raw.getNumReferees());
    }

    @Test
    void getNumReferees1() {
        raw.addSuperstar(bryanDanielson);
        raw.addSuperstar(undertaker);
        raw.addSuperstar(romanReigns);
        raw.addSuperstar(aubreyEdwards);
        raw.addSuperstar(axiom);
        raw.addSuperstar(thunderRosa);
        assertEquals(1, raw.getNumReferees());
    }

    @Test
    void getNumReferees2() {
        raw.addSuperstar(bryanDanielson);
        raw.addSuperstar(aubreyEdwards);
        raw.addSuperstar(undertaker);
        raw.addSuperstar(romanReigns);
        raw.addSuperstar(axiom);
        raw.addSuperstar(thunderRosa);
        raw.addSuperstar(mikeChioda);
        assertEquals(2, raw.getNumReferees());
    }

    @Test
    void getWeightAverage() {
        assertEquals(0, raw.getWeightAverage());
        raw.addSuperstar(bryanDanielson);
        raw.addSuperstar(aubreyEdwards);
        raw.addSuperstar(undertaker);
        raw.addSuperstar(romanReigns);
        raw.addSuperstar(axiom);
        raw.addSuperstar(thunderRosa);
        raw.addSuperstar(mikeChioda);
        assertEquals(91.57, raw.getWeightAverage(), 0.01);
    }

    @Test
    void setDefaultMoves(){
        raw.addSuperstar(bryanDanielson);
        raw.addSuperstar(aubreyEdwards);
        raw.addSuperstar(undertaker);
        raw.addSuperstar(romanReigns);
        raw.addSuperstar(axiom);
        raw.addSuperstar(thunderRosa);

        raw.setDefaultMoves();

        assertEquals("Superkick", bryanDanielson.getSignature());
        assertEquals("Superkick", undertaker.getSignature());
        assertEquals("Superkick", romanReigns.getSignature());
        assertEquals("Superkick", axiom.getSignature());
        assertEquals("Superkick", thunderRosa.getSignature());

        assertEquals("Canadian Destroyer", bryanDanielson.getFinisher());
        assertEquals("Canadian Destroyer", undertaker.getFinisher());
        assertEquals("Canadian Destroyer", romanReigns.getFinisher());
        assertEquals("Canadian Destroyer", axiom.getFinisher());
        assertEquals("Canadian Destroyer", thunderRosa.getFinisher());
    }

    @Test
    void getTop3Wrestlers() {
        raw.addSuperstar(bryanDanielson);
        raw.addSuperstar(aubreyEdwards);
        raw.addSuperstar(undertaker);
        raw.addSuperstar(romanReigns);
        raw.addSuperstar(axiom);
        raw.addSuperstar(thunderRosa);
        raw.addSuperstar(mikeChioda);

        assertArrayEquals(new Wrestler[]{romanReigns}, raw.getTopWrestlers(1).toArray());
        assertArrayEquals(new Wrestler[]{romanReigns, bryanDanielson}, raw.getTopWrestlers(2).toArray());
        assertArrayEquals(new Wrestler[]{romanReigns, bryanDanielson, undertaker}, raw.getTopWrestlers(3).toArray());
        assertArrayEquals(new Wrestler[]{romanReigns, bryanDanielson, undertaker, thunderRosa}, raw.getTopWrestlers(4).toArray());
        assertArrayEquals(new Wrestler[]{romanReigns, bryanDanielson, undertaker, thunderRosa, axiom}, raw.getTopWrestlers(5).toArray());
    }
}