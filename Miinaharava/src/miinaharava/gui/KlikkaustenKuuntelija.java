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

    /**
     * Miinaharava-olio johon kuuntelija liittyy
     */
    private Miinaharava harava;
    /**
     * Ruutu johon kuuntelija liittyy
     */
    private Ruutu ruutu;

    /**
     * Konstruktori
     *
     * @param harava Miinaharava johon kuuntelija liittyy
     * @param ruutu Ruutu johon kuuntelija liittyy
     */
    public KlikkaustenKuuntelija(Miinaharava harava, Ruutu ruutu) {
        this.harava = harava;
        this.ruutu = ruutu;
    }

    /**
    * Klikkauksessa tarkistetaan ensin että ruutu ei ole jo avattu.
    * Sen jälkeen merkataan ruutu, jos klikattiin hiiren oikeaa nappulaa.
    * Sen jälkeen tarkistetaan oliko ruutu ensimmäinen jota klikattiin, jos oli niin
    * tehdään siihen liittyvät toiminnot.
    * Sitten testataan oliko ruudussa miina tarkistaHavio()-metodilla.
    * Avataan ruutu normaalisti jos ei ollut. Lopussa tarkistetaan onko peli tämän
    * avaamisen seurauksena voitettu.
    */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (!this.ruutu.getStatus().equals("avattu")) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                this.ruutu.merkkaa();
            } else if (!this.ruutu.getStatus().equals("merkattu")) {
                this.harava.getLauta().tarkistaAlkutilanne(this.ruutu);

                if (this.ruutu.onkoMiinaa()) {
                    this.ruutu.vaihdaKuvake(9);
                    this.harava.havio();
                    return;
                }

                this.ruutu.avaa(this.harava.getLauta());
            }
        }

        if (this.harava.getLauta().onkoVoitettu()) {
            this.harava.voitto();
        }
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
