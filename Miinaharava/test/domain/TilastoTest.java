package domain;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import miinaharava.domain.Tilasto;
import miinaharava.domain.Tulos;
import static org.junit.Assert.*;
import org.junit.*;

public class TilastoTest {

    File tiedosto;
    Tilasto tilasto;

    public TilastoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        tiedosto = new File("src/tiedostot/tilasto_testi.txt");
        tilasto = new Tilasto(tiedosto);
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void lisaaTulosToimiiOikein() throws IOException {
        Tulos tulos = new Tulos("kaisa", 10);
        tilasto.lisaaTulos(tulos);

        assertTrue(tilasto.getTulokset().contains(tulos));
    }
    
    @Test
    public void generoiTulosToimiiOikein() throws IOException {
        Tulos tulos = tilasto.generoiTulos("kaisa 100000");

        assertEquals("kaisa", tulos.getNimi());
        assertEquals(100000, tulos.getTulos());
    }    
    
    @Test
    public void listassaOikeaMaaraTuloksia() throws IOException {
        int lines = laskeRivitTiedostossa();

        assertEquals(lines, tilasto.getTulokset().size());
    }

    @Test
    public void listassaOikeaMaaraTuloksiaKunLisattyYksi() throws IOException {
        tilasto.lisaaTulos(new Tulos("kaisa", 10));
        tilasto.paivitaTiedosto();
        tilasto.tilastoListaksi();
        int lines = laskeRivitTiedostossa();

        assertEquals(lines, tilasto.getTulokset().size());
    }

    @Test
    public void listassaOikeaMaaraTuloksiaKunLisattyKaksi() throws IOException {
        tilasto.lisaaTulos(new Tulos("kaisa", 10));
        tilasto.lisaaTulos(new Tulos("pekka", 16));
        tilasto.paivitaTiedosto();
        tilasto.tilastoListaksi();
        int lines = laskeRivitTiedostossa();

        assertEquals(lines, tilasto.getTulokset().size());
    }

    /**
     * Testeissä käytettävä tiedoston rivien laskeminen
     */
    public int laskeRivitTiedostossa() throws IOException {
        BufferedReader reader = null;
        int lines = 0;
        try {
            reader = new BufferedReader(new FileReader(tiedosto));
            while (reader.readLine() != null) {
                lines++;
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TilastoTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(TilastoTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lines;
    }
}
