package edu.uoc.pac3;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class FlightTest {

    @Test
    public void test1() {
        Flight flight1;
        try{
            ClassLoader classLoader = this.getClass().getClassLoader();
            File file = new File(classLoader.getResource("flight1.csv").getFile());
            flight1 = new Flight(file);
            Passenger[] passengers = flight1.getPassengers();
            assertEquals("David Garcia", passengers[0].getName());
            assertEquals("25A648", passengers[0].getPassportNumber());
            assertEquals(30, passengers[0].getAge());
            assertTrue(passengers[0].hasSpecialNeeds());

            assertEquals("Fran Manez", passengers[1].getName());
            assertEquals("B12325", passengers[1].getPassportNumber());
            assertEquals(25, passengers[1].getAge());
            assertFalse(passengers[1].hasSpecialNeeds());

            assertEquals("Jordina Torrents", passengers[2].getName());
            assertEquals("J98010", passengers[2].getPassportNumber());
            assertEquals(30, passengers[2].getAge());
            assertFalse(passengers[2].hasSpecialNeeds());

            assertEquals("Romualdo Moreno", passengers[3].getName());
            assertEquals("Z0324", passengers[3].getPassportNumber());
            assertEquals(45, passengers[3].getAge());
            assertFalse(passengers[3].hasSpecialNeeds());

            assertEquals("Leticia Alonso", passengers[4].getName());
            assertEquals("LA9191", passengers[4].getPassportNumber());
            assertEquals(34, passengers[4].getAge());
            assertFalse(passengers[4].hasSpecialNeeds());

            assertEquals("Werner Nahle", passengers[5].getName());
            assertEquals("89237Y", passengers[5].getPassportNumber());
            assertEquals(39, passengers[5].getAge());
            assertFalse(passengers[5].hasSpecialNeeds());

            assertEquals("Hector Florido", passengers[6].getName());
            assertEquals("H2O13", passengers[6].getPassportNumber());
            assertEquals(36, passengers[6].getAge());
            assertFalse(passengers[6].hasSpecialNeeds());

            assertEquals("Yeray Perez", passengers[7].getName());
            assertEquals("FPY1234", passengers[7].getPassportNumber());
            assertEquals(26, passengers[7].getAge());
            assertFalse(passengers[7].hasSpecialNeeds());

            assertEquals("Marc Albareda", passengers[8].getName());
            assertEquals("12345", passengers[8].getPassportNumber());
            assertEquals(27, passengers[8].getAge());
            assertFalse(passengers[8].hasSpecialNeeds());

            assertEquals("Marta Tarres", passengers[9].getName());
            assertEquals("MTTM31", passengers[9].getPassportNumber());
            assertEquals(43, passengers[9].getAge());
            assertFalse(passengers[9].hasSpecialNeeds());

            assertEquals(10, flight1.getNumPassengers());

        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void test2() {
        Flight flight2;
        try{
            ClassLoader classLoader = this.getClass().getClassLoader();
            File file = new File(classLoader.getResource("flight2.csv").getFile());
            flight2 = new Flight(file);
            Passenger[] passengers = flight2.getPassengers();
            assertEquals("Mickey Mouse", passengers[0].getName());
            assertEquals("DISNEY20", passengers[0].getPassportNumber());
            assertEquals(95, passengers[0].getAge());
            assertFalse(passengers[0].hasSpecialNeeds());

            assertEquals("Donald Duck", passengers[1].getName());
            assertEquals("1234", passengers[1].getPassportNumber());
            assertEquals(78, passengers[1].getAge());
            assertFalse(passengers[1].hasSpecialNeeds());

            assertEquals("Goofy", passengers[2].getName());
            assertEquals("GOD13", passengers[2].getPassportNumber());
            assertEquals(75, passengers[2].getAge());
            assertTrue(passengers[2].hasSpecialNeeds());

            assertEquals("Daisy Duck", passengers[3].getName());
            assertEquals("DD98DD", passengers[3].getPassportNumber());
            assertEquals(70, passengers[3].getAge());
            assertFalse(passengers[3].hasSpecialNeeds());

            assertEquals("Minnie Mouse", passengers[4].getName());
            assertEquals("PARFUM2023", passengers[4].getPassportNumber());
            assertEquals(94, passengers[4].getAge());
            assertFalse(passengers[4].hasSpecialNeeds());

            assertEquals("Pluto", passengers[5].getName());
            assertEquals("B0N3", passengers[5].getPassportNumber());
            assertEquals(90, passengers[5].getAge());
            assertTrue(passengers[5].hasSpecialNeeds());

            assertEquals(6, flight2.getNumPassengers());

        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testWrongFile(){
         assertThrows(FileNotFoundException.class, () -> new Flight(new File("D")));
    }

}
