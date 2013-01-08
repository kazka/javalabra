package miinaharava.gui;

import java.util.ArrayList;
import java.util.Random;
import miinaharava.domain.Ruutu;

/**
 * Miinaharavan pelilauta-ruudukkoa kuvaava luokka
 */
public class Pelilauta {

    private int leveys;
    private int korkeus;
    private int miinoja;
    private Ruutu[][] taulukko;

    public Pelilauta(int leveys, int korkeus, int miinoja) {
        tarkistaKoko(leveys, korkeus, miinoja);
        this.taulukko = new Ruutu[this.korkeus][this.leveys];
        generoiTaulukko(this.korkeus, this.leveys);
        asetaMiinat();
        asetaMiinattomat();
    }
    
    public Pelilauta(int leveys, int korkeus, int miinoja, String vari) {
        tarkistaKoko(leveys, korkeus, miinoja);
        this.taulukko = new Ruutu[this.korkeus][this.leveys];
        generoiTaulukko(this.korkeus, this.leveys, vari);
        asetaMiinat();
        asetaMiinattomat();
    }
    
    public final void tarkistaKoko(int leveys, int korkeus, int miinoja) {
        if (leveys < 5) {
            this.leveys = 5;
        } else if (leveys > 300) {
            this.leveys = 300;
        } else {
            this.leveys = leveys;
        }
        if (korkeus < 5) {
            this.korkeus = 5;
        } else if (korkeus > 300) {
            this.korkeus = 300;
        } else {
            this.korkeus = korkeus;
        }
        if (miinoja < 1) {
            this.miinoja = 1;
        } else if (miinoja > this.leveys * this.korkeus / 2) {
            this.miinoja = this.leveys * this.korkeus / 2;  // miinoja korkeintaan puolet ruuduista
        } else {
            this.miinoja = miinoja;
        }
    }

    // generoi taulukon eli asettaa joka soluun uuden ruudun
//    public final void generoiTaulukko() {
//        for (int i = 0; i < this.korkeus; i++) {
//            for (int j = 0; j < this.leveys; j++) {
//                this.taulukko[i][j] = new Ruutu(j,i);
//            }
//        }
//    }

    // generoi tietyn kokoisen taulukon (käytetään testeissä)
    public final void generoiTaulukko(int korkeus, int leveys) {
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                this.taulukko[i][j] = new Ruutu(j,i);
            }
        }
    }
    
    public final void generoiTaulukko(int korkeus, int leveys, String vari) {
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                this.taulukko[i][j] = new Ruutu(j,i);
                this.taulukko[i][j].setVari(vari);
            }
        }
    }    

    // asetetaan oikea määrä miinoja sattumanvaraisiin ruutuihin
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

    // lasketaan miinattomiin ruutuihin luku, kuinka monessa viereisessä ruudussa on miina
    public final void asetaMiinattomat() {
        for (int i = 0; i < this.korkeus; i++) {
            for (int j = 0; j < this.leveys; j++) {
                if (!taulukko[i][j].onkoMiinaa()) {
                    taulukko[i][j].setSisalto(laskeYmparoivatMiinat(i, j));
                }
            }
        }
    }

    public void nollaa() {
        for (int i = 0; i < this.korkeus; i++) {
            for (int j = 0; j < this.leveys; j++) {
                taulukko[i][j].setSisalto(0);
            }
        }
    }

    public void generoiUusiLauta() {
        nollaa();
        asetaMiinat();
        asetaMiinattomat();
    }

    // laskee tietyssä solussa olevalle ruudulle ympäröivien miinojen määrän
    public int laskeYmparoivatMiinat(int rivi, int sarake) {
        int miinat = 0;
        miinat += haeYmparoivatMiinat(rivi-1, sarake-1);
        miinat += haeYmparoivatMiinat(rivi-1, sarake);
        miinat += haeYmparoivatMiinat(rivi-1, sarake+1);
        miinat += haeYmparoivatMiinat(rivi, sarake+1);
        miinat += haeYmparoivatMiinat(rivi, sarake-1);
        miinat += haeYmparoivatMiinat(rivi+1, sarake-1);
        miinat += haeYmparoivatMiinat(rivi+1, sarake);
        miinat += haeYmparoivatMiinat(rivi+1, sarake+1);

        return miinat;
    }
    
    public int haeYmparoivatMiinat(int rivi, int sarake){
        if (onkoTaulukossa(rivi, sarake) && taulukko[rivi][sarake].onkoMiinaa()){
            return 1;
        } else {
            return 0;
        }
    }

    // kun avatussa ruudussa on luku 0, lasketaan syvyyssuuntaisella läpikäynnillä kaikki viereiset ruudut 
    // joissa on myös luku 0 sekä lähimmät joissa on jokin muu luku, ja lisätään nämä ruudut ArrayListiin
    public ArrayList<Ruutu> avaaViereisetNollat(int sarake, int rivi, ArrayList<Ruutu> ruudut) {
        this.taulukko[rivi][sarake].setStatus("avattu");
        ruudut.add(this.taulukko[rivi][sarake]);

        //reunat
        tarkistaJaAvaa(rivi - 1, sarake, ruudut);
        tarkistaJaAvaa(rivi, sarake - 1, ruudut);
        tarkistaJaAvaa(rivi, sarake + 1, ruudut);
        tarkistaJaAvaa(rivi + 1, sarake, ruudut);
        // kulmat
        tarkistaJaAvaa(rivi - 1, sarake - 1, ruudut);
        tarkistaJaAvaa(rivi - 1, sarake + 1, ruudut);
        tarkistaJaAvaa(rivi + 1, sarake - 1, ruudut);
        tarkistaJaAvaa(rivi + 1, sarake + 1, ruudut);

        return ruudut;
    }

    // tarkistaa onko ruutu pelilaudan alueella ja ei avattu
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

    // tarkistaa onko ruutu ensimmäinen joka avattiin
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

    // lopussa avataan kaikki ruudut joita ei vielä ollut avattu ja vaihtaa niille oikeat jpg-kuvakkeet
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
    
    
    // tarkistaa onko peli voitettu, eli onko kaikki paitsi miinoja sisältävät ruudut avattu
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

    private boolean onkoTaulukossa(int rivi, int sarake) {
        if (rivi < 0 || rivi >= this.korkeus || sarake < 0 || sarake >= this.leveys) {
            return false;
        }
        return true;
    }
    
}
