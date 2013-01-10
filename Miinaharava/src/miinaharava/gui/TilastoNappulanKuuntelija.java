
package miinaharava.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import miinaharava.peli.Miinaharava;

/**
 * Luokka jolla kuunnellaan klikkauksia nappulassa jolla näytetään tilastot
 */
public class TilastoNappulanKuuntelija implements ActionListener {
    
    /**
    * Miinaharava-olio johon kuuntelija liittyy
    */
    private Miinaharava harava;

    /**
    * Konstruktori.
    * 
    * @param harava Miinaharava johon kuuntelija liittyy
    */
    public TilastoNappulanKuuntelija(Miinaharava harava, JFrame frame) {
        this.harava = harava;
    }

    /**
    * Klikkauksessa avataan uusi TilastoIkkuna
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        TilastoIkkuna tikkuna = new TilastoIkkuna(this.harava);
        SwingUtilities.invokeLater(tikkuna);        
    }
    
}
