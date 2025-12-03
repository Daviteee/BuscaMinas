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
		Joc joc = new Joc(t1); // Constructor per parametres (Tauler)
		BuscaminesVista vista  = new MockBuscaminesVista(joc); //Creem un mock de la vista.
		vista.initVista(); //La vista és genera a partir del mock (no fa res)
		
		//Afegim la vista al controlador (el mockObject). Comprovem que s'inicia correctament la vista al controlador. Això es comprova mitjançant postcondicions
		//al codi desenvolupat si no surt error està correcte.
		joc.crearVistaDelJoc(vista); 
		
		//Comprovació de les excepcions que han de sortir al constructor. Si tauler és null o si la vista és null. Aquestes comrpovacions es fan al codi desenvolupat
		//mitjançant precondicions.
		try {
	    	joc = new Joc(null);
	    }catch(AssertionError e) {
	    	System.err.println(e.getMessage());
	    }
		try {
	    	joc.crearVistaDelJoc(null);
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
        BuscaminesVista vista  = new MockBuscaminesVista(joc); //Creem un mock de la vista.
		vista.initVista(); //La vista és genera a partir del mock (no fa res)
		joc.crearVistaDelJoc(vista); 
		
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
        BuscaminesVista vista  = new MockBuscaminesVista(joc); //Creem un mock de la vista.
		vista.initVista(); //La vista és genera a partir del mock (no fa res)
		joc.crearVistaDelJoc(vista); 
		joc.clicEsquerra(0,0); // Destapem la casella 0,0
		assertTrue(joc.getTauler().getCasella(0,0).isDestapat());
	}
	

}
