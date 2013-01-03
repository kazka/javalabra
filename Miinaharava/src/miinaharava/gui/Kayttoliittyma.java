
package miinaharava.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
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
        frame.setPreferredSize(new Dimension(this.harava.getLauta().getLeveys()*21, this.harava.getLauta().getKorkeus()*27));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.setLayout(new BorderLayout());
        container.add(getGrid(), BorderLayout.CENTER);
        container.add(this.harava.getKello().getLabel(), BorderLayout.NORTH);
        JButton uusipeliBtn = new JButton("uusi peli");
        uusipeliBtn.addActionListener(new UusipeliNappulanKuuntelija(this.harava, this.frame));
        container.add(uusipeliBtn, BorderLayout.SOUTH);
    }

    public JFrame getFrame() {
        return frame;
    }

    public Miinaharava getHarava() {
        return harava;
    }
    
    private JComponent getGrid(){
        JPanel sisempi = new JPanel();
        sisempi.setLayout(new GridLayout(harava.getLauta().getKorkeus(), harava.getLauta().getLeveys()));
        
        for (int i = 0; i < this.harava.getLauta().getKorkeus(); i++){
            for (int j = 0; j < this.harava.getLauta().getLeveys(); j++){
                ImageIcon kuvake = new ImageIcon("materiaali/10.jpg");
                JButton ruutuBtn = new JButton(kuvake);
                ruutuBtn.setBorderPainted(false);
                ruutuBtn.setContentAreaFilled(false);
                ruutuBtn.setRolloverEnabled(false);
                //ruutuBtn.addMouseListener(new KlikkaustenKuuntelija(this.harava, j, i, ruutuBtn));
                ruutuBtn.addMouseListener(new KlikkaustenKuuntelija(this.harava, this.harava.getLauta().getTaulukko()[i][j], ruutuBtn));
                this.harava.getLauta().getTaulukko()[i][j].setBtn(ruutuBtn);
                sisempi.add(this.harava.getLauta().getTaulukko()[i][j].getBtn());
            }
        }
        
        return sisempi;
    }


}