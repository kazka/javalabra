package miinaharava.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import miinaharava.domain.Ruutu;
import miinaharava.peli.Miinaharava;

public class KlikkaustenKuuntelija implements MouseListener {

    private Miinaharava harava;
    private int rivi;
    private int sarake;
    private JButton btn;
    private Kayttoliittyma kali;

    public KlikkaustenKuuntelija(Miinaharava harava, int x, int y, JButton btn, Kayttoliittyma kali) {
        this.rivi = y;
        this.sarake = x;
        this.harava = harava;
        this.btn = btn;
        this.kali = kali;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!this.harava.getLauta().getTaulukko()[rivi][sarake].getStatus().equals("avattu")) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                merkkaaRuutu(this.harava.getLauta().getTaulukko()[rivi][sarake]);
            } else if (!this.harava.getLauta().getTaulukko()[rivi][sarake].getStatus().equals("merkattu")){
                if (this.harava.getLauta().onkoEnsimmainenAvaus() && this.harava.getLauta().getTaulukko()[rivi][sarake].getSisalto() == 9) {
                    generoiUusiLauta(this.harava.getLauta().getLeveys(), this.harava.getLauta().getKorkeus(), this.harava.getLauta().getMiinoja());
                }

                if (this.harava.getLauta().getTaulukko()[rivi][sarake].getSisalto() == 9) {
                    vaihdaKuvake(9);
                    this.harava.havio();
                    return;
                }

                this.harava.getLauta().getTaulukko()[rivi][sarake].setStatus("avattu");

                if (this.harava.getLauta().getTaulukko()[rivi][sarake].getSisalto() == 0) {
                    ArrayList<Ruutu> ruudut = this.harava.getLauta().avaaViereisetNollat(sarake, rivi, new ArrayList<Ruutu>());
                    for (Ruutu ruutu : ruudut) {
                        ImageIcon kuvake = new ImageIcon("materiaali/" + ruutu.getSisalto() + ".jpg");
                        ruutu.getBtn().setIcon(kuvake);
                    }
                }

                vaihdaKuvake(this.harava.getLauta().getTaulukko()[rivi][sarake].getSisalto());
            }
        }

        this.harava.getLauta().tulosta();
        this.harava.tarkistaVoitto();
    }
    
    public void generoiUusiLauta(int leveys, int korkeus, int miinoja) {
        // jos ensimmäinen klikattu oli miina -> generoidaan uusi lauta
        Pelilauta uusiLauta = new Pelilauta(leveys, korkeus, miinoja);
        while (uusiLauta.getTaulukko()[rivi][sarake].getSisalto() == 9) {
            uusiLauta = new Pelilauta(leveys, korkeus, miinoja);
        }
        this.harava.setLauta(uusiLauta);
    }

    public void merkkaaRuutu(Ruutu ruutu) {
        if (ruutu.getStatus().equals("merkattu")){
            ruutu.setStatus("kiinni");
            vaihdaKuvake(10);
        } else {
            ruutu.setStatus("merkattu");
            vaihdaKuvake(88);
        }
    }

    //vaihtaa jpg kuvakkeen vastaamaan oikeaa sisaltöä. 0.jpg = tyhjä, 1-8.jpg = miinojen määrän mukainen kuvake, 9.jpg = miina, 10.jpg = suljettu ruutu,
    public void vaihdaKuvake(int sisalto) {
        ImageIcon kuvake = new ImageIcon("materiaali/" + sisalto + ".jpg");
        this.btn.setIcon(kuvake);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
