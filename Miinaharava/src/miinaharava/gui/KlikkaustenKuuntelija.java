
package miinaharava.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import miinaharava.peli.Miinaharava;

public class KlikkaustenKuuntelija implements ActionListener {
    private Miinaharava harava;
    private int rivi;
    private int sarake;
    private JButton btn;

    public KlikkaustenKuuntelija(Miinaharava harava, int x, int y, JButton btn) {
        this.rivi = y;
        this.sarake = x;
        this.harava = harava;
        this.btn = btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.harava.getLauta().getTaulukko()[rivi][sarake].getStatus().equals("avattu")){
            //älä tee mitään
        } else {
            this.harava.getLauta().getTaulukko()[rivi][sarake].setStatus("avattu");
            this.btn.setText(Integer.toString(this.harava.getLauta().getTaulukko()[rivi][sarake].getSisalto()));
        }
        
        harava.start();
    }
    
}
