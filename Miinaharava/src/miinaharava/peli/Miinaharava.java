package miinaharava.peli;

import java.util.Timer;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import miinaharava.gui.Kello;
import miinaharava.gui.Pelilauta;

public class Miinaharava {

    private Pelilauta lauta;
    private Timer timer;
    private Kello kello;

    public Miinaharava() {
        this.lauta = new Pelilauta(9,9,10);   
        this.timer = new Timer();
        this.kello = new Kello(new JLabel());
        timer.schedule(kello, 0, 1000);

    }

    public void uusiPeli() {
        //naytaAsetukset();
        this.lauta.tulosta(); // väliaikainen toiminto tekstiversiolle
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
    
}
