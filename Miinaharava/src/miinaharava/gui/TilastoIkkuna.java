package miinaharava.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.*;
import miinaharava.peli.Miinaharava;

/**
 * Käyttöliittymä pistetilastot näyttävälle toiminnallisuudelle
 */
public class TilastoIkkuna implements Runnable {

    /**
     * TilastoIkkunaan liittyvä Miinaharava-olio
     */
    private Miinaharava harava;
    
    /**
     * TilastoIkkunaan liittyvä JFrame-olio
     */
    private JFrame frame;

    /**
    * Konstruktori
    * 
    * @param harava Miinaharava johon tilastoikkunaä asetetaan kuuluvaksi
    */
    public TilastoIkkuna(Miinaharava harava) {
        this.harava = harava;
    }

    /**
    * Runnable-rajapinnan toteuttavan olion abstrakti metodi, joka luo uuden
    * ikkunan luokassa määritellyille käyttöliittymäelementeille
    */
    @Override
    public void run() {
        frame = new JFrame("Tilastot");
        frame.setPreferredSize(new Dimension(200, 500));
        frame.setResizable(true);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Metodi luo tilastoikkunan komponentit
     * 
     * @param container Container-olio johon komponentit luodaan
     */
    private void luoKomponentit(Container container) {
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);

        JTextPane jtp = new JTextPane();
        jtp.setEditable(false);
        jtp.setText(this.harava.getTilastonhallinta().kaikkiTilastotStringiksi());
        jtp.setFont(new Font("Georgia", Font.PLAIN, 15));
        jtp.setBackground(new Color(0xffdddd));
        container.add(jtp);
  
    }

    /**
     * Palauttaa tilastoikkunaan liittyvän JFrame-olion
     *
     * @return frame
     */  
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Palauttaa kirjautumisikkunaan liittyvän Miinaharava-olion
     *
     * @return harava
     */  
    public Miinaharava getHarava() {
        return harava;
    }
}
