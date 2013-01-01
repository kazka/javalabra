
package miinaharava.domain;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Ruutu {
    private String status; // vaihtoehdot: kiinni/avattu/merkattu
    private int sisalto; //0 = tyhja, 1-8 = miinojen lkm vieressa, 9 = miina
    private JButton btn;
    private int x;
    private int y;
    
    public Ruutu(int x, int y){
        this.status = "kiinni";      
        this.x = x;
        this.y = y;
    }
    
    // testeille
    public Ruutu(){
        this.status = "kiinni";    
        this.btn = new JButton();
    }    
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public void setSisalto(int luku){
        this.sisalto = luku;
    }
    
    // palauttaa true jos ruudussa on miina
    public boolean onkoMiinaa(){
        if (this.sisalto == 9){
            return true;
        }
        return false;
    }

    public int getSisalto() {
        return sisalto;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return Integer.toString(sisalto);
    }

    public JButton getBtn() {
        return btn;
    }

    public void setBtn(JButton btn) {
        this.btn = btn;
    }

    public void merkkaa() {
        if (this.status.equals("merkattu")) {
            this.status = "kiinni";
            vaihdaKuvake(10);
        } else {
            this.status = "merkattu";
            vaihdaKuvake(88);
        }
    }
    
    //vaihtaa jpg kuvakkeen vastaamaan oikeaa sisaltöä. 0.jpg = tyhjä, 1-8.jpg = miinojen määrän mukainen kuvake, 9.jpg = miina, 10.jpg = suljettu ruutu,
    public void vaihdaKuvake(int sisalto) {
        ImageIcon kuvake = new ImageIcon("materiaali/" + sisalto + ".jpg");
        this.btn.setIcon(kuvake);
    }

//    public void setX(int x) {
//        this.x = x;
//    }
//
//    public void setY(int y) {
//        this.y = y;
//    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
}
