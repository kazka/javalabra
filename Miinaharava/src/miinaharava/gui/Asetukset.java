package miinaharava.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.*;
import miinaharava.peli.Miinaharava;

public class Asetukset implements Runnable {

    private Miinaharava harava;
    private JFrame frame;

    public Asetukset(Miinaharava harava) {
        this.harava = harava;
    }

    @Override
    public void run() {
        frame = new JFrame("Asetukset - uusi peli");
        frame.setPreferredSize(new Dimension(300, 200));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        JLabel label = new JLabel("Kentän koko:");
        label.setFont(new Font("Georgia", Font.PLAIN, 24));
        container.add(label);
        ButtonGroup buttonGroup = new ButtonGroup();
        ArrayList<JRadioButton> vaihtoehdot = new ArrayList<JRadioButton>();

        JRadioButton radiobt1 = new JRadioButton("pieni");
        buttonGroup.add(radiobt1);
        panel.add(radiobt1);
        vaihtoehdot.add(radiobt1);
        JRadioButton radiobt2 = new JRadioButton("keskikoko");
        buttonGroup.add(radiobt2);
        panel.add(radiobt2);
        vaihtoehdot.add(radiobt2);
        JRadioButton radiobt3 = new JRadioButton("iso");
        buttonGroup.add(radiobt3);
        panel.add(radiobt3);
        vaihtoehdot.add(radiobt3);

        container.add(panel);

        JButton button = new JButton("OK");
        button.addActionListener(new ValinnanKuuntelija(this.harava, vaihtoehdot, this.frame));
        container.add(button);
    }

    public JFrame getFrame() {
        return frame;
    }

    public Miinaharava getHarava() {
        return harava;
    }
}
