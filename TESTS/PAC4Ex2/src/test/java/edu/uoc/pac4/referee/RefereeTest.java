package edu.uoc.pac4.referee;

import edu.uoc.pac4.Gender;
import edu.uoc.pac4.Superstar;
import edu.uoc.pac4.SuperstarException;
import edu.uoc.pac4.wrestler.Wrestler;
import edu.uoc.pac4.wrestler.WrestlerException;
import edu.uoc.pac4.wrestler.styles.HighFlyer;
import edu.uoc.pac4.wrestler.styles.Powerhouse;
import edu.uoc.pac4.wrestler.styles.Striker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RefereeTest {

    private Referee referee;

    @BeforeEach
    void setUp() {
        try {
            referee = new Referee();
        } catch (SuperstarException e) {
            fail("There was an error with the setup: " + e.getMessage());
        }
    }


    @Test
    public void testDecide(){
        try{
            Wrestler undertaker = new Striker("Mark William Calaway", LocalDate.of(1965,3,24),
                    "Houston, Texas, U.S.", Gender.MALE,208,140,"The Undertaker");
            Wrestler johnCena = new Powerhouse("John Felix Anthony Cena", LocalDate.of(1977, 4, 23),
                    "West Newbury, Massachusetts, U.S.", Gender.MALE, 185, 114, "John Cena");
            Wrestler reyMysterio = new HighFlyer("Oscar Gutierrez Rubio", LocalDate.of(1974, 12, 11),
                    "Chula Vista, California, U.S.", Gender.MALE, 168, 79, "Rey Mysterio");

            Wrestler kennyOmega = new HighFlyer("Tyson Smith", LocalDate.of(1983, 10, 16),
                    "Transcona, Winnipeg, Canadá", Gender.MALE, 183, 99, "Kenny Omega");


            assertFalse(referee.decide(undertaker, johnCena));
            assertFalse(referee.decide(johnCena, undertaker));

            //1.5
            johnCena.setEnergy(66);
            assertFalse(referee.decide(johnCena, undertaker));
            assertTrue(referee.decide(undertaker,johnCena));

            //1.2 heavier
            johnCena.setEnergy(80);
            assertFalse(referee.decide(johnCena, undertaker));
            assertTrue(referee.decide(undertaker,johnCena));

            //1.2 heavier
            johnCena.setEnergy(100);
            reyMysterio.setEnergy(80);
            assertTrue(referee.decide(johnCena, reyMysterio));

            //1.2 but not heavier
            kennyOmega.setEnergy(80);
            reyMysterio.setEnergy(100);
            assertFalse(referee.decide(reyMysterio, kennyOmega));

            //1.5
            johnCena.setEnergy(66);
            reyMysterio.setEnergy(100);
            assertTrue(referee.decide(reyMysterio,johnCena));

            //Knockout John Cena
            johnCena.setEnergy(4.99);
            reyMysterio.setEnergy(6);
            assertTrue(referee.decide(reyMysterio,johnCena));
            assertFalse(referee.decide(johnCena, reyMysterio));


        }catch(SuperstarException | WrestlerException e){
            fail("There was an error with decide: " + e.getMessage());
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Fields definition")
    public void checkFieldsSanity() {
        final Class<Referee> ownClass = Referee.class;

        //check attribute fields
        assertEquals(0, ownClass.getDeclaredFields().length);
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    public void checkMethodsSanity() {
        final Class<Referee> ownClass = Referee.class;
        final Class<Superstar> superstarClass = Superstar.class;

        //Referee is a Superstar's subclass
        assertTrue(superstarClass.isAssignableFrom(ownClass));

        //check constructors
        assertEquals(2, ownClass.getDeclaredConstructors().length);

        assertEquals(3, ownClass.getDeclaredMethods().length);
        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredConstructor().getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredConstructor(String.class, LocalDate.class, String.class,
                    Gender.class, double.class, double.class, String.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("decide", Wrestler.class, Wrestler.class).getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("countPinfall", Wrestler.class, Wrestler.class).getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("decideKnockout", Wrestler.class).getModifiers()));
        }catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }
}
