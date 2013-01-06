
package miinaharava.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Tietun kokoisen pelilaudan pistetilastoa kuvaava luokka
 */
public class Tilasto {
    private File tiedosto;
    private ArrayList<Tulos> tulokset = new ArrayList<>();
    
    public Tilasto(File tiedosto){
        this.tiedosto = tiedosto;
        try {
            tilastoListaksi();
        } catch (FileNotFoundException ex) {
            System.out.println("Tiedostoa ei löydy:" + ex.getMessage());
        }
    }
    
    public String tulostaTilasto(){
        Collections.sort(this.tulokset);      
        StringBuilder sb = new StringBuilder();
        for (Tulos tulos : this.tulokset){
            sb.append(tulos.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
    
    public final void tilastoListaksi() throws FileNotFoundException{
        Scanner lukija;

        lukija = new Scanner(this.tiedosto);

        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            Tulos tulos = generoiTulos(rivi);
            this.tulokset.add(tulos);
        }

        lukija.close();
        
    }    
    
    public Tulos generoiTulos(String rivi) {
        String[] split = rivi.split("\\s+");
        return new Tulos(split[0], Integer.parseInt(split[1]));
    }
    
    public void lisaaTulos(Tulos tulos){
        this.tulokset.add(tulos);
    }    
    
    public void paivitaTiedosto() throws IOException{
        try (FileWriter kirjoittaja = new FileWriter(this.tiedosto)) {
            for (Tulos tulos : this.tulokset){
                kirjoittaja.write(tulos.toString() + "\n");
            }
        }
    }
}
