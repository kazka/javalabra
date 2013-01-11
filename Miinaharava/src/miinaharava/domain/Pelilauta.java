package miinaharava.domain;

import java.util.ArrayList;
import java.util.Random;
import miinaharava.domain.Ruutu;

/**
 * Miinaharavan pelilautaa kuvaava luokka
 */
public class Pelilauta {

    /**
    * Pelilaudan leveys
    */
    private int leveys;
    
    /**
    * Pelilaudan korkeus
    */
    private int korkeus;
    
    /**
    * Pelilaudan miinojen lukumäärä
    */
    private int miinoja;
    
    /**
    * 2-ulotteinen Ruutu-olioista koostuva taulukko joka toimii pelilaudan pohjana
    */
    private Ruutu[][] taulukko;
    
    /**
    * Konstruktori.
    * Generoi pelilaudalle Ruutu-taulukon ja kutsuu metodeita jotka asettavat
    * miinat sekä miinattomien ruutujen sisällöt.
    * 
    * @param leveys Pelilaudan leveys
    * @param korkeus Pelilaudan korkeus
    * @param miinoja Pelilaudan miinojen määrä
    * @param vari Pelilaudan väri, "punainen" tai "vihrea"
    */
    public Pelilauta(int leveys, int korkeus, int miinoja, String vari) {
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.miinoja = miinoja;
        //this.taulukko = new Ruutu[this.korkeus][this.leveys];
        generoiTaulukko(this.korkeus, this.leveys, vari);
        asetaMiinat();
        asetaMiinattomat();
    }  
    
