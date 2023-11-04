package edu.uoc.pac2;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    private ByteArrayOutputStream outContent;

    @BeforeEach
    public void setUpStreams() {
        restoreStreams();
    }

    @BeforeEach
    public void restoreStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testSetTitle() {
        Book book = new Book("Sample Book", "John Doe", "Fiction", "Sample Publisher",
                LocalDate.now().minusDays(7), "English", "1234567890", 29.99);

        assertEquals("Sample Book", book.getTitle());

        book.setTitle(null);
        assertEquals("[ERROR] Invalid title format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("Sample Book", book.getTitle());

        book.setTitle(" 3234423     ");
        assertEquals("[ERROR] Invalid title format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("Sample Book", book.getTitle());

        book.setTitle("56754");
        assertEquals("[ERROR] Invalid title format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("Sample Book", book.getTitle());

        book.setTitle("El Quijote*");
        assertEquals("[ERROR] Invalid title format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("Sample Book", book.getTitle());

        book.setTitle("El Qui?jote");
        assertEquals("[ERROR] Invalid title format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("Sample Book", book.getTitle());

        book.setTitle("El Qui, jote");
        assertEquals("[ERROR] Invalid title format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("Sample Book", book.getTitle());

        book.setTitle("ffsdfsd/&%$·!");
        assertEquals("[ERROR] Invalid title format.",
                outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("Sample Book", book.getTitle());

        book.setTitle("El Qui-jote");
        assertEquals("El Qui-jote", book.getTitle());

        book.setTitle("Peter Pan");
        assertEquals("Peter Pan", book.getTitle());

        book.setTitle("      ");
        assertEquals("      ", book.getTitle());

        book.setTitle("   Flashforward   ");
        assertEquals("   Flashforward   ", book.getTitle());
    }

    @Test
    public void testSetAuthor() {
        Book book = new Book("Sample Book", "John Doe", "Fiction", "Sample Publisher",
                LocalDate.now().minusDays(7), "English", "1234567890", 29.99);

        assertEquals("John Doe", book.getAuthor());

        book.setAuthor("Carles");
        assertEquals("Carles", book.getAuthor());

        book.setAuthor(null);
        assertEquals("[ERROR] Author cannot be empty.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("Carles", book.getAuthor());

        book.setAuthor("       ");
        assertEquals("[ERROR] Author cannot be empty.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("Carles", book.getAuthor());

        book.setAuthor("");
        assertEquals("[ERROR] Author cannot be empty.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("Carles", book.getAuthor());

        book.setAuthor("david");
        assertEquals("david", book.getAuthor());
    }

    @Test
    public void testSetGenre() {
        Book book = new Book("Sample Book", "John Doe", "Fiction", "Sample Publisher",
                LocalDate.now().minusDays(7), "English", "1234567890", 29.99);

        assertEquals("Fiction", book.getGenre());

        book.setGenre(null);
        assertEquals("[ERROR] Genre cannot be empty.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("Fiction", book.getGenre());

        book.setGenre("       ");
        assertEquals("[ERROR] Genre cannot be empty.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("Fiction", book.getGenre());

        book.setGenre("Fantasy");
        assertEquals("Fantasy", book.getGenre());

        book.setGenre("Fanta**sy");
        assertEquals("Fanta**sy", book.getGenre());
    }

    @Test
    public void testSetPublisher() {
        Book book = new Book("Sample Book", "John Doe", "Fiction", "Sample Publisher",
                LocalDate.now().minusDays(7), "English", "1234567890", 29.99);

        assertEquals("Sample Publisher", book.getPublisher());

        book.setPublisher("Carles");
        assertEquals("Carles", book.getPublisher());

        book.setPublisher("  ");
        assertEquals("  ", book.getPublisher());

        book.setPublisher("Carl3s 34243");
        assertEquals("Carl3s 34243", book.getPublisher());

        book.setPublisher("Carl3s, .3443..");
        assertEquals("Carl3s, .3443..", book.getPublisher());

        book.setPublisher("Carl3s, () .3..A");
        assertEquals("Carl3s, () .3..A", book.getPublisher());

        book.setPublisher("2312312.fffsd-ae$·");
        assertEquals("[ERROR] Invalid publisher format.",
                outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("Carl3s, () .3..A", book.getPublisher());

        book.setPublisher(null);
        assertEquals("[ERROR] Invalid publisher format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("Carl3s, () .3..A", book.getPublisher());

        book.setPublisher("  ?     ");
        assertEquals("[ERROR] Invalid publisher format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("Carl3s, () .3..A", book.getPublisher());

        book.setPublisher(" .!");
        assertEquals("[ERROR] Invalid publisher format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("Carl3s, () .3..A", book.getPublisher());

        book.setPublisher("\"sa\"");
        assertEquals("[ERROR] Invalid publisher format.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("Carl3s, () .3..A", book.getPublisher());

        book.setPublisher("david");
        assertEquals("david", book.getPublisher());
    }

    @Test
    public void testSetReleaseDate() {
        Book book = new Book("Sample Book", "John Doe", "Fiction", "Sample Publisher",
                LocalDate.now().minusDays(7), "English", "1234567890", 29.99);

        book.setReleaseDate(LocalDate.now());
        assertEquals(LocalDate.now(),book.getReleaseDate());

        book.setReleaseDate(LocalDate.of(1983,9,12));
        assertEquals(LocalDate.of(1983,9,12),book.getReleaseDate());

        book.setReleaseDate(book.getReleaseDate().minusYears(210));
        assertEquals("[ERROR] Invalid release date. It should be within the last 200 years and not in the future.",
                outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals(LocalDate.of(1983,9,12),book.getReleaseDate());

        book.setReleaseDate(LocalDate.now().plusMonths(2));

        assertEquals("[ERROR] Invalid release date. It should be within the last 200 years and not in the future.",
                outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals(LocalDate.of(1983,9,12),book.getReleaseDate());
    }

    @Test
    public void testSetLanguage(){
        Book book = new Book("Sample Book", "John Doe", "Fiction", "Sample Publisher",
                LocalDate.now().minusDays(7), "English", "1234567890", 29.99);

        assertEquals("English", book.getLanguage());

        book.setLanguage("Spanish");
        assertEquals("Spanish", book.getLanguage());

        book.setLanguage("english");
        assertEquals("[ERROR] Invalid language.",
                outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("Spanish", book.getLanguage());

        book.setLanguage("Swedish");
        assertEquals("[ERROR] Invalid language.",
                outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("Spanish", book.getLanguage());

        book.setLanguage("Italian");
        assertEquals("Italian", book.getLanguage());
    }

    @Test
    public void testSetPrice(){
        Book book = new Book("Sample Book", "John Doe", "Fiction", "Sample Publisher",
                LocalDate.now().minusDays(7), "English", "1234567890", 29.99);

        assertEquals(29.99, book.getPrice());

        book.setPrice(0.03);
        assertEquals(0.03, book.getPrice());

        book.setPrice(0);
        assertEquals("[ERROR] Price cannot be neither negative nor zero.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals(0.03, book.getPrice());

        book.setPrice(-10);
        assertEquals("[ERROR] Price cannot be neither negative nor zero.", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals(0.03, book.getPrice());

        book.setPrice(10.56);
        assertEquals(10.56, book.getPrice());
    }

    @Test
    public void testIsCheaperThan() {
        Book book1 = new Book("Sample Book", "Author 1", "Genre", "Publisher",
                LocalDate.now().minusDays(7), "English", "1234567890", 20.0);
        Book book2 = new Book("New Book", "Author 2", "Genre", "Publisher",
                LocalDate.now().minusDays(7), "Spanish", "0987654321", 25.0);

        assertTrue(book1.isCheaperThan(book2));
        assertFalse(book2.isCheaperThan(book1));

        book1.setPrice(25);
        assertFalse(book2.isCheaperThan(book1));
        assertFalse(book1.isCheaperThan(book2));
    }

    @Test
    public void testIsWrittenBy() {
        Book book = new Book("Sample Book", "John Doe", "Fiction", "Sample Publisher",
                LocalDate.now().minusDays(7), "English", "1234567890", 29.99);

        assertTrue(book.isWrittenBy("John Doe"));
        assertFalse(book.isWrittenBy("Jane Smith"));
        assertTrue(book.isWrittenBy("JOHN DOE"));
        assertTrue(book.isWrittenBy("JoHn DoE"));
    }

    @Test
    public void testIsClassic() {
        LocalDate fiftyYearsAgo = LocalDate.now().minusYears(51);
        Book book = new Book("Sample Book", "John Doe", "Fiction", "Sample Publisher",
                fiftyYearsAgo, "English", "1234567890", 29.99);

        assertTrue(book.isClassic());

        book.setReleaseDate(LocalDate.of(1919, 11, 8));
        assertTrue(book.isClassic());

        book.setReleaseDate(LocalDate.now());
        assertFalse(book.isClassic());
    }

    @Test
    public void testSetIsbn() {
        Book book = new Book("Sample Book", "John Doe", "Fiction", "Sample Publisher",
                LocalDate.now().minusDays(7), "English", "1234567890", 29.99);

        book.setIsbn("1234567890"); //ISBN10
        assertEquals("1234567890", book.getIsbn());

        book.setIsbn("1234567890234"); //ISBN13
        assertEquals("1234567890234", book.getIsbn());

        book.setIsbn("12345678-99"); //ISBN10 with dash
        assertEquals("1234567899", book.getIsbn());

        book.setIsbn("978-1234567891"); //ISBN13 with dash
        assertEquals("9781234567891", book.getIsbn());

        book.setIsbn("12345678900000000"); //Nor ISBN10 nor ISBN13
        assertEquals("[ERROR] Invalid ISBN format.",
                outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("9781234567891", book.getIsbn());

        book.setIsbn("12345-678905"); //Nor ISBN10 NOR ISB13
        assertEquals("[ERROR] Invalid ISBN format.",
                outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("9781234567891", book.getIsbn());
    }

    @Test
    public void testApplyDiscount() {
        Book book = new Book("Sample Book", "John Doe", "Fiction", "Sample Publisher",
                LocalDate.now().minusDays(7), "English", "1234567890", 29.99);

        assertEquals(26.991, book.applyDiscount(10),0.002);

        assertEquals(23.991, book.applyDiscount(20), 0.002);

        assertEquals(14.995, book.applyDiscount(50), 0.002);
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Fields definition")
    void checkFieldsSanity() {
        //check attribute fields
        assertEquals(9, Book.class.getDeclaredFields().length);
        try {
            assertTrue(Modifier.isPrivate(Book.class.getDeclaredField("title").getModifiers()));
            assertTrue(Modifier.isPrivate(Book.class.getDeclaredField("author").getModifiers()));
            assertTrue(Modifier.isPrivate(Book.class.getDeclaredField("genre").getModifiers()));
            assertTrue(Modifier.isPrivate(Book.class.getDeclaredField("publisher").getModifiers()));
            assertTrue(Modifier.isPrivate(Book.class.getDeclaredField("releaseDate").getModifiers()));
            assertTrue(Modifier.isPrivate(Book.class.getDeclaredField("language").getModifiers()));
            assertTrue(Modifier.isPrivate(Book.class.getDeclaredField("isbn").getModifiers()));
            assertTrue(Modifier.isPrivate(Book.class.getDeclaredField("price").getModifiers()));

        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    void checkMethodsSanity() {
        //check constructors
        assertEquals(1, Book.class.getDeclaredConstructors().length);

        try {
            Class<Book> bookClass = Book.class;
            Constructor<Book> constructor = bookClass.getConstructor(String.class, String.class, String.class,
                    String.class, LocalDate.class, String.class, String.class, double.class);

            assertNotNull(constructor);
            assertTrue(Modifier.isPublic(constructor.getModifiers()));

            Class<?>[] parameterTypes = constructor.getParameterTypes();
            assertArrayEquals(new Class<?>[]{
                    String.class, String.class, String.class, String.class, LocalDate.class, String.class, String.class, double.class
            }, parameterTypes);
        } catch (NoSuchMethodException e) {
            fail("There is some problem with the definition of constructors");
        }

        //check methods, parameters and return types
        try {
            assertTrue(Modifier.isPublic(Book.class.getDeclaredMethod("getTitle").getModifiers()));
            assertEquals(String.class, Book.class.getDeclaredMethod("getTitle").getReturnType());
            assertTrue(Modifier.isPublic(Book.class.getDeclaredMethod("setTitle", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the title attribute");
        }

        try {
            assertTrue(Modifier.isPublic(Book.class.getDeclaredMethod("getAuthor").getModifiers()));
            assertEquals(String.class, Book.class.getDeclaredMethod("getAuthor").getReturnType());
            assertTrue(Modifier.isPublic(Book.class.getDeclaredMethod("setAuthor", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the author attribute");
        }

        try {
            assertTrue(Modifier.isPublic(Book.class.getDeclaredMethod("getGenre").getModifiers()));
            assertEquals(String.class, Book.class.getDeclaredMethod("getGenre").getReturnType());
            assertTrue(Modifier.isPublic(Book.class.getDeclaredMethod("setGenre", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the genre attribute");
        }

        try {
            assertTrue(Modifier.isPublic(Book.class.getDeclaredMethod("getPublisher").getModifiers()));
            assertEquals(String.class, Book.class.getDeclaredMethod("getPublisher").getReturnType());
            assertTrue(Modifier.isPublic(Book.class.getDeclaredMethod("setPublisher", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the publisher attribute");
        }

        try {
            assertTrue(Modifier.isPublic(Book.class.getDeclaredMethod("getReleaseDate").getModifiers()));
            assertEquals(LocalDate.class, Book.class.getDeclaredMethod("getReleaseDate").getReturnType());
            assertTrue(Modifier.isPublic(Book.class.getDeclaredMethod("setReleaseDate", LocalDate.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the release date attribute");
        }

        try {
            assertTrue(Modifier.isPrivate(Book.class.getDeclaredMethod("isValidLanguage", String.class).getModifiers()));
            assertEquals(boolean.class, Book.class.getDeclaredMethod("isValidLanguage", String.class).getReturnType());
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of isValidaLanguage method");
        }

        try {
            assertTrue(Modifier.isPublic(Book.class.getDeclaredMethod("getLanguage").getModifiers()));
            assertEquals(String.class, Book.class.getDeclaredMethod("getLanguage").getReturnType());
            assertTrue(Modifier.isPublic(Book.class.getDeclaredMethod("setLanguage", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the language attribute");
        }

        try {
            assertTrue(Modifier.isPublic(Book.class.getDeclaredMethod("getIsbn").getModifiers()));
            assertEquals(String.class, Book.class.getDeclaredMethod("getIsbn").getReturnType());
            assertTrue(Modifier.isPublic(Book.class.getDeclaredMethod("setIsbn", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the isbn attribute");
        }

        try {
            assertTrue(Modifier.isPublic(Book.class.getDeclaredMethod("getPrice").getModifiers()));
            assertEquals(double.class, Book.class.getDeclaredMethod("getPrice").getReturnType());
            assertTrue(Modifier.isPublic(Book.class.getDeclaredMethod("setPrice", double.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the price attribute");
        }
    }
}
