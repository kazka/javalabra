
package miinaharava.domain;

import java.io.File;
import java.io.IOException;

/**
 * Pisteilastojen hallintaan tarkoitettu luokka
 */
public class TilastonHallinta {
    private Tilasto pieni;
    private Tilasto keski;
    private Tilasto iso;

    public TilastonHallinta() {
        this.pieni = new Tilasto(new File("src/tiedostot/tilasto_pieni.txt"));
        this.keski = new Tilasto(new File("src/tiedostot/tilasto_keskikoko.txt"));
        this.iso = new Tilasto(new File("src/tiedostot/tilasto_iso.txt"));
        
    }
    
    public String tulostaKaikkiTilastot(){
        StringBuilder sb = new StringBuilder();
        sb.append("Pieni\n=====\n");
        sb.append(this.pieni.tulostaTilasto());
        sb.append("\nKeskikoko\n=========\n");
        sb.append(this.keski.tulostaTilasto());
        sb.append("\nIso\n===\n");
        sb.append(this.iso.tulostaTilasto());        

        return sb.toString();
    }
    
    public void paivitaKaikkiTiedostot() throws IOException{
        this.pieni.paivitaTiedosto();
        this.keski.paivitaTiedosto();
        this.iso.paivitaTiedosto();
    }    
    
    public void lisaaTulosTilastoon(String nimi, int tulos, String koko){
        Tulos lisattava = new Tulos(nimi, tulos);
        switch (koko) {
            case "pieni":
                this.pieni.lisaaTulos(lisattava);
                break;
            case "keskikoko":
                this.keski.lisaaTulos(lisattava);
                break;
            default:
                this.iso.lisaaTulos(lisattava);
                break;
        }
    }   
    
    
}
