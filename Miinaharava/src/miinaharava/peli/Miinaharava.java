package miinaharava.peli;

import javax.swing.JOptionPane;
import miinaharava.gui.Pelilauta;

public class Miinaharava {

    private Pelilauta lauta;
    private long alkuaika;

    public Miinaharava(Pelilauta lauta) {
        this.lauta = lauta;
       // this.lauta.generoiTaulukko();
       // this.lauta.asetaMiinat();
       // this.lauta.asetaMiinattomat();
        this.alkuaika = System.currentTimeMillis();
    }


    public void start() {
        this.lauta.tulosta(); // väliaikainen toiminto  
    }

    public Pelilauta getLauta() {
        return lauta;
    }

    public void setLauta(Pelilauta lauta) {
        this.lauta = lauta;
    }
    
    public void tarkistaVoitto() {
        if (this.lauta.onkoVoitettu()){
            this.lauta.avaaKaikki();
            long loppuaika = (System.currentTimeMillis() - this.alkuaika)/1000;
            JOptionPane.showMessageDialog(null, ":> voitit\naikasi: " + loppuaika + " sek");
        }
    }

    public void havio() {
        this.lauta.avaaKaikki();
            JOptionPane.showMessageDialog(null, ":< hävisit");
    }
    
}
