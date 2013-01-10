
package miinaharava.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import miinaharava.peli.Miinaharava;

/**
 * Luokka jolla kuunnellaan klikkauksia Uusi peli-nappulassa
 */
public class UusipeliNappulanKuuntelija implements ActionListener {
    
    /**
    * Miinaharava-olio johon kuuntelija liittyy
    */
    private Miinaharava harava;
    
    /**
    * JFrame jonka tietoja kuuntelijalla päivitetään
    */
    private JFrame frame;

    /**
    * Konstruktori.
    * 
    * @param harava Miinaharava johon kuuntelija liittyy
    * @param frame JFrame joka kuuntelijaan liittyy
    */
    public UusipeliNappulanKuuntelija(Miinaharava harava, JFrame frame) {
        this.harava = harava;
        this.frame = frame;
    }

    /**
    * Klikatessa suljetaan nykyinen frame ja avataan uuteen peliin liittyvä asetusikkuna
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.dispose();
        this.harava.uusiPeli();           
    }
}
