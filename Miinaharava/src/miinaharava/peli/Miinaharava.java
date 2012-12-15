package miinaharava.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import miinaharava.gui.Pelilauta;

public class Miinaharava implements ActionListener {

    private Pelilauta lauta;

    public Miinaharava(Pelilauta lauta) {
        this.lauta = lauta;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void start() {
        this.lauta.asetaMiinat();
    }
}
