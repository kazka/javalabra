
package peli;

import java.util.Timer;
import javax.swing.JLabel;
import miinaharava.domain.Kello;
import miinaharava.peli.Miinaharava;
import static org.junit.Assert.assertEquals;
import org.junit.*;


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
        harava.luoPelilauta("pieni", "punainen");
        
        assertEquals(10, harava.getLauta().getLeveys());
        assertEquals(10, harava.getLauta().getKorkeus());
        assertEquals(12, harava.getLauta().getMiinoja());
    }
    
    @Test
    public void uudenKeskikokoisenPelilaudanKokoAsetetaanOikein(){
        harava.luoPelilauta("keskikoko", "punainen");
        
        assertEquals(20, harava.getLauta().getLeveys());
        assertEquals(15, harava.getLauta().getKorkeus());
        assertEquals(40, harava.getLauta().getMiinoja());
    }   
    
    @Test
    public void uudenIsonPelilaudanKokoAsetetaanOikein(){
        harava.luoPelilauta("iso", "punainen");
        
        assertEquals(35, harava.getLauta().getLeveys());
        assertEquals(20, harava.getLauta().getKorkeus());
        assertEquals(100, harava.getLauta().getMiinoja());
    }     
           
}
