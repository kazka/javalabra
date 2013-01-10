package miinaharava.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import miinaharava.peli.Miinaharava;

/**
 * Luokka jolla kuunnellaan minkä kokoinen ja minkä värinen pelilauta valitaan asetusruudussa
 */
public class ValinnanKuuntelija implements ActionListener {

    /**
    * ArrayList kokovaihtoehdoista
    */
    private ArrayList<JRadioButton> vaihtoehdot;
    
    /**
    * ArrayList värivaihtoehdoista
    */
    private ArrayList<JRadioButton> vaihtoehdotVari;
    
    /**
    * Miinaharava-olio johon kuuntelija liittyy
    */
    private Miinaharava harava;
    
    /**
    * JFrame jonka tietoja kuuntelijalla päivitetään
    */
    private JFrame frame;

    /**
    * Konstruktori
    * 
    * @param harava Miinaharava johon kuuntelija liittyy
    * @param vaihtoehdot Lista kokovaihtoehdoista
    * @param vaihtoehdotVari Lista värivaihtoehdoista
    * @param frame Kuuntelijaan liittyvä JFrame
    */
    public ValinnanKuuntelija(Miinaharava harava, ArrayList<JRadioButton> vaihtoehdot, ArrayList<JRadioButton> vaihtoehdotVari, JFrame frame) {
        this.vaihtoehdot = vaihtoehdot;
        this.vaihtoehdotVari = vaihtoehdotVari;
        this.harava = harava;
        this.frame = frame;
    }

    /**
    * Klikatessa haetaan valittu koko ja väri sekä luodaan uusi pelilauta näillä tiedoilla.
    * Suljetaan asetusikkuna ja avataan pelilaudan ikkuna.
    */
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