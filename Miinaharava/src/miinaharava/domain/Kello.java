
package miinaharava.domain;

import java.awt.Color;
import java.awt.Font;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Luokka joka pitää kirjaa peliin käytetystä ajasta sekunteina
 */
public class Kello extends TimerTask {
    
     /**
     * Näyttää kuinka monta sekuntia peli on kestänyt tähän mennessä
     */    
    private int aika;
    
     /**
     * JLabel joka näyttää pelin keston käyttöliittymässä
     */    
    private JLabel label;
    
    /**
     * Kellon konstruktori
     *
     * @param label JLabel joka näyttää pelin keston tietyllä hetkellä
     */    
    public Kello(JLabel label){
        this.aika = -1;
        this.label = label;
        this.label.setHorizontalAlignment(SwingConstants.CENTER);
        this.label.setFont(new Font("Georgia", Font.PLAIN, 24));
        this.label.setForeground(new Color(0xaa0000));
        this.label.setText(Integer.toString(this.aika));
    }

    /**
     * Palauttaa kuinka monta sekuntia peli on kestänyt tietyllä hetkellä
     *
     * @return pelin kesto tällä hetkellä
     */      
    public int getAikanyt() {
        return this.aika;
    }
    
    /**
     * TimerTask-olion abstrakti metodi joka suoritetaan sekunnin välein
     * (aika määritelty Miinaharava-luokassa).
     * Lisää aikaa yhdellä sekunnin välein ja päivittää JLabelissa näkyvän ajan.
     */  
    @Override
    public void run() {
        this.aika++;
        this.label.setText(Integer.toString(this.aika));
    }
    
    /**
     * Palauttaa JLabelin joka näyttää kauanko peli on kestänyt.
     *
     * @return JLabel
     */ 
    public JLabel getLabel() {
        return label;
    }
    
}
