
package miinaharava.domain;

import java.io.File;
import java.io.IOException;

/**
 * Pisteilastojen hallintaan tarkoitettu luokka
 */
public class TilastonHallinta {
    
    /**
    * Tilasto joka pitää kirjaa pienen pelilaudan tuloksista
    */
    private Tilasto pieni;
    
    /**
    * Tilasto joka pitää kirjaa keskikokoisen pelilaudan tuloksista
    */
    private Tilasto keski;
    
    /**
    * Tilasto joka pitää kirjaa ison pelilaudan tuloksista
    */
    private Tilasto iso;

    /**
    * Konstruktori joka luo kullekin pelilaudan koolle omat Tilasto-olionsa
    * kuhunkin liittyvällä tiedostolla
    */
    public TilastonHallinta() {
        this.pieni = new Tilasto(new File("src/tiedostot/tilasto_pieni.txt"));
        this.keski = new Tilasto(new File("src/tiedostot/tilasto_keskikoko.txt"));
        this.iso = new Tilasto(new File("src/tiedostot/tilasto_iso.txt"));
        
    }
    
    /**
    * Rakentaa StringBuilderilla kaikki tilastot yhdeksi valmiiksi stringiksi
    * joka voidaan tulostaa
    * 
    * @return kaikista tilastoista yhteen koostettu teksti
    */
    public String kaikkiTilastotStringiksi(){
        StringBuilder sb = new StringBuilder();
        sb.append("Pieni\n=====\n");
        sb.append(this.pieni.tulostaTilasto());
        sb.append("\nKeskikoko\n=========\n");
        sb.append(this.keski.tulostaTilasto());
        sb.append("\nIso\n===\n");
        sb.append(this.iso.tulostaTilasto());        

        return sb.toString();
    }
    
    /**
    * Kutsuu kaikille tilastoille metodia joka päivittää kyseiseen tilastoon
    * liittyvän tekstitiedoston sisällön
    */
    public void paivitaKaikkiTiedostot() throws IOException{
        this.pieni.paivitaTiedosto();
        this.keski.paivitaTiedosto();
        this.iso.paivitaTiedosto();
    }    

    /**
    * Luo uuden Tulos-olion ja lisää sen oikeaan tilastoon riippuen pelilaudan koosta
    * 
    * @param tunnus Sen käyttäjän tunnus jolle tulos lisätään
    * @param tulos Aika joka kului pelin voittamiseen
    * @param koko Pelatun pelilaudan koko
    */
    public void lisaaTulosTilastoon(String tunnus, int tulos, String koko){
        Tulos lisattava = new Tulos(tunnus, tulos);
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

    /**
     * Palauttaa isoon pelilautaan liittyvän Tilasto-olion
     *
     * @return iso tilasto
     */   
    public Tilasto getIso() {
        return iso;
    }

    /**
     * Palauttaa keskikokoiseen pelilautaan liittyvän Tilasto-olion
     *
     * @return keski tilasto
     */  
    public Tilasto getKeski() {
        return keski;
    }

    /**
     * Palauttaa pieneen pelilautaan liittyvän Tilasto-olion
     *
     * @return pieni tilasto
     */  
    public Tilasto getPieni() {
        return pieni;
    }
    
    
}
