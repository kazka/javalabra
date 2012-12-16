
package miinaharava.domain;

public class Ruutu {
    private String status; // vaihtoehdot: kiinni/avattu/merkattu
    private int sisalto; //0 = tyhja, 1-8 = miinojen lkm vieressa, 9 = miina
//    private int x;
//    private int y;    
    
    public Ruutu(){
        this.status = "kiinni";
//        this.x = x;
//        this.y = y;        
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public void setSisalto(int luku){
        this.sisalto = luku;
    }
    
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
    
}
