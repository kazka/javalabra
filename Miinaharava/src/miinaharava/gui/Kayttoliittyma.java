
package miinaharava.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
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
        frame.setPreferredSize(new Dimension(this.harava.getLauta().getLeveys()*21, this.harava.getLauta().getKorkeus()*24));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.setLayout(new GridLayout(harava.getLauta().getKorkeus(), harava.getLauta().getLeveys()));
        
        for (int i = 0; i < this.harava.getLauta().getKorkeus(); i++){
            for (int j = 0; j < this.harava.getLauta().getLeveys(); j++){
                ImageIcon kuvake = new ImageIcon("materiaali/10.jpg");
                JButton ruutuBtn = new JButton(kuvake);
                ruutuBtn.setBorderPainted(false);
                ruutuBtn.setContentAreaFilled(false);
                ruutuBtn.setRolloverEnabled(false);
                ruutuBtn.addActionListener(new KlikkaustenKuuntelija(this.harava,j,i, ruutuBtn));
                this.harava.getLauta().getTaulukko()[i][j].setBtn(ruutuBtn);
                container.add(ruutuBtn);
            }
        }
    }

    public JFrame getFrame() {
        return frame;
    }

}