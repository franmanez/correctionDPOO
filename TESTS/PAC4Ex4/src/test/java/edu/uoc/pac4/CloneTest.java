package edu.uoc.pac4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CloneTest {

    @Test
    void testClone() {
        Dinosaur c1 = new Dinosaur("T-Rex",52.3, 12.4413, 1, 10, false);
        try {
            Dinosaur c2 = (Dinosaur) c1.clone();
            assertNotEquals(c1, c2);
            assertEquals(c1.getName(),c2.getName());
            assertNotEquals(c1.getCoordinate(),c2.getCoordinate());
            assertEquals(c1.getCoordinate().getLatitude(),c2.getCoordinate().getLatitude());
            assertEquals(c1.getCoordinate().getLongitude(),c2.getCoordinate().getLongitude());
            assertNotEquals(c1.getFindingDetail(),c2.getFindingDetail());
            assertEquals(c1.getFindingDetail().getNumFootprints(),c2.getFindingDetail().getNumFootprints());
            assertEquals(c1.getFindingDetail().getNumBones(),c2.getFindingDetail().getNumBones());
            assertEquals(c1.getFindingDetail().isSkeletonComplete(),c2.getFindingDetail().isSkeletonComplete());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            fail("test failed");
        }
    }
}
