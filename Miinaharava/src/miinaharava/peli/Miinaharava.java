package miinaharava.peli;

import miinaharava.gui.Pelilauta;

public class Miinaharava {

    private Pelilauta lauta;

    public Miinaharava(Pelilauta lauta) {
        this.lauta = lauta;
       // this.lauta.generoiTaulukko();
       // this.lauta.asetaMiinat();
       // this.lauta.asetaMiinattomat();
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
    
    
}
