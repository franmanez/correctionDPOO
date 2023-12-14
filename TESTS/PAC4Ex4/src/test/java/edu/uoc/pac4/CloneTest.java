package edu.uoc.pac4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CloneTest {

    @Test
    void testClone() {
        Wrestler wrestler1 = new Wrestler("Mercedes Justine Kaestner-Varnado","Mercedes Mone", "Fairfield", "U.S", 10, 20, 20, 40, 10);
        try {
            Wrestler wrestler2 = (Wrestler) wrestler1.clone();
            assertNotEquals(wrestler1, wrestler2);
            assertEquals(wrestler1.getBirthName(),wrestler2.getBirthName());
            assertEquals(wrestler1.getRingName(),wrestler2.getRingName());
            assertNotEquals(wrestler1.getBirthplace(),wrestler2.getBirthplace());
            assertEquals(wrestler1.getBirthplace().getCity(),wrestler2.getBirthplace().getCity());
            assertEquals(wrestler1.getBirthplace().getCountry(),wrestler2.getBirthplace().getCountry());
            assertNotEquals(wrestler1.getProperties(),wrestler2.getProperties());
            assertEquals(wrestler1.getProperties().getStrength(),wrestler2.getProperties().getStrength());
            assertEquals(wrestler1.getProperties().getAgility(),wrestler2.getProperties().getAgility());
            assertEquals(wrestler1.getProperties().getStamina(),wrestler2.getProperties().getStamina());
            assertEquals(wrestler1.getProperties().getTechnique(),wrestler2.getProperties().getTechnique());
            assertEquals(wrestler1.getProperties().getDefense(),wrestler2.getProperties().getDefense());
        } catch (CloneNotSupportedException e) {
            fail("test failed: "+e.getMessage());
        }
    }
}
