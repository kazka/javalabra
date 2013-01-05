
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
    private Miinaharava harava;
    private JFrame frame;

    public TilastoNappulanKuuntelija(Miinaharava harava, JFrame frame) {
        this.harava = harava;
        this.frame = frame;        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TilastoIkkuna tikkuna = new TilastoIkkuna(this.harava);
        SwingUtilities.invokeLater(tikkuna);        
    }
    
}
