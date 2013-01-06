
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
 * Luokka jolla kuunnellaan klikkauksia nappulassa jolla kirjaudutaan
 */
public class RekisteroitymisNappulanKuuntelija implements ActionListener{
    private Miinaharava harava;
    private JFrame frame;
    private JTextField tunnusKentta;
    private JTextField ssKentta;
    private JLabel virheilmoitus;

    public RekisteroitymisNappulanKuuntelija(Miinaharava harava, JFrame frame, JTextField tunnusKentta, JTextField ssKentta, JLabel virheilmoitus) {
        this.harava = harava;
        this.frame = frame;        
        this.tunnusKentta = tunnusKentta;
        this.ssKentta = ssKentta;
        this.virheilmoitus = virheilmoitus;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.harava.getKayttajalista().getKayttajat().containsKey(this.tunnusKentta.getText())){
            this.virheilmoitus.setText(":< tunnus on varattu");
        } else if (this.tunnusKentta.getText().indexOf(" ")!=-1 || this.ssKentta.getText().indexOf(" ")!=-1){
            this.virheilmoitus.setText(":< tunnus tai salasana ei saa sisältää välilyöntejä");
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
