

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
class TaulerTest {

	Tauler t1;
	
	//Realitzem una funció per llançar les excepcions del mètode getCasella
	private void llançarExcepcionsCasella(int x, int y)
	{
		try{
			t1.getCasella(x, y);
			assertTrue(false);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	

	
	private void llançarExcepcionsGeneraMines(int x, int y) {
		try{
			t1.generaMinesRandom(x, y);
			assertTrue(false);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	private void llançarExcepcionsChangeBandera(int x, int y) {
		try{
			t1.changeBandera(x, y);
			assertTrue(false);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	//Fem el mateix per els asserts
	private void assertsGetCasella(int x, int y) {
		assertTrue(t1.getCasella(x, y) instanceof Casella);
	}
	

	private void assertsChangeBandera(int x, int y) {
		t1.changeBandera(x, y); //Posem Bandera.
		assertTrue(t1.getCasella(x, y).isBandera());
		
		t1.getCasella(x, y).changeBandera(); //Treiem Bandera.
		assertFalse(t1.getCasella(x, y).isBandera());
	}
	
	//Mètode privat per comprovar si al tauler hi han 10 mines.
	private void te10Mines(){
		int n_mines = 0;
		for(int i=0;i<13;i++)
			for(int j=0;j<13;j++){
				if(t1.getCasella(i,j).isMina())
					n_mines ++ ;
				if(n_mines == 10) {
					assertTrue(true);
					break;
				}
			}
		
		if(n_mines < 10)
			assertTrue(false);
	}
	
	
	@Test
	void ConstructorInicialTaulerTest() {
		//Comrpovem que el tauler inicial s'inicialitza correctament amb totes les caselles corresponents sense mines, sense banderas i tapades.
		t1 = new Tauler();
		for(int i=0; i < 13; i++)
			for(int j=0; j < 13; j++)
			{
				assertFalse(t1.getCasella(i,j).isBandera());
				assertFalse(t1.getCasella(i,j).isMina());
				assertFalse(t1.getCasella(i,j).isDestapat());
			}		
	}
	
	
	
	@Test
	void getCasellaTest() {
		// Test del GetCasella, mètode que apartir de dos parametres d'entrada (x i y) retorna la casella corresponent
		// a la coordenada indicada pels paràmetres d'entrada dins del tauler.
		// I comprova que el mètode getCasella fa un control correcte dels limits del tauler.
		// Utilitzant testing amb particions equivalents, no es pot utilitzar Pairwise Testing ja que com el mètode
		// reb només 2 parametres no es possible realitzar el pairwise ja que el resultat són totes les combinacions.
		t1 = new Tauler();
		int []valors_x = new int [] {0,0,0,0,0,1,1,1,1,12,12,12,12,12,11,11,11,11,11,6,6,6,6,6};
		int []valors_y = new int [] {0,1,12,11,6,0,1,12,11,6,0,1,12,11,6,0,1,12,11,6,0,1,12,11};
		
		for(int i = 0; i < valors_x.length;i++)
				assertsGetCasella(valors_x[i],valors_y[i]);
		
		valors_x = new int [] {0,0,1,1,-1,-1,-1,-1,-1,-1,12,12,13,13,13,13,13,13,11,11,6,6,-1,5,13,7};
		valors_y = new int [] {-1,13,-1,13,0,1,-1,12,13,11,-1,13,0,1,12,13,11,6,-1,13,-1,13,5,-1,7,13};
				
		for(int i = 0; i < valors_x.length;i++)
			llançarExcepcionsCasella(valors_x[i],valors_y[i]);
				
	}
	
		
	
	@Test
	void generaMinesRandomTest() {
		t1 = new Tauler();
		t1.generaMinesRandom(0,0);
		assertFalse(t1.getCasella(0, 0).isMina());
		assertFalse(t1.getCasella(1, 0).isMina());
		assertFalse(t1.getCasella(0, 1).isMina());
		assertFalse(t1.getCasella(1, 1).isMina());
		
		te10Mines();
		
		t1 = new Tauler();
		
		t1.generaMinesRandom(0,12);
		assertFalse(t1.getCasella(0, 12).isMina());
		assertFalse(t1.getCasella(1, 12).isMina());
		assertFalse(t1.getCasella(1, 11).isMina());
		assertFalse(t1.getCasella(0, 11).isMina());

		te10Mines();
		
		t1 = new Tauler();
		
		t1.generaMinesRandom(12,0);
		assertFalse(t1.getCasella(12, 0).isMina());
		assertFalse(t1.getCasella(11, 0).isMina());
		assertFalse(t1.getCasella(12, 1).isMina());
		assertFalse(t1.getCasella(11, 1).isMina());
		
		te10Mines();
		
		t1 = new Tauler();
		
		t1.generaMinesRandom(12,12);
		assertFalse(t1.getCasella(12, 12).isMina());
		assertFalse(t1.getCasella(11, 11).isMina());
		assertFalse(t1.getCasella(12, 11).isMina());
		assertFalse(t1.getCasella(11, 12).isMina());
		
		te10Mines();
		
		t1 = new Tauler();
		
		t1.generaMinesRandom(0,6);
		assertFalse(t1.getCasella(0, 6).isMina());
		assertFalse(t1.getCasella(0, 5).isMina());
		assertFalse(t1.getCasella(0, 7).isMina());
		assertFalse(t1.getCasella(1, 5).isMina());
		assertFalse(t1.getCasella(1, 6).isMina());
		assertFalse(t1.getCasella(1, 7).isMina());

		te10Mines();
		
		
		t1 = new Tauler();
		
		t1.generaMinesRandom(6,0);
		assertFalse(t1.getCasella(6, 0).isMina());
		assertFalse(t1.getCasella(5, 0).isMina());
		assertFalse(t1.getCasella(7, 0).isMina());
		assertFalse(t1.getCasella(5, 1).isMina());
		assertFalse(t1.getCasella(6, 1).isMina());
		assertFalse(t1.getCasella(7, 1).isMina());

		te10Mines();
		
		t1 = new Tauler();
		
		t1.generaMinesRandom(6,12);
		assertFalse(t1.getCasella(6, 12).isMina());
		assertFalse(t1.getCasella(6, 11).isMina());
		assertFalse(t1.getCasella(5, 12).isMina());
		assertFalse(t1.getCasella(7, 12).isMina());
		assertFalse(t1.getCasella(5, 11).isMina());
		assertFalse(t1.getCasella(7, 11).isMina());
		
		te10Mines();
		
		t1 = new Tauler();
		
		t1.generaMinesRandom(12,6);
		assertFalse(t1.getCasella(12,6).isMina());
		assertFalse(t1.getCasella(11, 6).isMina());
		assertFalse(t1.getCasella(12, 5).isMina());
		assertFalse(t1.getCasella(12, 7).isMina());
		assertFalse(t1.getCasella(11, 5).isMina());
		assertFalse(t1.getCasella(11, 7).isMina());
		
		te10Mines();
		
		t1 = new Tauler();
		
		t1.generaMinesRandom(6,6);
		assertFalse(t1.getCasella(6,6).isMina());
		assertFalse(t1.getCasella(5, 5).isMina());
		assertFalse(t1.getCasella(5, 6).isMina());
		assertFalse(t1.getCasella(5, 7).isMina());
		assertFalse(t1.getCasella(6, 5).isMina());
		assertFalse(t1.getCasella(6, 7).isMina());
		assertFalse(t1.getCasella(7, 5).isMina());
		assertFalse(t1.getCasella(7, 6).isMina());
		assertFalse(t1.getCasella(7, 7).isMina());

		te10Mines();
		
		//Casos de prova que llencen excepcions (fora dels limits del tauler):
		int []valors_x = new int [] {0,0,1,1,-1,-1,-1,-1,-1,-1,12,12,13,13,13,13,13,13,11,11,6,6,-1,5,13,7};
		int []valors_y = new int [] {-1,13,-1,13,0,1,-1,12,13,11,-1,13,0,1,12,13,11,6,-1,13,-1,13,5,-1,7,13};
		
		for(int i = 0; i < valors_x.length;i++)
			llançarExcepcionsGeneraMines(valors_x[i],valors_y[i]);
		
	}
	
	@Test
	void changeBanderaTest() {
		//Test que comprova que una bandera es col·loca correctament al tauler i a més a més que fa un control dels límits del tauler.
		t1 = new Tauler();
		int []valors_x = new int [] {0,0,0,0,0,1,1,1,1,12,12,12,12,12,11,11,11,11,11,6,6,6,6,6};
		int []valors_y = new int [] {0,1,12,11,6,0,1,12,11,6,0,1,12,11,6,0,1,12,11,6,0,1,12,11};
		for(int i = 0; i < valors_x.length;i++)
			assertsChangeBandera(valors_x[i],valors_y[i]);
		
		// Valors que han de llançar excepció
		valors_x = new int [] {0,0,1,1,-1,-1,-1,-1,-1,-1,12,12,13,13,13,13,13,13,11,11,6,6,-1,5,13,7};
		valors_y = new int [] {-1,13,-1,13,0,1,-1,12,13,11,-1,13,0,1,12,13,11,6,-1,13,-1,13,5,-1,7,13};
		
		for(int i = 0; i < valors_x.length;i++)
			llançarExcepcionsChangeBandera(valors_x[i],valors_y[i]);
		
	}
	
	@Test
	void setNumMinesVoltantTest() {
		// Test que comprova el numero de mines al voltant de cada casella (casos especials i regulars) sigui correctament generat.
		// Caselles (0,0), (6,0), (12,0), (12,6), (12,12), (6,12), (0,12), (0,6) i (6,6).
		// Amb aquests casos tindrem cantonades, laterals i casella central del tauler comprobats amb tots els nombres de mines possibles.
		
		// El numero de mines pot ser: 0, 1, 2, 3, 4, 5, 6, 7 i 8.
		// Depenent de la casella (cantonades max 3, laterals max 5 i qualsevol altre max 8).
		
		// Particions equivalents: Per les cantonades comprovem els valors frontera (0 i 3) i valors limit interiors (1 i 2).
		// Per els laterals comprovem valors frontera (0 i 5), valors limit interiors (1 i 4) i un del mig (2 o 3)
		// Per les caselles amb 8 caselles adjacents comprovem valors frontera (0 i 8), valors limits interiors (1 i 7) i un del mig (2, 3, 4, 5 o 6)
		// No comprovem valors com el -1 perque es impossible que el comptador de mines al voltant generi aquest tipus de valor.
		
		// Casella 0,0 (cantonada adalt esquerra):
		// 0 mines:
		t1 = new MockTauler(); // Utilitzem el mock per realitzar el tauler predeterminat.
		t1.generaMinesRandom(1, 0); // Cas 1-0.
		t1.setNumMinesVoltant();
		assertEquals(0,t1.getCasella(0, 0).getNumMinesVoltant());
		// 1 mina:
		t1 = new MockTauler();
		t1.generaMinesRandom(1, 1); // Cas 1-1
		t1.setNumMinesVoltant();
		assertEquals(1, t1.getCasella(0, 0).getNumMinesVoltant());
		// 2 mines:
		t1 = new MockTauler();
		t1.generaMinesRandom(1, 2); // Cas 1-2
		t1.setNumMinesVoltant();
		assertEquals(2, t1.getCasella(0, 0).getNumMinesVoltant());
		// 3 mines:
		t1 = new MockTauler();
		t1.generaMinesRandom(1, 3); // Cas 1-3
		t1.setNumMinesVoltant();
		assertEquals(3, t1.getCasella(0, 0).getNumMinesVoltant());
		
		// Casella 6,0 (lateral esquerre):
		// 0 mines: (valor fontera)
		t1 = new MockTauler();
		t1.generaMinesRandom(2, 0); // Cas 2-0
		t1.setNumMinesVoltant();
		assertEquals(0, t1.getCasella(6, 0).getNumMinesVoltant());
		// 1 mina: (valor limit interor)
		t1 = new MockTauler();
		t1.generaMinesRandom(2, 1); // Cas 2-1
		t1.setNumMinesVoltant();
		assertEquals(1, t1.getCasella(6, 0).getNumMinesVoltant());
		// 2 mines: (valor del mig de la partició)
		t1 = new MockTauler();
		t1.generaMinesRandom(2, 2); // Cas 2-2
		t1.setNumMinesVoltant();
		assertEquals(2, t1.getCasella(6, 0).getNumMinesVoltant());
		// 4 mines: (valor limit interior)
		t1 = new MockTauler();
		t1.generaMinesRandom(2, 4); // Cas 2-4
		t1.setNumMinesVoltant();
		assertEquals(4, t1.getCasella(6, 0).getNumMinesVoltant());
		// 5 mines: (valor frontera)
		t1 = new MockTauler();
		t1.generaMinesRandom(2, 5); // Cas 2-5
		t1.setNumMinesVoltant();
		assertEquals(5, t1.getCasella(6, 0).getNumMinesVoltant());
		
		// Casella 12,0 (cantonada abaix esquerra):
		// 0 mines: (valor frontera)
		t1 = new MockTauler(); //Utilitzem el mock per realitzar el tauler predeterminat.
		t1.generaMinesRandom(3, 0); // Cas 1-0.
		t1.setNumMinesVoltant();
		assertEquals(0,t1.getCasella(12, 0).getNumMinesVoltant());
		// 1 mina: (valor limit interior)
		t1 = new MockTauler();
		t1.generaMinesRandom(3, 1); // Cas 1-1
		t1.setNumMinesVoltant();
		assertEquals(1, t1.getCasella(12, 0).getNumMinesVoltant());
		// 2 mines: (valor limit interior)
		t1 = new MockTauler();
		t1.generaMinesRandom(3, 2); // Cas 1-2
		t1.setNumMinesVoltant();
		assertEquals(2, t1.getCasella(12, 0).getNumMinesVoltant());
		// 3 mines: (valor frontera)
		t1 = new MockTauler();
		t1.generaMinesRandom(3, 3); // Cas 1-3
		t1.setNumMinesVoltant();
		assertEquals(3, t1.getCasella(12, 0).getNumMinesVoltant());
		
		// Casella 12,6 (lateral abaix):
		// 0 mines: (valor fontera)
		t1 = new MockTauler();
		t1.generaMinesRandom(4, 0); // Cas 4-0
		t1.setNumMinesVoltant();
		assertEquals(0, t1.getCasella(12, 6).getNumMinesVoltant());
		// 1 mina: (valor limit interor)
		t1 = new MockTauler();
		t1.generaMinesRandom(4, 1); // Cas 4-1
		t1.setNumMinesVoltant();
		assertEquals(1, t1.getCasella(12, 6).getNumMinesVoltant());
		// 3 mines: (valor del mig de la partició)
		t1 = new MockTauler();
		t1.generaMinesRandom(4, 3); // Cas 4-3
		t1.setNumMinesVoltant();
		assertEquals(3, t1.getCasella(12, 6).getNumMinesVoltant());
		// 4 mines: (valor limit interior)
		t1 = new MockTauler();
		t1.generaMinesRandom(4, 4); // Cas 4-4
		t1.setNumMinesVoltant();
		assertEquals(4, t1.getCasella(12, 6).getNumMinesVoltant());
		// 5 mines: (valor frontera)
		t1 = new MockTauler();
		t1.generaMinesRandom(4, 5); // Cas 4-5
		t1.setNumMinesVoltant();
		assertEquals(5, t1.getCasella(12, 6).getNumMinesVoltant());
		
		// Casella 12,12 (cantonada abaix dreta):
		// 0 mines: (valor frontera)
		t1 = new MockTauler();
		t1.generaMinesRandom(5, 0); // Cas 5-0.
		t1.setNumMinesVoltant();
		assertEquals(0,t1.getCasella(12, 12).getNumMinesVoltant());
		// 1 mina: (valor limit interior)
		t1 = new MockTauler();
		t1.generaMinesRandom(5, 1); // Cas 5-1
		t1.setNumMinesVoltant();
		assertEquals(1, t1.getCasella(12, 12).getNumMinesVoltant());
		// 2 mines: (valor limit interior)
		t1 = new MockTauler();
		t1.generaMinesRandom(5, 2); // Cas 5-2
		t1.setNumMinesVoltant();
		assertEquals(2, t1.getCasella(12, 12).getNumMinesVoltant());
		// 3 mines: (valor frontera)
		t1 = new MockTauler();
		t1.generaMinesRandom(5, 3); // Cas 5-3
		t1.setNumMinesVoltant();
		assertEquals(3, t1.getCasella(12, 12).getNumMinesVoltant());
		
		// Casella 6,12 (lateral abaix):
		// 0 mines: (valor fontera)
		t1 = new MockTauler();
		t1.generaMinesRandom(6, 0); // Cas 6-0
		t1.setNumMinesVoltant();
		assertEquals(0, t1.getCasella(6, 12).getNumMinesVoltant());
		// 1 mina: (valor limit interor)
		t1 = new MockTauler();
		t1.generaMinesRandom(6, 1); // Cas 6-1
		t1.setNumMinesVoltant();
		assertEquals(1, t1.getCasella(6, 12).getNumMinesVoltant());
		// 3 mines: (valor del mig de la partició)
		t1 = new MockTauler();
		t1.generaMinesRandom(6, 2); // Cas 6-2
		t1.setNumMinesVoltant();
		assertEquals(2, t1.getCasella(6, 12).getNumMinesVoltant());
		// 4 mines: (valor limit interior)
		t1 = new MockTauler();
		t1.generaMinesRandom(6, 4); // Cas 6-4
		t1.setNumMinesVoltant();
		assertEquals(4, t1.getCasella(6, 12).getNumMinesVoltant());
		// 5 mines: (valor frontera)
		t1 = new MockTauler();
		t1.generaMinesRandom(6, 5); // Cas 6-5
		t1.setNumMinesVoltant();
		assertEquals(5, t1.getCasella(6, 12).getNumMinesVoltant());
		
		// Casella 0,12 (cantonada adalt dreta):
		// 0 mines: (valor frontera)
		t1 = new MockTauler();
		t1.generaMinesRandom(7, 0); // Cas 7-0.
		t1.setNumMinesVoltant();
		assertEquals(0,t1.getCasella(0, 12).getNumMinesVoltant());
		// 1 mina: (valor limit interior)
		t1 = new MockTauler();
		t1.generaMinesRandom(7, 1); // Cas 7-1
		t1.setNumMinesVoltant();
		assertEquals(1, t1.getCasella(0, 12).getNumMinesVoltant());
		// 2 mines: (valor limit interior)
		t1 = new MockTauler();
		t1.generaMinesRandom(7, 2); // Cas 7-2
		t1.setNumMinesVoltant();
		assertEquals(2, t1.getCasella(0, 12).getNumMinesVoltant());
		// 3 mines: (valor frontera)
		t1 = new MockTauler();
		t1.generaMinesRandom(7, 3); // Cas 7-3
		t1.setNumMinesVoltant();
		assertEquals(3, t1.getCasella(0, 12).getNumMinesVoltant());
		
		// Casella 0,6 (lateral adalt):
		// 0 mines: (valor fontera)
		t1 = new MockTauler();
		t1.generaMinesRandom(8, 0); // Cas 8-0
		t1.setNumMinesVoltant();
		assertEquals(0, t1.getCasella(0, 6).getNumMinesVoltant());
		// 1 mina: (valor limit interor)
		t1 = new MockTauler();
		t1.generaMinesRandom(8, 1); // Cas 8-1
		t1.setNumMinesVoltant();
		assertEquals(1, t1.getCasella(0, 6).getNumMinesVoltant());
		// 3 mines: (valor del mig de la partició)
		t1 = new MockTauler();
		t1.generaMinesRandom(8, 3); // Cas 8-3
		t1.setNumMinesVoltant();
		assertEquals(3, t1.getCasella(0, 6).getNumMinesVoltant());
		// 4 mines: (valor limit interior)
		t1 = new MockTauler();
		t1.generaMinesRandom(8, 4); // Cas 8-4
		t1.setNumMinesVoltant();
		assertEquals(4, t1.getCasella(0, 6).getNumMinesVoltant());
		// 5 mines: (valor frontera)
		t1 = new MockTauler();
		t1.generaMinesRandom(8, 5); // Cas 8-5
		t1.setNumMinesVoltant();
		assertEquals(5, t1.getCasella(0, 6).getNumMinesVoltant());
		
		// Casella 6,6 (casella central):
		// 0 mines: (valor fontera)
		t1 = new MockTauler();
		t1.generaMinesRandom(9, 0); // Cas 9-0
		t1.setNumMinesVoltant();
		assertEquals(0, t1.getCasella(6, 6).getNumMinesVoltant());
		// 1 mina: (valor limit interor)
		t1 = new MockTauler();
		t1.generaMinesRandom(9, 1); // Cas 9-1
		t1.setNumMinesVoltant();
		assertEquals(1, t1.getCasella(6, 6).getNumMinesVoltant());
		// 5 mines: (valor del mig de la partició))
		t1 = new MockTauler();
		t1.generaMinesRandom(9, 5); // Cas 9-5
		t1.setNumMinesVoltant();
		assertEquals(5, t1.getCasella(6, 6).getNumMinesVoltant());
		// 7 mines: (valor limit interior)
		t1 = new MockTauler();
		t1.generaMinesRandom(9, 7); // Cas 9-7
		t1.setNumMinesVoltant();
		assertEquals(7, t1.getCasella(6, 6).getNumMinesVoltant());
		// 8 mines: (valor frontera)
		t1 = new MockTauler();
		t1.generaMinesRandom(9, 8); // Cas 9-8
		t1.setNumMinesVoltant();
		assertEquals(8, t1.getCasella(6, 6).getNumMinesVoltant());
	}
	
	@Test
	void destapaCasellaTest() {
		// Test que comprova que una casella ha estat destapada correctament, segons les circumstancies hi ha caselles que no s'han de destapar
		// o que el seu destapament ha de provocar el destapament d'altres caselles adjacents.
		
		t1 = new MockTauler(); // Instancies el Tauler Mock per posar mines on volguem
		t1.generaMinesRandom(1, 1); // Posem 1 mina a la posició 0,1
		t1.setNumMinesVoltant();
		assertEquals(1, t1.getCasella(1, 1).getNumMinesVoltant()); // Comprovem que el numero de mines es correcte (1)
		t1.destapaCasella(1, 1); // Destapem la casella 1,1
		assertTrue(t1.getCasella(1, 1).isDestapat()); // Comprovem que ha sigut destapada
	}
	
}
