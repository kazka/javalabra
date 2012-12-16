package miinaharava.peli;

import miinaharava.gui.Pelilauta;

public class Miinaharava {

    private Pelilauta lauta;

    public Miinaharava(Pelilauta lauta) {
        this.lauta = lauta;
        this.lauta.asetaMiinat();
        this.lauta.asetaMiinattomat();
    }


    public void start() {
        this.lauta.tulosta();
    }

    public Pelilauta getLauta() {
        return lauta;
    }
    
    
}
