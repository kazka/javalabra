package miinaharava.peli;

import java.util.Timer;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import miinaharava.gui.Asetukset;
import miinaharava.gui.Kello;
import miinaharava.gui.Pelilauta;

/**
 * Miinaharavan pelitapahtumaa kuvaava luokka
 */
public class Miinaharava {

     /**
     * Miinaharavaan liittyvä pelilauta
     */
    private Pelilauta lauta;
    
    /**
     * Timer-olio jonka avulla säädetään peliin ajanotto
     */
    private Timer timer;
    
     /**
     * Kello-olio joka pitää kirjaa peliin käytetystä ajasta
     */
    private Kello kello;

    /**
     * Konstruktori
     */
    public Miinaharava() {
        //this.lauta = new Pelilauta(9,9,10);   
    }

    /**
     * Aloittaa uuden pelin avaamalla asetukset-ikkunan jossa säädetään pelilaudan koko,
     * ja asettaa miinaharavalle uudet Timer- ja Kello-oliot
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
     * @return Pelilauta
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
     * Tarkistaa onko peli voitettu. Jos on kutsuu pelilaudan metodia joka avaa kaikki ruudut,
     * hakee pelaamiseen kuluneen ajan, pysäyttää kellon sekä näyttää voittoviestin.
     */   
    public void tarkistaVoitto() {
        if (this.lauta.onkoVoitettu()){
            this.lauta.avaaKaikki();
            int loppuaika = this.kello.getAikanyt();
            this.timer.cancel();
            JOptionPane.showMessageDialog(null, ":> voitit\naikasi: " + loppuaika + " sek");
        }
    }
    
    /**
     * Metodi jota kutsutaan kun pelaaja osuu miinaan. Avaa kaikki ruudut,
     * pysäyttää kellon ja näyttää häviöviestin.
     */   
    public void havio() {
        this.lauta.avaaKaikki();
        this.timer.cancel();
        JOptionPane.showMessageDialog(null, ":< hävisit");
    }

    public Kello getKello() {
        return kello;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setKello(Kello kello) {
        this.kello = kello;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
    
    /**
     * Asettaa miinaharavalle uuden pelilaudan valitussa koossa sekä käynnistää ajanoton.
     *
     * @param valittu Pelilaudan koko, joko "pieni", "keskikoko" tai "iso"
     */ 
    public void luoPelilauta(String valittu) {
        switch (valittu) {
            case "pieni":
                this.lauta = new Pelilauta(10, 10, 12);
                break;
            case "keskikoko":
                this.lauta = new Pelilauta(20, 15, 40);
                break;
            case "iso":
                this.lauta = new Pelilauta(35, 20, 110);
                break;
        }
        this.lauta.tulosta(); // väliaikainen toiminto tekstiversiolle 
        this.timer.schedule(kello, 0, 1000);        
    }
    
}
