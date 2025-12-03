package controlador;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import org.junit.jupiter.api.Test;
import model.Tauler;
import vista.BuscaminesVista;
import vista.MockBuscaminesVista;

class JocTest {
	
	@Test
	public void TestConstructorPerParàmetresJoc() {
		Random r = new Random();
		Tauler t1 = new Tauler(r);
		//Constructor per paràmetres per comprovar que s'inicialitza correctament, es comprova amb postcondicions al codi desenvolupat.Si no surt error es correcte.
		Joc joc1 = new Joc(t1); // Constructor per parametres (Tauler)
		BuscaminesVista vista  = new MockBuscaminesVista(joc1);
		vista.initVista();
		joc1.crearVistaDelJoc(vista);
		//Comprovem a més a més que si el tauler es null ha de donar error.
		try {
	    	joc1 = new Joc(null);
	    }catch(AssertionError e) {
	    	System.err.println(e.getMessage());
	    }
		
	}
	

	@Test
    public void clicDretTest() {
		// Test que comproba que el clic Dret possa la bandera correctament.
		Random r = new Random();
		Tauler t1 = new Tauler(r);
        Joc joc = new Joc(t1);
        joc.clicDret(2, 2); // Posem bandera a la posició 2,2
        assertTrue(joc.getTauler().getCasella(2, 2).isBandera()); // Comprovem que la bandera s'ha possat
        
        // Cas on no podrem possar una bandera ja que la casella ja ha estat destapada
        joc.getTauler().getCasella(0,1).setMina(); // Posem una mina a la casella 0,1
        joc.getTauler().destapaCasella(1,1); // Destapem la casella 1,1 (per tant tindrá el numero de mines al voltant = 1)
        assertFalse(joc.getTauler().getCasella(1, 1).isBandera()); // Comprovem que no podem col·locar bandera ja que esta destapada
        
    }
	
	@Test
	public void getTaulerTest() {
		Random r = new Random();
		Tauler t1 = new Tauler(r);
		Joc joc = new Joc(t1); // Constructor per parametres
		assertEquals(t1, joc.getTauler());
	}
	
	@Test
	public void getVistaTest() {
		Random r = new Random();
		Tauler t1 = new Tauler(r);
		Joc joc = new Joc(t1);
		BuscaminesVista v = new BuscaminesVista(joc);
		joc.setVista(v);
		assertEquals(v, joc.getVista());
	}
	
	@Test
	public void clicEsquerraTest() {
		Random r = new Random();
		Tauler t1 = new Tauler(r);
		Joc joc = new Joc(t1);
		joc.clicEsquerra(0,0); // Destapem la casella 0,0
		assertTrue(joc.getTauler().getCasella(0,0).isDestapat());
	}
	

}
