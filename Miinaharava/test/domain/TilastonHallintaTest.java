
package domain;

import miinaharava.domain.TilastonHallinta;
import static org.junit.Assert.assertFalse;
import org.junit.*;


public class TilastonHallintaTest {
    
    TilastonHallinta th;
    
    public TilastonHallintaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        th = new TilastonHallinta();
    }
    
    @After
    public void tearDown() {
    }

//     @Test
//     public void tulosLisataanOikeaanTilastoon() {
//         
//     }
     
     @Test
     public void tilastotLuodaan() {
         assertFalse(th.getIso() == null);
         assertFalse(th.getKeski() == null);
         assertFalse(th.getPieni() == null);
     }     
}
