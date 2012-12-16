
package miinaharava.gui;

import java.util.ArrayList;
import java.util.Random;
import miinaharava.domain.Ruutu;
import miinaharava.peli.Miinaharava;

public class Pelilauta {
    private int leveys;
    private int korkeus;
    private int miinoja;
    private ArrayList<Ruutu> ruudut;
    
    public Pelilauta(int leveys, int korkeus, int miinoja) {
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.miinoja = miinoja;
        this.ruudut = new ArrayList<Ruutu>();
    }
    
    public void asetaMiinat(){
        Random random = new Random();
        for (int i = 0; i < 10; i++){
            int randomX = random.nextInt(this.leveys);
            int randomY = random.nextInt(this.korkeus);
            for (Ruutu ruutu : this.ruudut){
                if (!ruutu.onkoMiinaa()){
                    if (ruutu.getX() == randomX && ruutu.getY() == randomY){
                        ruutu.setMiina();
                    }
                }
            }
        }
    }
}
