package edu.uoc.pac4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodTypeTest {

    @Test
    void getEnergy() {
        assertEquals(100, FoodType.MEAT.getEnergy());
        assertEquals(85, FoodType.FISH.getEnergy());
        assertEquals(50, FoodType.VEGETABLE.getEnergy());
        assertEquals(10, FoodType.WATER.getEnergy());
    }
}