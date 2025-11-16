import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JocTest {
	

	@Test
    public void clicDretTest() {
		// Test que comproba que el clic Dret possa la bandera correctament.
        Joc joc = new Joc();
        joc.clicDret(2, 2); // Posem bandera a la posició 2,2
        assertTrue(joc.getTauler().getCasella(2, 2).isBandera()); // Comprovem que la bandera s'ha possat
        
        // Cas on no podrem possar una bandera ja que la casella ja ha estat destapada
        joc.getTauler().getCasella(0,1).setMina(); // Posem una mina a la casella 0,1
        joc.getTauler().destapaCasella(1,1); // Destapem la casella 1,1 (per tant tindrá el numero de mines al voltant = 1)
        assertFalse(joc.getTauler().getCasella(1, 1).isBandera()); // Comprovem que no podem col·locar bandera ja que esta destapada
        
    }
	
	@Test
	public void getTaulerTest() {
		Tauler t1 = new Tauler();
		Joc joc = new Joc(t1); // Constructor per parametres
		assertEquals(t1, joc.getTauler());
	}
	

}
