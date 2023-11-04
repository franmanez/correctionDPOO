package edu.uoc.pac2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SentenceAnalyzerTest {

    @Test
    public void testReverseWords() {
        assertEquals("ecnetnes gniveileb ot tnemirepxe", SentenceAnalyzer.reverseWords("sentence believing to experiment"));
        assertEquals("madam a racecar was How ot see", SentenceAnalyzer.reverseWords("madam a racecar saw woH to ees"));
        assertEquals("a", SentenceAnalyzer.reverseWords("a"));
        assertEquals("dcba", SentenceAnalyzer.reverseWords("abcd"));
        assertEquals("! world Hello,", SentenceAnalyzer.reverseWords("! dlrow ,olleH"));
        assertEquals("tacocat", SentenceAnalyzer.reverseWords("tacocat"));
        assertEquals("abba", SentenceAnalyzer.reverseWords("abba"));
        assertEquals("ab  ba", SentenceAnalyzer.reverseWords("ba  ab"));
    }

    @Test
    public void testReverseSentence() {
        assertEquals("world! Hello,", SentenceAnalyzer.reverseSentence("Hello, world!"));
        assertEquals("experiment to how was racecar a madam", SentenceAnalyzer.reverseSentence("madam a racecar was how to experiment"));
        assertEquals("ees to woH saw racecar a madam", SentenceAnalyzer.reverseSentence("madam a racecar saw woH to ees"));
        assertEquals("a", SentenceAnalyzer.reverseSentence("a"));
        assertEquals("abcd", SentenceAnalyzer.reverseSentence("abcd"));
        assertEquals(",olleH dlrow !", SentenceAnalyzer.reverseSentence("! dlrow ,olleH"));
        assertEquals("tacocat", SentenceAnalyzer.reverseSentence("tacocat"));
        assertEquals("abba", SentenceAnalyzer.reverseSentence("abba"));
        assertEquals("ab  ba", SentenceAnalyzer.reverseSentence("ba  ab"));
    }

    @Test
    public void testEncrypt() {
        assertEquals("Khoor, zruog!", SentenceAnalyzer.encrypt("Hello, world!", 3));
        assertEquals("Rovvy, gybvn!", SentenceAnalyzer.encrypt("Hello, world!", 10));
        assertEquals("Ebiil, tloia!", SentenceAnalyzer.encrypt("Hello, world!", -3));
        assertEquals("B", SentenceAnalyzer.encrypt("A", 1));
        assertEquals("bcd", SentenceAnalyzer.encrypt("abc", 1));
        assertEquals("", SentenceAnalyzer.encrypt("", 3));
        assertEquals("! Kiqrv, mxaby!", SentenceAnalyzer.encrypt("! Hfnos, juxyv!", 3));
        assertEquals("Ifmmp xpsme!", SentenceAnalyzer.encrypt("Hello world!", 1));
        assertEquals("Ocz lpdxf wmjri!", SentenceAnalyzer.encrypt("The quick brown!", -5));
        assertEquals("Khoor, zruog!", SentenceAnalyzer.encrypt("Hello, world!", 29));
        assertEquals("Bcd, efg!", SentenceAnalyzer.encrypt("Abc, def!", 1));
        assertEquals("Lipps, Asvph!", SentenceAnalyzer.encrypt("Hello, World!", 4));
        assertEquals("Gdkkn, Vnqkc!", SentenceAnalyzer.encrypt("Hello, World!", -1));
        assertEquals("Gdkkn, Vnqkc!", SentenceAnalyzer.encrypt("Hello, World!", 25));
        assertEquals("Clrq, Yhqxv!", SentenceAnalyzer.encrypt("Zion, Venus!", 3));
        assertEquals("Wflk, Sbkrp!", SentenceAnalyzer.encrypt("Zion, Venus!", -3));
        assertEquals("123, 456!", SentenceAnalyzer.encrypt("123, 456!", 7));
        assertEquals("123, 456!", SentenceAnalyzer.encrypt("123, 456!", -7));
        assertEquals("Kyn, ryji!", SentenceAnalyzer.encrypt("Map, talk!", 24));
        assertEquals("Ocr, vcnm!", SentenceAnalyzer.encrypt("Map, talk!", -24));
        assertEquals("Zabcdefghijklmnopqrstuvwxy", SentenceAnalyzer.encrypt("Abcdefghijklmnopqrstuvwxyz", 25));
        assertEquals("Abcdefghijklmnopqrstuvwxyz", SentenceAnalyzer.encrypt("Abcdefghijklmnopqrstuvwxyz", 26));
        assertEquals("Czggj, Rjmgy!", SentenceAnalyzer.encrypt("Hello, World!", 21));
        assertEquals("Czggj, Rjmgy!", SentenceAnalyzer.encrypt("Hello, World!", -5));
        assertEquals("Jhm, flx!", SentenceAnalyzer.encrypt("Zxc, vbn!", 10));
    }

    @Test
    public void testDecrypt() {
        assertEquals("Hello, world!", SentenceAnalyzer.decrypt("Khoor, zruog!", 3));
        assertEquals("Hello, world!", SentenceAnalyzer.decrypt("Rovvy, gybvn!", 10));
        assertEquals("Hello, world!", SentenceAnalyzer.decrypt("Ebiil, tloia!", -3));
        assertEquals("Z", SentenceAnalyzer.decrypt("A", 1));
        assertEquals("abc", SentenceAnalyzer.decrypt("bcd", 1));
        assertEquals("", SentenceAnalyzer.decrypt("", 3));
        assertEquals("Hello, world!", SentenceAnalyzer.decrypt("Khoor, zruog!", 3));
        assertEquals("Hello world!", SentenceAnalyzer.decrypt("Ifmmp xpsme!", 1));
        assertEquals("The quick brown!", SentenceAnalyzer.decrypt("Ocz lpdxf wmjri!", -5));
        assertEquals("Hello, world!", SentenceAnalyzer.decrypt("Khoor, zruog!", 29));
        assertEquals("Bcd, efg!", SentenceAnalyzer.encrypt("Abc, def!", 1));
        assertEquals("Hello, World!", SentenceAnalyzer.decrypt("Lipps, Asvph!", 4));
        assertEquals("Hello, World!", SentenceAnalyzer.decrypt("Gdkkn, Vnqkc!", -1));
        assertEquals("Hello, World!", SentenceAnalyzer.decrypt("Gdkkn, Vnqkc!", 25));
        assertEquals("Zion, Venus!", SentenceAnalyzer.decrypt("Clrq, Yhqxv!", 3));
        assertEquals("Zion, Venus!", SentenceAnalyzer.decrypt("Wflk, Sbkrp!", -3));
        assertEquals("123, 456!", SentenceAnalyzer.decrypt("123, 456!", 7));
        assertEquals("123, 456!", SentenceAnalyzer.decrypt("123, 456!", -7));
        assertEquals("Map, talk!", SentenceAnalyzer.decrypt("Kyn, ryji!", 24));
        assertEquals("Map, talk!", SentenceAnalyzer.decrypt("Ocr, vcnm!", -24));
        assertEquals("Abcdefghijklmnopqrstuvwxyz", SentenceAnalyzer.decrypt("Zabcdefghijklmnopqrstuvwxy", 25));
        assertEquals("Abcdefghijklmnopqrstuvwxyz", SentenceAnalyzer.decrypt("Abcdefghijklmnopqrstuvwxyz", 26));
        assertEquals("Hello, World!", SentenceAnalyzer.decrypt("Czggj, Rjmgy!", 21));
        assertEquals("Hello, World!", SentenceAnalyzer.decrypt("Czggj, Rjmgy!", -5));
        assertEquals("Zxc, vbn!", SentenceAnalyzer.decrypt("Jhm, flx!", 10));
    }
}
