package miinaharava.peli;

import java.util.Timer;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import miinaharava.gui.Asetukset;
import miinaharava.gui.Kayttoliittyma;
import miinaharava.gui.Kello;
import miinaharava.gui.Pelilauta;

public class Miinaharava {

    private Pelilauta lauta;
    private Timer timer;
    private Kello kello;

    public Miinaharava() {
        //this.lauta = new Pelilauta(9,9,10);   
    }

    public void uusiPeli() {
        Asetukset asetukset = new Asetukset(this);
        SwingUtilities.invokeLater(asetukset);  
        this.timer = new Timer();
        this.kello = new Kello(new JLabel());
    }

    public Pelilauta getLauta() {
        return lauta;
    }

    public void setLauta(Pelilauta lauta) {
        this.lauta = lauta;
    }
    
    // tarkistaa onko peli voitettu, jos on niin avaa kaikki ruudut ja laskee pelaamiseen kuluneen ajan sekä näyttää voittoviestin
    public void tarkistaVoitto() {
        if (this.lauta.onkoVoitettu()){
            this.lauta.avaaKaikki();
            int loppuaika = this.kello.getAikanyt();
            this.timer.cancel();
            JOptionPane.showMessageDialog(null, ":> voitit\naikasi: " + loppuaika + " sek");
        }
    }

    // metodi jota kutsutaan kun pelaaja osuu miinaan. avaa kaikki ruudut ja näyttää häviöviestin
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
