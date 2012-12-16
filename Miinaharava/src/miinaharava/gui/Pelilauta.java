
package miinaharava.gui;

import java.util.ArrayList;
import java.util.Random;
import miinaharava.domain.Ruutu;
import miinaharava.peli.Miinaharava;

public class Pelilauta {
    private int leveys;
    private int korkeus;
    private int miinoja;
    //private ArrayList<Ruutu> ruudut;
    private Ruutu[][] taulukko;
    
    public Pelilauta(int leveys, int korkeus, int miinoja) {
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.miinoja = miinoja;
        this.taulukko = new Ruutu [korkeus][leveys];
        //this.ruudut = new ArrayList<>();
        for (int i = 0; i < leveys; i++){
            for (int j = 0; j < korkeus; j++){
                this.taulukko[j][i] = new Ruutu();
            }
        }
    }
   
//    public Ruutu haeRuutu(int x, int y){
//        for (Ruutu ruutu : this.ruudut){
//            if (ruutu.getX() == x && ruutu.getY() == y){
//                return ruutu;
//            }
//        }
//        
//        return null;
//    }
    
    public void asetaMiinat(){
        Random random = new Random();
        for (int i = 0; i < this.miinoja; i++){
            int randomX = random.nextInt(this.leveys);
            int randomY = random.nextInt(this.korkeus);
            for (Ruutu ruutu : this.ruudut){
                if (!ruutu.onkoMiinaa()){
                    if (ruutu.getX() == randomX && ruutu.getY() == randomY){
                        ruutu.setSisalto(9);
                    }
                }
            }
        }
    }
    
    public void asetaMiinattomat(){
        for (Ruutu ruutu : this.ruudut){
            if (!ruutu.onkoMiinaa()){
                ruutu.setSisalto(laskeYmparoivatMiinat(ruutu));
            }
        }
    }

    public int laskeYmparoivatMiinat(Ruutu ruutu) {
        int miinat = 0;
        if (haeRuutu(ruutu.getX()-1, ruutu.getY()-1).onkoMiinaa()){
            miinat++;
        }
        if (haeRuutu(ruutu.getX()-1, ruutu.getY()).onkoMiinaa()){
            miinat++;
        }  
        if (haeRuutu(ruutu.getX()-1, ruutu.getY()+1).onkoMiinaa()){
            miinat++;
        }
        if (haeRuutu(ruutu.getX(), ruutu.getY()-1).onkoMiinaa()){
            miinat++;
        }
        if (haeRuutu(ruutu.getX(), ruutu.getY()+1).onkoMiinaa()){
            miinat++;
        }
        if (haeRuutu(ruutu.getX()+1, ruutu.getY()-1).onkoMiinaa()){
            miinat++;
        }
        if (haeRuutu(ruutu.getX()+1, ruutu.getY()).onkoMiinaa()){
            miinat++;
        }
        if (haeRuutu(ruutu.getX()+1, ruutu.getY()+1).onkoMiinaa()){
            miinat++;
        }
        return miinat;
    }
}
