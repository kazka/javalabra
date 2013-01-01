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
   // private int rivi;
   // private int sarake;
    private JButton btn;
    private Ruutu ruutu;
    //private Kayttoliittyma kali;

    public KlikkaustenKuuntelija(Miinaharava harava, Ruutu ruutu, JButton btn) {
       // this.rivi = y;
       // this.sarake = x;
        this.harava = harava;
        this.btn = btn;
        this.ruutu = ruutu;
        // this.kali = kali;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!this.ruutu.getStatus().equals("avattu")) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                this.ruutu.merkkaa();
            } else if (!this.ruutu.getStatus().equals("merkattu")) {
                if (this.harava.getLauta().onkoEnsimmainenAvaus() && this.ruutu.getSisalto() == 9) {
                    // jos ensimmäinen klikattu oli miina -> asetetaan miinat uudestaan
                    while (this.ruutu.getSisalto() == 9) {
                        this.harava.getLauta().generoiUusiLauta();
                    }
                }

                if (this.ruutu.getSisalto() == 9) {
                    this.ruutu.vaihdaKuvake(9);
                    this.harava.havio();
                    return;
                }

                this.ruutu.setStatus("avattu");

                if (this.ruutu.getSisalto() == 0) {
                    ArrayList<Ruutu> ruudut = this.harava.getLauta().avaaViereisetNollat(this.ruutu.getX(), this.ruutu.getY(), new ArrayList<Ruutu>());
                    for (Ruutu ruutuTaulukossa : ruudut) {
                        ImageIcon kuvake = new ImageIcon("materiaali/" + ruutuTaulukossa.getSisalto() + ".jpg");
                        ruutuTaulukossa.getBtn().setIcon(kuvake);
                    }
                }

                this.ruutu.vaihdaKuvake(this.ruutu.getSisalto());
            }
        }

        this.harava.getLauta().tulosta();
        this.harava.tarkistaVoitto();
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
