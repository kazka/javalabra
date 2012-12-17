
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
        asetaMiinat();
        asetaMiinattomat();
    }
    
    public void asetaMiinat(){
        Random random = new Random();
        for (int i = 0; i < this.miinoja; i++){
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
        if (rivi > 0 && sarake < this.leveys-1 && taulukko[rivi-1][sarake+1].onkoMiinaa()){
            miinat++;
        }
        if (sarake > 0 && taulukko[rivi][sarake-1].onkoMiinaa()){
            miinat++;
        }
        if (sarake < this.leveys-1 && taulukko[rivi][sarake+1].onkoMiinaa()){
            miinat++;
        }
        if (rivi < this.korkeus-1 && sarake > 0 && taulukko[rivi+1][sarake-1].onkoMiinaa()){
            miinat++;
        }
        if (rivi < this.korkeus-1 && taulukko[rivi+1][sarake].onkoMiinaa()){
            miinat++;
        }
        if (rivi < this.korkeus-1 && sarake < this.leveys-1 && taulukko[rivi+1][sarake+1].onkoMiinaa()){
            miinat++;
        }
        return miinat;
    }
    
    public void avaaViereisetNollat(int sarake, int rivi){
        // dfs
        this.taulukko[rivi][sarake].setStatus("avattu");
        
        if (rivi > 0){
            if (taulukko[rivi-1][sarake].getSisalto() == 0 && !taulukko[rivi-1][sarake].getStatus().equals("avattu")){
                avaaViereisetNollat(sarake, rivi-1);
            } else {
                taulukko[rivi-1][sarake].setStatus("avattu");
            }
        }        
        if (sarake > 0){
            if (taulukko[rivi][sarake-1].getSisalto() == 0 && !taulukko[rivi][sarake-1].getStatus().equals("avattu")){
                avaaViereisetNollat(sarake-1, rivi);
            } else {
                taulukko[rivi][sarake-1].setStatus("avattu");
            }
        }
        if (sarake < this.leveys-1){
            if (taulukko[rivi][sarake+1].getSisalto() == 0 && !taulukko[rivi][sarake+1].getStatus().equals("avattu")){
                avaaViereisetNollat(sarake+1, rivi);
            } else {
                taulukko[rivi][sarake+1].setStatus("avattu");
            }
        }
        if (rivi < this.korkeus-1){
            if (taulukko[rivi+1][sarake].getSisalto() == 0 && !taulukko[rivi+1][sarake].getStatus().equals("avattu")){
                avaaViereisetNollat(sarake, rivi+1);
            } else {
                taulukko[rivi+1][sarake].setStatus("avattu");
            }
        }
        // kulmat
        if (rivi > 0 && sarake > 0){
            if (taulukko[rivi-1][sarake-1].getSisalto() == 0 && !taulukko[rivi-1][sarake-1].getStatus().equals("avattu")){
                avaaViereisetNollat(sarake-1, rivi-1);
            } else {
                taulukko[rivi-1][sarake-1].setStatus("avattu");
            }
        }
        if (rivi > 0 && sarake < this.leveys-1){
            if (taulukko[rivi-1][sarake+1].getSisalto() == 0 && !taulukko[rivi-1][sarake+1].getStatus().equals("avattu")){
                avaaViereisetNollat(sarake+1, rivi-1);
            } else {
                taulukko[rivi-1][sarake+1].setStatus("avattu");
            }
        }
        if (rivi < this.korkeus-1 && sarake > 0){
            if (taulukko[rivi+1][sarake-1].getSisalto() == 0 && !taulukko[rivi+1][sarake-1].getStatus().equals("avattu")){
                avaaViereisetNollat(sarake-1, rivi+1);
            } else {
                taulukko[rivi+1][sarake-1].setStatus("avattu");
            }
        }  
        if (rivi < this.korkeus-1 && sarake < this.leveys-1){
            if (taulukko[rivi+1][sarake+1].getSisalto() == 0 && !taulukko[rivi+1][sarake+1].getStatus().equals("avattu")){
                avaaViereisetNollat(sarake+1, rivi+1);
            } else {
                taulukko[rivi+1][sarake+1].setStatus("avattu");
            }
        }        
    }
    
    public void avaaNollienViereiset(){
        
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
    
    public boolean onkoEnsimmainenAvaus(){
        for (int i = 0; i < this.korkeus; i++){
            for (int j = 0; j < this.leveys; j++){
                if (taulukko[i][j].getStatus().equals("avattu")){
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
    
}
