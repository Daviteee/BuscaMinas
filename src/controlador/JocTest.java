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
		// El clic dret significa posar una bandera a una casella
		// Casos posibles: Posem la bandera en una casella tapada, posem una bandera en una casella destapada,
		// treiem una bandera (clic dret sobre una bandera)
		
		// Cas que comproba que el clic dret posa la bandera en una casella tapada (correcte).
		Random r = new Random();
		Tauler t1 = new Tauler(r);
        Joc joc = new Joc(t1);
        BuscaminesVista vista  = new MockBuscaminesVista(joc); //Creem un mock de la vista.
		vista.initVista(); //La vista és genera a partir del mock (no fa res).
		joc.crearVistaDelJoc(vista);
        joc.clicDret(2, 2); // Posem bandera a la posició 2,2
        assertEquals(29, joc.getBanderesRestants()); // Comprovem que el numero de banderes ha baixat.
        assertTrue(joc.isBandera(2,2)); // Comprovem que la bandera s'ha possat
        
        // Cas on fem clic dret sobre una casella que ja té una bandera (es treu la bandera)
        joc.clicDret(2, 2);
        assertEquals(30, joc.getBanderesRestants()); // Comprovem que el numero de banderes ha incrementat.  
        assertFalse(joc.isBandera(2,2)); // Comprovem que la bandera s'ha tret.
        
        // Cas on no podrem possar una bandera ja que la casella ja ha estat destapada.
        r = new Random();
		t1 = new Tauler(r);
        joc = new Joc(t1);
        vista  = new MockBuscaminesVista(joc); //Creem un mock de la vista.
		vista.initVista(); //La vista és genera a partir del mock (no fa res).
		joc.crearVistaDelJoc(vista);
		joc.clicEsquerra(2, 2);
        joc.clicDret(2, 2); // Posem bandera a la posició 2,2 (que esta destapada)
        assertEquals(30, joc.getBanderesRestants()); // Comprovem que el numero de banderes no ha baixat.
        assertFalse(joc.isBandera(2,2)); // Comprovem que la bandera no s'ha possat
    }
	
	@Test
	public void clicEsquerraTest() {
		// El clic esquerra significa destapar una casella
		// Casos posibles: Destapem la primera casella (mai té mina), destapem una casella ja destapada, 
		// destapem una casella amb mina (incorrecte), destapem una casella amb bandera (incorrecte).
		
		// Cas on destapem la primera casella
		Random r = new Random();
		Tauler t1 = new Tauler(r);
        Joc joc = new Joc(t1);
        BuscaminesVista vista  = new MockBuscaminesVista(joc); //Creem un mock de la vista.
		vista.initVista(); //La vista és genera a partir del mock (no fa res)
		joc.crearVistaDelJoc(vista); 
		joc.clicEsquerra(6,6); // Destapem la casella 6,6 (com a primera casella)
		assertTrue(joc.isDestapat(6, 6)); // Comprovem que la casella s'ha destapat.
		assertFalse(joc.getPartidaAcabada()); // Comprovem que la partida no s'ha acabat.
		
		// Cas on destapem una casella ja destapada
		joc.clicEsquerra(6, 6); // Tornem a clicar en la mateixa casella anterior
		assertTrue(joc.isDestapat(6, 6)); // La casella ha de seguir destapada (no ha de fer res).
		
		// Cas on destapem una casella que té una bandera (no es pot):
		joc.clicDret(0, 0); // Posem una bandera a la casella 0, 0.
		joc.clicEsquerra(0, 0); // Intentem destapar la casella 0, 0.
		assertFalse(joc.isDestapat(0, 0)); // Comprovem que no s'ha destapat la casella amb la bandera.
		
		// Cas on destapem una mina (la casella SI que es destapa, però la partida acaba)
		r = new MockRandom(8, 7);
		t1 = new Tauler(r);
		t1.setMaxMines(1);
        joc = new Joc(t1);
        vista  = new MockBuscaminesVista(joc); //Creem un mock de la vista.
		vista.initVista(); //La vista és genera a partir del mock (no fa res)
		joc.crearVistaDelJoc(vista); 
		joc.clicEsquerra(0, 0); 
		joc.clicEsquerra(5, 5); // Destapem la casella 12,5 (casella amb mina, predefinida pel MockRandom)
		assertTrue(joc.isDestapat(12, 5)); // Comprovem que la casella s'ha destapat.
		assertTrue(joc.getPartidaAcabada()); // Comprovem que la partida s'ha acabat.
	}
	
	@Test
	public void simulacioPartida() {
		
		// Test final de la classe Joc, aquest Test utilitza el Mock de Tauler (per posar las mines on nosaltres volguem) i 
		// de la vista (per evitar que la vista real s'obri). Aquest test es basa en la lectura d'uns fitxers (simulacio 1 i 2)
		// que simulen una partida real del nostre buscamines, es van llegint linia a linia els fitxer i s'executen les funcions
		// clicEsquerra i clicDret segons el que indiqui el fitxer amb les coordenades. Al fnal es comprova si n'ha guanyat la partida
		// o si ha perdut depenet del fitxer llegit i per tant comprovem el correcte funcionament del joc al complet.
		// Utilitzem la funció totesDestapadesSenseMina per comprovar si ha guanyat o ha perdut en cada cas.
		
		Random r = new Random();
		Tauler t1 = new MockTauler(r);
		t1.generaMinesRandom(10, 0);
		Joc joc =  new Joc(t1);
		BuscaminesVista vista  = new MockBuscaminesVista(joc); //Creem un mock de la vista.
		vista.initVista(); //La vista és genera a partir del mock (no fa res)
		joc.crearVistaDelJoc(vista); 
		
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
		    
		    // Comprovar que ha guanyat la partida (simulacio 1):
		    assertTrue(t1.totesDestapadesSenseMines());

		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		r = new Random();
		t1 = new MockTauler(r);
		t1.generaMinesRandom(10, 0);
		joc =  new Joc(t1);
		vista  = new MockBuscaminesVista(joc); //Creem un mock de la vista.
		vista.initVista(); //La vista és genera a partir del mock (no fa res)
		joc.crearVistaDelJoc(vista);
		
		try (BufferedReader br = new BufferedReader(new FileReader("data/simulacio2.txt"))) {
		    String linea;

		    while ((linea = br.readLine()) != null) {
		        String[] parts = linea.split(" ");

		        String accion = parts[0];
		        int x = Integer.parseInt(parts[1]);
		        int y = Integer.parseInt(parts[2]);

		        if (accion.equals("esq")) joc.clicEsquerra(x, y);
		        else if (accion.equals("drt")) joc.clicDret(x, y);
		    }
		    
		    // Comprovar que ha perdut la partida (simulacio 2):
		    assertFalse(t1.totesDestapadesSenseMines());

		} catch (IOException e) {
		    e.printStackTrace();
		}
	}

}
