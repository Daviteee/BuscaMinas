package model;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;
import org.junit.jupiter.api.Test;

class TaulerTest {

	Tauler t1;
	Random r1;
	
	//Comentari d'aclaració: Cal tenir en compte que totes les funcions que tinguin límits del tauler, es a dir els getters de la classe Tauler com isMina,isBandera...
	//TOTS utilitzen el mètode getCasella per fer la comprovació dels límits Tauler, per això mateix no fa falta realitzar test sobre ells ja que el mètode que està provat
	//i testejat és getCasella i com està testejat ja sabem que comprova correctament els límits del tauler. Per tant com tots els mètodes anteriorment mencionats ho utlitzen al codi desenvolupat
	//per comprovar els límits, sabem que aquests límits es comproven correctament. L'únic mètode que no tuilitza getCasella al codi desenvolupat és generaMinesRandom per aquest mateix 
	//motiu en aquest mètode si es realitza la comprovació dels límits del tauler.
	
	//Realitzem una funció per llançar les excepcions del mètode getCasella de quan les coordenades no estan a dins del tauler
	private void llançarExcepcionsCasella(int x, int y){
		try{
			t1.getCasella(x, y);
			assertTrue(false);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
		
	//Realitzem una funció per llançar les excepcions del mètode generaMinesRandom de quan les coordenades del click no estan a dins del tauler
	private void llançarExcepcionsGeneraMines(int x, int y) {
		try{
			t1.generaMinesRandom(x, y);
			assertTrue(false);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	//Fem el mateix per els asserts de totes els mètodes anteriors
	private void assertsGetCasella(int x, int y) {
		assertTrue(t1.getCasella(x, y) instanceof Casella); //Simplement ha de retornar una instancia de la classe casella
	}
	
	//Mètode privat per comprovar si al tauler hi han 10 mines.
	private void te30Mines(){
		int n_mines = 0;
		for(int i=0;i<13;i++)
			for(int j=0;j<13;j++){
				if(t1.getCasella(i,j).isMina())
					n_mines ++ ;
				if(n_mines == 30) {
					assertTrue(true);
					break;
				}
			}
		
		if(n_mines < 30)
			assertTrue(false);
	}
	
	@Test
	void ConstructorInicialTaulerTest() {
		//Comrpovem que el tauler inicial s'inicialitza correctament amb totes les caselles corresponents sense mines, sense banderas i tapades.
		r1 = new Random();
		t1 = new Tauler(r1);
		for(int i=0; i < 13; i++)
			for(int j=0; j < 13; j++)
			{
				assertFalse(t1.isBandera(i,j));
				assertFalse(t1.isMina(i,j));
				assertFalse(t1.isDestapat(i,j));
			}		
	}
	
	@Test
	void getCasellaTest() {
		// Test del GetCasella, mètode que apartir de dos parametres d'entrada (x i y) retorna la casella corresponent
		// a la coordenada indicada pels paràmetres d'entrada dins del tauler.
		// I comprova que el mètode getCasella fa un control correcte dels limits del tauler.
		// Utilitzant testing amb particions equivalents, no es pot utilitzar Pairwise Testing ja que com el mètode
		// reb només 2 parametres no es possible realitzar el pairwise ja que el resultat són totes les combinacions.
		r1 = new Random();
		t1 = new Tauler(r1);
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
		r1 = new Random();
		t1 = new Tauler(r1);
		t1.generaMinesRandom(0,0);
		assertFalse(t1.isMina(0,0));
		assertFalse(t1.isMina(1,0));
		assertFalse(t1.isMina(0,1));
		assertFalse(t1.isMina(1,1));
		
		te30Mines();
		
		t1 = new Tauler(r1);
		
		t1.generaMinesRandom(0,12);
		assertFalse(t1.isMina(0,12));
		assertFalse(t1.isMina(1,12));
		assertFalse(t1.isMina(1,11));
		assertFalse(t1.isMina(0,11));

		te30Mines();
		
		t1 = new Tauler(r1);
		
		t1.generaMinesRandom(12,0);
		assertFalse(t1.isMina(12,0));
		assertFalse(t1.isMina(11,0));
		assertFalse(t1.isMina(12,1));
		assertFalse(t1.isMina(11,1));
		
		te30Mines();
		
		t1 = new Tauler(r1);
		
		t1.generaMinesRandom(12,12);
		assertFalse(t1.isMina(12,12));
		assertFalse(t1.isMina(11,11));
		assertFalse(t1.isMina(12,11));
		assertFalse(t1.isMina(11,12));
		
		te30Mines();
		
		t1 = new Tauler(r1);
		
		t1.generaMinesRandom(0,6);
		assertFalse(t1.isMina(0,6));
		assertFalse(t1.isMina(0,5));
		assertFalse(t1.isMina(0,7));
		assertFalse(t1.isMina(1,5));
		assertFalse(t1.isMina(1,6));
		assertFalse(t1.isMina(1,7));

		te30Mines();
		
		
		t1 = new Tauler(r1);
		
		t1.generaMinesRandom(6,0);
		assertFalse(t1.isMina(6,0));
		assertFalse(t1.isMina(5,0));
		assertFalse(t1.isMina(7,0));
		assertFalse(t1.isMina(5,1));
		assertFalse(t1.isMina(6,1));
		assertFalse(t1.isMina(7,1));

		te30Mines();
		
		t1 = new Tauler(r1);
		
		t1.generaMinesRandom(6,12);
		assertFalse(t1.isMina(6,12));
		assertFalse(t1.isMina(6,11));
		assertFalse(t1.isMina(5,12));
		assertFalse(t1.isMina(7,12));
		assertFalse(t1.isMina(5,11));
		assertFalse(t1.isMina(7,11));
		
		te30Mines();
		
		t1 = new Tauler(r1);
		
		t1.generaMinesRandom(12,6);
		assertFalse(t1.isMina(12,6));
		assertFalse(t1.isMina(11,6));
		assertFalse(t1.isMina(12,5));
		assertFalse(t1.isMina(12,7));
		assertFalse(t1.isMina(11,5));
		assertFalse(t1.isMina(11,7));
		
		te30Mines();
		
		t1 = new Tauler(r1);
		
		t1.generaMinesRandom(6,6);
		assertFalse(t1.isMina(6,6));
		assertFalse(t1.isMina(5,5));
		assertFalse(t1.isMina(5,6));
		assertFalse(t1.isMina(5,7));
		assertFalse(t1.isMina(6,5));
		assertFalse(t1.isMina(6,7));
		assertFalse(t1.isMina(7,5));
		assertFalse(t1.isMina(7,6));
		assertFalse(t1.isMina(7,7));

		te30Mines();
		
		//Casos de prova que llencen excepcions (fora dels limits del tauler):
		int []valors_x = new int [] {0,0,1,1,-1,-1,-1,-1,-1,-1,12,12,13,13,13,13,13,13,11,11,6,6,-1,5,13,7};
		int []valors_y = new int [] {-1,13,-1,13,0,1,-1,12,13,11,-1,13,0,1,12,13,11,6,-1,13,-1,13,5,-1,7,13};
		
		for(int i = 0; i < valors_x.length;i++)
			llançarExcepcionsGeneraMines(valors_x[i],valors_y[i]);
		
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
		
		// En aquest test la crida al mètode generaMinesRandom no té cap rellevància en el resultat del test però es necessàri per poder fer els següents
		// passos del test, per això la crida al mètode sempre envia les coordenades 0,0. Perque no influeix en el resultat.
		
		
		// Casella 0,0 (cantonada adalt esquerra):
		// Generem els següents casos de mines al voltant de la casella 0,0 a traves d'un bucle:
		for(int i=-1;i<3;i++) {
			r1 = new MockRandom(0,i);
			t1 = new Tauler(r1);
			t1.setMaxMines(i+1);
			t1.generaMinesRandom(11,0);
			t1.setNumMinesVoltant();
			assertEquals(i+1, t1.getNumMinesVoltant(0,0));
		}
		
		//Casella 6,0
		// Generem els següents casos de mines al voltant de la casella 6,0 a traves d'un bucle:
		for(int i=-1;i<5;i++) {
			r1 = new MockRandom(1,i);
			t1 = new Tauler(r1);
			t1.setMaxMines(i+1);
			t1.generaMinesRandom(11,0);
			t1.setNumMinesVoltant();
			assertEquals(i+1, t1.getNumMinesVoltant(6,0));
		}
		
		//Casella 12,0
		// Generem els següents casos de mines al voltant de la casella 12,0 a traves d'un bucle:
		for(int i=-1;i<3;i++) {
			r1 = new MockRandom(2,i);
			t1 = new Tauler(r1);
			t1.setMaxMines(i+1);
			t1.generaMinesRandom(0,0);
			t1.setNumMinesVoltant();
			assertEquals(i+1, t1.getNumMinesVoltant(12,0));
		}
		
		// Casella 12,6
		// Generem els següents casos de mines al voltant de la casella 12,6 a traves d'un bucle:
		for(int i=-1;i<5;i++) {
			r1 = new MockRandom(3,i);
			t1 = new Tauler(r1);
			t1.setMaxMines(i+1);
			t1.generaMinesRandom(0,0);
			t1.setNumMinesVoltant();
			assertEquals(i+1, t1.getNumMinesVoltant(12,6));
		}
		
		// Casella 12,12
		// Generem els següents casos de mines al voltant de la casella 12,12 a traves d'un bucle:
		for(int i=-1;i<3;i++) {
			r1 = new MockRandom(4,i);
			t1 = new Tauler(r1);
			t1.setMaxMines(i+1);
			t1.generaMinesRandom(0,0);
			t1.setNumMinesVoltant();
			assertEquals(i+1, t1.getNumMinesVoltant(12,12));
		}
		

		// Casella 6,12
		// Generem els següents casos de mines al voltant de la casella 6,12 a traves d'un bucle:
		for(int i=-1;i<5;i++) {
			r1 = new MockRandom(5,i);
			t1 = new Tauler(r1);
			t1.setMaxMines(i+1);
			t1.generaMinesRandom(0,0);
			t1.setNumMinesVoltant();
			assertEquals(i+1, t1.getNumMinesVoltant(6,12));
		}
		
		// Casella 0,12
		// Generem els següents casos de mines al voltant de la casella 0,12 a traves d'un bucle:
		for(int i=-1;i<3;i++) {
			r1 = new MockRandom(6,i);
			t1 = new Tauler(r1);
			t1.setMaxMines(i+1);
			t1.generaMinesRandom(0,0);
			t1.setNumMinesVoltant();
			assertEquals(i+1, t1.getNumMinesVoltant(0,12));
		}
		
		// Casella 0,6
		// Generem els següents casos de mines al voltant de la casella 0,6 a traves d'un bucle:
		for(int i=-1;i<5;i++) {
			r1 = new MockRandom(7,i);
			t1 = new Tauler(r1);
			t1.setMaxMines(i+1);
			t1.generaMinesRandom(0,0);
			t1.setNumMinesVoltant();
			assertEquals(i+1, t1.getNumMinesVoltant(0,6));
		}
		
		// Casella 6,6
		// Generem els següents casos de mines al voltant de la casella 6,6 a traves d'un bucle:
		for(int i=-1;i<8;i++) {
			r1 = new MockRandom(8,i);
			t1 = new Tauler(r1);
			t1.setMaxMines(i+1);
			t1.generaMinesRandom(0,0);
			t1.setNumMinesVoltant();
			assertEquals(i+1, t1.getNumMinesVoltant(6,6));
		}
	}
		
	
	@Test
	void destapaCasellaTest() {
		// Test que comprova que una casella ha estat destapada correctament, segons les circumstancies hi ha caselles que no s'han de destapar
		// o que el seu destapament ha de provocar el destapament d'altres caselles adjacents. Per això utilitzarem un Mock de Random.
		r1 = new MockRandom(0,0); //Posem una mina a la posció 0,1.
		t1 = new Tauler(r1); 
		t1.setMaxMines(1);
		t1.generaMinesRandom(12, 12); //Cliquem lluny per a que no doni error a la funció generaMinesRandom
		t1.setNumMinesVoltant(); //Afegim els números al tauler.
		t1.destapaCasella(1, 1); // Destapem la casella 1,1
		assertTrue(t1.isDestapat(1, 1)); // Comprovem que ha sigut destapada
		
		// Comprovació de que amb 2 mines destapa les del voltant d'una casella amb 0 mines al voltant.
		r1 = new MockRandom(1,1); //Posem dues mines a les posicions 5,0 i 5,1
		t1 = new Tauler(r1);
		t1.setMaxMines(2);
		t1.generaMinesRandom(12, 12); //Cliquem lluny per a que no doni error a la funció generaMinesRandom
		t1.setNumMinesVoltant(); //Afegim els números al tauler.
		t1.destapaCasella(0, 0); // Destapem la casella 0,0 (hauría de tenir el número 0)
		
		// Caselles que haurien d'estar destapades per l'algorisme:
		assertTrue(t1.isDestapat(4,0));
        assertTrue(t1.isDestapat(4,1));
        assertTrue(t1.isDestapat(4,2));
        assertTrue(t1.isDestapat(5,2));
        assertTrue(t1.isDestapat(6,0));
        assertTrue(t1.isDestapat(6,1));
        assertTrue(t1.isDestapat(6,2));
        assertFalse(t1.isDestapat(5,0));
        assertFalse(t1.isDestapat(5,1));

		// Comprovació de que si no posem cap mina al tauler es destapa tot sencer:
		t1 = new Tauler(r1);	
		t1.destapaCasella(0, 0);
		for(int i = 0; i < 13; i++) {
			for(int j = 0; j < 13; j++) {
				assertTrue(t1.isDestapat(i, j));
			}
		}
		//Comprovació de que no es destapa una casella amb una bandera
		r1 = new Random();
		t1 = new Tauler(r1);
		t1.changeBandera(0, 0);
		t1.changeBandera(0, 1);
		t1.destapaCasella(0, 0);
		assertFalse(t1.isDestapat(0,0));
		t1.destapaCasella(0,1);
        assertFalse(t1.isDestapat(0,1));
		t1.destapaCasella(1,0);
        assertTrue(t1.isDestapat(1,0));
        //Tornem a destapar una casella que ja esta destapada no hauria de passar res.
		t1.destapaCasella(1,0);
        assertTrue(t1.isDestapat(1,0));
        
        //Destapar casella amb número > 0 → no expandir
        r1 = new MockRandom(0,0); // Mina a 0,1
        t1 = new Tauler(r1);
        t1.setMaxMines(1);
        t1.generaMinesRandom(12, 12);
        t1.setNumMinesVoltant();
        t1.destapaCasella(0,0); // 0,0 té número 1 al costat de la mina

        assertTrue(t1.isDestapat(0,0));
        assertFalse(t1.isDestapat(0,2));
        assertFalse(t1.isDestapat(1,0));
        assertFalse(t1.isDestapat(1,1));
        
        //Destapar una mina → només destapa la mina
        r1 = new MockRandom(0,0); // Mina a 0,1
        t1 = new Tauler(r1);
        t1.setMaxMines(1);
        t1.generaMinesRandom(12, 12);
        t1.destapaCasella(0,1); // Mina
        assertTrue(t1.isDestapat(0,1));
        assertFalse(t1.isDestapat(0,0)); // no ha d’afectar veïns
        assertFalse(t1.isDestapat(1,1));
        
        //Expansió de zeros amb barrera de números
        r1 = new MockRandom(1,1); // Mines en 5,0 i 5,1
        t1 = new Tauler(r1);
        t1.setMaxMines(2);
        t1.generaMinesRandom(12,12);
        t1.setNumMinesVoltant();
        t1.destapaCasella(12,12); // zona llunyana sense mines

        assertTrue(t1.isDestapat(12,12));
        assertTrue(t1.isDestapat(11,12));
        assertTrue(t1.isDestapat(12,11));
        // Les caselles amb números al voltant de mines no s’expandeixen més
        assertFalse(t1.isDestapat(5,0));
        assertFalse(t1.isDestapat(5,1));

        //Bandera talla expansió
        r1 = new MockRandom(1,1); // Mines en 5,0 i 5,1
        t1 = new Tauler(r1);
        t1.changeBandera(11,11);
        t1.destapaCasella(12,12);
        assertTrue(t1.isDestapat(12,12));
        assertFalse(t1.isDestapat(11,11)); // bandera talla expansió
	}
	
	@Test
	public void totesDestapadesSenseMinesTest() {
		r1 = new Random();
		t1 = new Tauler(r1);
		assertFalse(t1.totesDestapadesSenseMines()); // Inicialment cal que cap estigui destapada.
		t1.destapaCasella(0, 0); // Destapem una casella i com no te cap mina el tauler es destapen totes.
		assertTrue(t1.totesDestapadesSenseMines()); // Després de destaparles totes sense mines cal que ens retorni true.
	}
	
}
