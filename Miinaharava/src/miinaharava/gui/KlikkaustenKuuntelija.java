
package miinaharava.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import miinaharava.peli.Miinaharava;

public class KlikkaustenKuuntelija implements ActionListener {
    private Miinaharava harava; //
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
            if (this.harava.getLauta().onkoEnsimmainenAvaus() && this.harava.getLauta().getTaulukko()[rivi][sarake].getSisalto() == 9){
                // jos ensimmäinen klikattu oli miina -> generoidaan uusi lauta
                Pelilauta uusiLauta = new Pelilauta(this.harava.getLauta().getLeveys(), this.harava.getLauta().getKorkeus(), this.harava.getLauta().getMiinoja());
                this.harava.setLauta(uusiLauta);
            }
            this.harava.getLauta().getTaulukko()[rivi][sarake].setStatus("avattu");
            this.btn.setText(Integer.toString(this.harava.getLauta().getTaulukko()[rivi][sarake].getSisalto()));
            
            if (this.harava.getLauta().getTaulukko()[rivi][sarake].getSisalto() == 0){
                this.harava.getLauta().avaaViereisetNollat(sarake, rivi);
            }
        }
        
        harava.start();
    }
    
}
