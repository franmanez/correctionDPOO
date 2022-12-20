package edu.uoc.pac4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    private Position position;

    @BeforeEach
    public void setUp() {
        position = new Position(1,2);
    }

    @Test
    public void testGetX(){
        assertEquals(1,position.getX());
    }

    @Test
    public void testSetX(){
        assertEquals(1,position.getX());
        position.setX(5);
        assertEquals(5,position.getX());
    }

    @Test
    public void testGetY(){
        assertEquals(2,position.getY());
    }

    @Test
    public void testSetY(){
        assertEquals(2,position.getY());
        position.setY(8);
        assertEquals(8,position.getY());
    }

    @Test
    public void testAddOffset(){
        position.addOffset(1,2);
        assertEquals(2, position.getX());
        assertEquals(4, position.getY());

        position.addOffset(-1,2);
        assertEquals(1, position.getX());
        assertEquals(6, position.getY());

        position.addOffset(8,-3);
        assertEquals(9, position.getX());
        assertEquals(3, position.getY());
    }

    @Test
    public void testIsCloseTo(){
        Position positionClose = new Position(3,3);

        assertTrue(position.isCloseTo(positionClose,4));
        assertTrue(position.isCloseTo(positionClose,3));
        assertFalse(position.isCloseTo(positionClose,2));
    }

    @Test
    public void testGetXOrientation(){
        Position positionPositive = new Position(3,1);
        assertEquals(1,position.getXOrientation(positionPositive));


        Position positionNegative = new Position(-3,1);
        assertEquals(-1,position.getXOrientation(positionNegative));

        Position positionEquals = new Position(1,1);
        assertEquals(0,position.getXOrientation(positionEquals));
    }

    @Test
    public void testGetYOrientation(){
        Position positionPositive = new Position(3,3);
        assertEquals(1,position.getYOrientation(positionPositive));


        Position positionNegative = new Position(3,1);
        assertEquals(-1,position.getYOrientation(positionNegative));

        Position positionEquals = new Position(1,2);
        assertEquals(0,position.getYOrientation(positionEquals));
    }

    @Test
    public void testEquals(){
        Position positionEquals = new Position(1,2);
        assertTrue(position.equals(positionEquals));


        Position positionNotEqualsX = new Position(2,2);
        assertFalse(position.equals(positionNotEqualsX));

        Position positionNotEqualsY = new Position(1,4);
        assertFalse(position.equals(positionNotEqualsY));

        Position positionNotEqualsXY = new Position(8,22);
        assertFalse(position.equals(positionNotEqualsXY));
    }

    @Test
    public void testToString(){
        assertEquals("(1,2)", position.toString());
    }

}
