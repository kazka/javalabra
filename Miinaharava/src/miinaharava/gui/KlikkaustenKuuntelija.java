package miinaharava.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import miinaharava.domain.Ruutu;
import miinaharava.peli.Miinaharava;

/**
 * Luokka jolla kuunnellaan klikkauksia tiettyyn ruutuun liittyvässä JButtonissa
 */
public class KlikkaustenKuuntelija implements MouseListener {

    private Miinaharava harava;
    private Ruutu ruutu;

    public KlikkaustenKuuntelija(Miinaharava harava, Ruutu ruutu) {
        this.harava = harava;
        this.ruutu = ruutu;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!this.ruutu.getStatus().equals("avattu")) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                this.ruutu.merkkaa();
            } else if (!this.ruutu.getStatus().equals("merkattu")) { // siirrä nämä
                if (this.harava.getLauta().onkoEnsimmainenAvaus() && this.ruutu.getSisalto() == 9) {
                    // jos ensimmäinen klikattu oli miina -> asetetaan miinat uudestaan
                    while (this.ruutu.onkoMiinaa()) {
                        this.harava.getLauta().generoiUusiLauta();
                    }
                }

                if (this.ruutu.onkoMiinaa()) {
                    this.ruutu.vaihdaKuvake(9);
                    this.harava.havio();
                    return;
                }

                this.ruutu.setStatus("avattu");

                if (this.ruutu.getSisalto() == 0) {
                    ArrayList<Ruutu> ruudut = this.harava.getLauta().avaaViereisetNollat(this.ruutu.getX(), this.ruutu.getY(), new ArrayList<Ruutu>());
                    for (Ruutu ruutuTaulukossa : ruudut) {
                        ruutuTaulukossa.vaihdaKuvake(ruutuTaulukossa.getSisalto());
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
