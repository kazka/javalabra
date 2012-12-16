
package gui;

import miinaharava.gui.Pelilauta;
import org.junit.*;
import static org.junit.Assert.*;


public class PelilautaTest {
    
    Pelilauta lauta;
    
    public PelilautaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        lauta = new Pelilauta(20, 30, 10);
    }
    
    @After
    public void tearDown() {
    }
    
     @Test
     public void pelilaudanKokoAsetetaanOikein() {
         assertEquals(lauta.getKorkeus(), 30);
         assertEquals(lauta.getLeveys(), 20);
     }
}
