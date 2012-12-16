
package miinaharava.domain;

public class Ruutu {
    private String status; // vaihtoehdot: kiinni/avattu/merkattu
    private int sisalto; //0 = tyhja, 1-8 = miinojen lkm vieressa, 9 = miina
    private int x;
    private int y;    
    
    public Ruutu(int x, int y){
        this.status = "kiinni";
        this.x = x;
        this.y = y;        
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public void setMiina(){
        this.sisalto = 9;
    }
    
    public boolean onkoMiinaa(){
        if (this.sisalto == 9){
            return true;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
}
