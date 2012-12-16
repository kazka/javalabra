
package miinaharava.peli;

import javax.swing.SwingUtilities;
import miinaharava.gui.Kayttoliittyma;
import miinaharava.gui.Pelilauta;

public class Main{

    public static void main(String[] args) {
        Pelilauta pelilauta = new Pelilauta(10, 10, 10);        
        
        Miinaharava miinaharava = new Miinaharava(pelilauta);
        
        Kayttoliittyma kali = new Kayttoliittyma(miinaharava);
        SwingUtilities.invokeLater(kali);

        while (kali.getAlusta() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole vielä luotu.");
            }
        }

        miinaharava.setPaivitettava(kali.getAlusta());        
        
        miinaharava.start();
    }
}
