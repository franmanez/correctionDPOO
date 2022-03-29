package edu.uoc.pac2;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
class DNATest {

    private static final String MESSAGE_ERROR_SET = "El setter no está asignando el valor correctamente para la entrada ";
    private static final String MESSAGE_ERROR_EXCEPTION = "El método set debe devolver una excepción en este caso porque no se está cumpliendo algún prerequisito indicado para la entrada en el enunciado.";
    private static final String MESSAGE_ERROR_ADD_SEQUENCE = "El método addSequence no está añadiendo en la posición correcta la secuencia al atributo bases para el valor de entrada ";
    private static final String MESSAGE_ERROR_TO_RNA = "El método toRNA, o bien no está transformando las bases nitrogenadas complementarias correctamente, o bien no le da la vuelta a la cadena de RNAm para el valor de entrada ";
    private static final String MESSAGE_ERROR_IS_PROTEIN_TRUE = "Es una proteína que no está siendo detectada por isProtein. Recuerda que una proteína debe empezar por ATG, acabar por TGA y la longitud debe ser múltiplo de 3.";
    private static final String MESSAGE_ERROR_IS_PROTEIN_FALSE = "isProtein está detectado como proteína algo que no es. Recuerda que una protéina debe empezar por ATG, acabar por TGA y la longitud debe ser múltiplo de 3";
    private static final String MESSAGE_ERROR_REPETED_LENGHT = "El método sequencesRepeatedLength no está detectando todas las secuencias de longitud 'l' que hay repetidas.";

    DNA dna;

    @Test
    void testConstructorGetterSetter() {
        try{
            dna = new DNA("TGAC");
            assertEquals("TGAC", dna.getBases(), MESSAGE_ERROR_SET + "\"TGAC\"");

            dna = new DNA("cCTAGGCTACGGCTACGctagcCTGAtcagt");
            assertEquals("CCTAGGCTACGGCTACGCTAGCCTGATCAGT", dna.getBases(), MESSAGE_ERROR_SET + "\"cCTAGGCTACGGCTACGctagcCTGAtcagt\"");

            dna.setBases("CGAGTAGTAa");
            assertEquals("CGAGTAGTAA", dna.getBases(), MESSAGE_ERROR_SET + "\"CGAGTAGTAa\"");

        }catch(Exception e) {
            fail("testConstructorGetterSetter failed");
        }
    }

    @Test
    void testConstructorGetterSetterException() {
        try{
            Exception ex = assertThrows(Exception.class, () -> new DNA("HHJ"));
            assertEquals("[ERROR] DNA's pattern is incorrect", ex.getMessage(), MESSAGE_ERROR_EXCEPTION);

            ex = assertThrows(Exception.class, () -> dna.setBases("AGTCGGGAGFA"));
            assertEquals("[ERROR] DNA's pattern is incorrect", ex.getMessage(), MESSAGE_ERROR_EXCEPTION);

        }catch(Exception e) {
            fail("testConstructorGetterSetter failed");
        }
    }

    @Test
    void testAddSequence() {
        try{
            dna = new DNA("CTAG");

            dna.addSequence("TTAA");
            assertEquals("CTAGTTAA", dna.getBases(), MESSAGE_ERROR_ADD_SEQUENCE + "\"TTAA\"");

            dna.addSequence("tgggt", 2);
            assertEquals("CTTGGGTAGTTAA", dna.getBases(), MESSAGE_ERROR_ADD_SEQUENCE + "\"tgggt\"");

            dna.addSequence("AAAAA", 7, 0);
            assertEquals("AAAAACTTGGGTAAAAAAGTTAA", dna.getBases(), MESSAGE_ERROR_ADD_SEQUENCE + "\"AAAAA\"");

            dna.addSequence("TT", 9, 10, 11);
            assertEquals("AAAAACTTGTTTTTTGGTAAAAAAGTTAA", dna.getBases(), MESSAGE_ERROR_ADD_SEQUENCE + "\"TT\"");

            dna.addSequence("G", 29);
            assertEquals("AAAAACTTGTTTTTTGGTAAAAAAGTTAAG", dna.getBases(), MESSAGE_ERROR_ADD_SEQUENCE + "\"G\"");

            dna = new DNA("CTAG");
            dna.addSequence("CC", 1, 3);
            assertEquals("CCCCCTAG", dna.getBases(), MESSAGE_ERROR_ADD_SEQUENCE + "\"CC\"");

            dna = new DNA("CTAG");
            dna.addSequence("CC", 3, 1);
            assertEquals("CCCTACCG", dna.getBases(), MESSAGE_ERROR_ADD_SEQUENCE + "\"CC\"");

        }catch(Exception e) {
            fail("testAddSequence failed");
        }
    }


