package edu.uoc.pac3;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.*;

class GenreTest {

	private Movie movie;


	void GenreTest(){
		try {
			movie = new Movie("There's something about Mary", Genre.COMEDY);
			assertEquals(Genre.COMEDY, movie.getGenre());
		} catch (Exception e) {
			e.printStackTrace();
			fail("GenreTest failed");
		}
	}

	@Test
	void testGetDescription() {

		Genre genre = Genre.ACTION;
		assertEquals("Movies with shots, explosions, etc.", genre.getDescription());

		genre = Genre.ADVENTURE;
		assertEquals("Thousands of things happen", genre.getDescription());

		genre = Genre.COMEDY;
		assertEquals("Lot of laughs", genre.getDescription());

		genre = Genre.DRAMA;
		assertEquals("Plenty of sadness", genre.getDescription());

		genre = Genre.FANTASY;
		assertEquals("Imaginary friends", genre.getDescription());

		genre = Genre.HORROR;
		assertEquals("Screams", genre.getDescription());

		genre = Genre.MUSICAL;
		assertEquals("La, la, la", genre.getDescription());

		genre = Genre.ROMANCE;
		assertEquals("Love, love and much love", genre.getDescription());

		genre = Genre.SCIFI;
		assertEquals("Aliens, time travels and so on", genre.getDescription());

		genre = Genre.THRILLER;
		assertEquals("Crimes, clues, policemen...", genre.getDescription());

		genre = Genre.WESTERN;
		assertEquals("Bang!!! Movies wanted", genre.getDescription());
	}


	@Test
	void testGetAbbreviation() {

		Genre genre = Genre.ACTION;
		assertEquals("A", genre.getAbbreviation());

		genre = Genre.ADVENTURE;
		assertEquals("ADV", genre.getAbbreviation());

		genre = Genre.COMEDY;
		assertEquals("C", genre.getAbbreviation());

		genre = Genre.DRAMA;
		assertEquals("D", genre.getAbbreviation());

		genre = Genre.FANTASY;
		assertEquals("F", genre.getAbbreviation());

		genre = Genre.HORROR;
		assertEquals("H", genre.getAbbreviation());

		genre = Genre.MUSICAL;
		assertEquals("M", genre.getAbbreviation());

		genre = Genre.ROMANCE;
		assertEquals("R", genre.getAbbreviation());

		genre = Genre.SCIFI;
		assertEquals("SF", genre.getAbbreviation());

		genre = Genre.THRILLER;
		assertEquals("T", genre.getAbbreviation());

		genre = Genre.WESTERN;
		assertEquals("W", genre.getAbbreviation());
	}

	@Test
	void testGetGenre() {
		assertEquals(Genre.ACTION, Genre.getGenre("A"));
		assertEquals(Genre.ADVENTURE, Genre.getGenre("ADV"));
		assertEquals(Genre.COMEDY, Genre.getGenre("C"));
		assertEquals(Genre.DRAMA, Genre.getGenre("D"));
		assertEquals(Genre.FANTASY, Genre.getGenre("F"));
		assertEquals(Genre.HORROR, Genre.getGenre("H"));
		assertEquals(Genre.MUSICAL, Genre.getGenre("M"));
		assertEquals(Genre.ROMANCE, Genre.getGenre("R"));
		assertEquals(Genre.SCIFI, Genre.getGenre("SF"));
		assertEquals(Genre.THRILLER, Genre.getGenre("T"));
		assertEquals(Genre.WESTERN, Genre.getGenre("W"));
		assertNull(Genre.getGenre("AA"));
		assertNull(Genre.getGenre("S"));
		assertNull(Genre.getGenre("a"));
	}

	@Test
	void tesgGetAbbreviationByIndex() {

		assertEquals("A", Genre.getAbbreviationByIndex(0));
		assertEquals("ADV", Genre.getAbbreviationByIndex(1));
		assertEquals("C", Genre.getAbbreviationByIndex(2));
		assertEquals("D", Genre.getAbbreviationByIndex(3));
		assertEquals("F", Genre.getAbbreviationByIndex(4));
		assertEquals("H", Genre.getAbbreviationByIndex(5));
		assertEquals("M", Genre.getAbbreviationByIndex(6));
		assertEquals("R", Genre.getAbbreviationByIndex(7));
		assertEquals("SF", Genre.getAbbreviationByIndex(8));
		assertEquals("T", Genre.getAbbreviationByIndex(9));
		assertEquals("W", Genre.getAbbreviationByIndex(10));

		assertEquals("A", Genre.getAbbreviationByIndex(-1));
		assertEquals("W", Genre.getAbbreviationByIndex(11));
	}


	@Test
	void testNext() {
		assertEquals(Genre.ACTION, Genre.WESTERN.next());
		assertEquals(Genre.ADVENTURE, Genre.ACTION.next());
		assertEquals(Genre.COMEDY, Genre.ADVENTURE.next());
		assertEquals(Genre.DRAMA, Genre.COMEDY.next());
		assertEquals(Genre.FANTASY, Genre.DRAMA.next());
		assertEquals(Genre.HORROR, Genre.FANTASY.next());
		assertEquals(Genre.MUSICAL, Genre.HORROR.next());
		assertEquals(Genre.ROMANCE, Genre.MUSICAL.next());
		assertEquals(Genre.SCIFI, Genre.ROMANCE.next());
		assertEquals(Genre.THRILLER, Genre.SCIFI.next());
		assertEquals(Genre.WESTERN, Genre.THRILLER.next());

	}
}
