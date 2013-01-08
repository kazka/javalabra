package miinaharava.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.*;
import miinaharava.peli.Miinaharava;

/**
 * Käyttöliittymä Miinaharavaan liittyville asetuksille ja valinnoille
 */
public class Asetukset implements Runnable {

    /**
     * Asetuksiin liittyvä Miinaharava-olio
     */
    private Miinaharava harava;
    
    /**
     * Asetuksiin liittyvä JFrame-olio
     */
    private JFrame frame;

    /**
    * Konstruktori
    * 
    * @param harava Miinaharava johon asetukset asetetaan kuuluvaksi
    */
    public Asetukset(Miinaharava harava) {
        this.harava = harava;
    }

    /**
    * Runnable-rajapinnan toteuttavan olion abstrakti metodi, joka luo uuden
    * ikkunan luokassa määritellyille käyttöliittymäelementeille
    */
    @Override
    public void run() {
        frame = new JFrame("Asetukset - uusi peli");
        frame.setPreferredSize(new Dimension(220, 300));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Metodi luo asetusikkunan komponentit
     * 
     * @param container Container-olio johon komponentit luodaan
     */
    private void luoKomponentit(Container container) {
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        JLabel label = new JLabel("Kentän koko:");
        label.setFont(new Font("Georgia", Font.PLAIN, 24));
        container.add(label);
        container.setBackground(new Color(0xffdddd));
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(0xffdddd));
        ButtonGroup buttonGroup = new ButtonGroup();
        ArrayList<JRadioButton> vaihtoehdot = new ArrayList<>();

        lisaaRadiobtnGuihin(buttonGroup, new JRadioButton("pieni"), panel, vaihtoehdot);
        lisaaRadiobtnGuihin(buttonGroup, new JRadioButton("keskikoko"), panel, vaihtoehdot);
        lisaaRadiobtnGuihin(buttonGroup, new JRadioButton("iso"), panel, vaihtoehdot);
        
        
        JLabel labelVari = new JLabel("Ruutujen väri:");
        labelVari.setFont(new Font("Georgia", Font.PLAIN, 24));
        
        JPanel panelVari = new JPanel();
        panelVari.add(labelVari);
        panelVari.setBackground(new Color(0xffdddd));
        panelVari.setLayout(new BoxLayout(panelVari, BoxLayout.Y_AXIS));
        ButtonGroup buttonGroupVari = new ButtonGroup();
        ArrayList<JRadioButton> vaihtoehdotVari = new ArrayList<>();

        lisaaRadiobtnGuihin(buttonGroupVari, new JRadioButton("punainen"), panelVari, vaihtoehdotVari);
        lisaaRadiobtnGuihin(buttonGroupVari, new JRadioButton("vihrea"), panelVari, vaihtoehdotVari);     

        container.add(panel);
        container.add(panelVari);
        
        JButton button = new JButton("OK");
        button.setBackground(new Color(0xff8888));
        button.addActionListener(new ValinnanKuuntelija(this.harava, vaihtoehdot, vaihtoehdotVari, this.frame));
        container.add(button);
        JButton tilastoButton = new JButton("Näytä tilastot");
        tilastoButton.setBackground(new Color(0xff8888));
        tilastoButton.addActionListener(new TilastoNappulanKuuntelija(this.harava, this.frame));
        container.add(tilastoButton);      
    }

    /**
     * Metodi joka lisää JRadioButton-olion haluttuun paneeliin, buttongroupiin
     * ja vaihtoehtojen listaan sekä asettaa oletusarvoiset JRadioButtonit valituiksi.
     * 
     * @param buttonGroup ButtonGroup johon JRadioButton lisätään
     * @param radiobt Lisättävä JRadioButton
     * @param panel JPanel johon JRadioButton lisätään
     * @param vaihtoehdot ArrayList joka listaa kyseiseen buttongroupiin liittyvät vaihtoehdot
     */
    public void lisaaRadiobtnGuihin(ButtonGroup buttonGroup, JRadioButton radiobt, JPanel panel, ArrayList<JRadioButton> vaihtoehdot) {
        radiobt.setBackground(new Color(0xffdddd));
        buttonGroup.add(radiobt);
        panel.add(radiobt);
        vaihtoehdot.add(radiobt);
        if (radiobt.getText().equals("pieni") || radiobt.getText().equals("punainen")){
            radiobt.setSelected(true);
        }
    }
    
    /**
     * Palauttaa asetusikkunaan liittyvän JFrame-olion
     *
     * @return frame
     */  
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Palauttaa kirjautumisikkunaan liittyvän Miinaharava-olion
     *
     * @return harava
     */  
    public Miinaharava getHarava() {
        return harava;
    }
}
