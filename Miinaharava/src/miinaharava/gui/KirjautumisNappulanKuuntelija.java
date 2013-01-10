
package miinaharava.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import miinaharava.peli.Miinaharava;

/**
 * Luokka jolla kuunnellaan klikkauksia nappulassa jolla kirjaudutaan
 */
public class KirjautumisNappulanKuuntelija implements ActionListener{
    
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
    public KirjautumisNappulanKuuntelija(Miinaharava harava, JFrame frame, JTextField tunnusKentta, JTextField ssKentta, JLabel virheilmoitus) {
        this.harava = harava;
        this.frame = frame;        
        this.tunnusKentta = tunnusKentta;
        this.ssKentta = ssKentta;
        this.virheilmoitus = virheilmoitus;
    }

    /**
    * Klikkauksessa tarkistetaan tunnuksen olemassaolo sekä tunnuksen ja salasanan
    * täsmäävyys. Epäonnistuessa näytetään virheilmoitus. Onnistuessa asetetaan
    * sisäänkirjautunut käyttäjä ja avataan uuden pelin asetusikkuna.
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!this.harava.getKayttajalista().getKayttajat().containsKey(this.tunnusKentta.getText())){
            this.virheilmoitus.setText(":< tunnusta ei ole");
        } else if (this.harava.getKayttajalista().getKayttajat().get(this.tunnusKentta.getText()).equals(this.ssKentta.getText())){
            this.frame.dispose();
            this.harava.setKayttajanNimi(this.tunnusKentta.getText());
            this.harava.uusiPeli();
        } else {
            this.virheilmoitus.setText(":< tunnus ja salasana eivät täsmää");
        }
    }
}
