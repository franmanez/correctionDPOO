package edu.uoc.pac4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SuperstarEqualsStringTest {

    private Superstar superstar1;
    private Superstar superstar2;

    @BeforeEach
    void setUp(){

        try {
            superstar1 = new Superstar("Mark William Calaway", LocalDate.of(1965,3,24),
                    "Houston, Texas, U.S.", Gender.MALE,208,140,"The Undertaker");
            superstar2 = new Superstar("John Felix Anthony Cena", LocalDate.of(1977, 4, 23),
                    "West Newbury, Massachusetts, U.S.", Gender.MALE, 185, 114, "John Cena");
        } catch (Exception e) {
            e.printStackTrace();
            fail("SuperstarEqualsStringTest failed");
        }
    }

    @Test
    public void testToString(){
        assertEquals("THE UNDERTAKER" + System.lineSeparator() +
                "\tBirth name: Mark William Calaway" + System.lineSeparator() +
                "\tBorn: 24-03-1965" + System.lineSeparator() +
                "\t      Houston, Texas, U.S.", superstar1.toString());

        assertEquals("JOHN CENA" + System.lineSeparator() +
                "\tBirth name: John Felix Anthony Cena" + System.lineSeparator() +
                "\tBorn: 23-04-1977" + System.lineSeparator() +
                "\t      West Newbury, Massachusetts, U.S.", superstar2.toString());
    }

    @Test
    public void testEqualsOK1() {
        assertEquals(superstar1, superstar1);
        assertEquals(superstar2, superstar2);
        assertNotEquals(superstar1, superstar2);
        assertNotEquals(superstar2, superstar1);

        superstar2 = superstar1;
        assertEquals(superstar1, superstar2);
        assertEquals(superstar2, superstar1);
    }

    @Test
    public void testEqualsOK2() {
        Superstar superstar3, superstar4;

        try {
            superstar3 = new Superstar("   Mark William Calaway   ", LocalDate.of(1965,3,24),
                    "Ateca, Calatayud, Aragon, Spain", Gender.FEMALE,208,140,"American Badass");

            superstar4 = new Superstar("Mark William Calaway", LocalDate.of(1965,3,24),
                    "Madrid, Spain", Gender.FEMALE,208,140,"Deadman");

            assertEquals(superstar3, superstar3);
            assertEquals(superstar4, superstar4);

            assertEquals(superstar1, superstar3);
            assertEquals(superstar3, superstar1);

            assertEquals(superstar1, superstar4);
            assertEquals(superstar4, superstar1);

            assertEquals(superstar3, superstar4);
            assertEquals(superstar4, superstar3);
        } catch (SuperstarException e) {
            e.printStackTrace();
            fail("testEqualsOK2 failed");
        }
    }

    @Test
    public void testEqualsKO1() {
        assertNotEquals(superstar1, superstar2);
        assertNotEquals(superstar2, superstar1);
    }

    @Test
    public void testEqualsKO2() {
        Superstar superstar3, superstar4, superstar5, superstar6;

        try {
            superstar3 = new Superstar("Mark Calaway", LocalDate.of(1965,3,24),
                    "Houston, Texas, U.S.", Gender.MALE,208,140,"The Undertaker");
            superstar4 = new Superstar("Mark William Calaway", LocalDate.of(1965,12,24),
                    "Houston, Texas, U.S.", Gender.MALE,208,140,"The Undertaker");
            superstar5 =new Superstar("Mark William Calaway", LocalDate.of(1965,3,24),
                    "Houston, Texas, U.S.", Gender.MALE,200,140,"The Undertaker");
            superstar6 =new Superstar("Mark William Calaway", LocalDate.of(1965,3,24),
                    "Houston, Texas, U.S.", Gender.MALE,208,110,"The Undertaker");

            assertNotEquals(superstar1, superstar3);
            assertNotEquals(superstar1, superstar4);
            assertNotEquals(superstar1, superstar5);
            assertNotEquals(superstar1, superstar6);
        } catch (SuperstarException e) {
            e.printStackTrace();
            fail("testEqualsKO2 failed");
        }
    }

    @Test
    public void testEqualsKO3() {
        assertNotEquals(superstar1, null);
        assertNotEquals(null, superstar1);

        assertNotEquals(superstar1, new String("bad"));
        assertNotEquals(new String("bad"), superstar1);
    }
}
