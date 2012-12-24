
package miinaharava.peli;

import javax.swing.SwingUtilities;
import miinaharava.gui.Kayttoliittyma;
import miinaharava.gui.Pelilauta;

public class Main{

    public static void main(String[] args) {
        Pelilauta pelilauta = new Pelilauta(9,9,10);        
        
        Miinaharava miinaharava = new Miinaharava(pelilauta);
        
        Kayttoliittyma kali = new Kayttoliittyma(miinaharava);
        SwingUtilities.invokeLater(kali);    
        
        miinaharava.start();
    }
}
