package edu.uoc.pac4;

import org.junit.jupiter.api.*;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StreamTest {

    //Saurischian
    Saurischian sauropodo1, sauropodo2, theropoda1, theropoda2;

    Herd saurischianHerd1;
    Herd ornithischianHerd;

    @BeforeAll
    void init() {
        try {

            //Alliance
            sauropodo1 = new Sauropodo("Brontosaruio", 5898, 6100, Gender.MALE, new Position(0,1));
            sauropodo2 = new Sauropodo("Diplodocus", 6000, 6505, Gender.FEMALE, new Position(1,1));

            theropoda1 = new Theropoda("T Rex", 3500, 6001, Gender.MALE, new Position(0,9));
            theropoda2 = new Theropoda("X Rex", 3500, 6100, Gender.MALE, new Position(8,0));

            saurischianHerd1 = new Herd("Saurischian", 5);
            saurischianHerd1.add(sauropodo1);
            saurischianHerd1.add(sauropodo2);
            saurischianHerd1.add(theropoda1);
            saurischianHerd1.add(theropoda2);

            ornithischianHerd = new Herd("Ornithischian", 10);

        } catch (Exception e) {
            e.printStackTrace();
            fail("init");
        }
    }

    @Test
    void testGetDinosaursHeavierThan3000AndLifeExpectancyGreaterThanXAndOrderedByName() {
        assertEquals("[Brontosaruio, Diplodocus, T Rex, X Rex]", saurischianHerd1.getHeaviestDinosaursWithLifeExpectancy(22.2).toString());
        assertEquals("[Brontosaruio, Diplodocus, T Rex]", saurischianHerd1.getHeaviestDinosaursWithLifeExpectancy(29).toString());
        assertEquals("[Brontosaruio, Diplodocus]", saurischianHerd1.getHeaviestDinosaursWithLifeExpectancy(29.6).toString());
        assertEquals("[]", saurischianHerd1.getHeaviestDinosaursWithLifeExpectancy(30).toString());
        assertEquals("[]", ornithischianHerd.getHeaviestDinosaursWithLifeExpectancy(100).toString());
    }

    @Test
    void testKillAll() {

        sauropodo1.kill();
        theropoda2.kill();

        saurischianHerd1.add(sauropodo1);
        saurischianHerd1.add(sauropodo2);
        saurischianHerd1.add(theropoda1);
        saurischianHerd1.add(theropoda2);

        assertTrue(saurischianHerd1.getDinosaurs().stream().anyMatch(Dinosaur::isDead));
        assertTrue(saurischianHerd1.getDinosaurs().stream().anyMatch(Predicate.not(Dinosaur::isDead)));

        saurischianHerd1.killAll();
        assertTrue(saurischianHerd1.getDinosaurs().stream().allMatch(Dinosaur::isDead));
        assertTrue(saurischianHerd1.getDinosaurs().stream().noneMatch(Predicate.not(Dinosaur::isDead)));
        assertTrue(saurischianHerd1.getDinosaurs().stream().allMatch(dino -> dino.getPosition().equals(new Position(0,0))));
  }

    @Test
    void testAverageLevelGroup() {
        assertEquals(6176.5, saurischianHerd1.averageWeight(), 0.1);
        assertEquals(0, ornithischianHerd.averageWeight());

    }
}
