
package controlador;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import org.junit.jupiter.api.Test;
import model.MockRandom;
import model.MockTauler;
import model.Tauler;
import vista.BuscaminesVista;
import vista.MockBuscaminesVista;

class JocTest {
	
	@Test
	public void TestConstructorPerParàmetresJoc() {
		Random r = new Random();
		Tauler t1 = new Tauler(r);

		// Constructor per paràmetres: comprovem que s’inicialitza correctament.
		// Això es comprova mitjançant les postcondicions del codi. Si no es llença cap error, és correcte.
		Joc joc = new Joc(t1);

		// Creem un mock de la vista per evitar obrir la vista real.
		BuscaminesVista vista  = new MockBuscaminesVista(joc);
		vista.initVista(); // El mock no fa res.

		// Assignem la vista al controlador. Es comprova mitjançant postcondicions.
		joc.crearVistaDelJoc(vista);
		
		// Comprovem les excepcions del constructor (tauler null) i del mètode crearVistaDelJoc (vista null),
		// controlades mitjançant precondicions al codi.
		try {
	    	joc = new Joc(null);
	    } catch (AssertionError e) {
	    	System.err.println(e.getMessage());
	    }

		try {
	    	joc.crearVistaDelJoc(null);
	    } catch (AssertionError e) {
	    	System.err.println(e.getMessage());
	    }
	}
	

	@Test
    public void clicDretTest() {

		// El clic dret permet posar o treure una bandera.
		// Casos possibles:
		// 1. Posar bandera en una casella tapada
		// 2. Treure bandera si ja n’hi havia
		// 3. No permetre bandera en una casella destapada
		
		// Cas 1: Posar bandera correctament
		Random r = new Random();
		Tauler t1 = new Tauler(r);
        Joc joc = new Joc(t1);

		BuscaminesVista vista  = new MockBuscaminesVista(joc);
		vista.initVista();
		joc.crearVistaDelJoc(vista);

        joc.clicDret(2, 2); 
        assertEquals(29, joc.getBanderesRestants());
        assertTrue(joc.isBandera(2,2));
        
        // Cas 2: Treure bandera
        joc.clicDret(2, 2);
        assertEquals(30, joc.getBanderesRestants());
        assertFalse(joc.isBandera(2,2));
        
        // Cas 3: No permetre bandera en una casella destapada
        r = new Random();
		t1 = new Tauler(r);
        joc = new Joc(t1);

        vista  = new MockBuscaminesVista(joc);
		vista.initVista();
		joc.crearVistaDelJoc(vista);

		joc.clicEsquerra(2, 2); // Destapem
        joc.clicDret(2, 2);

        assertEquals(30, joc.getBanderesRestants());
        assertFalse(joc.isBandera(2,2));
    }
	
	@Test
	public void clicEsquerraTest() {

		// clicEsquerra destapa caselles.
		// Casos possibles:
		// 1. Partida ja acabada → no fa res
		// 2. Casella amb bandera → no es pot destapar
		// 3. Primer clic → generar mines
		// 4. Casella ja destapada → no fa res
		// 5. Casella amb mina → perdre la partida
		// 6. Últim clic per guanyar (test complet a simulacioPartida)

		// Path coverage: aquest mètode permet fer test per paths perquè té una estructura condicional clara.

		// Path 1 — partida acabada
		Random r = new Random();
		Tauler t1 = new Tauler(r);
        Joc joc = new Joc(t1);

        BuscaminesVista vista  = new MockBuscaminesVista(joc);
		vista.initVista();
		joc.crearVistaDelJoc(vista);

		joc.setPartidaAcabada(); 
	    joc.clicEsquerra(0,0); 
	    assertTrue(joc.getPartidaAcabada());
	    
	    // Path 2 — casella amb bandera
	 	joc.clicDret(0, 0);
		joc.clicEsquerra(0, 0);
		assertFalse(joc.isDestapat(0, 0));
		
		// Path 3 — primer clic
		r = new Random();
		t1 = new Tauler(r);
        joc = new Joc(t1);

        vista  = new MockBuscaminesVista(joc);
		vista.initVista();
		joc.crearVistaDelJoc(vista);

		joc.clicEsquerra(6,6); 
		assertTrue(joc.isDestapat(6, 6));
		assertFalse(joc.getPartidaAcabada());
		
		// Path 4 — casella ja destapada
		joc.clicEsquerra(6, 6);
		assertTrue(joc.isDestapat(6, 6));
		
		// Path 5 — destapar una mina
		r = new MockRandom(8, 7); // Mock que controla la mina
		t1 = new Tauler(r);
		t1.setMaxMines(1);

        joc = new Joc(t1);
        vista  = new MockBuscaminesVista(joc);
		vista.initVista();
		joc.crearVistaDelJoc(vista);

		joc.clicEsquerra(0, 0); 
		joc.clicEsquerra(5, 5); // Casella amb mina

		assertTrue(joc.isDestapat(12, 5)); 
		assertTrue(joc.getPartidaAcabada());
		
		// Path 6 — es comprova completament al test simulacioPartida()
	}
	
	@Test
	public void simulacioPartida() {
		
		// Test complet de la classe Joc.
		// Utilitza MockTauler per controlar la posició de les mines i MockBuscaminesVista
		// per evitar obrir la vista real.
		// Els fitxers simulacio1.txt i simulacio2.txt contenen una seqüència real de clics.
		// Cada línia indica “esq x y” o “drt x y”.
		//
		// Al final:
		//   simulació 1 → ha de guanyar
		//   simulació 2 → ha de perdre
		//
		// Això comprova el Path 6 del mètode clicEsquerra (condició final de victòria o derrota).

		Random r = new Random();
		Tauler t1 = new MockTauler(r);
		t1.generaMinesRandom(10, 0);

		Joc joc = new Joc(t1);

		BuscaminesVista vista  = new MockBuscaminesVista(joc);
		vista.initVista();
		joc.crearVistaDelJoc(vista); 
		
		// Simulació 1 → ha de guanyar
		try (BufferedReader br = new BufferedReader(new FileReader("data/simulacio1.txt"))) {

		    String linea;

		    while ((linea = br.readLine()) != null) {
		        String[] parts = linea.split(" ");

		        String accion = parts[0];
		        int x = Integer.parseInt(parts[1]);
		        int y = Integer.parseInt(parts[2]);

		        if (accion.equals("esq")) joc.clicEsquerra(x, y);
		        else if (accion.equals("drt")) joc.clicDret(x, y);
		    }
		    
		    assertTrue(t1.totesDestapadesSenseMines()); // Ha de guanyar

		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		// Simulació 2 → ha de perdre
		Random r1 = new Random();
        Tauler t2 = new MockTauler(r1);
        t2.generaMinesRandom(10, 0);

        Joc joc2 = new Joc(t2);

        BuscaminesVista vista2  = new MockBuscaminesVista(joc2);
        vista2.initVista();

        // ⚠️ Probable error original: aquí s'estava passant "vista" en comptes de "vista2".
        joc2.crearVistaDelJoc(vista2);

        try (BufferedReader br = new BufferedReader(new FileReader("data/simulacio2.txt"))) {

            String linea;

            while ((linea = br.readLine()) != null) {
                String[] parts = linea.split(" ");

                String accion = parts[0];
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);

                if (accion.equals("esq")) joc2.clicEsquerra(x, y);
                else if (accion.equals("drt")) joc2.clicDret(x, y);
            }

            assertFalse(t2.totesDestapadesSenseMines()); // Ha de perdre

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
