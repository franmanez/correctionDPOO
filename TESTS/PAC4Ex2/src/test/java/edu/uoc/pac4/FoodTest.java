package edu.uoc.pac4;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class FoodTest {
    Food food1, food2, food3;

    Food foodWater;

    Position position;

    @BeforeAll
    void init() {
        position = mock(Position.class);

        try {
            food1 = new Food(" GrilLed MeAt ", FoodType.MEAT, position);
            food2 = new Food("prawns", FoodType.FISH, position);
            food3 = new Food("blue ToMATO", FoodType.VEGETABLE, position);
            foodWater = new Food();
        } catch (Exception e) {
            e.printStackTrace();
            fail("init");
        }
    }

    @Test
    void testGetName() {
        assertEquals("grilled-meat", food1.getName());
        assertEquals("prawns", food2.getName());
        assertEquals("blue-tomato", food3.getName());
        assertEquals("water", foodWater.getName());
    }

    @Test
    void testGetPosition(){
        assertEquals(position,food1.getPosition());
    }

    @Test
    void testGetEnergy() {
        assertEquals(100, food1.getEnergy());
        assertEquals(85, food2.getEnergy());
        assertEquals(50, food3.getEnergy());
        assertEquals(10, foodWater.getEnergy());
    }

    @Test
    void testGetType(){
        assertEquals(FoodType.MEAT, food1.getType());
        assertEquals(FoodType.FISH, food2.getType());
        assertEquals(FoodType.VEGETABLE, food3.getType());
        assertEquals(FoodType.WATER, foodWater.getType());

    }

    @Test
    void testToString() {
        assertEquals("(MEAT) grilled-meat=100", food1.toString());
        assertEquals("(FISH) prawns=85", food2.toString());
        assertEquals("(VEGETABLE) blue-tomato=50", food3.toString());
        assertEquals("(WATER) water=10", foodWater.toString());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    void checkMethodsSanity() {
        final Class<Food> ownClass = Food.class;

        //check attribute fields
        assertEquals(3, ownClass.getDeclaredFields().length);

        try {
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("name").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("position").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("type").getModifiers()));
        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }

        //check constructors
        assertEquals(2, ownClass.getDeclaredConstructors().length);


        //check methods, parameters and return types
        assertEquals(6, ownClass.getDeclaredMethods().length);
        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getName").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getPosition").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setPosition", Position.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getEnergy").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getType").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("toString").getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }
}
