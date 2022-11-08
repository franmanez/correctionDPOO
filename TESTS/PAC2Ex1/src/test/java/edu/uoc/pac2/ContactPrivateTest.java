package edu.uoc.pac2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
class ContactPrivateTest {

    private ByteArrayOutputStream outContent;
    private final PrintStream originalOut = System.out;


    @BeforeEach
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }


    @BeforeEach
    void init() {
        try{
            Field field = Contact.class.getDeclaredField("nextId");
            field.setAccessible(true);
            field.set(null, 0);
        }catch(Exception e) {
            fail("Parameterized constructor failed");
        }
    }

    @Test
    void testSetPhone(){
        Contact contact = new Contact();
        assertEquals("+34652668900",contact.getPhone());

        contact.setPhone("933582340");
        assertEquals("933582340",contact.getPhone());

        contact.setPhone("+34933582340");
        assertEquals("+34933582340",contact.getPhone());

        contact.setPhone("346526689");
        assertEquals("346526689",contact.getPhone());

        contact.setPhone("+346526689");
        assertEquals("[ERROR] Phone pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setPhone("+334933582340");
        assertEquals("[ERROR] Phone pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setPhone("93-3582340");
        assertEquals("[ERROR] Phone pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setPhone("334933582340");
        assertEquals("[ERROR] Phone pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setPhone("+4933582340");
        assertEquals("[ERROR] Phone pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setPhone("34933582340");
        assertEquals("[ERROR] Phone pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setPhone("+933582340");
        assertEquals("[ERROR] Phone pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setPhone("33582340");
        assertEquals("[ERROR] Phone pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setPhone("34+933582340");
        assertEquals("[ERROR] Phone pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setPhone("3+4933582340");
        assertEquals("[ERROR] Phone pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setPhone("34933582340+");
        assertEquals("[ERROR] Phone pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setPhone("3478933582340");
        assertEquals("[ERROR] Phone pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setPhone("582340");
        assertEquals("[ERROR] Phone pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setPhone("+3933582340");
        assertEquals("[ERROR] Phone pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setPhone("+933582340");
        assertEquals("[ERROR] Phone pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setPhone("+582340");
        assertEquals("[ERROR] Phone pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
    }

    @Test
    void testSetEmail(){
        Contact contact = new Contact();
        assertEquals("dpoo@uoc.edu",contact.getEmail());

        contact.setEmail("d@d.d");
        assertEquals("d@d.d",contact.getEmail());

        contact.setEmail("david@garcia.solorzano");
        assertEquals("david@garcia.solorzano",contact.getEmail());

        contact.setEmail("david.garcia.solorzano@yahoo.es");
        assertEquals("david.garcia.solorzano@yahoo.es",contact.getEmail());

        contact.setEmail("DaVId.Garcia.soloRZanO@yahoo.es");
        assertEquals("DaVId.Garcia.soloRZanO@yahoo.es",contact.getEmail());

        contact.setEmail("uocoders@eimt.uoc.edu");
        assertEquals("uocoders@eimt.uoc.edu",contact.getEmail());

        contact.setEmail("d.a@d.d");
        assertEquals("d.a@d.d",contact.getEmail());

        contact.setEmail("DaVId.Garcia.soloRZanO@YaHoO.eS");
        assertEquals("DaVId.Garcia.soloRZanO@YaHoO.eS",contact.getEmail());

        contact.setEmail("uocoders@eiTM.uOc.edU");
        assertEquals("uocoders@eiTM.uOc.edU",contact.getEmail());

        contact.setEmail("D.D@D.D");
        assertEquals("D.D@D.D",contact.getEmail());

        contact.setEmail("uoccoders83@eimt.uoc.edu");
        assertEquals("[ERROR] Email pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setEmail("24uoccoders@eimt.uoc.edu");
        assertEquals("[ERROR] Email pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setEmail("uoc32coders@eimt.uoc.edu");
        assertEquals("[ERROR] Email pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setEmail(".@eimt.uoc.edu");
        assertEquals("[ERROR] Email pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setEmail(".a@eimt.uoc.edu");
        assertEquals("[ERROR] Email pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setEmail("a.@eimt.uoc.edu");
        assertEquals("[ERROR] Email pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setEmail("uoc_coders@eimt.uoc.edu");
        assertEquals("[ERROR] Email pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setEmail("uoccoders@eimt.uoc.edu.");
        assertEquals("[ERROR] Email pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setEmail("uoc3coders@eimt.uoc.edu");
        assertEquals("[ERROR] Email pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setEmail("uoccoders@3imt.uoc.edu");
        assertEquals("[ERROR] Email pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setEmail("uoccoders@eimt.uoc2.edu");
        assertEquals("[ERROR] Email pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setEmail("uoccoders@eimt.uoc2.ed55u");
        assertEquals("[ERROR] Email pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setEmail("uoccoders@eimt.uoc@dfa.edu");
        assertEquals("[ERROR] Email pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setEmail("uoccoders@eimt.uoc.edu.");
        assertEquals("[ERROR] Email pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setEmail("uoccoders@.a");
        assertEquals("[ERROR] Email pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setEmail("uoccoders.a");
        assertEquals("[ERROR] Email pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setEmail("uoccoders@a");
        assertEquals("[ERROR] Email pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
    }
}
