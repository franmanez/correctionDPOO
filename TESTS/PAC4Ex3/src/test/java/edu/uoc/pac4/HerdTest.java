package edu.uoc.pac4;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class HerdTest {

    //Saurischian
    Saurischian sauropodo1, sauropodo2, theropoda1, theropoda2;

    //Ornithischian
    Ornithischian ornithischian1, ornithischian2, ornithischian3, ornithischian4, ornithischian5, ornithischian6;

    Herd saurischianHerd1, saurischianHerd2;
    Herd ornithischianHerd;

    @BeforeEach
    void init() {
        try {
            //Saurischian
            sauropodo1 = mock(Sauropodo.class);
            sauropodo2 = mock(Sauropodo.class);
            theropoda1 = mock(Theropoda.class);
            theropoda2 = mock(Theropoda.class);

            //Ornithischian
            ornithischian1 = mock(Ornithischian.class);
            ornithischian2 = mock(Ornithischian.class);
            ornithischian3 = mock(Ornithischian.class);
            ornithischian4 = mock(Ornithischian.class);
            ornithischian5 = mock(Ornithischian.class);
            ornithischian6 = mock(Ornithischian.class);

            //Groups
            saurischianHerd1 = new Herd("Saurischian 1", 4);
            saurischianHerd2 = new Herd("Saurischian 2", 6);
            ornithischianHerd = new Herd("Ornithischian", 5);
        } catch (Exception e) {
            e.printStackTrace();
            fail("init");
        }
    }

    @Test
    void testConstructor() {
        assertEquals("Saurischian 1", saurischianHerd1.getName());
        assertEquals("Saurischian 2", saurischianHerd2.getName());
        assertEquals("Ornithischian", ornithischianHerd.getName());

        assertEquals(5, saurischianHerd1.getMaxSize());
        assertEquals(6, saurischianHerd2.getMaxSize());
        assertEquals(5, ornithischianHerd.getMaxSize());

        assertNotNull(saurischianHerd1.getDinosaurs());
        assertNotNull(saurischianHerd2.getDinosaurs());
        assertNotNull(ornithischianHerd.getDinosaurs());

        assertEquals(0, saurischianHerd1.getDinosaurs().size());
        assertEquals(0, saurischianHerd2.getDinosaurs().size());
        assertEquals(0, ornithischianHerd.getDinosaurs().size());
    }

    @Test
    void testAddDinoOK() {
        Sauropodo sauropodo = mock(Sauropodo.class);
        Theropoda theropoda = mock(Theropoda.class);

        assertTrue(saurischianHerd1.add(sauropodo));
        assertEquals(1, saurischianHerd1.getDinosaurs().size());
        assertEquals(5, saurischianHerd1.getMaxSize());

        assertTrue(saurischianHerd2.add(sauropodo));
        assertEquals(1, saurischianHerd2.getDinosaurs().size());
        assertEquals(6, saurischianHerd2.getMaxSize());

        assertTrue(saurischianHerd1.add(theropoda));
        assertEquals(2, saurischianHerd1.getDinosaurs().size());
        assertEquals(5, saurischianHerd1.getMaxSize());
    }

    @Test
    void testAddDinoOKAndExceed() {
        Ornithischian ornithischian1 = mock(Ornithischian.class);
        Ornithischian ornithischian2 = mock(Ornithischian.class);
        Ornithischian ornithischian3 = mock(Ornithischian.class);
        Ornithischian ornithischian4 = mock(Ornithischian.class);
        Ornithischian ornithischian5 = mock(Ornithischian.class);
        Ornithischian ornithischian6 = mock(Ornithischian.class);

        assertTrue(ornithischianHerd.add(ornithischian1));
        assertEquals(1, ornithischianHerd.getDinosaurs().size());

        assertTrue(ornithischianHerd.add(ornithischian2));
        assertEquals(2, ornithischianHerd.getDinosaurs().size());

        assertTrue(ornithischianHerd.add(ornithischian3));
        assertEquals(3, ornithischianHerd.getDinosaurs().size());

        assertTrue(ornithischianHerd.add(ornithischian4));
        assertEquals(4, ornithischianHerd.getDinosaurs().size());

        assertTrue(ornithischianHerd.add(ornithischian5));
        assertEquals(5, ornithischianHerd.getDinosaurs().size());

        //Overflow the capacity of ornithischianHerd
        assertFalse(ornithischianHerd.add(ornithischian6));
        assertEquals(5, ornithischianHerd.getDinosaurs().size());
    }

    @Test
    void testAddDinoRepeated() {
        Sauropodo sauropodo = mock(Sauropodo.class);

        assertTrue(saurischianHerd1.add(sauropodo));
        assertEquals(1, saurischianHerd1.getDinosaurs().size());

        assertFalse(saurischianHerd1.add(sauropodo));
        assertEquals(1, saurischianHerd1.getDinosaurs().size());
    }

    @Test
    void testAddDinoNull() {
        Exception ex = assertThrows(Exception.class, () -> saurischianHerd1.add(null));
        assertEquals("[ERROR] The Dinosaur object cannot be null", ex.getMessage());
    }

    @Test
    void testAddDinoNotSameFamily() {
        Sauropodo sauropodo = mock(Sauropodo.class);
        Theropoda theropoda = mock(Theropoda.class);
        Ornithischian ornithischian = mock(Ornithischian.class);

        assertTrue(saurischianHerd1.add(sauropodo));
        Exception ex = assertThrows(Exception.class, () -> saurischianHerd1.add(ornithischian));
        assertEquals("[ERROR] Dinosaurs do not belong to the same family!!", ex.getMessage());

        assertTrue(saurischianHerd1.add(theropoda));

        assertTrue(ornithischianHerd.add(ornithischian));
        ex = assertThrows(Exception.class, () -> ornithischianHerd.add(sauropodo));
        assertEquals("[ERROR] Dinosaurs do not belong to the same family!!", ex.getMessage());

        ex = assertThrows(Exception.class, () -> ornithischianHerd.add(theropoda));
        assertEquals("[ERROR] Dinosaurs do not belong to the same family!!", ex.getMessage());
    }

    @Test
    void testExists() {
        Sauropodo sauropodo = mock(Sauropodo.class);
        Theropoda theropoda = mock(Theropoda.class);
        Ornithischian ornithischian = mock(Ornithischian.class);

        saurischianHerd1.add(sauropodo);

        assertTrue(saurischianHerd1.exists(sauropodo));
        assertFalse(saurischianHerd1.exists(theropoda));
        assertFalse(saurischianHerd1.exists(ornithischian));
    }

    @Test
    void testEmpty() {
        assertTrue(saurischianHerd1.isEmpty());
        assertTrue(saurischianHerd2.isEmpty());
        assertTrue(ornithischianHerd.isEmpty());

        //Given
        Sauropodo sauropodo = mock(Sauropodo.class);
        //When
        saurischianHerd1.add(sauropodo);
        //Then
        assertFalse(saurischianHerd1.isEmpty());
        assertTrue(saurischianHerd2.isEmpty());
        assertTrue(ornithischianHerd.isEmpty());
    }

    @Test
    void testRemove() {
        Sauropodo sauropodo = mock(Sauropodo.class);
        Theropoda theropoda = mock(Theropoda.class);

        //Given and when
        saurischianHerd1.add(sauropodo);
        //then
        assertEquals(1, saurischianHerd1.getDinosaurs().size());
        assertTrue(saurischianHerd1.remove(sauropodo));
        assertEquals(0, saurischianHerd1.getDinosaurs().size());

        //Given
        saurischianHerd1.add(theropoda);
        //When
        assertFalse(saurischianHerd1.remove(sauropodo)); //not exists in herd
        //Then
        assertEquals(1, saurischianHerd1.getDinosaurs().size());

        Exception ex = assertThrows(Exception.class, () -> saurischianHerd1.remove(null));
        assertEquals("[ERROR] The Dinosaur object cannot be null", ex.getMessage());
        assertEquals(1, saurischianHerd1.getDinosaurs().size());
    }

    @Test
    void testToString() {
        Ornithischian ornithischian1 = mock(Ornithischian.class);
        Ornithischian ornithischian2 = mock(Ornithischian.class);
        Ornithischian ornithischian3 = mock(Ornithischian.class);

        //Given and when
        ornithischianHerd.add(ornithischian1);
        ornithischianHerd.add(ornithischian2);
        ornithischianHerd.add(ornithischian3);

        //Then
        assertEquals("Herd: Ornithischian - Max. Size: 5 - Current size: 3", ornithischianHerd.toString());
    }

    @Test
    void testRemoveAll() {
        Ornithischian ornithischian1 = mock(Ornithischian.class);
        Ornithischian ornithischian2 = mock(Ornithischian.class);
        Ornithischian ornithischian3 = mock(Ornithischian.class);

        //Given
        ornithischianHerd.add(ornithischian1);
        ornithischianHerd.add(ornithischian2);
        ornithischianHerd.add(ornithischian3);

        //When
        ornithischianHerd.remove();

        //Then
        assertEquals(0, ornithischianHerd.getDinosaurs().size());
        assertEquals(5, ornithischianHerd.getMaxSize());
    }
}
