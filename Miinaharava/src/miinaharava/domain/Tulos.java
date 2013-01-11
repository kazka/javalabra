
package miinaharava.domain;

/**
 * Yksittäistä tulosta kuvaava luokka
 */
public class Tulos implements Comparable<Tulos> {
    
    /**
    * Käyttäjän tunnus jolle tulos kuuluu
    */
    private String tunnus;
    
    /**
    * Tulos eli peliin kulunut aika sekunteina
    */
    private int tulos;

    /**
    * Konstruktori
    * 
    * @param tunnus Pelaajan tunnus
    * @param tulos Pelistä saatu tulos
    */
    public Tulos(String tunnus, int tulos) {
        this.tunnus = tunnus;
        this.tulos = tulos;
    }

    /**
    * Generoi tuloksen stringiksi, tasaa nimen 22 merkin pituiseksi
    */
    @Override
    public String toString() {
        String nimiJaTyhjaa = String.format("%-22s", this.tunnus);
        return nimiJaTyhjaa + " " + this.tulos + " sek";
    }
    
    /**
    * Tulosten vertailu
    */
    @Override
    public int compareTo(Tulos tulos) {
        if(this.tulos == tulos.getTulos()) {
            return 0;
        } else if (this.tulos > tulos.getTulos()) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * Palauttaa tulokseen liittyvän pelaajan tunnuksen
     *
     * @return tunnus
     */ 
    public String getTunnus() {
        return tunnus;
    }

    /**
     * Palauttaa tuloksen eli peliin käytetyn ajan
     *
     * @return tulos
     */ 
    public int getTulos() {
        return tulos;
    }
    
}
