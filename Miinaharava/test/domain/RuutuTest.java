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
    public void kaikkiRuudutAlussaKiinni() {
        Pelilauta lauta = new Pelilauta(10, 10, 10);
        boolean kiinni = true;
        for (int i = 0; i < lauta.getKorkeus(); i++) {
            for (int j = 0; j < lauta.getLeveys(); j++) {
                if (!lauta.getTaulukko()[i][j].getStatus().equals("kiinni")) {
                    kiinni = false;
                }
            }
        }
        assertTrue(kiinni);
    }

    @Test
    public void onkoMiinaaPalauttaaTrueJosMiina() {
        ruutu.setSisalto(9);
        assertTrue(ruutu.onkoMiinaa());
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
