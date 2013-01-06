
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
    private Miinaharava harava;
    private JFrame frame;
    private JTextField tunnusKentta;
    private JTextField ssKentta;
    private JLabel virheilmoitus;

    public KirjautumisNappulanKuuntelija(Miinaharava harava, JFrame frame, JTextField tunnusKentta, JTextField ssKentta, JLabel virheilmoitus) {
        this.harava = harava;
        this.frame = frame;        
        this.tunnusKentta = tunnusKentta;
        this.ssKentta = ssKentta;
        this.virheilmoitus = virheilmoitus;
    }

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
