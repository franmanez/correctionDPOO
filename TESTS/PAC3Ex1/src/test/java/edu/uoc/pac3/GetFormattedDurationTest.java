package edu.uoc.pac3;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class GetFormattedDurationTest {

    Trailer trailer;

    GetFormattedDurationTest(){
        try{
            trailer = new Trailer("www.uoc.edu",83, LocalDate.of(2022,8,1));
        }catch(Exception e){
            fail("TrailerTest failed");
        }
    }

    @Test
    void getFormattedDuration() {
        assertEquals("01:23",trailer.getFormattedDuration());

        try{
            trailer.setDuration(50);
            assertEquals("00:50",trailer.getFormattedDuration());
            trailer.setDuration(61);
            assertEquals("01:01",trailer.getFormattedDuration());

            trailer.setDuration(90);
            assertEquals("01:30",trailer.getFormattedDuration());

            trailer.setDuration(103);
            assertEquals("01:43",trailer.getFormattedDuration());

            trailer.setDuration(180);
            assertEquals("03:00",trailer.getFormattedDuration());

        }catch(Exception e){
            fail("getFormattedDuration failed");
        }
    }
}
