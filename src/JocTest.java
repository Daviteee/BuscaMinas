import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JocTest {
	

	@Test
    public void clicDretTest() {
		// Test que comproba que el clic Dret possa la bandera correctament.
        Joc joc = new Joc();
        joc.clicDret(2, 2); // Posem bandera a la posició 2,2
        assertTrue(joc.getTauler().getCasella(2, 2).isBandera()); // Comprovem que la bandera s'ha possat
    }
	
	

}
