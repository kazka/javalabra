package gui;

import miinaharava.domain.Ruutu;
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
    
    @Test
    public void pelilaudanKokoAsetetaanOikeinJosMinimikoko(){
        Pelilauta minimilauta = new Pelilauta(5,5,2);
        
        assertEquals(minimilauta.getKorkeus(), 5);
        assertEquals(minimilauta.getLeveys(), 5);
    }
    
    @Test
    public void pelilaudanKokoAsetetaanOikeinJosLiianPieni(){
        Pelilauta pienilauta = new Pelilauta(4,4,3);
        
        assertEquals(pienilauta.getKorkeus(), 5);
        assertEquals(pienilauta.getLeveys(), 5);
    }    

    @Test
    public void miinojaAsetetaanOikeaMaara() {
        int lkm = 0;
        for (int i = 0; i < lauta.getKorkeus(); i++) {
            for (int j = 0; j < lauta.getLeveys(); j++) {
                if (lauta.getTaulukko()[i][j].onkoMiinaa()) {
                    lkm++;
                }
            }
        }
        assertEquals(lauta.getMiinoja(), lkm);
    }

    @Test
    public void miinatSivuillaLasketaanOikein() {
        lauta.generoiTaulukko(3, 3);
        lauta.getTaulukko()[0][1] = new Ruutu();
        lauta.getTaulukko()[0][1].setSisalto(9);  
        lauta.getTaulukko()[1][0] = new Ruutu();
        lauta.getTaulukko()[1][0].setSisalto(9);
        lauta.getTaulukko()[1][2] = new Ruutu();
        lauta.getTaulukko()[1][2].setSisalto(9);
        lauta.getTaulukko()[2][1] = new Ruutu();
        lauta.getTaulukko()[2][1].setSisalto(9);

        assertEquals(4, lauta.laskeYmparoivatMiinat(1, 1));
    }
    
    @Test
    public void miinatKulmissaLasketaanOikein() {
        lauta.generoiTaulukko(3, 3);
        lauta.getTaulukko()[0][0] = new Ruutu();
        lauta.getTaulukko()[0][0].setSisalto(9);  
        lauta.getTaulukko()[0][2] = new Ruutu();
        lauta.getTaulukko()[0][2].setSisalto(9);
        lauta.getTaulukko()[2][0] = new Ruutu();
        lauta.getTaulukko()[2][0].setSisalto(9);
        lauta.getTaulukko()[2][2] = new Ruutu();
        lauta.getTaulukko()[2][2].setSisalto(9);

        assertEquals(4, lauta.laskeYmparoivatMiinat(1, 1));
    }    
}
