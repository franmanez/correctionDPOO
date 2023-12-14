package edu.uoc.pac4.wrestler;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeightClassTest {

    @Test
    @Tag("minimum")
    void getMin() {
        assertEquals(0,WeightClass.LIGHT_HEAVYWEIGHT.getMin());
        assertEquals(85.01,WeightClass.CRUISERWEIGHT.getMin());
        assertEquals(99.01,WeightClass.HEAVYWEIGHT.getMin());
        assertEquals(120.01,WeightClass.SUPER_HEAVYWEIGHT.getMin());
    }

    @Test
    @Tag("minimum")
    void getMax() {
        assertEquals(85,WeightClass.LIGHT_HEAVYWEIGHT.getMax());
        assertEquals(99,WeightClass.CRUISERWEIGHT.getMax());
        assertEquals(120,WeightClass.HEAVYWEIGHT.getMax());
        assertEquals(Double.POSITIVE_INFINITY,WeightClass.SUPER_HEAVYWEIGHT.getMax());

    }

    @Test
    @Tag("minimum")
    void getWeightClass() {
        assertEquals(WeightClass.LIGHT_HEAVYWEIGHT,WeightClass.getWeightClass(79));
        assertEquals(WeightClass.LIGHT_HEAVYWEIGHT,WeightClass.getWeightClass(82));
        assertEquals(WeightClass.CRUISERWEIGHT,WeightClass.getWeightClass(90));
        assertEquals(WeightClass.HEAVYWEIGHT,WeightClass.getWeightClass(100));
        assertEquals(WeightClass.SUPER_HEAVYWEIGHT,WeightClass.getWeightClass(120.5));
    }

    @Test
    @Tag("minimum")
    void isHeavier() {
        assertTrue(WeightClass.CRUISERWEIGHT.isHeavier(WeightClass.LIGHT_HEAVYWEIGHT));
        assertTrue(WeightClass.HEAVYWEIGHT.isHeavier(WeightClass.CRUISERWEIGHT));
        assertTrue(WeightClass.SUPER_HEAVYWEIGHT.isHeavier(WeightClass.HEAVYWEIGHT));
        assertFalse(WeightClass.LIGHT_HEAVYWEIGHT.isHeavier(WeightClass.CRUISERWEIGHT));
        assertFalse(WeightClass.LIGHT_HEAVYWEIGHT.isHeavier(WeightClass.SUPER_HEAVYWEIGHT));
    }
}
