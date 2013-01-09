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
    * Konstruktori joka toimii ilman värin antamista, käytetään testeissä.
    * Generoi pelilaudalle Ruutu-taulukon ja kutsuu metodeita jotka asettavat
    * miinat sekä miinattomien ruutujen sisällöt.
    * 
    * @param leveys Pelilaudan leveys
    * @param korkeus Pelilaudan korkeus
    * @param miinoja Pelilaudan miinojen määrä
    */
//    public Pelilauta(int leveys, int korkeus, int miinoja) {
//        this.taulukko = new Ruutu[this.korkeus][this.leveys];
//        generoiTaulukko(this.korkeus, this.leveys);
//        asetaMiinat();
//        asetaMiinattomat();
//    }
    
    /**
    * Pääasiallinen konstruktori.
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
    
//    public final void tarkistaKoko(int leveys, int korkeus, int miinoja) {
//        if (leveys < 5) {
//            this.leveys = 5;
//        } else if (leveys > 300) {
//            this.leveys = 300;
//        } else {
//            this.leveys = leveys;
//        }
//        if (korkeus < 5) {
//            this.korkeus = 5;
//        } else if (korkeus > 300) {
//            this.korkeus = 300;
//        } else {
//            this.korkeus = korkeus;
//        }
//        if (miinoja < 1) {
//            this.miinoja = 1;
//        } else if (miinoja > this.leveys * this.korkeus / 2) {
//            this.miinoja = this.leveys * this.korkeus / 2;  // miinoja korkeintaan puolet ruuduista
//        } else {
//            this.miinoja = miinoja;
//        }
//    }

    // generoi taulukon eli asettaa joka soluun uuden ruudun
//    public final void generoiTaulukko() {
//        for (int i = 0; i < this.korkeus; i++) {
//            for (int j = 0; j < this.leveys; j++) {
//                this.taulukko[i][j] = new Ruutu(j,i);
//            }
//        }
//    }

    /**
    * Generoi pelilaudalle Ruutu-taulukon
    * 
    * @param korkeus Generoitavan taulukon korkeus
    * @param leveys Generoitavan taulukon leveys
    */
//    public final void generoiTaulukko(int korkeus, int leveys) {
//        for (int i = 0; i < korkeus; i++) {
//            for (int j = 0; j < leveys; j++) {
//                this.taulukko[i][j] = new Ruutu(j,i);
//            }
//        }
//    }
    
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
    * Asettaa oikean määrän miinoja sattumanvaraisiin ruutuihin
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
    * viereisessä ruudussa on miina
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

    public int getKorkeus() {
        return korkeus;
    }

    public int getLeveys() {
        return leveys;
    }

    public Ruutu[][] getTaulukko() {
        return taulukko;
    }

    public int getMiinoja() {
        return miinoja;
    }

    public void setTaulukko(Ruutu[][] taulukko) {
        this.taulukko = taulukko;
    }

    /**
    * Lopussa avataan kaikki ruudut joita ei vielä ollut avattu ja vaihdetaan
    * niille oikeat jpg-kuvakkeet
    * 
    * @param lopputulos "voitto" tai "havio", jos voitettoon niin miinallisia
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
    
}
