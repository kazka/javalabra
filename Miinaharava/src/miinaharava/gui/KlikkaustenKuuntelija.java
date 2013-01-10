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
    * Sen jälkeen tarkistetaan alkutilanne, testataan oliko ruudussa miina ja
    * avataan ruutu normaalisti jos ei ollut. Lopussa tarkistetaan onko peli tämän
    * avaamisen seurauksena voitettu.
    */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (!this.ruutu.getStatus().equals("avattu")) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                this.ruutu.merkkaa();
            } else if (!this.ruutu.getStatus().equals("merkattu")) {
                tarkistaAlkutilanne();

                if (tarkistaHavio()) {
                    return;
                }

                avaa();
            }
        }

        //    this.harava.getLauta().tulosta();

        if (this.harava.getLauta().onkoVoitettu()) {
            this.harava.voitto();
        }
    }

    /**
    * Tarkistaa oliko ensimmäisenä klikatussa ruudussa miina. Jos oli, jakaa miinat
    * uudelleen kunnes ruudussa ei ole miinaa.
    */
    public void tarkistaAlkutilanne() {
        if (this.harava.getLauta().onkoEnsimmainenAvaus() && this.ruutu.getSisalto() == 9) {
            // jos ensimmäinen klikattu oli miina -> asetetaan miinat uudestaan
            while (this.ruutu.onkoMiinaa()) {
                this.harava.getLauta().generoiUusiLauta();
            }
        }
    }

    /**
    * Avaa ruudun johon kuuntelija liittyy. Jos ruudussa oli luku 0, hakee muut
    * ympäröivät avattavat ruudut pelilaudan avaaViereisetNollat()-metodilta ja
    * vaihtaa ruuduille oikeat kuvakkeet.
    */
    public void avaa() {
        this.ruutu.setStatus("avattu");

        if (this.ruutu.getSisalto() == 0) {
            ArrayList<Ruutu> ruudut = this.harava.getLauta().avaaViereisetNollat(this.ruutu.getX(), this.ruutu.getY(), new ArrayList<Ruutu>());
            for (Ruutu ruutuTaulukossa : ruudut) {
                ruutuTaulukossa.vaihdaKuvake(ruutuTaulukossa.getSisalto());
            }
        }

        this.ruutu.vaihdaKuvake(this.ruutu.getSisalto());
    }

    /**
    * Tarkistaa onko ruudussa miina eli hävittiinkö peli
    */
    public boolean tarkistaHavio() {
        if (this.ruutu.onkoMiinaa()) {
            this.ruutu.vaihdaKuvake(9);
            this.harava.havio();
            return true;
        }
        return false;
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
