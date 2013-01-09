
package miinaharava.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Tietyn kokoisen pelilaudan pistetilastoa kuvaava luokka
 */
public class Tilasto {
    
    /**
    * Tilastoon liittyvä tekstitiedosto joka sisältää tulokset
    */
    private File tiedosto;
    
    /**
    * Lista tuloksista
    */
    private ArrayList<Tulos> tulokset = new ArrayList<>();
    
    /**
    * Konstruktori, asettaa tilastolle tiedoston
    * 
    * @param tiedosto Tiedosto jota tilastossa käytetään
    */
    public Tilasto(File tiedosto){
        this.tiedosto = tiedosto;
        try {
            tilastoListaksi();
        } catch (FileNotFoundException ex) {
            System.out.println("Tiedostoa ei löydy:" + ex.getMessage());
        }
    }
    
    /**
    * Lajittelee tilastossa olevat tulokset ja rakentaa niistä StringBuilderilla
    * stringin
    * 
    * @return tulokset stringinä
    */
    public String tulostaTilasto(){
        Collections.sort(this.tulokset);      
        StringBuilder sb = new StringBuilder();
        for (Tulos tulos : this.tulokset){
            sb.append(tulos.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
    
    /**
    * Lukee tilastoon liittyvän tiedoston ja lisää löytyneet tulokset ArrayListiin
    */
    public final void tilastoListaksi() throws FileNotFoundException{
        this.tulokset.clear();
        Scanner lukija;

        lukija = new Scanner(this.tiedosto);

        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            Tulos tulos = generoiTulos(rivi);
            this.tulokset.add(tulos);
        }

        lukija.close();
        
    }    
    
    /**
    * Muuttaa tiedostossa olevan rivin uudeksi Tulos-olioksi
    * 
    * @param rivi Tiedostossa oleva rivi, joka on muotoa "tunnus pisteet"
    * 
    * @return uusi generoitu Tulos-olio
    */
    public Tulos generoiTulos(String rivi) {
        String[] split = rivi.split("\\s+");
        return new Tulos(split[0], Integer.parseInt(split[1]));
    }
    
    /**
    * Lisää tuloksen ArrayListiin
    * 
    * @param tulos Lisättävä tulos
    */
    public void lisaaTulos(Tulos tulos){
        this.tulokset.add(tulos);
    }    
    
    /**
    * Päivittää tilastoon liittyvän tiedoston eli kirjaa sinne kaikki tulokset
    * jotka tällä hetkellä ovat tulokset-ArrayListissä
    */
    public void paivitaTiedosto() throws IOException{
        try (FileWriter kirjoittaja = new FileWriter(this.tiedosto)) {
            for (Tulos tulos : this.tulokset){
                kirjoittaja.write(tulos.toString() + "\n");
            }
        }
    }

    public ArrayList<Tulos> getTulokset() {
        return tulokset;
    }
    
}
