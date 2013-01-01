
package gui;

import miinaharava.gui.Kayttoliittyma;
import miinaharava.gui.Pelilauta;
import miinaharava.peli.Miinaharava;
import org.junit.*;
import static org.junit.Assert.*;

public class KayttoliittymaTest {
    
    Kayttoliittyma kali;
    
    public KayttoliittymaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        kali = new Kayttoliittyma(new Miinaharava());
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void jokaRuudulleAsetetaanJButton() {
        kali.run();
        Pelilauta lauta = kali.getHarava().getLauta();
        //lauta.generoiUusiLauta();
        boolean onJbutton = true;
        for (int i = 0; i < lauta.getKorkeus(); i++) {
            for (int j = 0; j < lauta.getLeveys(); j++) {
                if (lauta.getTaulukko()[i][j].getBtn() == null) {
                    onJbutton = false;
                }
            }
        }        
        assertTrue(onJbutton);
    }    
    
}
