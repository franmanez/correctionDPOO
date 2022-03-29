package edu.uoc.pac2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
class CarPrivateTest {

    private static final String MESSAGE_ERROR_GET = "Valor incorrecto devuelto por el método getter";
    private static final String MESSAGE_ERROR_SET = "El setter no está asignando el valor correctamente para la entrada "; //concatenar el valor de entrada
    private static final String MESSAGE_ERROR_EXCEPTION = "El método set debe devolver una excepción porque no se está cumpliendo algún prerrequisito para la entrada "; // concatenar el valor de la entrada
    private static final String MESSAGE_ERROR_DEFAULT_CONSTRUCTOR = "El constructor por defecto no está asignando correctamente el valor del atributo "; //concatenar al final el nombre del atributo. Este mensaje se puede también concatenar con algún mensaje de sugerencia que viene después.
    private static final String MESSAGE_ERROR_PARAMETERIZED_CONSTRUCTOR = "El constructor por parámetros no está asignando correctamente el valor del atributo "; //ídem

    //Son mensajes para concatenar al final que dan pistas sobre posibles cosas a revisar.
    private static final String MESSAGE_SUGGESTION_TRACE =  ". Haz un traza/debugación para ver cómo se está haciendo la asignación y dónde está fallando.";
    private static final String MESSAGE_SUGGESTION_CHECK_PAC = ". Revisa las especificaciones indicadas en el enunciado.";
    private static final String MESSAGE_SUGGESTION_CHECK_VAT = ". A los coches con matrícula de España se les aplica un 21% de IVA, y a los franceses un 21.5%.";
    private static final String MESSAGE_SUGGESTION_EXCEPTION_LENGTH = ". El valor de entrada tiene una longitud no permitida. Revisa las especificaciones del enunciado.";
    private static final String MESSAGE_SUGGESTION_EXCEPTION_RANGE = ". El valor de entrada esta fuera de rango. Revisa las especificaciones del enunciado.";
    private static final String MESSAGE_SUGGESTION_EXCEPTION_CORRECT_VALUE = ". El valor de entrada no tiene un valor correcto, o no sigue el patrón establecido. Revisa las especificaciones del enunciado.";

    private ByteArrayOutputStream outContent;
    private final PrintStream originalOut = System.out;

    Car car;

    @BeforeEach
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @BeforeAll
    void init() {
        try{
            Field field = Car.class.getDeclaredField("nextId");
            field.setAccessible(true);
            field.set(null, 0);
            car = new Car("Volkswagen", "California", 2017, 'H', "3232KJF", 50000);
        }catch(Exception e) {
            fail("Parameterized constructor failed");
        }
    }


    /******************
     * PRIVATE TESTS
     ******************/

    @Test
    void testGetLicensePlate() {
        assertEquals("3232KJF", car.getLicensePlate(), MESSAGE_ERROR_GET);
    }

    @Test
    void testSetLicensePlate() {

        car.setLicensePlate("1234FFH");
        assertEquals("1234FFH", car.getLicensePlate(), MESSAGE_ERROR_SET + "\"1234FFH\"" + MESSAGE_SUGGESTION_CHECK_PAC);

        car.setLicensePlate("FG-123-GF");
        assertEquals("FG-123-GF", car.getLicensePlate(), MESSAGE_ERROR_SET + "\"FG-123-GF\"" + MESSAGE_SUGGESTION_CHECK_PAC);
    }

