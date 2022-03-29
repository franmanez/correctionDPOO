package edu.uoc.pac2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CarTest {

    private static final String MESSAGE_ERROR_GET = "Valor incorrecto devuelto por el método getter";
    private static final String MESSAGE_ERROR_SET = "El setter no está asignando el valor correctamente para la entrada "; //concatenar el valor de entrada
    private static final String MESSAGE_ERROR_EXCEPTION = "El método set debe devolver una excepción porque no se está cumpliendo algún prerrequisito para la entrada "; // concatenar el valor de la entrada
    private static final String MESSAGE_ERROR_DEFAULT_CONSTRUCTOR = "El constructor por defecto no está asignando correctamente el valor del atributo "; //concatenar al final el nombre del atributo. Este mensaje se puede también concatenar con algún mensaje de sugerencia que viene después.
    private static final String MESSAGE_ERROR_PARAMETERIZED_CONSTRUCTOR = "El constructor por parámetros no está asignando correctamente el valor del atributo "; //ídem

    //Son mensajes para concatenar al final que dan pistas sobre posibles cosas a revisar.
    private static final String MESSAGE_SUGGESTION_TRACE =  ". Haz un traza/debugación para ver cómo se está haciendo la asignación y dónde está fallando.";
    private static final String MESSAGE_SUGGESTION_CHECK_PAC = ". Revisa las especificaciones indicadas en el enunciado.";
    private static final String MESSAGE_SUGGESTION_EXCEPTION_LENGTH = ". El valor de entrada tiene una longitud no permitida. Revisa las especificaciones del enunciado.";
    private static final String MESSAGE_SUGGESTION_EXCEPTION_RANGE = ". El valor de entrada esta fuera de rango. Revisa las especificaciones del enunciado.";
    private static final String MESSAGE_SUGGESTION_EXCEPTION_CORRECT_VALUE = ". El valor de entrada no tiene un valor correcto, o no sigue el patrón establecido. Revisa las especificaciones del enunciado.";

    Car car;

    @BeforeAll
    void init() {
        try {
            car = new Car("Seat", "IBIZA", 2010, 'D', "9898HJC", 8500.50);
        } catch (Exception e) {
            fail("Default constructor failed");
        }
    }

    @Test
    @Order(1)
    void testCarDefault() {
        try {
            Car carDefault = new Car();
            assertEquals(1, carDefault.getId(), MESSAGE_ERROR_DEFAULT_CONSTRUCTOR + "id" + MESSAGE_SUGGESTION_TRACE);
            assertEquals("Lorem", carDefault.getMake(), MESSAGE_ERROR_DEFAULT_CONSTRUCTOR + "make" + MESSAGE_SUGGESTION_TRACE);
            assertEquals("IPSUM", carDefault.getModel(), MESSAGE_ERROR_DEFAULT_CONSTRUCTOR + "model" + MESSAGE_SUGGESTION_TRACE);
            assertEquals(2000, carDefault.getLicenseYear(), MESSAGE_ERROR_DEFAULT_CONSTRUCTOR + "licenseYear" + MESSAGE_SUGGESTION_TRACE);
            assertEquals('P', carDefault.getFuel(), MESSAGE_ERROR_DEFAULT_CONSTRUCTOR + "fuel" + MESSAGE_SUGGESTION_TRACE);
            assertEquals("0000CDV", carDefault.getLicensePlate(), MESSAGE_ERROR_DEFAULT_CONSTRUCTOR + "licensePlate" + MESSAGE_SUGGESTION_TRACE);
            assertFalse(carDefault.getWarranty(), MESSAGE_ERROR_DEFAULT_CONSTRUCTOR + "warranty" + MESSAGE_SUGGESTION_TRACE);
            assertEquals(12100, carDefault.getPrice(), MESSAGE_ERROR_DEFAULT_CONSTRUCTOR + "price" + MESSAGE_SUGGESTION_TRACE);
        } catch (Exception e) {
            fail("Default constructor failed");
        }
    }

    @Test
    @Order(2)
    void testCar() {
        assertEquals(0, car.getId(), MESSAGE_ERROR_PARAMETERIZED_CONSTRUCTOR + "id" + MESSAGE_SUGGESTION_TRACE);
        assertEquals("Seat", car.getMake(), MESSAGE_ERROR_PARAMETERIZED_CONSTRUCTOR + "make" + MESSAGE_SUGGESTION_TRACE);
        assertEquals("IBIZA", car.getModel(), MESSAGE_ERROR_PARAMETERIZED_CONSTRUCTOR + "model" + MESSAGE_SUGGESTION_TRACE);
        assertEquals(2010, car.getLicenseYear(), MESSAGE_ERROR_PARAMETERIZED_CONSTRUCTOR + "licenseYear" + MESSAGE_SUGGESTION_TRACE);
        assertEquals('D', car.getFuel(), MESSAGE_ERROR_PARAMETERIZED_CONSTRUCTOR + "fuel" + MESSAGE_SUGGESTION_TRACE);
        assertEquals("9898HJC", car.getLicensePlate(), MESSAGE_ERROR_PARAMETERIZED_CONSTRUCTOR + "licensePlate" + MESSAGE_SUGGESTION_TRACE);
        assertFalse(car.getWarranty(), MESSAGE_ERROR_PARAMETERIZED_CONSTRUCTOR + "warranty" + MESSAGE_SUGGESTION_TRACE);
        assertEquals(10285.605, car.getPrice(), MESSAGE_ERROR_PARAMETERIZED_CONSTRUCTOR + "price" + MESSAGE_SUGGESTION_TRACE);
    }


    @Test
    @Order(3)
    void testGetId() {
        assertEquals(0, car.getId(), MESSAGE_ERROR_GET);
    }

    @Test
    @Order(4)
    void testGetMake() {
        assertEquals("Seat", car.getMake(), MESSAGE_ERROR_GET);
    }

    @Test
    @Order(5)
    void testSetMake() {
        try{
            car.setMake("auDI");
            assertEquals("Audi", car.getMake(), MESSAGE_ERROR_SET + "\"auDI\"" + MESSAGE_SUGGESTION_CHECK_PAC);
        }catch(Exception e) {
            fail("testSetMake failed");
        }
    }

    @Test
    @Order(6)
    void testSetMakeException() {
        Exception ex = assertThrows(Exception.class, () ->	car.setMake("Lorem ipsum dolo"));
        assertEquals("[ERROR] Car's make cannot be longer than 15 characters", ex.getMessage(), MESSAGE_ERROR_EXCEPTION + "\"Lorem ipsum dolo\"" + MESSAGE_SUGGESTION_EXCEPTION_LENGTH);
    }

    @Test
    @Order(7)
    void testGetModel() {
        assertEquals("IBIZA", car.getModel(), MESSAGE_ERROR_GET);
    }


    @Test
    @Order(8)
    void testSetModel() {
        try{
            car.setModel("ToleDo");
            assertEquals("TOLEDO", car.getModel(), MESSAGE_ERROR_SET + "\"ToleDo\"" + MESSAGE_SUGGESTION_CHECK_PAC);

            car.setModel("Ibiza Kit Car");
            assertEquals("IBIZA KIT CAR", car.getModel(), MESSAGE_ERROR_SET + "\"Ibiza Kit Car\"" + MESSAGE_SUGGESTION_CHECK_PAC);
        }catch(Exception e) {
            fail("testSetModel failed");
        }
    }

    @Test
    @Order(9)
    void testSetModelException() {
        Exception ex = assertThrows(Exception.class, () ->	car.setModel("Lorem ipsum dolo lore"));
        assertEquals("[ERROR] Car's model cannot be longer than 20 characters", ex.getMessage(), MESSAGE_ERROR_EXCEPTION + "\"Lorem ipsum doll lore\"" + MESSAGE_SUGGESTION_EXCEPTION_LENGTH);
    }


    @Test
    @Order(10)
    void testGetLicenseYear() {
        assertEquals(2010, car.getLicenseYear(), MESSAGE_ERROR_GET);
    }

    @Test
    @Order(11)
    void testSetLicenseYear() {
        try{
            car.setLicenseYear(2019);
            assertEquals(2019, car.getLicenseYear(), MESSAGE_ERROR_SET + "2019" + MESSAGE_SUGGESTION_CHECK_PAC);
        }catch(Exception e) {
            fail("testSetLicenseYear failed");
        }
    }

    @Test
    @Order(12)
    void testSetLicenseYearException() {

        Exception ex = assertThrows(Exception.class, () -> car.setLicenseYear(1999));
        assertEquals("[ERROR] Car's license year must be in range [2000, current year]", ex.getMessage(), MESSAGE_ERROR_EXCEPTION + "1999" + MESSAGE_SUGGESTION_EXCEPTION_RANGE);

        ex = assertThrows(Exception.class, () -> car.setLicenseYear(2023));
        assertEquals("[ERROR] Car's license year must be in range [2000, current year]", ex.getMessage(), MESSAGE_ERROR_EXCEPTION + "2023" + MESSAGE_SUGGESTION_EXCEPTION_RANGE);

        try{
            assertEquals(2019, car.getLicenseYear(), MESSAGE_ERROR_GET);
        }catch(Exception e) {
            fail("testSetLicenseYearException failed");
        }
    }


    @Test
    @Order(13)
    void testGetFuel() {
        assertEquals('D', car.getFuel(), MESSAGE_ERROR_GET);
    }

    @Test
    @Order(14)
    void testSetFuel() {
        try{
            car.setFuel('P');
            assertEquals('P', car.getFuel(), MESSAGE_ERROR_SET + "'P'" + MESSAGE_SUGGESTION_CHECK_PAC);

            car.setFuel('E');
            assertEquals('E', car.getFuel(), MESSAGE_ERROR_SET + "'E'" + MESSAGE_SUGGESTION_CHECK_PAC);

            car.setFuel('D');
            assertEquals('D', car.getFuel(), MESSAGE_ERROR_SET + "'D'" + MESSAGE_SUGGESTION_CHECK_PAC);

            car.setFuel('H');
            assertEquals('H', car.getFuel(), MESSAGE_ERROR_SET + "'H'" + MESSAGE_SUGGESTION_CHECK_PAC);

        }catch(Exception e) {
            fail("testSetFuel failed");
        }
    }

    @Test
    @Order(15)
    void testSetFuelException() {

        Exception ex = assertThrows(Exception.class, () -> car.setFuel('p'));
        assertEquals("[ERROR] Car's fuel is incorrect", ex.getMessage(), MESSAGE_ERROR_EXCEPTION + "'p'" + MESSAGE_SUGGESTION_EXCEPTION_CORRECT_VALUE);

        ex = assertThrows(Exception.class, () -> car.setFuel('N'));
        assertEquals("[ERROR] Car's fuel is incorrect", ex.getMessage(), MESSAGE_ERROR_EXCEPTION + "'N'" + MESSAGE_SUGGESTION_EXCEPTION_CORRECT_VALUE);

        ex = assertThrows(Exception.class, () -> car.setFuel('r'));
        assertEquals("[ERROR] Car's fuel is incorrect", ex.getMessage(), MESSAGE_ERROR_EXCEPTION + "'r'" + MESSAGE_SUGGESTION_EXCEPTION_CORRECT_VALUE);

        assertEquals('H', car.getFuel(), MESSAGE_ERROR_GET);

    }

    @Test
    @Order(16)
    void testWarranty() {
        assertTrue(car.getWarranty(), MESSAGE_ERROR_SET);

        try {
            car.setLicenseYear(2015);
            assertFalse(car.getWarranty(), MESSAGE_ERROR_GET);

            car.setLicenseYear(2020);
            assertTrue(car.getWarranty(), MESSAGE_ERROR_GET);

            car.setLicenseYear(2015);
            assertFalse(car.getWarranty(), MESSAGE_ERROR_GET);
        } catch (Exception e) {
            fail("testWarranty failed");
            e.printStackTrace();
        }
    }
}