    @Test
    void testAddSequenceException() {
        try{
            dna = new DNA("AAAAACTTGTTTTTTGGTAAAAAAGTTAAG");

            Exception ex = assertThrows(Exception.class, () -> dna.addSequence("GACT", 31));
            assertEquals("[ERROR] position is greater than sequence length", ex.getMessage(), MESSAGE_ERROR_EXCEPTION);
            assertEquals("AAAAACTTGTTTTTTGGTAAAAAAGTTAAG", dna.getBases(), MESSAGE_ERROR_ADD_SEQUENCE);

            ex = assertThrows(Exception.class, () -> dna.addSequence("TTAH", 2));
            assertEquals("[ERROR] DNA's pattern is incorrect", ex.getMessage(), MESSAGE_ERROR_EXCEPTION);
            assertEquals("AAAAACTTGTTTTTTGGTAAAAAAGTTAAG", dna.getBases(), MESSAGE_ERROR_ADD_SEQUENCE);

            dna = new DNA("CTAG");

            ex = assertThrows(Exception.class, () -> dna.addSequence("CC", 5, 1));
            assertEquals("[ERROR] position is greater than sequence length", ex.getMessage(), MESSAGE_ERROR_EXCEPTION);
            assertEquals("CTAG", dna.getBases(), MESSAGE_ERROR_ADD_SEQUENCE);

            ex = assertThrows(Exception.class, () -> dna.addSequence("CC", 1, 7));
            assertEquals("[ERROR] position is greater than sequence length", ex.getMessage(), MESSAGE_ERROR_EXCEPTION);
            assertEquals("CTAG", dna.getBases(), MESSAGE_ERROR_ADD_SEQUENCE);

        }catch(Exception e) {
            fail("testAddSequenceException failed");
        }
    }


    @Test
    void testToRNA() {

        try{
            dna = new DNA("C");
            assertEquals("G", dna.toRNA(), MESSAGE_ERROR_TO_RNA + "\"C\"");

            dna = new DNA("T");
            assertEquals("A", dna.toRNA(), MESSAGE_ERROR_TO_RNA + "\"T\"");

            dna = new DNA("A");
            assertEquals("U", dna.toRNA(), MESSAGE_ERROR_TO_RNA + "\"A\"");

            dna = new DNA("G");
            assertEquals("C", dna.toRNA(), MESSAGE_ERROR_TO_RNA + "\"G\"");

            dna = new DNA("CT");
            assertEquals("AG", dna.toRNA(), MESSAGE_ERROR_TO_RNA + "\"CT\"");

            dna = new DNA("TC");
            assertEquals("GA", dna.toRNA(), MESSAGE_ERROR_TO_RNA + "\"TC\"");

            dna = new DNA("CTAG");
            assertEquals("CUAG", dna.toRNA(), MESSAGE_ERROR_TO_RNA + "CTAG\"");

            dna = new DNA("ACGGGCATCAGTATTAGCACAGT");
            assertEquals("ACUGUGCUAAUACUGAUGCCCGU", dna.toRNA(), MESSAGE_ERROR_TO_RNA + "\"ACGGGCATCAGTATTAGCACAGT\"");

        }catch(Exception e) {
            fail("testToRNA failed");
        }
    }

    @Test
    void testIsProtein() {
        try{

            dna = new DNA("ATGCGATACTGA");
            assertTrue(dna.isProtein(), MESSAGE_ERROR_IS_PROTEIN_TRUE);

            dna = new DNA("ATGCGATACTAG");
            assertFalse(dna.isProtein(), MESSAGE_ERROR_IS_PROTEIN_FALSE);

            dna = new DNA("TGACGATACATG");
            assertFalse(dna.isProtein(), MESSAGE_ERROR_IS_PROTEIN_FALSE);

            dna = new DNA("TAATGCGATACTGACGTC");
            assertFalse(dna.isProtein(), MESSAGE_ERROR_IS_PROTEIN_FALSE);

            dna = new DNA("ATGCGTACTGA");
            assertFalse(dna.isProtein(), MESSAGE_ERROR_IS_PROTEIN_FALSE);

            dna = new DNA("ATGTGA");
            assertTrue(dna.isProtein(), MESSAGE_ERROR_IS_PROTEIN_TRUE);

            dna = new DNA("ATGGTGA");
            assertFalse(dna.isProtein(), MESSAGE_ERROR_IS_PROTEIN_FALSE);

        }catch(Exception e) {
            fail("testIsProtein failed");
        }
    }
    
