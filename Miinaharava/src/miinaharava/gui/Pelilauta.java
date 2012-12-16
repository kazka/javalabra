
package miinaharava.gui;

import java.util.Random;
import miinaharava.domain.Ruutu;
import miinaharava.peli.Miinaharava;

public class Pelilauta {
    private int leveys;
    private int korkeus;
    private int miinoja;
    private Ruutu[][] taulukko;
    
    public Pelilauta(int leveys, int korkeus, int miinoja) {
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.miinoja = miinoja;
        this.taulukko = new Ruutu [korkeus][leveys];
        for (int i = 0; i < korkeus; i++){
            for (int j = 0; j < leveys; j++){
                this.taulukko[i][j] = new Ruutu();
            }
        }
    }
    
    public void asetaMiinat(){
        Random random = new Random();
        for (int i = 0; i <= this.miinoja; i++){
            int randomX = random.nextInt(this.leveys);
            int randomY = random.nextInt(this.korkeus);
                if (!taulukko[randomY][randomX].onkoMiinaa()){
                     taulukko[randomY][randomX].setSisalto(9);
                }
        }
    }
    
    public void asetaMiinattomat(){
        for (int i = 0; i < this.korkeus; i++){
            for (int j = 0; j < this.leveys; j++){
                if (!taulukko[i][j].onkoMiinaa()){
                    taulukko[i][j].setSisalto(laskeYmparoivatMiinat(i,j));
                }
            }
        }
    }

    public int laskeYmparoivatMiinat(int rivi, int sarake) {
        int miinat = 0;
        if (rivi > 0 && sarake > 0 && taulukko[rivi-1][sarake-1].onkoMiinaa()){
            miinat++;
        }
        if (rivi > 0 && taulukko[rivi-1][sarake].onkoMiinaa()){
            miinat++;
        }
        if (rivi > 0 && sarake < this.korkeus-1 && taulukko[rivi-1][sarake+1].onkoMiinaa()){
            miinat++;
        }
        if (sarake > 0 && taulukko[rivi][sarake-1].onkoMiinaa()){
            miinat++;
        }
        if (sarake < this.korkeus-1 && taulukko[rivi][sarake+1].onkoMiinaa()){
            miinat++;
        }
        if (rivi < this.leveys-1 && sarake > 0 && taulukko[rivi+1][sarake-1].onkoMiinaa()){
            miinat++;
        }
        if (rivi < this.leveys-1 && taulukko[rivi+1][sarake].onkoMiinaa()){
            miinat++;
        }
        if (rivi < this.leveys-1 && sarake < this.korkeus-1 && taulukko[rivi+1][sarake+1].onkoMiinaa()){
            miinat++;
        }
        return miinat;
    }

    public void tulosta() {
        for (int i = 0; i < this.korkeus; i++){
            for (int j = 0; j < this.leveys; j++){
                    System.out.print("[" + taulukko[i][j].toString() + "]");
            }
            System.out.println("");
        }
        System.out.println("");
        for (int i = 0; i < this.korkeus; i++){
            for (int j = 0; j < this.leveys; j++){
                    System.out.print("[" + taulukko[i][j].getStatus() + "]");
            }
            System.out.println("");
        }
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
    
}
