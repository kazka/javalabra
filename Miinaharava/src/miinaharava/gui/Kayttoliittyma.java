
package miinaharava.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
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
        frame.setPreferredSize(new Dimension(this.harava.getLauta().getLeveys()*20, this.harava.getLauta().getKorkeus()*30));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.setLayout(new GridLayout(harava.getLauta().getLeveys(), harava.getLauta().getKorkeus()));
        
        for (int i = 0; i < this.harava.getLauta().getKorkeus(); i++){
            for (int j = 0; j < this.harava.getLauta().getLeveys(); j++){
                JButton ruutuBtn = new JButton("");
                ruutuBtn.addActionListener(new KlikkaustenKuuntelija(this.harava,j,i, ruutuBtn));
                container.add(ruutuBtn);
            }
        }
    }

    public JFrame getFrame() {
        return frame;
    }

}