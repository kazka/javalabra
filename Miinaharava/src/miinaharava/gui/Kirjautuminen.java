
package miinaharava.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
import miinaharava.peli.Miinaharava;

/**
 *
 * Käyttöliittymä kirjautumiselle ja rekisteröitymiselle
 */
public class Kirjautuminen implements Runnable {
    private Miinaharava harava;
    private JFrame frame;

    public Kirjautuminen(Miinaharava harava) {
        this.harava = harava;
    }

    @Override
    public void run() {
        frame = new JFrame("Kirjaudu tai luo tunnus");
        frame.setPreferredSize(new Dimension(300, 400));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        container.setBackground(new Color(0xffdddd));
        
        JLabel label = new JLabel("Kirjaudu");
        label.setFont(new Font("Georgia", Font.PLAIN, 24));
        container.add(label);
        
        JLabel tunnusTeksti = new JLabel("Tunnus: ");
        JTextField tunnusKentta = new JTextField();
        JLabel ssTeksti = new JLabel("Salasana: ");
        JTextField ssKentta = new JTextField();
        JLabel virheilmoitus = new JLabel();

        JButton kirjauduBtn = new JButton("Kirjaudu");
        kirjauduBtn.setBackground(new Color(0xff8888));
        kirjauduBtn.addActionListener(new KirjautumisNappulanKuuntelija(this.harava, this.frame, tunnusKentta, ssKentta, virheilmoitus));

        container.add(tunnusTeksti);
        container.add(tunnusKentta);
        container.add(ssTeksti);
        container.add(ssKentta);
        container.add(virheilmoitus);
        container.add(new JLabel(""));
        container.add(kirjauduBtn);    
        
        
        JLabel label2 = new JLabel("Luo uusi tunnus");
        label2.setFont(new Font("Georgia", Font.PLAIN, 24));
        container.add(label2);
        
        JLabel tunnusTeksti2 = new JLabel("Tunnus (ei välilyöntejä): ");
        JTextField tunnusKentta2 = new JTextField();
        JLabel ssTeksti2 = new JLabel("Salasana (ei välilyöntejä): ");
        JTextField ssKentta2 = new JTextField();
        JLabel virheilmoitus2 = new JLabel();

        JButton kirjauduBtn2 = new JButton("Luo tunnus");
        kirjauduBtn2.setBackground(new Color(0xff8888));
        kirjauduBtn2.addActionListener(new RekisteroitymisNappulanKuuntelija(this.harava, this.frame, tunnusKentta2, ssKentta2, virheilmoitus2));

        container.add(tunnusTeksti2);
        container.add(tunnusKentta2);
        container.add(ssTeksti2);
        container.add(ssKentta2);
        container.add(virheilmoitus2);
        container.add(new JLabel(""));
        container.add(kirjauduBtn2);           
    }

    public JFrame getFrame() {
        return frame;
    }

    public Miinaharava getHarava() {
        return harava;
    }
}
