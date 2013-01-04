
package miinaharava.gui;

import java.awt.*;
import javax.swing.*;
import miinaharava.domain.Ruutu;
import miinaharava.peli.Miinaharava;

public class Kayttoliittyma implements Runnable {
    private Miinaharava harava;
    private JFrame frame;    

    public Kayttoliittyma(Miinaharava harava) {
        this.harava = harava;
    }

    @Override
    public void run() {
        frame = new JFrame("Miinaharava");
        frame.setPreferredSize(new Dimension(this.harava.getLauta().getLeveys()*29, this.harava.getLauta().getKorkeus()*36));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * Metodi luo käyttöliittymän komponentit
     * 
     * @param container Container-olio johon komponentit luodaan
     */
    private void luoKomponentit(Container container) {
        container.setLayout(new BorderLayout());
        container.setBackground(Color.white);
        container.add(getGrid(), BorderLayout.CENTER);
        container.add(this.harava.getKello().getLabel(), BorderLayout.NORTH);
        JButton uusipeliBtn = new JButton("uusi peli");
        uusipeliBtn.addActionListener(new UusipeliNappulanKuuntelija(this.harava, this.frame));
        container.add(uusipeliBtn, BorderLayout.SOUTH);
    }

    public JFrame getFrame() {
        return frame;
    }

    public Miinaharava getHarava() {
        return harava;
    }
    
    /**
     * Rakentaa uuden pelilaudan gui:n uudeksi GridLayoutiksi ja palauttaa JPanelin, johon on asetettu kyseinen GridLayout
     *
     * @return JPanel jossa valmiiksi rakennettu GridLayout-pelilauta
     */
    private JPanel getGrid(){
        JPanel sisempi = new JPanel();
        sisempi.setLayout(new GridLayout(harava.getLauta().getKorkeus(), harava.getLauta().getLeveys()));
        
        for (int i = 0; i < this.harava.getLauta().getKorkeus(); i++){
            for (int j = 0; j < this.harava.getLauta().getLeveys(); j++){
                asetaRuudunJButtonLayoutiin(i, j, sisempi);
            }
        }
        sisempi.setBackground(Color.white);
        return sisempi;
    }
    
    /**
     * Asettaa pelin alussa uudelle ruudulle JButtonin ja siihen liittyvän KlikkaustenKuuntelijan, sekä lisää JButtonin JPaneliin
     * 
     * @param i Rivi jolla ruutu sijaitsee pelilauta-taulukossa
     * @param j Sarake jolla ruutu sijaitsee pelilauta-taulukossa
     *
     * @return JPanel jossa valmiiksi rakennettu GridLayout-pelilauta
     */
    public void asetaRuudunJButtonLayoutiin(int i, int j, JPanel sisempi) {
        Ruutu ruutu = this.harava.getLauta().getTaulukko()[i][j];
        ruutu.luoBtn();
        ruutu.vaihdaKuvake(10);
        ruutu.getBtn().addMouseListener(new KlikkaustenKuuntelija(this.harava, ruutu));
        sisempi.add(this.harava.getLauta().getTaulukko()[i][j].getBtn());
    }

}