    @Test
    void testSequencesRepeatedLength() {
        try{

            dna = new DNA("TTTTTCCCCCTTTTTCCCCCCTTTTTGGGAAA");
            MatcherAssert.assertThat(Arrays.asList("TTTTTCCCCC", "CCCCCTTTTT"), Matchers.containsInAnyOrder(dna.sequencesRepeatedLength(10).split(" ")));
            MatcherAssert.assertThat(Arrays.asList("TTTTTCCC", "TTTTCCCC", "TTTCCCCC", "CCCCCTTT", "CCCCTTTT", "CCCTTTTT"), Matchers.containsInAnyOrder(dna.sequencesRepeatedLength(8).split(" ")));
            MatcherAssert.assertThat(Arrays.asList("TTTTT", "TTTTC", "TTTCC", "TTCCC", "TCCCC", "CCCCC", "CCCCT", "CCCTT", "CCTTT", "CTTTT"), Matchers.containsInAnyOrder(dna.sequencesRepeatedLength(5).split(" ")));

            dna = new DNA("TTTTTCCCCCTTTTTCCCCCCTTTTTAAATTAGGGGGGCCCCCCAATTGGGGGGCCCCCC");
            MatcherAssert.assertThat(Arrays.asList("GGGGGGCCCCCC"), Matchers.containsInAnyOrder(dna.sequencesRepeatedLength(12).split(" ")));
            MatcherAssert.assertThat(Arrays.asList("GGGGGGCCCCC", "GGGGGCCCCCC"), Matchers.containsInAnyOrder(dna.sequencesRepeatedLength(11).split(" ")));
            MatcherAssert.assertThat(Arrays.asList("TTTTTCCCC", "TTTTCCCCC", "CCCCCTTTT", "CCCCTTTTT", "GGGGGGCCC", "GGGGGCCCC", "GGGGCCCCC", "GGGCCCCCC"), Matchers.containsInAnyOrder(dna.sequencesRepeatedLength(9).split(" ")));


            dna = new DNA("TGATGGCGTAGCTTGGACCACCAGTAC");
            MatcherAssert.assertThat(Arrays.asList("TGG", "ACC", "CCA", "GTA"), Matchers.containsInAnyOrder(dna.sequencesRepeatedLength(3).split(" ")));

            dna = new DNA("ATATAT");
            MatcherAssert.assertThat(Arrays.asList("ATA", "TAT"), Matchers.containsInAnyOrder(dna.sequencesRepeatedLength(3).split(" ")));
            assertTrue(dna.sequencesRepeatedLength(3).contains("ATA"));
            assertTrue(dna.sequencesRepeatedLength(3).contains("TAT"));

            dna = new DNA("ACGTACGTACGTACGT");
            MatcherAssert.assertThat(Arrays.asList("ACGT", "CGTA", "GTAC", "TACG"), Matchers.containsInAnyOrder(dna.sequencesRepeatedLength(4).split(" ")));

        }catch(Exception e) {
            fail("testSequencesRepeatedLength failed");
        }
    }

    /*@Test
    void testSequencesRepeatedLength() {
        try{

            dna = new DNA("TTTTTCCCCCTTTTTCCCCCCTTTTTGGGAAA");
            assertEquals("TTTTTCCCCC CCCCCTTTTT", dna.sequencesRepeatedLength(10), MESSAGE_ERROR_REPETED_LENGHT);
            assertEquals("TTTTTCCC TTTTCCCC TTTCCCCC CCCCCTTT CCCCTTTT CCCTTTTT", dna.sequencesRepeatedLength(8), MESSAGE_ERROR_REPETED_LENGHT);
            assertEquals("TTTTT TTTTC TTTCC TTCCC TCCCC CCCCC CCCCT CCCTT CCTTT CTTTT", dna.sequencesRepeatedLength(5), MESSAGE_ERROR_REPETED_LENGHT);

            dna = new DNA("TTTTTCCCCCTTTTTCCCCCCTTTTTAAATTAGGGGGGCCCCCCAATTGGGGGGCCCCCC");
            assertEquals("GGGGGGCCCCCC", dna.sequencesRepeatedLength(12), MESSAGE_ERROR_REPETED_LENGHT);
            assertEquals("GGGGGGCCCCC GGGGGCCCCCC", dna.sequencesRepeatedLength(11), MESSAGE_ERROR_REPETED_LENGHT);
            assertEquals("TTTTTCCCC TTTTCCCCC CCCCCTTTT CCCCTTTTT GGGGGGCCC GGGGGCCCC GGGGCCCCC GGGCCCCCC", dna.sequencesRepeatedLength(9), MESSAGE_ERROR_REPETED_LENGHT);

            dna = new DNA("TGATGGCGTAGCTTGGACCACCAGTAC");
            assertEquals("TGG ACC CCA GTA", dna.sequencesRepeatedLength(3), MESSAGE_ERROR_REPETED_LENGHT);

            dna = new DNA("ATATAT");
            assertEquals("ATA TAT", dna.sequencesRepeatedLength(3), MESSAGE_ERROR_REPETED_LENGHT);

            dna = new DNA("ACGTACGTACGTACGT");
            assertEquals("ACGT CGTA GTAC TACG", dna.sequencesRepeatedLength(4), MESSAGE_ERROR_REPETED_LENGHT);

        }catch(Exception e) {
            fail("testSequencesRepeatedLength failed");
        }
    }*/
}

