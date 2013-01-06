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

    private Miinaharava harava;
    private JFrame frame;

    public TilastoIkkuna(Miinaharava harava) {
        this.harava = harava;
    }

    @Override
    public void run() {
        frame = new JFrame("Tilastot");
        frame.setPreferredSize(new Dimension(200, 500));
        frame.setResizable(true);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);


        JTextPane jtp = new JTextPane();
        jtp.setEditable(false);
        jtp.setText(this.harava.getTilastonhallinta().tulostaKaikkiTilastot());
        jtp.setFont(new Font("Georgia", Font.PLAIN, 15));
        jtp.setBackground(new Color(0xffdddd));
        container.add(jtp);
  
    }

    public JFrame getFrame() {
        return frame;
    }

    public Miinaharava getHarava() {
        return harava;
    }
}
