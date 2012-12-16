
package miinaharava.gui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import miinaharava.peli.Miinaharava;

public class Kayttoliittyma implements Runnable {
    private Miinaharava harava;
    private JFrame frame;    

    public Kayttoliittyma(Miinaharava harava) {
        this.harava = harava;
    }

    @Override
    public void run() {
        frame = new JFrame("Miinaharava");
        frame.setPreferredSize(new Dimension(this.harava.getLauta().getLeveys()*20, this.harava.getLauta().getKorkeus()*20));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
    }

    public JFrame getFrame() {
        return frame;
    }

}