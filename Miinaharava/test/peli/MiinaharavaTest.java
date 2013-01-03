
package peli;

import java.util.Timer;
import javax.swing.JLabel;
import miinaharava.gui.Kello;
import miinaharava.peli.Miinaharava;
import org.junit.*;
import static org.junit.Assert.*;


public class MiinaharavaTest {
    
    Miinaharava harava;
    
    public MiinaharavaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        harava = new Miinaharava();
        harava.setTimer(new Timer());
        harava.setKello(new Kello(new JLabel()));
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void uudenPienenPelilaudanKokoAsetetaanOikein(){
        harava.luoPelilauta("pieni");
        
        assertEquals(10, harava.getLauta().getLeveys());
        assertEquals(10, harava.getLauta().getKorkeus());
        assertEquals(12, harava.getLauta().getMiinoja());
    }
    
    @Test
    public void uudenKeskikokoisenPelilaudanKokoAsetetaanOikein(){
        harava.luoPelilauta("keskikoko");
        
        assertEquals(20, harava.getLauta().getLeveys());
        assertEquals(15, harava.getLauta().getKorkeus());
        assertEquals(40, harava.getLauta().getMiinoja());
    }   
    
    @Test
    public void uudenIsonPelilaudanKokoAsetetaanOikein(){
        harava.luoPelilauta("iso");
        
        assertEquals(35, harava.getLauta().getLeveys());
        assertEquals(20, harava.getLauta().getKorkeus());
        assertEquals(110, harava.getLauta().getMiinoja());
    }     
   
//    @Test
//    public void kelloNollataanUudessaPelissa(){
//        harava.luoPelilauta("iso");
//        
//        assertEquals(35, harava.getLauta().getLeveys());
//        assertEquals(20, harava.getLauta().getKorkeus());
//        assertEquals(110, harava.getLauta().getMiinoja());
//    }         
}
