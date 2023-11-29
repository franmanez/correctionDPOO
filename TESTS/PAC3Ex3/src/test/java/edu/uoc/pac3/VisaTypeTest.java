package edu.uoc.pac3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class VisaTypeTest {

    @Test
    void testVisaTypeDescriptions() {
        assertEquals("Tourist Visa", VisaType.TOURIST.getDescription());
        assertEquals("Business Visa", VisaType.BUSINESS.getDescription());
        assertEquals("Student Visa", VisaType.STUDENT.getDescription());
        assertEquals("Work Visa", VisaType.WORK.getDescription());
        assertEquals("Transit Visa", VisaType.TRANSIT.getDescription());
        assertEquals("Family Visa", VisaType.FAMILY.getDescription());
        assertEquals("Journalist Visa", VisaType.JOURNALIST.getDescription());
        assertEquals("Medical Visa", VisaType.MEDICAL.getDescription());
        assertEquals("Retirement Visa", VisaType.RETIREMENT.getDescription());
        assertEquals("Investor Visa", VisaType.INVESTOR.getDescription());
        assertEquals("Diplomatic Visa", VisaType.DIPLOMATIC.getDescription());
        assertEquals("Schengen Visa", VisaType.SCHENGEN.getDescription());
        assertEquals("Employment Visa", VisaType.EMPLOYMENT.getDescription());
        assertEquals("Visiting Friends Visa", VisaType.VISITING_FRIENDS.getDescription());
        assertEquals("Religious Visa", VisaType.RELIGIOUS.getDescription());
        assertEquals("Other Visa", VisaType.OTHER.getDescription());
    }

    @Test
    void testVisaTypeStringIdentifiers() {
        assertEquals("T", VisaType.TOURIST.getStringIdentifier());
        assertEquals("B", VisaType.BUSINESS.getStringIdentifier());
        assertEquals("S", VisaType.STUDENT.getStringIdentifier());
        assertEquals("W", VisaType.WORK.getStringIdentifier());
        assertEquals("TR", VisaType.TRANSIT.getStringIdentifier());
        assertEquals("F", VisaType.FAMILY.getStringIdentifier());
        assertEquals("J", VisaType.JOURNALIST.getStringIdentifier());
        assertEquals("M", VisaType.MEDICAL.getStringIdentifier());
        assertEquals("R", VisaType.RETIREMENT.getStringIdentifier());
        assertEquals("I", VisaType.INVESTOR.getStringIdentifier());
        assertEquals("D", VisaType.DIPLOMATIC.getStringIdentifier());
        assertEquals("SCH", VisaType.SCHENGEN.getStringIdentifier());
        assertEquals("E", VisaType.EMPLOYMENT.getStringIdentifier());
        assertEquals("VF", VisaType.VISITING_FRIENDS.getStringIdentifier());
        assertEquals("RL", VisaType.RELIGIOUS.getStringIdentifier());
        assertEquals("O", VisaType.OTHER.getStringIdentifier());
    }

    @Test
    void testVisaTypeIntIdentifiers() {
        assertEquals(123, VisaType.TOURIST.getIntIdentifier());
        assertEquals(456, VisaType.BUSINESS.getIntIdentifier());
        assertEquals(789, VisaType.STUDENT.getIntIdentifier());
        assertEquals(321, VisaType.WORK.getIntIdentifier());
        assertEquals(654, VisaType.TRANSIT.getIntIdentifier());
        assertEquals(987, VisaType.FAMILY.getIntIdentifier());
        assertEquals(234, VisaType.JOURNALIST.getIntIdentifier());
        assertEquals(567, VisaType.MEDICAL.getIntIdentifier());
        assertEquals(890, VisaType.RETIREMENT.getIntIdentifier());
        assertEquals(432, VisaType.INVESTOR.getIntIdentifier());
        assertEquals(765, VisaType.DIPLOMATIC.getIntIdentifier());
        assertEquals(198, VisaType.SCHENGEN.getIntIdentifier());
        assertEquals(543, VisaType.EMPLOYMENT.getIntIdentifier());
        assertEquals(876, VisaType.VISITING_FRIENDS.getIntIdentifier());
        assertEquals(321, VisaType.RELIGIOUS.getIntIdentifier());
        assertEquals(654, VisaType.OTHER.getIntIdentifier());
    }

    @Test
    void testGetVisaTypeByStringIdentifier() {
        assertEquals(VisaType.TOURIST, VisaType.getVisaTypeByStringIdentifier("T"));
        assertEquals(VisaType.BUSINESS, VisaType.getVisaTypeByStringIdentifier("B"));
        assertEquals(VisaType.STUDENT, VisaType.getVisaTypeByStringIdentifier("S"));
        assertEquals(VisaType.WORK, VisaType.getVisaTypeByStringIdentifier("W"));
        assertEquals(VisaType.TRANSIT, VisaType.getVisaTypeByStringIdentifier("TR"));
        assertEquals(VisaType.FAMILY, VisaType.getVisaTypeByStringIdentifier("F"));
        assertEquals(VisaType.JOURNALIST, VisaType.getVisaTypeByStringIdentifier("J"));
        assertEquals(VisaType.MEDICAL, VisaType.getVisaTypeByStringIdentifier("M"));
        assertEquals(VisaType.RETIREMENT, VisaType.getVisaTypeByStringIdentifier("R"));
        assertEquals(VisaType.INVESTOR, VisaType.getVisaTypeByStringIdentifier("I"));
        assertEquals(VisaType.DIPLOMATIC, VisaType.getVisaTypeByStringIdentifier("D"));
        assertEquals(VisaType.SCHENGEN, VisaType.getVisaTypeByStringIdentifier("SCH"));
        assertEquals(VisaType.EMPLOYMENT, VisaType.getVisaTypeByStringIdentifier("E"));
        assertEquals(VisaType.VISITING_FRIENDS, VisaType.getVisaTypeByStringIdentifier("VF"));
        assertEquals(VisaType.RELIGIOUS, VisaType.getVisaTypeByStringIdentifier("RL"));
        assertEquals(VisaType.OTHER, VisaType.getVisaTypeByStringIdentifier("O"));
        assertNull(VisaType.getVisaTypeByStringIdentifier("X")); // Non-existing identifier
    }

    @Test
    void testNext() {
        assertEquals(VisaType.BUSINESS, VisaType.TOURIST.next());
        assertEquals(VisaType.STUDENT, VisaType.BUSINESS.next());
        assertEquals(VisaType.WORK, VisaType.STUDENT.next());
        assertEquals(VisaType.TRANSIT, VisaType.WORK.next());
        assertEquals(VisaType.FAMILY, VisaType.TRANSIT.next());
        assertEquals(VisaType.JOURNALIST, VisaType.FAMILY.next());
        assertEquals(VisaType.MEDICAL, VisaType.JOURNALIST.next());
        assertEquals(VisaType.RETIREMENT, VisaType.MEDICAL.next());
        assertEquals(VisaType.INVESTOR, VisaType.RETIREMENT.next());
        assertEquals(VisaType.DIPLOMATIC, VisaType.INVESTOR.next());
        assertEquals(VisaType.SCHENGEN, VisaType.DIPLOMATIC.next());
        assertEquals(VisaType.EMPLOYMENT, VisaType.SCHENGEN.next());
        assertEquals(VisaType.VISITING_FRIENDS, VisaType.EMPLOYMENT.next());
        assertEquals(VisaType.RELIGIOUS, VisaType.VISITING_FRIENDS.next());
        assertEquals(VisaType.OTHER, VisaType.RELIGIOUS.next());
        assertEquals(VisaType.TOURIST, VisaType.OTHER.next());
    }

    @Test
    @DisplayName("Sanity - Fields definition")
    void checkFieldsSanity() {
        //check attribute fields: 3 fields + 16 values + 1 hidden
        assertEquals(20, VisaType.class.getDeclaredFields().length);
        try {
            assertTrue(Modifier.isPrivate(VisaType.class.getDeclaredField("description").getModifiers()));
            assertTrue(Modifier.isPrivate(VisaType.class.getDeclaredField("stringIdentifier").getModifiers()));
            assertTrue(Modifier.isPrivate(VisaType.class.getDeclaredField("intIdentifier").getModifiers()));
        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Sanity - Methods definition")
    void checkMethodsSanity() {
        //check constructors
        assertEquals(1, VisaType.class.getDeclaredConstructors().length);



        //check methods, parameters and return types
        //5 methods + 2 hidden
        assertEquals(7,
                Arrays.stream(VisaType.class.getDeclaredMethods()).filter(m -> Modifier.isPublic(m.getModifiers())).toList().size());

        try {
            assertTrue(Modifier.isPublic(VisaType.class.getDeclaredMethod("getDescription").getModifiers()));
            assertEquals(String.class, VisaType.class.getDeclaredMethod("getDescription").getReturnType());

            assertTrue(Modifier.isPublic(VisaType.class.getDeclaredMethod("getStringIdentifier").getModifiers()));
            assertEquals(String.class, VisaType.class.getDeclaredMethod("getStringIdentifier").getReturnType());

            assertTrue(Modifier.isPublic(VisaType.class.getDeclaredMethod("getIntIdentifier").getModifiers()));
            assertEquals(int.class, VisaType.class.getDeclaredMethod("getIntIdentifier").getReturnType());

            assertTrue(Modifier.isPublic(VisaType.class.getDeclaredMethod("next").getModifiers()));
            assertEquals(VisaType.class, VisaType.class.getDeclaredMethod("next").getReturnType());
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
