package edu.uoc.pac2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
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
            car = new Car("Seat", "IBIZA", 2010, 'D', "9898HJC", 8500.50);
        }catch(Exception e) {
            fail("Parameterized constructor failed");
        }
    }


    @Test
    void testCarDefault() {
        Car carDefault = new Car();
        assertEquals(1, carDefault.getId(), MESSAGE_ERROR_DEFAULT_CONSTRUCTOR + "id" + MESSAGE_SUGGESTION_TRACE);
        assertEquals("Lorem", carDefault.getMake(), MESSAGE_ERROR_DEFAULT_CONSTRUCTOR + "make" + MESSAGE_SUGGESTION_TRACE);
        assertEquals("IPSUM", carDefault.getModel(), MESSAGE_ERROR_DEFAULT_CONSTRUCTOR + "model" + MESSAGE_SUGGESTION_TRACE);
        assertEquals(2000, carDefault.getLicenseYear(), MESSAGE_ERROR_DEFAULT_CONSTRUCTOR + "licenseYear" + MESSAGE_SUGGESTION_TRACE);
        assertEquals('P', carDefault.getFuel(), MESSAGE_ERROR_DEFAULT_CONSTRUCTOR + "fuel" + MESSAGE_SUGGESTION_TRACE);
        assertEquals("0000CDV", carDefault.getLicensePlate(), MESSAGE_ERROR_DEFAULT_CONSTRUCTOR + "licensePlate" + MESSAGE_SUGGESTION_TRACE);
        assertFalse(carDefault.getWarranty(), MESSAGE_ERROR_DEFAULT_CONSTRUCTOR + "warranty" + MESSAGE_SUGGESTION_TRACE);
        assertEquals(12100, carDefault.getPrice(), MESSAGE_ERROR_DEFAULT_CONSTRUCTOR + "price" + MESSAGE_SUGGESTION_TRACE);
    }

    @Test
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
    void testGetId() {
        assertEquals(0, car.getId(), MESSAGE_ERROR_GET);
    }

    @Test
    void testGetMake() {
        assertEquals("Seat", car.getMake(), MESSAGE_ERROR_GET);
    }

    @Test
    void testSetMake() {
        car.setMake("auDI");
        assertEquals("Audi", car.getMake(), MESSAGE_ERROR_SET + "\"auDI\"" + MESSAGE_SUGGESTION_CHECK_PAC);
    }

    @Test
    void testSetMakeException() {
        car.setMake("Lorem ipsum dolo");
        assertEquals("[ERROR] Car's make cannot be longer than 15 characters", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()), MESSAGE_ERROR_EXCEPTION + "\"Lorem ipsum dolo\"" + MESSAGE_SUGGESTION_EXCEPTION_LENGTH);
    }

    @Test
    void testGetModel() {
        assertEquals("IBIZA", car.getModel(), MESSAGE_ERROR_GET);
    }

    @Test
    void testSetModel() {
        car.setModel("ToleDo");
        assertEquals("TOLEDO", car.getModel(), MESSAGE_ERROR_SET + "\"ToleDo\"" + MESSAGE_SUGGESTION_CHECK_PAC);

        car.setModel("Ibiza Kit Car");
        assertEquals("IBIZA KIT CAR", car.getModel(), MESSAGE_ERROR_SET + "\"Ibiza Kit Car\"" + MESSAGE_SUGGESTION_CHECK_PAC);
    }

    @Test
    void testSetModelException() {
        car.setModel("Lorem ipsum doll lore");
        assertEquals("[ERROR] Car's model cannot be longer than 20 characters", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()), MESSAGE_ERROR_EXCEPTION + "\"Lorem ipsum doll lore\"" + MESSAGE_SUGGESTION_EXCEPTION_LENGTH);
    }


    @Test
    void testGetLicenseYear() {
        assertEquals(2010, car.getLicenseYear(), MESSAGE_ERROR_GET);
    }

    @Test
    void testSetLicenseYear() {
        car.setLicenseYear(2019);
        assertEquals(2019, car.getLicenseYear(), MESSAGE_ERROR_SET + "2019" + MESSAGE_SUGGESTION_CHECK_PAC);
    }

    @Test
    void testSetLicenseYearException() {

        car.setLicenseYear(2019);

        car.setLicenseYear(1999);
        assertEquals("[ERROR] Car's license year must be in range [2000, current year]", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()),MESSAGE_ERROR_EXCEPTION + "1999" + MESSAGE_SUGGESTION_EXCEPTION_RANGE);

        restoreStreams();

        car.setLicenseYear(2023);
        assertEquals("[ERROR] Car's license year must be in range [2000, current year]", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()),MESSAGE_ERROR_EXCEPTION + "2023" + MESSAGE_SUGGESTION_EXCEPTION_RANGE);

        restoreStreams();

        assertEquals(2019, car.getLicenseYear(), MESSAGE_ERROR_GET);
    }


    @Test
    void testGetFuel() {
        assertEquals('D', car.getFuel(), MESSAGE_ERROR_GET);
    }

    @Test
    void testSetFuel() {
        car.setFuel('P');
        assertEquals('P', car.getFuel(), MESSAGE_ERROR_SET + "'P'" + MESSAGE_SUGGESTION_CHECK_PAC);

        car.setFuel('E');
        assertEquals('E', car.getFuel(), MESSAGE_ERROR_SET + "'E'" + MESSAGE_SUGGESTION_CHECK_PAC);

        car.setFuel('D');
        assertEquals('D', car.getFuel(), MESSAGE_ERROR_SET + "'D'" + MESSAGE_SUGGESTION_CHECK_PAC);

        car.setFuel('H');
        assertEquals('H', car.getFuel(), MESSAGE_ERROR_SET + "'H'" + MESSAGE_SUGGESTION_CHECK_PAC);

    }

    @Test
    void testSetFuelException() {

        car.setFuel('H');

        car.setFuel('p');
        assertEquals("[ERROR] Car's fuel is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()), MESSAGE_ERROR_EXCEPTION + "'p'" + MESSAGE_SUGGESTION_EXCEPTION_CORRECT_VALUE);
        restoreStreams();

        car.setFuel('N');
        assertEquals("[ERROR] Car's fuel is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()), MESSAGE_ERROR_EXCEPTION + "'N'" + MESSAGE_SUGGESTION_EXCEPTION_CORRECT_VALUE);
        restoreStreams();

        car.setFuel('r');
        assertEquals("[ERROR] Car's fuel is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()), MESSAGE_ERROR_EXCEPTION + "'r'" + MESSAGE_SUGGESTION_EXCEPTION_CORRECT_VALUE);
        restoreStreams();

        assertEquals('H', car.getFuel(), MESSAGE_ERROR_GET);

    }

    @Test
    void testWarranty() {
        assertTrue(car.getWarranty(), MESSAGE_ERROR_SET);

        car.setLicenseYear(2015);
        assertFalse(car.getWarranty(), MESSAGE_ERROR_SET);

        car.setLicenseYear(2020);
        assertTrue(car.getWarranty(), MESSAGE_ERROR_SET);

        car.setLicenseYear(2010);
        assertFalse(car.getWarranty(), MESSAGE_ERROR_SET);
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    void checkMethodsSanity() {
        //check attribute fields
        assertEquals(10, Car.class.getDeclaredFields().length);

        try {
            assertTrue(Modifier.isPrivate(Car.class.getDeclaredField("VAT_SPAIN").getModifiers()));
            assertTrue(Modifier.isStatic(Car.class.getDeclaredField("VAT_SPAIN").getModifiers()));
            assertTrue(Modifier.isFinal(Car.class.getDeclaredField("VAT_SPAIN").getModifiers()));
            assertTrue(Modifier.isPrivate(Car.class.getDeclaredField("VAT_FRANCE").getModifiers()));
            assertTrue(Modifier.isStatic(Car.class.getDeclaredField("VAT_FRANCE").getModifiers()));
            assertTrue(Modifier.isFinal(Car.class.getDeclaredField("VAT_FRANCE").getModifiers()));

            assertTrue(Modifier.isPrivate(Car.class.getDeclaredField("id").getModifiers()));
            assertTrue(Modifier.isPrivate(Car.class.getDeclaredField("nextId").getModifiers()));
            assertTrue(Modifier.isPrivate(Car.class.getDeclaredField("make").getModifiers()));
            assertTrue(Modifier.isPrivate(Car.class.getDeclaredField("model").getModifiers()));
            assertTrue(Modifier.isPrivate(Car.class.getDeclaredField("licenseYear").getModifiers()));
            assertTrue(Modifier.isPrivate(Car.class.getDeclaredField("fuel").getModifiers()));
            assertTrue(Modifier.isPrivate(Car.class.getDeclaredField("licensePlate").getModifiers()));
            assertTrue(Modifier.isPrivate(Car.class.getDeclaredField("price").getModifiers()));

        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }

        //check constructors
        assertEquals(2, Car.class.getDeclaredConstructors().length);

        try {
            assertTrue(Modifier.isPublic(Car.class.getDeclaredConstructor().getModifiers()));
            assertTrue(Modifier.isPublic(Car.class.getDeclaredConstructor(String.class, String.class, int.class, char.class, String.class, double.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("There is some problem with the definition of constructors");
            e.printStackTrace();
        }

        //assertEquals("setId", Arrays.stream(Car.class.getDeclaredMethods()).filter(m -> m.getName().equals("setId")).findFirst().get().getName());

        //check methods, parameters and return types
        try {
            assertTrue(Modifier.isPublic(Car.class.getDeclaredMethod("getId").getModifiers()));
            assertEquals(int.class, Car.class.getDeclaredMethod("getId").getReturnType());
            assertTrue(Modifier.isPrivate(Car.class.getDeclaredMethod("setId").getModifiers()));
            assertTrue(Modifier.isStatic(Car.class.getDeclaredMethod("getNextId").getModifiers()));
            assertEquals(int.class, Car.class.getDeclaredMethod("getNextId").getReturnType());
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the id attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Car.class.getDeclaredMethod("getMake").getModifiers()));
            assertEquals(String.class, Car.class.getDeclaredMethod("getMake").getReturnType());
            assertTrue(Modifier.isPublic(Car.class.getDeclaredMethod("setMake", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the make attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Car.class.getDeclaredMethod("getModel").getModifiers()));
            assertEquals(String.class, Car.class.getDeclaredMethod("getModel").getReturnType());
            assertTrue(Modifier.isPublic(Car.class.getDeclaredMethod("setModel", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the model attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Car.class.getDeclaredMethod("getLicenseYear").getModifiers()));
            assertEquals(int.class, Car.class.getDeclaredMethod("getLicenseYear").getReturnType());
            assertTrue(Modifier.isPublic(Car.class.getDeclaredMethod("setLicenseYear", int.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the matriculationYear attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Car.class.getDeclaredMethod("getFuel").getModifiers()));
            assertEquals(char.class, Car.class.getDeclaredMethod("getFuel").getReturnType());
            assertTrue(Modifier.isPublic(Car.class.getDeclaredMethod("setFuel", char.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the fuel attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Car.class.getDeclaredMethod("getWarranty").getModifiers()));
            assertEquals(boolean.class, Car.class.getDeclaredMethod("getWarranty").getReturnType());
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the warranty attribute");
            e.printStackTrace();
        }

        //methods tested in private test (licensePlate, price)
        try{
            assertTrue(Modifier.isPublic(Car.class.getDeclaredMethod("getLicensePlate").getModifiers()));
            assertEquals(String.class, Car.class.getDeclaredMethod("getLicensePlate").getReturnType());
            assertTrue(Modifier.isPublic(Car.class.getDeclaredMethod("setLicensePlate", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the license plate attribute");
            e.printStackTrace();
        }
        try{
            assertTrue(Modifier.isPublic(Car.class.getDeclaredMethod("getPrice").getModifiers()));
            assertEquals(double.class, Car.class.getDeclaredMethod("getPrice").getReturnType());
            assertTrue(Modifier.isPublic(Car.class.getDeclaredMethod("setPrice", double.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the price attribute");
            e.printStackTrace();
        }
    }

}
