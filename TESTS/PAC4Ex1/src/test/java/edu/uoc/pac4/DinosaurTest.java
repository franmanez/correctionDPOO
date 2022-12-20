package edu.uoc.pac4;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class DinosaurTest {

    private Position position;

    Dinosaur dino1;
    Dinosaur dino2;

    public DinosaurTest() {
        position = mock(Position.class); // Mock object

        try {
            dino1 = new Dinosaur("T.Rex", 1300, 6000, 2, Gender.FEMALE, 5, position);
            dino2 = new Dinosaur("Iguanodon", 900, 5000.4, 4, Gender.MALE, 3, position);
        } catch (Exception e) {
            e.printStackTrace();
            fail("DinosaurTest failed");
        }
    }

    @Test
    public void testGetName() {
        assertEquals("T.Rex", dino1.getName());
        assertEquals("Iguanodon", dino2.getName());
    }

    @Test
    public void testSetNameOK() {
        try {
            dino1.setName("Diplodocus");
        } catch (Exception e) {
            fail("testSetNameOK failed");
        }

        assertEquals("Diplodocus", dino1.getName());
    }

    @Test
    public void testSetNameKO() {
        Exception ex = assertThrows(Exception.class, () -> dino1.setName("Rex"));
        assertEquals(DinosaurException.MSG_ERR_NAME_LENGTH, ex.getMessage());

        ex = assertThrows(Exception.class, () -> dino1.setName("T-REX"));
        assertEquals(DinosaurException.MSG_ERR_NAME_FORMAT, ex.getMessage());

        ex = assertThrows(Exception.class, () -> dino1.setName("T/REX"));
        assertEquals(DinosaurException.MSG_ERR_NAME_FORMAT, ex.getMessage());

        ex = assertThrows(Exception.class, () -> dino1.setName("Dipl/odocus."));
        assertEquals(DinosaurException.MSG_ERR_NAME_FORMAT, ex.getMessage());
    }

    @Test
    public void testGetHeight() {
        assertEquals(1300, dino1.getHeight());
        assertEquals(900, dino2.getHeight());
    }

    @Test
    public void testSetHeightOK() {
        try {
            dino1.setHeight(130);
        } catch (Exception e) {
            fail("testSetHeightOK failed");
        }

        assertEquals(130, dino1.getHeight());
    }

    @Test
    public void testSetHeightKO() {
        Exception ex = assertThrows(Exception.class, () -> dino1.setHeight(0));
        assertEquals(DinosaurException.MSG_ERR_HEIGHT, ex.getMessage());

        ex = assertThrows(Exception.class, () -> dino1.setHeight(-61));
        assertEquals(DinosaurException.MSG_ERR_HEIGHT, ex.getMessage());
    }

    @Test
    public void testGetWeight() {
        assertEquals(6000, dino1.getWeight());
        assertEquals(5000.4, dino2.getWeight());
    }

    @Test
    public void testSetWeightOK() {
        try {
            dino1.setWeight(450);
        } catch (Exception e) {
            fail("testSetWeightOK failed");
        }
        assertEquals(450, dino1.getWeight());
    }

    @Test
    public void testSetWeightKO() {
        Exception ex = assertThrows(Exception.class, () -> dino1.setWeight(0));
        assertEquals(DinosaurException.MSG_ERR_WEIGHT, ex.getMessage());

        ex = assertThrows(Exception.class, () -> dino1.setWeight(-100));
        assertEquals(DinosaurException.MSG_ERR_WEIGHT, ex.getMessage());
    }

    @Test
    public void testGetNumLegs() {
        assertEquals(2, dino1.getNumLegs());
        assertEquals(4, dino2.getNumLegs());
    }

    @Test
    public void testSetNumLegsOK() {
        try {
            dino1.setNumLegs(0);
            dino2.setNumLegs(10);
        } catch (Exception e) {
            fail("testSetWeightOK failed");
        }
        assertEquals(0, dino1.getNumLegs());
        assertEquals(10, dino2.getNumLegs());
    }

    @Test
    public void testSetNumLegsKO() {
        Exception ex = assertThrows(Exception.class, () -> dino1.setNumLegs(-1));
        assertEquals(DinosaurException.MSG_ERR_NUM_LEGS, ex.getMessage());

        ex = assertThrows(Exception.class, () -> dino1.setNumLegs(-32));
        assertEquals(DinosaurException.MSG_ERR_NUM_LEGS, ex.getMessage());
    }

    @Test
    public void testGetGender() {
        assertEquals(Gender.FEMALE, dino1.getGender());
        assertEquals(Gender.MALE, dino2.getGender());
    }

    @Test
    public void testSetGender() {
        assertEquals(Gender.FEMALE, dino1.getGender());
        dino1.setGender(Gender.MALE);
        assertEquals(Gender.MALE, dino1.getGender());
    }

    @Test
    public void testGetSpeed(){
        assertEquals(5, dino1.getSpeed());
        assertEquals(3, dino2.getSpeed());
    }

    @Test
    public void testSetSpeed() {
        assertEquals(5, dino1.getSpeed());
        dino1.setSpeed(15);
        assertEquals(15, dino1.getSpeed());

        dino1.setSpeed(-20);
        assertEquals(1, dino1.getSpeed());

        dino1.setSpeed(10);
        assertEquals(10, dino1.getSpeed());

        dino1.setSpeed(0);
        assertEquals(1, dino1.getSpeed());

        dino1.setSpeed(1);
        assertEquals(1, dino1.getSpeed());
    }

    @Test
    public void testGetStamina(){
        assertEquals(0, dino1.getStamina());
    }

    @Test
    public void testIncStamina(){
        dino1.incStamina(5);
        assertEquals(5, dino1.getStamina());
        dino1.incStamina(5);
        assertEquals(10, dino1.getStamina());

        dino1.incStamina(-2);
        assertEquals(8, dino1.getStamina());

        dino1.incStamina(-9);
        assertEquals(0, dino1.getStamina());

        dino1.incStamina(10);
        assertEquals(10, dino1.getStamina());

        dino1.incStamina(90);
        assertEquals(100, dino1.getStamina());

        dino1.incStamina(1);
        assertEquals(100, dino1.getStamina());
    }

    @Test
    public void testDead(){
        assertFalse(dino1.isDead());
        assertFalse(dino2.isDead());
    }

    @Test
    public void testKill(){
        assertFalse(dino1.isDead());
        dino1.kill();

        assertTrue(dino1.isDead());
        assertFalse(dino2.isDead());
    }

    @Test
    public void getLifeExpectancy() {
        try {
            Dinosaur dino3 = new Dinosaur("T.Rex", 200, 4000, 2, Gender.FEMALE, 3, position);

            assertEquals(22.9, dino1.getLifeExpectancy());
            assertEquals(17.65, dino2.getLifeExpectancy());
            assertEquals(30, dino3.getLifeExpectancy());
        } catch (Exception e) {
            fail("getLifeExpectancy failed");
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Fields and methods definition")
    public void checkMethodsSanity() {
        final Class<Dinosaur> ownClass = Dinosaur.class;

        //check attribute fields
        assertEquals(11, ownClass.getDeclaredFields().length);

        try {
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("name").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("height").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("weight").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("numLegs").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("gender").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("speed").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("stamina").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredField("MIN_NAME_LENGTH").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("MIN_NAME_LENGTH").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("MIN_NAME_LENGTH").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredField("AVERAGE_AGE").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("AVERAGE_AGE").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("AVERAGE_AGE").getModifiers()));
        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);


        //check methods, parameters and return types
        assertEquals(21, ownClass.getDeclaredMethods().length);
        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getName").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setName", String.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getHeight").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setHeight", int.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getWeight").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setWeight", double.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getNumLegs").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setNumLegs", int.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getGender").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setGender", Gender.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getSpeed").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setSpeed", int.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getStamina").getModifiers()));
            assertTrue(Modifier.isProtected(ownClass.getDeclaredMethod("incStamina", double.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getPosition").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setPosition", Position.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("isDead").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("kill").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getLifeExpectancy").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("equals", Object.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("toString").getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }

    }
}
