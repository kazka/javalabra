package miinaharava.domain;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Miinaharavan yksittäistä ruutua kuvaava luokka
 */
public class Ruutu {

    /**
     * Ruudun senhetkinen status eli tila, voi olla joko "kiinni", "avattu" tai
     * "merkattu".
     */
    private String status;
    
    /**
     * Ruudun sisältö, joka kerrotaan lukuna joka tulee näkyviin kun ruutu
     * avataan. Mahdolliset luvut ovat 0 (eli tyhjä), 1-8, ja 9 jos ruudussa on
     * miina.
     */
    private int sisalto;
    
    /**
     * Ruutuun liittyvä JButton jonka avulla kuunnellaan klikkauksia ja
     * asetetaan ruudulle oikea kuvake.
     */
    private JButton btn;
    
    /**
     * Sarake jolla ruutu sijaitsee pelilaudan taulukossa
     */
    private int x;
    
    /**
     * Rivi jolla ruutu sijaitsee pelilaudan taulukossa
     */
    private int y;
    
    /**
     * Ruutuun liittyvä väri, asetetaan alussa kun ruutuja luodaan Pelilaudan
     * generoiTaulukko(int korkeus, int leveys, String vari)-metodissa.
     * Käytetään vaihdaKuvake()-metodissa jotta osataan käyttää oikean värisiä kuvakkeita.
     */
    private String vari;

    /**
     * Oletusarvoisesti käytettävä konstruktori
     *
     * @param x Sarake jolla Ruutu sijaitsee
     * @param y Rivi jolla Ruutu sijaitsee
     */
    public Ruutu(int x, int y) {
        this.status = "kiinni";
        this.x = x;
        this.y = y;
    }

    /**
     * Testien käytetössä oleva konstruktori
     */
    public Ruutu() {
        this.status = "kiinni";
        this.btn = new JButton();
    }

    /**
     * Vaihtaa Ruudun statuksen
     *
     * @param status Ruudulle asetettava status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Vaihtaa Ruudun sisällön
     *
     * @param luku Ruudulle asetettava sisältö
     */
    public void setSisalto(int luku) {
        this.sisalto = luku;
    }

    /**
     * Kertoo onko ruudussa miina
     *
     * @return true jos on miina, false jos ei ole miinaa
     */
    public boolean onkoMiinaa() {
        if (this.sisalto == 9) {
            return true;
        }
        return false;
    }

    /**
     * Palauttaa ruudun sisällön
     *
     * @return ruudun sisältö lukuna 0-9
     */
    public int getSisalto() {
        return sisalto;
    }

    /**
     * Palauttaa ruudun statuksen
     *
     * @return ruudun status, joko "kiinni", "avattu" tai "merkattu"
     */
    public String getStatus() {
        return status;
    }

    /**
     * Palauttaa ruutuun liittyvän JButtonin
     *
     * @return JButton
     */
    public JButton getBtn() {
        return btn;
    }

    /**
     * Asettaa ruudulle JButtonin
     *
     * @param btn Ruudulle asetettava JButton
     */
    public void setBtn(JButton btn) {
        this.btn = btn;
    }

    /**
     * Metodi asettaa ruudun merkatuksi, tai poistaa merkkauksen jos ruutu oli
     * jo merkattu
     */
    public void merkkaa() {
        if (this.status.equals("merkattu")) {
            this.status = "kiinni";
            vaihdaKuvake(10);
        } else {
            this.status = "merkattu";
            vaihdaKuvake(88);
        }
    }

    /**
     * Vaihtaa ruutuun liittyvän JButtonin kuvakkeen. Kuvake esitetään
     * ImageIcon-oliona. Jos ruudulla ei vielä ole JButtonia, kutsutaan metodia
     * luoBtn()
     *
     * @param sisalto Luku jonka perusteella haetaan kyseiseen sisaltoon
     * liittyva jpg-kuva. Lukujen/sisältöjen 0-9 lisäksi on käytössä luvut 10=tyhjä,
     * 88=merkattu, 99=väärin merkattu miinaton ruutu
     */
    public void vaihdaKuvake(int sisalto) {
        ImageIcon kuvake = new ImageIcon("materiaali/" + sisalto + ".jpg");
        if (this.vari != null && this.vari.equals("vihrea")) {
            kuvake = new ImageIcon("materiaali/vihrea/" + sisalto + ".jpg");
        }
        if (this.btn == null) {
            luoBtn();
        }
        this.btn.setIcon(kuvake);
    }

    /**
     * Luo ruudulle JButtonin ja asettaa sille oikeat asetukset.
     */
    public void luoBtn() {
        this.btn = new JButton();
        this.btn.setBorderPainted(false);
        this.btn.setContentAreaFilled(false);
        this.btn.setRolloverEnabled(false);
    }

    /**
     * Kun peli on loppu, avataan ruutu kutsumalla tätä metodia ja vaihdetaan
     * sille oikea kuvake. Alussa tarkistetaan oliko ruutu väärin merkattu
     * miina.
     */
    public void avaaLopussa() {
        if (this.status.equals("merkattu") && this.sisalto != 9) {
            this.status = "avattu";
            vaihdaKuvake(99);
        } else {
            this.status = "avattu";
            vaihdaKuvake(this.sisalto);
        }
    }

    /**
     * Palauttaa sen sarakkeen int-arvon jolla ruutu sijaitsee
     *
     * @return sarakkeen int-arvo
     */
    public int getX() {
        return x;
    }

    /**
     * Palauttaa sen rivin int-arvon jolla ruutu sijaitsee
     *
     * @return rivin int-arvo
     */
    public int getY() {
        return y;
    }

    /**
     * Asettaa ruudulle värin
     *
     * @param vari Ruudulle asetettava väri, joko "punainen" tai "vihrea"
     */
    public void setVari(String vari) {
        this.vari = vari;
    }
}
