
package domain;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import miinaharava.domain.Kayttajalista;
import miinaharava.domain.Tilasto;
import miinaharava.domain.Tulos;
import org.junit.*;
import static org.junit.Assert.*;


public class KayttajalistaTest {
    
    Kayttajalista lista;
    
    public KayttajalistaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        lista = new Kayttajalista();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void tiedostostaLuetaanOikeaMaaraKayttajia() throws IOException {
        int lines = laskeRivitTiedostossa();

        assertEquals(lines, lista.getKayttajat().keySet().size());
    }
    
    @Test
    public void tiedostostaLuetaanOikeaMaaraKayttajiaKunLisattyUusi() throws IOException {
        lista.lisaaKayttaja("kaka", "salasana");
        lista.paivitaTiedosto();
        lista.lueKayttajatiedosto(lista.getTiedosto());
        int lines = laskeRivitTiedostossa();

        assertEquals(lines, lista.getKayttajat().keySet().size());
    }
    
    @Test
    public void lisaaKayttajaToimiiOikein() throws IOException {
        lista.lisaaKayttaja("3636", "6777");

        assertEquals("6777", lista.getKayttajat().get("3636"));
    }
    
    @Test
    public void tarkistaTunnusPalauttaaTrueJosTasmaa() throws IOException {
        lista.lisaaKayttaja("3636", "6777");

        assertTrue(lista.tarkistaTunnus("3636", "6777"));
    }
    
    @Test
    public void tarkistaTunnusPalauttaaFalseJosEiTasmaa() throws IOException {
        lista.lisaaKayttaja("3636", "6777");

        assertFalse(lista.tarkistaTunnus("3636", "677788"));
    }
    
    @Test
    public void onkoSamannimistaKayttajaaPalauttaaTrueJosSamanniminenLoytyy() throws IOException {
        lista.lisaaKayttaja("kaisa", "6777");

        assertTrue(lista.onkoSamannimistaKayttajaa("kaisa"));
    }
    
    @Test
    public void onkoSamannimistaKayttajaaPalauttaaFalseJosSamannimistaEiLoydy() throws IOException {
        lista.lisaaKayttaja("kaisa", "6777");
        lista.lisaaKayttaja("matti", "pekka");

        assertFalse(lista.onkoSamannimistaKayttajaa("pekka"));
    }
    
    @Test
    public void lisaaKayttajaTiedostostaHashmapiinToimiiOikein() throws IOException {
        lista.lisaaKayttajaTiedostostaHashmapiin("kaisa sala");

        assertEquals("sala", lista.getKayttajat().get("kaisa"));
    }  
    
    /**
     * Testeissä käytettävä tiedoston rivien laskeminen
     */
    public int laskeRivitTiedostossa() throws IOException {
        BufferedReader reader = null;
        int lines = 0;
        try {
            reader = new BufferedReader(new FileReader(lista.getTiedosto()));
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
