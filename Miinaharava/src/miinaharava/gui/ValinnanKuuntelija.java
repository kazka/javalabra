package miinaharava.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import miinaharava.peli.Miinaharava;

/**
 * Luokka jolla kuunnellaan minkä kokoinen pelilauta valitaan asetusruudussa
 */
public class ValinnanKuuntelija implements ActionListener {

    private ArrayList<JRadioButton> vaihtoehdot;
    private Miinaharava harava;
    private JFrame frame;

    public ValinnanKuuntelija(Miinaharava harava, ArrayList<JRadioButton> vaihtoehdot, JFrame frame) {
        this.vaihtoehdot = vaihtoehdot;
        this.harava = harava;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String valittu = "";

        for (JRadioButton button : this.vaihtoehdot) {
            if (button.isSelected()) {
                valittu = button.getText();
                break;
            }
        }
        this.harava.luoPelilauta(valittu);     
        this.frame.dispose();
        Kayttoliittyma kali = new Kayttoliittyma(this.harava);
        SwingUtilities.invokeLater(kali);          
    }
}