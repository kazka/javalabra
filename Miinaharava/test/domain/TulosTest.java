
package domain;

import miinaharava.domain.Tulos;
import static org.junit.Assert.assertEquals;
import org.junit.*;


public class TulosTest {
    
    Tulos tulos;
    
    public TulosTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        tulos = new Tulos("kaisa", 200);
    }
    
    @After
    public void tearDown() {
    }
    
     @Test
     public void tuloksenNimiJaAikaAsetetaanOikein() {
         assertEquals("kaisa", tulos.getTunnus());
         assertEquals(200, tulos.getTulos());
     }
}
