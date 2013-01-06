
package miinaharava.domain;

/**
 * Yksittäistä tulosta kuvaava luokka
 */
public class Tulos implements Comparable<Tulos> {
    private String nimi;
    private int tulos;

    public Tulos(String nimi, int tulos) {
        this.nimi = nimi;
        this.tulos = tulos;
    }

    @Override
    public String toString() {
        String nimiJaTyhjaa = String.format("%-22s", this.nimi);
        return nimiJaTyhjaa + " " + this.tulos + " sek";
    }
    
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
