
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
	
	private void llançarExcepcionsSetMina(int x, int y)
	{
		try{
			t1.setMina(x, y);
			assertTrue(false);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	//Fem el mateix per els asserts
	private void assertsGetCasella(int x, int y) {
		assertTrue(t1.getCasella(x, y) instanceof Casella);
	}
	
	private void assertsSetMina(int x, int y) {
		t1.setMina(x, y);
		assertTrue(t1.getCasella(x,y).isMina());
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
	
	@BeforeEach
	void setUp() {
		t1 = new Tauler();
	}
	@Test
	void ConstructorInicialTaulerTest() {
		//Comrpovem que el tauler inicial s'inicialitza correctament amb totes les caselles corresponents sense mines, sense banderas i tapades.
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
	void setMinaTest() {
		//Test que comprova que una mina es col·loca correctament al tauler i a més a més que fa un control dels límits del tauler.
		int []valors_x = new int [] {0,0,0,0,0,1,1,1,1,12,12,12,12,12,11,11,11,11,11,6,6,6,6,6};
		int []valors_y = new int [] {0,1,12,11,6,0,1,12,11,6,0,1,12,11,6,0,1,12,11,6,0,1,12,11};
		for(int i = 0; i < valors_x.length;i++)
			assertsSetMina(valors_x[i],valors_y[i]);
		
		valors_x = new int [] {0,0,1,1,-1,-1,-1,-1,-1,-1,12,12,13,13,13,13,13,13,11,11,6,6,-1,5,13,7};
		valors_y = new int [] {-1,13,-1,13,0,1,-1,12,13,11,-1,13,0,1,12,13,11,6,-1,13,-1,13,5,-1,7,13};
		
		for(int i = 0; i < valors_x.length;i++)
			llançarExcepcionsSetMina(valors_x[i],valors_y[i]);
		
	}
	
	
	
	@Test
	void generaMinesRandomTest() {
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
		
	}
	
}
