
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
    
    /**
    * Tekstitiedosto joka sisältää käyttäjätunnukset ja salasanat
    */
    private File tiedosto;
    
    /**
    * HashMap joka sisältää käyttäjätunnus-salasana-parit
    */
    private HashMap<String, String> kayttajat = new HashMap<>();
    
    /**
    * Konstruktori, hakee käyttäjätiedoston ja kutsuu metodia joka lukee sen
    */
    public Kayttajalista(){
        this.tiedosto = new File("src/tiedostot/kayttajat.txt");
        lueKayttajatiedosto(this.tiedosto);
    }

    /**
    * Metodi joka lukee käyttäjätiedoston ja kutsuu joka riville metodia joka
    * lisää käyttäjän tiedot kayttajat-HashMapiin
    * 
    * @param tiedosto Käyttäjät sisältävä tekstitiedosto
    */
    public final void lueKayttajatiedosto(File tiedosto) {
        this.kayttajat.clear();
        Scanner lukija;
        try {
            lukija = new Scanner(tiedosto);
        } catch (FileNotFoundException ex) {
            System.out.println("Tiedostoa ei löydy:" + ex.getMessage());
            return;
        }
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            lisaaKayttajaTiedostostaHashmapiin(rivi);
        }
        lukija.close();
    }
    
    /**
    * Generoi käyttäjätiedostosta löytyneen rivin tunnus-salasana-pariksi ja
    * lisää sen HashMapiin
    * 
    * @param rivi Tiedoston rivi muotoa "tunnus salasana"
    */
    public final void lisaaKayttajaTiedostostaHashmapiin(String rivi) {
        String[] split = rivi.split("\\s+");
        this.kayttajat.put(split[0], split[1]);
    }
    
    /**
    * Lisää uuden käyttäjän HashMapiin
    * 
    * @param nimi Käyttäjän tunnus
    * @param salasana Käyttäjän salasana
    */
    public void lisaaKayttaja(String nimi, String salasana){
        this.kayttajat.put(nimi, salasana);
    }
    
    /**
    * Tarkistaa täsmääkö tunnus ja salasana
    * 
    * @param nimi Tunnus
    * @param salasana Salasana
    * 
    * @return true jos täsmää
    */
    public boolean tarkistaTunnus(String nimi, String salasana){
        if (this.kayttajat.get(nimi).equals(salasana)){
            return true;
        }
        return false;
    }
    
    /**
    * Tarkistaa onko käyttäjätunnus varattu
    * 
    * @param nimi Etsittävä tunnus
    * 
    * @return true jos tunnus on varattu
    */
    public boolean onkoSamannimistaKayttajaa(String nimi){
        if (this.kayttajat.containsKey(nimi)){
            return true;
        }
        return false;
    }

    public HashMap<String, String> getKayttajat() {
        return kayttajat;
    }
    
    /**
    * Päivittää käyttäjätiedoston kirjoittamalla sinne HashMapin sisällön
    */
    public void paivitaTiedosto() throws IOException {
        try (FileWriter kirjoittaja = new FileWriter(this.tiedosto)) {
            Iterator it = this.kayttajat.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pari = (Map.Entry)it.next();
                kirjoittaja.write(pari.getKey() + " " + pari.getValue() + "\n");
            }
        }
        
    }

    public File getTiedosto() {
        return tiedosto;
    }
    
}