    /**
    * Generoi pelilaudalle Ruutu-taulukon ja asettaa ruudulle oikean värin
    * 
    * @param korkeus Generoitavan taulukon korkeus
    * @param leveys Generoitavan taulukon leveys
    * @param vari Ruudulle asetettava väri
    */
    public final void generoiTaulukko(int korkeus, int leveys, String vari) {
        this.taulukko = new Ruutu[korkeus][leveys];
        this.leveys = leveys;
        this.korkeus = korkeus;
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                this.taulukko[i][j] = new Ruutu(j,i);
                this.taulukko[i][j].setVari(vari);
            }
        }
    }    

    /**
    * Asettaa oikean määrän miinoja sattumanvaraisiin ruutuihin. Jos ruudussa
    * on jo miina, etsitään miinalle toinen paikka.
    */
    public final void asetaMiinat() {
        Random random = new Random();
        for (int i = 0; i < this.miinoja; i++) {
            int randomX = random.nextInt(this.leveys);
            int randomY = random.nextInt(this.korkeus);
            if (!taulukko[randomY][randomX].onkoMiinaa()) {
                taulukko[randomY][randomX].setSisalto(9);
            } else {
                while(taulukko[randomY][randomX].onkoMiinaa()){
                    randomX = random.nextInt(this.leveys);
                    randomY = random.nextInt(this.korkeus);
                }
                taulukko[randomY][randomX].setSisalto(9);
            }
        }
    }

    /**
    * Kutsuu miinattomille ruuduille metodia joka laskee luvun, kuinka monessa
    * viereisessä ruudussa on miina.
    */
    public final void asetaMiinattomat() {
        for (int i = 0; i < this.korkeus; i++) {
            for (int j = 0; j < this.leveys; j++) {
                if (!taulukko[i][j].onkoMiinaa()) {
                    taulukko[i][j].setSisalto(laskeYmparoivatMiinat(i, j));
                }
            }
        }
    }

    /**
    * Nollaa pelilaudan, eli asettaa joka ruudun sisällöksi nollan
    */
    public void nollaa() {
        for (int i = 0; i < this.korkeus; i++) {
            for (int j = 0; j < this.leveys; j++) {
                taulukko[i][j].setSisalto(0);
            }
        }
    }

    /**
    * Generoi uuden pelilautaruudukon vanhan päälle nollaamalla vanhan ja asettamalla
    * uudestaan miinat ja miinattomat
    */
    public void generoiUusiLauta() {
        nollaa();
        asetaMiinat();
        asetaMiinattomat();
    }

    /**
    * Laskee tietyssä solussa olevalle ruudulle ympäröivien miinojen määrän,
    * eli testaa jokaisen ympäröivän ruudun kohdalta onko miinaa
    * 
    * @param rivi Rivi jolla solu sijaitsee
    * @param sarake Sarake jolla solu sijaitsee
    * 
    * @return ympäröivien miinojen määrä
    */
    public int laskeYmparoivatMiinat(int rivi, int sarake) {
        int miinat = 0;
        miinat += haeMiina(rivi-1, sarake-1);
        miinat += haeMiina(rivi-1, sarake);
        miinat += haeMiina(rivi-1, sarake+1);
        miinat += haeMiina(rivi, sarake+1);
        miinat += haeMiina(rivi, sarake-1);
        miinat += haeMiina(rivi+1, sarake-1);
        miinat += haeMiina(rivi+1, sarake);
        miinat += haeMiina(rivi+1, sarake+1);

        return miinat;
    }
    
    /**
    * Testaa onko tietty solu taulukon sisällä ja palauttaa 1 jos näin on sekä miina löytyi
    * 
    * @param rivi Rivi jolla solu sijaitsee
    * @param sarake Sarake jolla solu sijaitsee
    * 
    * @return 1 jos solu on taulukon sisällä ja siinä on miina, muuten 0
    */
    public int haeMiina(int rivi, int sarake){
        if (onkoTaulukossa(rivi, sarake)){
            if (taulukko[rivi][sarake].onkoMiinaa()){
                return 1;
            }
        }
        return 0;
    }
    
    /**
    * Kun avatussa ruudussa on luku 0, lasketaan syvyyssuuntaisella läpikäynnillä
    * kaikki viereiset ruudut joissa on myös luku 0 sekä ensimmäiset vastaantulevat
    * joissa on jokin muu luku, ja lisätään nämä ruudut ArrayListiin
    * 
    * @param rivi Rivi jolla solu sijaitsee
    * @param sarake Sarake jolla solu sijaitsee
    * @param ruudut ArrayList löydetyistä ruuduista
    * 
    * @return ArrayList joka sisältää löydetyt ruudut
    */
    public ArrayList<Ruutu> avaaViereisetNollat(int sarake, int rivi, ArrayList<Ruutu> ruudut) {
        this.taulukko[rivi][sarake].setStatus("avattu");
        ruudut.add(this.taulukko[rivi][sarake]);

        tarkistaJaAvaa(rivi - 1, sarake, ruudut);
        tarkistaJaAvaa(rivi, sarake - 1, ruudut);
        tarkistaJaAvaa(rivi, sarake + 1, ruudut);
        tarkistaJaAvaa(rivi + 1, sarake, ruudut);
        tarkistaJaAvaa(rivi - 1, sarake - 1, ruudut);
        tarkistaJaAvaa(rivi - 1, sarake + 1, ruudut);
        tarkistaJaAvaa(rivi + 1, sarake - 1, ruudut);
        tarkistaJaAvaa(rivi + 1, sarake + 1, ruudut);

        return ruudut;
    }

    /**
    * Jatkaa avaaViereisetNollat-metodin toimintaa tarkistamalla onko ruutu
    * pelilaudan alueella ja ei avattu. Rekursion "loppuun" päästyään merkkaa
    * ruudun avatuksi ja lisää sen ruudut-listaan.
    * 
    * @param rivi Rivi jolla solu sijaitsee
    * @param sarake Sarake jolla solu sijaitsee
    * @param ruudut ArrayList löydetyistä ruuduista
    */
    public void tarkistaJaAvaa(int rivi, int sarake, ArrayList<Ruutu> ruudut) {
        if (!onkoTaulukossa(rivi, sarake)){
            return;
        }
        
        if (this.taulukko[rivi][sarake].getSisalto() == 0 && !this.taulukko[rivi][sarake].getStatus().equals("avattu")) {
            avaaViereisetNollat(sarake, rivi, ruudut);
        } else {
            this.taulukko[rivi][sarake].setStatus("avattu");
            ruudut.add(this.taulukko[rivi][sarake]);
        }
    }

    /**
    * Tulostustoiminto jolla voi katsoa pelin etenemistä tekstiversiona
    */
    public void tulosta() {
        for (int i = 0; i < this.korkeus; i++) {
            for (int j = 0; j < this.leveys; j++) {
                System.out.print("[" + taulukko[i][j].getSisalto() + "]");
            }
            System.out.println("");
        }
        System.out.println("");
        for (int i = 0; i < this.korkeus; i++) {
            for (int j = 0; j < this.leveys; j++) {
                System.out.print("[" + taulukko[i][j].getStatus() + "]");
            }
            System.out.println("");
        }
    }

    /**
    * Tarkistaa onko ruutu ensimmäinen joka avattiin
    * 
    * @return true jos oli ensimmäinen joka avattiin
    */
    public boolean onkoEnsimmainenAvaus() {
        for (int i = 0; i < this.korkeus; i++) {
            for (int j = 0; j < this.leveys; j++) {
                if (taulukko[i][j].getStatus().equals("avattu")) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Palauttaa pelilaudan korkeuden
     *
     * @return korkeus
     */ 
    public int getKorkeus() {
        return korkeus;
    }

    /**
     * Palauttaa pelilaudan leveyden
     *
     * @return leveys
     */ 
    public int getLeveys() {
        return leveys;
    }

    /**
     * Palauttaa pelilautaan liittyvän taulukon
     *
     * @return taulukko
     */ 
    public Ruutu[][] getTaulukko() {
        return taulukko;
    }

    /**
     * Palauttaa pelilaudan miinojen määrän
     *
     * @return miinojen määrä
     */ 
    public int getMiinoja() {
        return miinoja;
    }

    /**
     * Asetetaan pelilaudalle uusi taulukko
     *
     * @param timer Asetettava taulukko
     */
    public void setTaulukko(Ruutu[][] taulukko) {
        this.taulukko = taulukko;
    }

    /**
    * Lopussa avataan kaikki ruudut joita ei vielä ollut avattu ja vaihdetaan
    * niille oikeat jpg-kuvakkeet
    * 
    * @param lopputulos "voitto" tai "havio", jos voitettiin niin miinallisia
    * ruutuja ei lopussa avata
    */
    public void avaaKaikki(String lopputulos) {
        for (int i = 0; i < this.korkeus; i++) {
            for (int j = 0; j < this.leveys; j++) {
                if (lopputulos.equals("voitto")){
                    if (!this.taulukko[i][j].getStatus().equals("avattu") && this.taulukko[i][j].getSisalto() != 9) {
                        this.taulukko[i][j].avaaLopussa();
                    }
                } else {
                    if (!this.taulukko[i][j].getStatus().equals("avattu")) {
                        this.taulukko[i][j].avaaLopussa();
                    }
                }
            }
        }
    }

    /**
    * Tarkistaa onko peli voitettu, eli onko kaikki paitsi miinoja sisältävät ruudut avattu
    * 
    * @return true jos on voitettu
    */
    public boolean onkoVoitettu() {
        for (int i = 0; i < this.korkeus; i++) {
            for (int j = 0; j < this.leveys; j++) {
                if (!this.taulukko[i][j].getStatus().equals("avattu") && this.taulukko[i][j].getSisalto() != 9) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
    * Tarkistaa onko tietty solu taulukon rajojen sisällä
    * 
    * @param rivi Solun rivi
    * @param sarake Solun sarake
    * 
    * @return true jos on taulukon rajojen sisällä
    */
    public boolean onkoTaulukossa(int rivi, int sarake) {
        if (rivi < 0 || rivi >= this.korkeus || sarake < 0 || sarake >= this.leveys) {
            return false;
        }
        return true;
    }
    
    /**
    * Tarkistaa oliko ensimmäisenä klikatussa ruudussa miina. Jos oli, jakaa miinat
    * uudelleen kunnes ruudussa ei ole miinaa.
    * 
    * @param ruutu Klikattu ruutu
    */
    public void tarkistaAlkutilanne(Ruutu ruutu) {
        int x = ruutu.getX();
        int y = ruutu.getY();
        if (onkoEnsimmainenAvaus() && this.taulukko[y][x].getSisalto() == 9) {
            while (this.taulukko[y][x].onkoMiinaa()) {
                generoiUusiLauta();
            }
        }
    }    
}
