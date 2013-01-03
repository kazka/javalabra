
package miinaharava.domain;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Ruutu {
    private String status; // vaihtoehdot: kiinni/avattu/merkattu
    private int sisalto; //0 = tyhja, 1-8 = miinojen lkm vieressa, 9 = miina
    private JButton btn;
    private int x;
    private int y;
    
    /**
    * Oletusarvoisesti käytettävä konstruktori
    *
    * @param   x   Sarake jolla Ruutu sijaitsee
    * @param   y   Rivi jolla Ruutu sijaitsee 
    */
    public Ruutu(int x, int y){
        this.status = "kiinni";      
        this.x = x;
        this.y = y;
    }
    
    /**
    * Testauksessa käytettävä konstruktori
    *
    * @param   x   Sarake jolla Ruutu sijaitsee
    * @param   y   Rivi jolla Ruutu sijaitsee 
    */
    public Ruutu(){
        this.status = "kiinni";    
        this.btn = new JButton();
    }    
    
    /**
    * Vaihdetaan Ruudun status
    *
    * @param   status   Ruudulle asetettava status
    */
    public void setStatus(String status){
        this.status = status;
    }
    
    /**
    * Vaihdetaan Ruudun sisalto
    *
    * @param   luku   Ruudulle asetettava sisalto
    */ 
    public void setSisalto(int luku){
        this.sisalto = luku;
    }
    
    /**
    * Kertoo onko ruudussa miina
    * 
    * @return true jos on miina, false jos ei ole miinaa
    */
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

//    @Override
//    public String toString() {
//        return Integer.toString(sisalto);
//    }

    public JButton getBtn() {
        return btn;
    }

    public void setBtn(JButton btn) {
        this.btn = btn;
    }

    /**
    * Metodi asettaa ruudun merkatuksi, tai poistaa merkkauksen jos ruutu oli jo merkattu
    */
    public void merkkaa() {
        if (this.status.equals("merkattu")) {
            this.status = "kiinni";
            vaihdaKuvake(10);
        } else {
            this.status = "merkattu";
            vaihdaKuvake(88);
        }
    }
    
    /**
    * Vaihtaa ruutuun liittyvän JButtonin kuvakkeen
    *
    * @param   sisalto   Luku jonka perusteella haetaan kyseiseen sisaltoon liittyva jpg-kuva
    */
    public void vaihdaKuvake(int sisalto) {
        ImageIcon kuvake = new ImageIcon("materiaali/" + sisalto + ".jpg");
        if (this.btn == null){
            this.btn = new JButton();
        }        
        this.btn.setIcon(kuvake);
    }
    
    /**
    * Kun peli on loppu, avataan ruutu kutsumalla tätä metodia ja vaihdetaan sille oikea kuvake
    */
    public void avaaLopussa(){
        if (this.status.equals("merkattu") && this.sisalto != 9) {
            this.status = "avattu";
            vaihdaKuvake(99);
        } else {
            this.status = "avattu";
            vaihdaKuvake(this.sisalto);
        }
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
