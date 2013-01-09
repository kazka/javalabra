package domain;

import java.util.ArrayList;
import miinaharava.domain.Ruutu;
import miinaharava.domain.Pelilauta;
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
        lauta = new Pelilauta(20, 30, 10, "punainen");
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
    public void pelilaudanTaulukkoGeneroidaanOikeaanKokoon() {
        lauta.generoiTaulukko(3, 3, "punainen");
        assertEquals(lauta.getKorkeus(), 3);
        assertEquals(lauta.getLeveys(), 3);
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
        lauta.generoiTaulukko(3, 3, "punainen");
        lauta.getTaulukko()[0][1].setSisalto(9);
        lauta.getTaulukko()[1][0].setSisalto(9);
        lauta.getTaulukko()[1][2].setSisalto(9);
        lauta.getTaulukko()[2][1].setSisalto(9);

        assertEquals(4, lauta.laskeYmparoivatMiinat(1, 1));
    }

    @Test
    public void miinatSivuillaLasketaanOikeinKulmaruudulle() {
        lauta.generoiTaulukko(3, 3, "punainen");
        lauta.getTaulukko()[0][1].setSisalto(9);
        lauta.getTaulukko()[1][0].setSisalto(9);

        assertEquals(2, lauta.laskeYmparoivatMiinat(0, 0));
    }

    @Test
    public void miinatKulmissaLasketaanOikein() {
        lauta.generoiTaulukko(3, 3, "punainen");
        lauta.getTaulukko()[0][0].setSisalto(9);
        lauta.getTaulukko()[0][2].setSisalto(9);
        lauta.getTaulukko()[2][0].setSisalto(9);
        lauta.getTaulukko()[2][2].setSisalto(9);

        assertEquals(4, lauta.laskeYmparoivatMiinat(1, 1));
    }

    @Test
    public void miinatKulmissaLasketaanOikeinKulmaruudulle() {
        lauta.generoiTaulukko(3, 3, "punainen");
        lauta.getTaulukko()[1][1].setSisalto(9);

        assertEquals(1, lauta.laskeYmparoivatMiinat(0, 0));
    }

    @Test
    public void onkoTaulukossaPalauttaaTrueJosOnTaulukossaTapaus1() {
        assertTrue(lauta.onkoTaulukossa(29,19));
    }  
    
    @Test
    public void onkoTaulukossaPalauttaaTrueJosOnTaulukossaTapaus2() {
        assertTrue(lauta.onkoTaulukossa(0,0));
    }  
    
    @Test
    public void onkoTaulukossaPalauttaaFalseJosEiOleTaulukossaTapaus1() {
        assertFalse(lauta.onkoTaulukossa(-1,0));
    }
    
    @Test
    public void onkoTaulukossaPalauttaaFalseJosEiOleTaulukossaTapaus2() {
        assertFalse(lauta.onkoTaulukossa(29,20));
    }    
    
    @Test
    public void haeMiinaPalauttaaYksiJosSoluOnTaulukossaJaSiinaOnMiinaTapaus1() {
        lauta.getTaulukko()[29][19].setSisalto(9);

        assertEquals(1, lauta.haeMiina(29,19));
    }
    
    @Test
    public void haeMiinaPalauttaaYksiJosSoluOnTaulukossaJaSiinaOnMiinaTapaus2() {
        lauta.getTaulukko()[0][0].setSisalto(9);

        assertEquals(1, lauta.haeMiina(0,0));
    }    
    
    @Test
    public void haeMiinaPalauttaaNollaJosSoluTaulukonUlkopuolellaKulmatapaus() {
        assertEquals(0, lauta.haeMiina(30,20));
    }   
       
    
    @Test
    public void haeMiinaPalauttaaNollaJosSoluTaulukonUlkopuolellaReunatapaus() {
        lauta.generoiTaulukko(3, 3, "punainen");

        assertEquals(0, lauta.haeMiina(30,19));
        assertEquals(0, lauta.haeMiina(29,20));
    }    
    
    @Test
    public void haeMiinaPalauttaaNollaJosEiMiinaa() {
        lauta.getTaulukko()[29][19].setSisalto(1);

        assertEquals(0, lauta.haeMiina(29,19));
    }    
    
    @Test
    public void onkoEnsimmainenAvausPalauttaaFalseJosJotainJoAvattu() {
        lauta.getTaulukko()[1][1].setStatus("avattu");
        boolean kiinni = true;
        for (int i = 0; i < lauta.getKorkeus(); i++) {
            for (int j = 0; j < lauta.getLeveys(); j++) {
                if (!lauta.getTaulukko()[i][j].getStatus().equals("kiinni")) {
                    kiinni = false;
                }
            }
        }
        assertFalse(kiinni);
    }

    @Test
    public void onkoEnsimmainenAvausPalauttaaFalseJosEnsimmainenKulmaruutuAvattu() {
        lauta.getTaulukko()[0][0].setStatus("avattu");
        boolean kiinni = true;
        for (int i = 0; i < lauta.getKorkeus(); i++) {
            for (int j = 0; j < lauta.getLeveys(); j++) {
                if (!lauta.getTaulukko()[i][j].getStatus().equals("kiinni")) {
                    kiinni = false;
                }
            }
        }
        assertFalse(kiinni);
    }

    @Test
    public void onkoEnsimmainenAvausPalauttaaFalseJosViimeinenKulmaruutuAvattu() {
        lauta.getTaulukko()[29][19].setStatus("avattu");
        boolean kiinni = true;
        for (int i = 0; i < lauta.getKorkeus(); i++) {
            for (int j = 0; j < lauta.getLeveys(); j++) {
                if (!lauta.getTaulukko()[i][j].getStatus().equals("kiinni")) {
                    kiinni = false;
                }
            }
        }
        assertFalse(kiinni);
    }

    @Test
    public void avaaKaikkiKutsunJalkeenMikaanRuutuEiOleKiinniHavitessa() {
        lauta.getTaulukko()[0][0].setStatus("merkattu");
        lauta.getTaulukko()[29][19].setStatus("merkattu");
        lauta.avaaKaikki("havio");
        boolean kiinni = false;
        for (int i = 0; i < lauta.getKorkeus(); i++) {
            for (int j = 0; j < lauta.getLeveys(); j++) {
                if (lauta.getTaulukko()[i][j].getStatus().equals("kiinni")) {
                    kiinni = true;
                }
            }
        }
        assertFalse(kiinni);
    }
    
    @Test
    public void avaaKaikkiKutsunJalkeenMiinojaEiAvataVoitettaessa() {
        lauta.getTaulukko()[0][0].setStatus("merkattu");
        lauta.getTaulukko()[29][19].setStatus("merkattu");
        lauta.avaaKaikki("voitto");
        boolean kiinni = false;
        for (int i = 0; i < lauta.getKorkeus(); i++) {
            for (int j = 0; j < lauta.getLeveys(); j++) {
                if (lauta.getTaulukko()[i][j].getStatus().equals("avattu") && lauta.getTaulukko()[i][j].getSisalto() == 9) {
                    kiinni = true;
                }
            }
        }
        assertFalse(kiinni);
    }    

    @Test
    public void voitettaessaKaikkiPaitsiMiinatAvattuTapausMiinaKeskella() {
        lauta.generoiTaulukko(3, 3, "punainen");
        lauta.getTaulukko()[1][1].setSisalto(9);

        for (int i = 0; i < lauta.getKorkeus(); i++) {
            for (int j = 0; j < lauta.getLeveys(); j++) {
                if (lauta.getTaulukko()[i][j].getSisalto() != 9) {
                    lauta.getTaulukko()[i][j].setStatus("avattu");
                }
            }
        }

        assertTrue(lauta.onkoVoitettu());
    }

    @Test
    public void voitettaessaKaikkiPaitsiMiinataAvattuTapausMiinatReunassa() {
        lauta.generoiTaulukko(3, 3, "punainen");
        lauta.getTaulukko()[1][1].setStatus("avattu");

        for (int i = 0; i < lauta.getKorkeus(); i++) {
            for (int j = 0; j < lauta.getLeveys(); j++) {
                if (!lauta.getTaulukko()[i][j].getStatus().equals("avattu")) {
                    lauta.getTaulukko()[i][j].setSisalto(9);
                }
            }
        }

        assertTrue(lauta.onkoVoitettu());
    }

    @Test
    public void avaaViereisetNollatToimiiOikeinIsompialue() {
        lauta.generoiTaulukko(3, 3, "punainen");
        lauta.getTaulukko()[0][0].setSisalto(9);
        lauta.asetaMiinattomat();
        ArrayList<Ruutu> avattavat = lauta.avaaViereisetNollat(2, 2, new ArrayList<Ruutu>());

        boolean nollatAvattu = true;
        for (Ruutu ruutu : avattavat) {
            if (ruutu.getStatus().equals("kiinni")) {
                nollatAvattu = false;
            }
        }

        assertEquals("kiinni", lauta.getTaulukko()[0][0].getStatus());
        assertTrue(nollatAvattu);
    }

    @Test
    public void avaaViereisetNollatToimiiOikeinPienempialue() {
        lauta.generoiTaulukko(3, 3, "punainen");
        lauta.getTaulukko()[0][0].setSisalto(9);
        lauta.getTaulukko()[0][1].setSisalto(9);
        lauta.getTaulukko()[0][2].setSisalto(9);
        lauta.getTaulukko()[1][2].setSisalto(9);
        lauta.getTaulukko()[2][2].setSisalto(9);

        lauta.asetaMiinattomat();
        ArrayList<Ruutu> avattavat = lauta.avaaViereisetNollat(0, 2, new ArrayList<Ruutu>());

        boolean nollatAvattu = true;
        for (Ruutu ruutu : avattavat) {
            if (ruutu.getStatus().equals("kiinni")) {
                nollatAvattu = false;
            }
        }

        assertEquals("kiinni", lauta.getTaulukko()[0][0].getStatus());
        assertEquals("kiinni", lauta.getTaulukko()[0][1].getStatus());
        assertEquals("kiinni", lauta.getTaulukko()[0][2].getStatus());
        assertEquals("kiinni", lauta.getTaulukko()[1][2].getStatus());
        assertEquals("kiinni", lauta.getTaulukko()[2][2].getStatus());

        assertTrue(nollatAvattu);
    }

    @Test
    public void nollauksenJalkeenKaikkienRuutujenSisaltoNolla() {
        lauta.nollaa();
        boolean kaikkiNollia = true;
        for (int i = 0; i < lauta.getKorkeus(); i++) {
            for (int j = 0; j < lauta.getLeveys(); j++) {
                if (lauta.getTaulukko()[i][j].getSisalto() != 0) {
                    kaikkiNollia = false;
                }
            }
        }
        assertTrue(kaikkiNollia);
    }

    @Test
    public void nollausNollaaKulmat() {
        lauta.generoiTaulukko(3, 3, "punainen");
        lauta.getTaulukko()[0][0].setSisalto(9);
        lauta.getTaulukko()[0][2].setSisalto(9);
        lauta.getTaulukko()[2][0].setSisalto(9);
        lauta.getTaulukko()[2][2].setSisalto(9);
        lauta.nollaa();
        boolean kaikkiNollia = true;
        for (int i = 0; i < lauta.getKorkeus(); i++) {
            for (int j = 0; j < lauta.getLeveys(); j++) {
                if (lauta.getTaulukko()[i][j].getSisalto() != 0) {
                    kaikkiNollia = false;
                }
            }
        }
        assertTrue(kaikkiNollia);
    }
}
