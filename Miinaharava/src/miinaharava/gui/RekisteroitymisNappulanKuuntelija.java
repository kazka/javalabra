
package miinaharava.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import miinaharava.peli.Miinaharava;

/**
 * Luokka jolla kuunnellaan klikkauksia nappulassa jolla luodaan uusi tunnus
 */
public class RekisteroitymisNappulanKuuntelija implements ActionListener{
    
    /**
    * Miinaharava-olio johon kuuntelija liittyy
    */
    private Miinaharava harava;
    
    /**
    * JFrame jonka tietoja kuuntelijalla päivitetään
    */
    private JFrame frame;
    
    /**
    * Tekstikenttä josta tuodaan sisältö kuuntelijalle
    */
    private JTextField tunnusKentta;
    
    /**
    * Tekstikenttä josta tuodaan sisältö kuuntelijalle
    */
    private JTextField ssKentta;
    
    /**
    * Mahdollinen virheilmoitus jota kuuntelijassa päivitetään
    */
    private JLabel virheilmoitus;

    /**
    * Konstruktori
    * 
    * @param harava Miinaharava johon kuuntelija liittyy
    * @param ruutu Ruutu johon kuuntelija liittyy
    * @param tunnusKentta Tekstikenttä josta saadaan syötetty tunnus
    * @param ssKenttä Tekstikenttä josta saadaan syötetty salasana
    * @param virheilmoitus Alussa tyhjä JLabel johon asetetaan mahdollinen virheilmoitus
    */
    public RekisteroitymisNappulanKuuntelija(Miinaharava harava, JFrame frame, JTextField tunnusKentta, JTextField ssKentta, JLabel virheilmoitus) {
        this.harava = harava;
        this.frame = frame;        
        this.tunnusKentta = tunnusKentta;
        this.ssKentta = ssKentta;
        this.virheilmoitus = virheilmoitus;
    }

    /**
    * Klikkauksessa tarkistetaan ettei tunnus ole varattu, ettei tunnuksessa tai
    * salasanassa ole välilyöntejä ja ettei kenttiä ole jätetty tyhjäksi.
    * Epäonnistuessa näytetään virheilmoitus.
    * Onnistuessa kirjataan uusi käyttäjä tilastoon, asetetaan sisäänkirjautunut
    * käyttäjä ja avataan uuden pelin asetusikkuna.
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.harava.getKayttajalista().getKayttajat().containsKey(this.tunnusKentta.getText())){
            this.virheilmoitus.setText(":< tunnus on varattu");
        } else if (this.tunnusKentta.getText().indexOf(" ")!=-1 || this.ssKentta.getText().indexOf(" ")!=-1){
            this.virheilmoitus.setText(":< tunnus tai salasana ei saa sisältää välilyöntejä");
        } else if (this.tunnusKentta.getText().equals("") || this.ssKentta.getText().equals("")){
            this.virheilmoitus.setText(":< kirjoita tunnus ja salasana");            
        } else {
            JOptionPane.showMessageDialog(null, ":> tunnus luotu");
            this.frame.dispose();
            this.harava.setKayttajanNimi(this.tunnusKentta.getText());
            this.harava.getKayttajalista().lisaaKayttaja(this.tunnusKentta.getText(), this.ssKentta.getText());
            try {
                this.harava.getKayttajalista().paivitaTiedosto();
            } catch (IOException ex) {
                System.out.println("Tiedoston päivitys ei onnistu:" + ex.getMessage());
            }
            this.harava.uusiPeli();
        }
    }
    
}
