
package miinaharava.peli;

import miinaharava.gui.Pelilauta;

public class Main{

    public static void main(String[] args) {
        Pelilauta pelilauta = new Pelilauta(10, 10, 10);        
        
        Miinaharava miinaharava = new Miinaharava(pelilauta);
        
        miinaharava.start();
    }
}
