
package miinaharava.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import miinaharava.peli.Miinaharava;


public class UusipeliNappulanKuuntelija implements ActionListener {
    private Miinaharava harava;
    private JFrame frame;

    public UusipeliNappulanKuuntelija(Miinaharava harava, JFrame frame) {
        this.harava = harava;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.dispose();
        this.harava.uusiPeli();           
    }
}
