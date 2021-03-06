package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto, varasto_minus, varasto_minus_minus, varasto2, varasto3;
    String merkkijono;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto_minus = new Varasto(-10);
        varasto_minus_minus = new Varasto(-10, -10);
        varasto = new Varasto(10);
        varasto2 = new Varasto(10, 2);
        varasto3 = new Varasto(10, 12);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void laitetaanLiikaa() {
        varasto.lisaaVarastoon(14);
        // varastoon pitäisi jäädä tilaa 0
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void otetaanLiikaa() {
        varasto.otaVarastosta(14);
        // varastoon pitäisi jäädä saldoksi 0
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void otetaanNegatiivinen() {
        varasto.otaVarastosta(-1);
        // varastoon pitäisi jäädä saldoksi 0
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void laitetaanNegatiivinen() {
        varasto.lisaaVarastoon(-1);
        // varastoon pitäisi jäädä saldoksi 0
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void merkkijonoksi() {
        // test
        merkkijono = varasto.toString();
        assertEquals("saldo = 0.0, vielä tilaa 10.0", merkkijono);
    }

}