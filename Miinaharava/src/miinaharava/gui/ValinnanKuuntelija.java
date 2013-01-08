package miinaharava.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import miinaharava.peli.Miinaharava;

/**
 * Luokka jolla kuunnellaan minkä kokoinen pelilauta valitaan asetusruudussa
 */
public class ValinnanKuuntelija implements ActionListener {

    private ArrayList<JRadioButton> vaihtoehdot;
    private ArrayList<JRadioButton> vaihtoehdotVari;
    private Miinaharava harava;
    private JFrame frame;

    public ValinnanKuuntelija(Miinaharava harava, ArrayList<JRadioButton> vaihtoehdot, ArrayList<JRadioButton> vaihtoehdotVari, JFrame frame) {
        this.vaihtoehdot = vaihtoehdot;
        this.vaihtoehdotVari = vaihtoehdotVari;
        this.harava = harava;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String valittuKoko = "";
        String valittuVari = "";

        for (JRadioButton button : this.vaihtoehdot) {
            if (button.isSelected()) {
                valittuKoko = button.getText();
                break;
            }
        }
        for (JRadioButton button : this.vaihtoehdotVari) {
            if (button.isSelected()) {
                valittuVari = button.getText();
                break;
            }
        }        
        this.harava.luoPelilauta(valittuKoko, valittuVari);     
        this.frame.dispose();
        Kayttoliittyma kali = new Kayttoliittyma(this.harava);
        SwingUtilities.invokeLater(kali);          
    }
}