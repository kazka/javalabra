
package miinaharava.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import miinaharava.domain.Ruutu;
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
            if (this.harava.getLauta().onkoEnsimmainenAvaus() && this.harava.getLauta().getTaulukko()[rivi][sarake].getSisalto() == 9){
                // jos ensimmäinen klikattu oli miina -> generoidaan uusi lauta
                Pelilauta uusiLauta = new Pelilauta(this.harava.getLauta().getLeveys(), this.harava.getLauta().getKorkeus(), this.harava.getLauta().getMiinoja());
                while (uusiLauta.getTaulukko()[rivi][sarake].getSisalto() == 9){
                    uusiLauta = new Pelilauta(this.harava.getLauta().getLeveys(), this.harava.getLauta().getKorkeus(), this.harava.getLauta().getMiinoja());
                }
                this.harava.setLauta(uusiLauta);
            }
            
            if (this.harava.getLauta().getTaulukko()[rivi][sarake].getSisalto() == 9){
                vaihdaKuvake(9);
                this.harava.havio();
                return;
            }
            
            this.harava.getLauta().getTaulukko()[rivi][sarake].setStatus("avattu");
            
            if (this.harava.getLauta().getTaulukko()[rivi][sarake].getSisalto() == 0){
                ArrayList<Ruutu> ruudut = this.harava.getLauta().avaaViereisetNollat(sarake, rivi, new ArrayList<Ruutu>());
                for (Ruutu ruutu : ruudut){
                    ImageIcon kuvake = new ImageIcon("materiaali/" + ruutu.getSisalto() + ".jpg");
                    ruutu.getBtn().setIcon(kuvake);
                }
            }
            
            //this.btn.setText(Integer.toString(this.harava.getLauta().getTaulukko()[rivi][sarake].getSisalto()));
            vaihdaKuvake(this.harava.getLauta().getTaulukko()[rivi][sarake].getSisalto());
            
        }
        
        this.harava.getLauta().tulosta();
        this.harava.tarkistaVoitto();
    }

    //vaihtaa jpg kuvakkeen vastaamaan oikeaa sisaltöä. 0.jpg = tyhjä, 1-8.jpg = miinojen määrän mukainen kuvake, 9.jpg = miina, 10.jpg = suljettu ruutu,
    public void vaihdaKuvake(int sisalto) {
        ImageIcon kuvake = new ImageIcon("materiaali/" + sisalto + ".jpg");
        this.btn.setIcon(kuvake);
    }
    
}
