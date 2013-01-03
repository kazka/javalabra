package domain;

import miinaharava.domain.Ruutu;
import miinaharava.gui.Pelilauta;
import org.junit.*;
import static org.junit.Assert.*;

public class RuutuTest {

    Ruutu ruutu;

    public RuutuTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        ruutu = new Ruutu();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void ruutuAlussaKiinni() {
        assertEquals("kiinni", ruutu.getStatus());
    }
    
    @Test
    public void ruudunKoordinaatitAsetetaanOikein() {
        ruutu = new Ruutu(5,3);
        assertEquals(5, ruutu.getX());
        assertEquals(3, ruutu.getY());
    }    

    @Test
    public void onkoMiinaaPalauttaaTrueJosMiina() {
        ruutu.setSisalto(9);
        assertTrue(ruutu.onkoMiinaa());
    }
    
    @Test
    public void onkoMiinaaPalauttaaFalseJosEiMiinaa() {
        ruutu.setSisalto(0);
        Ruutu ruutu2 = new Ruutu();
        ruutu2.setSisalto(8);
        assertFalse(ruutu.onkoMiinaa());
        assertFalse(ruutu2.onkoMiinaa());
    }    
    
    @Test
    public void yhdenRuudunMerkkauksenJalkeenStatusMerkattu() {
        ruutu.merkkaa();
        assertEquals("merkattu", ruutu.getStatus());
    }      
    
    @Test
    public void kahdenSamanRuudunMerkkauksenJalkeenStatusKiinni() {
        ruutu.merkkaa();
        ruutu.merkkaa();
        assertEquals("kiinni", ruutu.getStatus());
    }    
}
