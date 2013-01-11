package miinaharava.peli;

import miinaharava.domain.Kello;
import miinaharava.domain.Pelilauta;
import java.io.IOException;
import java.util.Timer;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import miinaharava.domain.Kayttajalista;
import miinaharava.domain.TilastonHallinta;
import miinaharava.gui.*;

/**
 * Miinaharavan pelitapahtumaa kuvaava luokka
 */
public class Miinaharava {

     /**
     * Miinaharavaan liittyvä pelilauta
     */
    private Pelilauta lauta;
    
    /**
     * Timer-olio jonka avulla säädetään peliin ajanotto joka toimii tietyin
     * väliajoin (tässä sekunnin välein)
     */
    private Timer timer;
    
    /**
    * Kello-olio joka pitää kirjaa peliin käytetystä ajasta
    */
    private Kello kello;
    
    /**
    * TilastonHallinta-olio joka ylläpitää tilastointitoiminnallisuutta
    */
    private TilastonHallinta tilastonhallinta;
    
    /**
    * Pelilaudan koko Stringinä eli joko "pieni", "keskikoko" tai "iso".
    * Käytetään parametrinä metodissa joka kirjaa tilastoon uuden tuloksen.
    */
    private String koko;
    
    /**
    * Olio joka pitää kirjaa rekisteröityneistä käyttäjistä
    */
    private Kayttajalista kayttajalista;
    
    /**
    * Pelaajan nimi tietyllä hetkellä, käytetään parametrinä metodissa joka
    * kirjaa tilastoon uuden tuloksen
    */
    private String kayttajanNimi;

    /**
     * Konstruktori
     */
    public Miinaharava() {
        this.tilastonhallinta = new TilastonHallinta();
        //this.tilastonhallinta.tulostaKaikkiTilastot();
        this.kayttajalista = new Kayttajalista();
        Kirjautuminen kirjautuminen = new Kirjautuminen(this);
        SwingUtilities.invokeLater(kirjautuminen);          
    }

    /**
     * Aloittaa uuden pelin avaamalla asetukset-ikkunan jossa säädetään pelilaudan
     * koko sekä asettaa miinaharavalle uudet Timer- ja Kello-oliot
     */
    public void uusiPeli() {
        Asetukset asetukset = new Asetukset(this);
        SwingUtilities.invokeLater(asetukset);  
        this.timer = new Timer();
        this.kello = new Kello(new JLabel());
    }

    /**
     * Palauttaa miinaharavaan liittyvän pelilaudan
     *
     * @return pelilauta
     */   
    public Pelilauta getLauta() {
        return lauta;
    }

    /**
     * Asettaa miinaharavalle pelilaudan
     *
     * @param lauta Asetettava pelilauta
     */   
    public void setLauta(Pelilauta lauta) {
        this.lauta = lauta;
    }
    
    /**
     * Jos peli on voitettu, kutsutaan pelilaudan metodia joka avaa kaikki ruudut,
     * haetaan pelaamiseen kulunut aika kellolta, pysäytetään ajastus sekä näytetään voittoviesti,
     * ja lopuksi kutsutaan tilastot päivittävää metodia.
     */   
    public void voitto() {
        this.lauta.avaaKaikki("voitto");
        int loppuaika = this.kello.getAikanyt();
        this.timer.cancel();
        JOptionPane.showMessageDialog(null, ":> voitit\naikasi: " + loppuaika + " sek");
        paivitaJaNaytaTilasto(loppuaika);
    }
    
    /**
     * Metodi joka päivittää tilaston voittotuloksella ja näyttää tilastoikkunan
     * 
     * @param loppuaika Aika jolla peli voitettiin
     */ 
    public void paivitaJaNaytaTilasto(int loppuaika) {
        this.tilastonhallinta.lisaaTulosTilastoon(this.kayttajanNimi, loppuaika, this.koko);
        TilastoIkkuna tikkuna = new TilastoIkkuna(this);
        SwingUtilities.invokeLater(tikkuna);   
        
        try {
            this.tilastonhallinta.paivitaKaikkiTiedostot();
        } catch (IOException ex) {
            System.out.println("Tiedostoon kirjoitaminen ei onnistu:" + ex.getMessage());
        }
    }
    /**
     * Metodi jota kutsutaan kun pelaaja osuu miinaan. Avataan kaikki ruudut,
     * pysäytetään ajastus ja näytetään häviöviesti.
     */   
    public void havio() {
        this.lauta.avaaKaikki("havio");
        this.timer.cancel();
        JOptionPane.showMessageDialog(null, ":< hävisit");
    }
    
    /**
     * Palauttaa miinaharavaan liittyvän Kello-olion
     *
     * @return kello
     */   
    public Kello getKello() {
        return kello;
    }

    /**
     * Palauttaa miinaharavaan liittyvän Timer-olion
     *
     * @return timer
     */   
    public Timer getTimer() {
        return timer;
    }
    
    /**
     * Asetetaan miinaharavalle Kello-olio
     *
     * @param kello Asetettava Kello-olio
     */   
    public void setKello(Kello kello) {
        this.kello = kello;
    }

    /**
     * Asetetaan miinaharavalle Timer-olio
     *
     * @param timer Asetettava Timer-olio
     */  
    public void setTimer(Timer timer) {
        this.timer = timer;
    }
    
    /**
     * Asettaa miinaharavalle uuden pelilaudan valitussa koossa valitun värisenä sekä
     * kertoo timer-oliolle, että kellon run()-metodia on kutsuttava sekunnin
     * (eli 1000 millisekunnin) välein.
     *
     * @param valittuKoko Pelilaudan koko, joko "pieni", "keskikoko" tai "iso"
     * @param valittuVari Pelilaudan väri, joko "punainen" tai "vihrea"
     */ 
    public void luoPelilauta(String valittuKoko, String valittuVari) {
        switch (valittuKoko) {
            case "pieni":
                this.lauta = new Pelilauta(10, 10, 12, valittuVari);
                this.koko = "pieni";
                break;
            case "keskikoko":
                this.lauta = new Pelilauta(20, 15, 40, valittuVari);
                this.koko = "keskikoko";
                break;
            case "iso":
                this.lauta = new Pelilauta(35, 20, 100, valittuVari);
                this.koko = "iso";
                break;
        }
        this.lauta.tulosta(); // väliaikainen toiminto tekstiversiolle 
        this.timer.schedule(kello, 0, 1000);        
    }

    /**
     * Palauttaa miinaharavaan liittyvän TilastonHallinta-olion
     *
     * @return tilastonhallinta
     */  
    public TilastonHallinta getTilastonhallinta() {
        return tilastonhallinta;
    }

    /**
     * Palauttaa miinaharavaan liittyvän KayttajaLista-olion
     *
     * @return kayttajalista
     */  
    public Kayttajalista getKayttajalista() {
        return kayttajalista;
    }

    /**
     * Asetetaan miinaharavalle käyttäjä
     *
     * @param kayttajanNimi Käyttäjän nimi/tunnus, saadaan tietoon alussa
     * kun peliin kirjaudutaan sisään
     */  
    public void setKayttajanNimi(String kayttajanNimi) {
        this.kayttajanNimi = kayttajanNimi;
    }
    
}
