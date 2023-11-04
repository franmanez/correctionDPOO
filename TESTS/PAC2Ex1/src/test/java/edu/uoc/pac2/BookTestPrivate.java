package edu.uoc.pac2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookTestPrivate {

    private ByteArrayOutputStream outContent;
    private final PrintStream originalOut = System.out;

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
    public void testSetIsbnWithInvalidLength() {
        Book book = new Book("Sample Book", "John Doe", "Fiction", "Sample Publisher",
                LocalDate.now().minusDays(7), "English", "1234567890", 29.99);

        book.setIsbn("978-A123456789"); //length = 12
        assertEquals("[ERROR] Invalid ISBN format.",
                outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("1234567890", book.getIsbn());

        book.setIsbn("-A123456789"); //length = 9
        assertEquals("[ERROR] Invalid ISBN format.",
                outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("1234567890", book.getIsbn());

        book.setIsbn("-A12345.67 89"); //length = 9
        assertEquals("[ERROR] Invalid ISBN format.",
                outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
        assertEquals("1234567890", book.getIsbn());
    }

    @Test
    public void testSetIsbnWithHyphens() {
        Book book = new Book("Sample Book", "John Doe", "Fiction", "Sample Publisher",
                LocalDate.now().minusDays(7), "English", "1234567890", 29.99);

        book.setIsbn("9-7-8-1-2-3-4-5-6-7-8-9-0");
        assertEquals("9781234567890", book.getIsbn()); // Hyphens should be removed
    }

    @Test
    public void testSetIsbnWithSpaces() {
        Book book = new Book("Sample Book", "John Doe", "Fiction", "Sample Publisher",
                LocalDate.now().minusDays(7), "English", "1234567890", 29.99);

        book.setIsbn("978 123 4567 890");
        assertEquals("9781234567890", book.getIsbn()); // Spaces should be removed
    }

    @Test
    public void testSetIsbnWithMultipleCharacters() {
        Book book = new Book("Sample Book", "John Doe", "Fiction", "Sample Publisher",
                LocalDate.now().minusDays(7), "English", "1234567890", 29.99);

        book.setIsbn("1234A5678-89");
        assertEquals("1234567889", book.getIsbn());

        book.setIsbn("15 34A5678-89");
        assertEquals("1534567889", book.getIsbn());

        book.setIsbn("9--7..8,1-2 3-4-5-6 7--8,9-0");
        assertEquals("9781234567890", book.getIsbn()); // Special characters should be removed
    }

    @Test
    public void testApplyDiscountWithFractionalPercentage() {
        Book book = new Book("Sample Book", "John Doe", "Fiction", "Sample Publisher",
                LocalDate.now().minusDays(7), "English", "1234567890", 29.99);
        assertEquals(26.24, book.applyDiscount(12.5), 0.02);
    }

    @Test
    public void testApplyDiscountToZeroPrice() {
        Book book = new Book("Sample Book", "John Doe", "Fiction", "Sample Publisher",
                LocalDate.now().minusDays(7), "English", "1234567890", 0.0);

        book.applyDiscount(25);
        assertEquals(0.0, book.getPrice());
    }

    @Test
    public void testApplyDiscountInExtremes() {
        Book book = new Book("Sample Book", "John Doe", "Fiction", "Sample Publisher",
                LocalDate.now().minusDays(7), "English", "1234567890", 29.99);

        assertEquals(29.99, book.applyDiscount(0));
        assertEquals(0, book.applyDiscount(100));
    }

    @Test
    public void testApplyDiscountOutOfRange() {
        Book book = new Book("Sample Book", "John Doe", "Fiction", "Sample Publisher",
                LocalDate.now().minusDays(7), "English", "1234567890", 29.99);

        assertEquals(-1, book.applyDiscount(-10));
        assertEquals("[ERROR] Invalid discount percentage.",
                outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        assertEquals(-1,book.applyDiscount(110));
        assertEquals("[ERROR] Invalid discount percentage.",
                outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();


    }

}