    @Test
    void testSetLicensePlateException() {

        car.setLicensePlate("1234 FFH");
        assertEquals("[ERROR] Car's license plate pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()), MESSAGE_ERROR_EXCEPTION + "\"1234 FFH\"" + MESSAGE_SUGGESTION_EXCEPTION_CORRECT_VALUE);
        restoreStreams();

        car.setLicensePlate("1234fFH");
        assertEquals("[ERROR] Car's license plate pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()), MESSAGE_ERROR_EXCEPTION + "\"1234fFH\"" + MESSAGE_SUGGESTION_EXCEPTION_CORRECT_VALUE);

        restoreStreams();

        car.setLicensePlate("1234FH");
        assertEquals("[ERROR] Car's license plate pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()), MESSAGE_ERROR_EXCEPTION + "\"1234FH\"" + MESSAGE_SUGGESTION_EXCEPTION_CORRECT_VALUE);

        restoreStreams();

        car.setLicensePlate("123FFH");
        assertEquals("[ERROR] Car's license plate pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()), MESSAGE_ERROR_EXCEPTION + "\"123FFH\"" + MESSAGE_SUGGESTION_EXCEPTION_CORRECT_VALUE);

        restoreStreams();

        car.setLicensePlate("1234HF");
        assertEquals("[ERROR] Car's license plate pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()), MESSAGE_ERROR_EXCEPTION + "\"1234HF\"" + MESSAGE_SUGGESTION_EXCEPTION_CORRECT_VALUE);

        restoreStreams();

        car.setLicensePlate("12345FFH");
        assertEquals("[ERROR] Car's license plate pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()), MESSAGE_ERROR_EXCEPTION + "\"12345FFH\"" + MESSAGE_SUGGESTION_EXCEPTION_CORRECT_VALUE);

        restoreStreams();

        car.setLicensePlate("1234GUFF");
        assertEquals("[ERROR] Car's license plate pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()), MESSAGE_ERROR_EXCEPTION + "\"1234GUFF\"" + MESSAGE_SUGGESTION_EXCEPTION_CORRECT_VALUE);

        restoreStreams();

        car.setLicensePlate("FF-34-GT");
        assertEquals("[ERROR] Car's license plate pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()), MESSAGE_ERROR_EXCEPTION + "\"FF-34-GT\"" + MESSAGE_SUGGESTION_EXCEPTION_CORRECT_VALUE);

        restoreStreams();

        car.setLicensePlate("FF343-GT");
        assertEquals("[ERROR] Car's license plate pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()), MESSAGE_ERROR_EXCEPTION + "\"FF343-GT\"" + MESSAGE_SUGGESTION_EXCEPTION_CORRECT_VALUE);

        restoreStreams();

        car.setLicensePlate("FF 345-GT");
        assertEquals("[ERROR] Car's license plate pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()), MESSAGE_ERROR_EXCEPTION + "\"FF 345-GT\"" + MESSAGE_SUGGESTION_EXCEPTION_CORRECT_VALUE);

        restoreStreams();

        car.setLicensePlate("F-345-GT");
        assertEquals("[ERROR] Car's license plate pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()), MESSAGE_ERROR_EXCEPTION + "\"F-345-GT\"" + MESSAGE_SUGGESTION_EXCEPTION_CORRECT_VALUE);

        restoreStreams();

        car.setLicensePlate("FFF-345-GT");
        assertEquals("[ERROR] Car's license plate pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()), MESSAGE_ERROR_EXCEPTION + "\"FFF-345-GT\"" + MESSAGE_SUGGESTION_EXCEPTION_CORRECT_VALUE);

        restoreStreams();

        car.setLicensePlate("FF-345-G");
        assertEquals("[ERROR] Car's license plate pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()), MESSAGE_ERROR_EXCEPTION + "\"FF-345-G\"" + MESSAGE_SUGGESTION_EXCEPTION_CORRECT_VALUE);

        restoreStreams();

        car.setLicensePlate("FF-3F4-GT");
        assertEquals("[ERROR] Car's license plate pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()), MESSAGE_ERROR_EXCEPTION + "\"FF-3F4-GT\"" + MESSAGE_SUGGESTION_EXCEPTION_CORRECT_VALUE);

        restoreStreams();

        car.setLicensePlate("FF-GGF-GT");
        assertEquals("[ERROR] Car's license plate pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()), MESSAGE_ERROR_EXCEPTION + "\"FF-GGF-GT\"" + MESSAGE_SUGGESTION_EXCEPTION_CORRECT_VALUE);

        restoreStreams();

        car.setLicensePlate("F2F-123-GT");
        assertEquals("[ERROR] Car's license plate pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()), MESSAGE_ERROR_EXCEPTION + "\"F2F-123-GT\"" + MESSAGE_SUGGESTION_EXCEPTION_CORRECT_VALUE);

        restoreStreams();

        car.setLicensePlate("13-123-GT");
        assertEquals("[ERROR] Car's license plate pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()), MESSAGE_ERROR_EXCEPTION + "\"13-123-GT\"" + MESSAGE_SUGGESTION_EXCEPTION_CORRECT_VALUE);

        restoreStreams();

        car.setLicensePlate("ff-123-GT");
        assertEquals("[ERROR] Car's license plate pattern is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()), MESSAGE_ERROR_EXCEPTION + "\"ff-123-GT\"" + MESSAGE_SUGGESTION_EXCEPTION_CORRECT_VALUE);
    }


    @Test
    void testGetPrice() {
        assertEquals(60500, car.getPrice(), MESSAGE_ERROR_GET);
    }

    @Test
    void testSetPrice() {

        car.setLicensePlate("FG-123-GF");
        car.setPrice(50000);
        assertEquals(60750, car.getPrice(), MESSAGE_ERROR_SET + "50000" + MESSAGE_SUGGESTION_CHECK_VAT);

        car.setLicensePlate("4123-GFF");
        car.setPrice(43000);
        assertEquals(52245, car.getPrice(), MESSAGE_ERROR_SET + "43000" + MESSAGE_SUGGESTION_CHECK_VAT);

        car.setLicensePlate("FF-413-GF");
        car.setPrice(43000);
        assertEquals(52245, car.getPrice(), MESSAGE_ERROR_SET + "43000" + MESSAGE_SUGGESTION_CHECK_VAT);

    }

}
