
package miinaharava.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * Pitää kirjaa rekisteröityneistä käyttäjistä
 */
public class Kayttajalista {
    private File tiedosto;
    private HashMap<String, String> kayttajat = new HashMap<>();
    
    public Kayttajalista(){
        this.tiedosto = new File("src/tiedostot/kayttajat.txt");
        lueKayttajatiedosto(this.tiedosto);
    }

    public final void lueKayttajatiedosto(File tiedosto) {
        Scanner lukija;
        try {
            lukija = new Scanner(tiedosto);
        } catch (FileNotFoundException ex) {
            System.out.println("Tiedostoa ei löydy:" + ex.getMessage());
            return;
        }
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            generoiKayttajaTiedostosta(rivi);
        }
        lukija.close();
    }
    
    public final void generoiKayttajaTiedostosta(String rivi) {
        String[] split = rivi.split("\\s+");
        this.kayttajat.put(split[0], split[1]);
    }
    
    public void lisaaKayttaja(String nimi, String salasana){
        this.kayttajat.put(nimi, salasana);
    }
    
    public boolean tarkistaTunnus(String nimi, String salasana){
        if (this.kayttajat.get(nimi).equals(salasana)){
            return true;
        }
        return false;
    }
    
    public boolean onkoSamannimistaKayttajaa(String nimi){
        if (this.kayttajat.containsKey(nimi)){
            return true;
        }
        return false;
    }

    public HashMap<String, String> getKayttajat() {
        return kayttajat;
    }
    
    public void paivitaTiedosto() throws IOException {
        try (FileWriter kirjoittaja = new FileWriter(this.tiedosto)) {
            Iterator it = this.kayttajat.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pari = (Map.Entry)it.next();
                kirjoittaja.write(pari.getKey() + " " + pari.getValue() + "\n");
            }
        }
        
    }
}
