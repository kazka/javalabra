
package miinaharava.domain;

/**
 * Yksittäistä tulosta kuvaava luokka
 */
public class Tulos implements Comparable<Tulos> {
    
    /**
    * Käyttäjän nimi eli tunnus jolle tulos kuuluu
    */
    private String nimi;
    
    /**
    * Tulos eli peliin kulunut aika sekunteina
    */
    private int tulos;

    /**
    * Konstruktori
    * 
    * @param nimi Pelaajan nimi/tunnus
    * @param tulos Pelistä saatu tulos
    */
    public Tulos(String nimi, int tulos) {
        this.nimi = nimi;
        this.tulos = tulos;
    }

    /**
    * Generoi tuloksen stringiksi, tasaa nimen 22 merkin pituiseksi
    */
    @Override
    public String toString() {
        String nimiJaTyhjaa = String.format("%-22s", this.nimi);
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

    public String getNimi() {
        return nimi;
    }

    
    public int getTulos() {
        return tulos;
    }
    
}
