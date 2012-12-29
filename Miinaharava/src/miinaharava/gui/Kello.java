
package miinaharava.gui;

import java.awt.Color;
import java.awt.Font;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Kello extends TimerTask {
    private int aika;
    private JLabel label;
    
    public Kello(JLabel label){
        this.aika = -1;
        this.label = label;
        this.label.setHorizontalAlignment(SwingConstants.CENTER);
        this.label.setFont(new Font("Georgia", Font.PLAIN, 24));
        this.label.setForeground(new Color(0xaa0000));
        this.label.setText(Integer.toString(this.aika));
    }

    public int getAikanyt() {
        return this.aika;
    }

    @Override
    public void run() {
        this.aika++;
        this.label.setText(Integer.toString(this.aika));
    }

    public JLabel getLabel() {
        return label;
    }
    
}
