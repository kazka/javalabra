
package miinaharava.peli;

import javax.swing.SwingUtilities;
import miinaharava.gui.Kayttoliittyma;
import miinaharava.gui.Pelilauta;

public class Main{

    public static void main(String[] args) {
        Miinaharava miinaharava = new Miinaharava();
        
        Kayttoliittyma kali = new Kayttoliittyma(miinaharava);
        SwingUtilities.invokeLater(kali);    
        
        miinaharava.uusiPeli();
    }
}
