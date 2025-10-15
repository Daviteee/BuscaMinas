
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
class TaulerTest {
 
	Tauler t1;
	
	@BeforeEach
	void setUp()
	{
		t1 = new Tauler();
	}
	@Test
	void ConstructorInicialTaulerTest()
	{
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
	void getCasellaTest()
	{
		// Test del GetCasella, mètode que apartir de dos parametres d'entrada (x i y) retorna la casella corresponent
		// a la coordenada indicada per els parametres d'entrada dins del tauler.
		// I comprova que el mètode getCasella fa un control correcte dels limits del tauler.
		// Utilitzant testing amb particions equivalents, no es pot utilitzar Pairwise Testing ja que com el mètode
		// reb només 2 parametres no es possible realitzar el pairwise ja que el resultat són totes les combinacions.
		
		assertTrue(t1.getCasella(0, 0) instanceof Casella);
		assertTrue(t1.getCasella(0, 1) instanceof Casella);
		assertTrue(t1.getCasella(0, 12) instanceof Casella);
		assertTrue(t1.getCasella(0, 11) instanceof Casella);
		assertTrue(t1.getCasella(0, 6) instanceof Casella);
		
		assertTrue(t1.getCasella(1, 0) instanceof Casella);
		assertTrue(t1.getCasella(1, 1) instanceof Casella);
		assertTrue(t1.getCasella(1, 12) instanceof Casella);
		assertTrue(t1.getCasella(1, 11) instanceof Casella);
		assertTrue(t1.getCasella(1, 6) instanceof Casella);
		
		assertTrue(t1.getCasella(12, 0) instanceof Casella);
		assertTrue(t1.getCasella(12, 1) instanceof Casella);
		assertTrue(t1.getCasella(12, 12) instanceof Casella);
		assertTrue(t1.getCasella(12, 11) instanceof Casella);
		assertTrue(t1.getCasella(12, 6) instanceof Casella);
		
		assertTrue(t1.getCasella(11, 0) instanceof Casella);
		assertTrue(t1.getCasella(11, 1) instanceof Casella);
		assertTrue(t1.getCasella(11, 12) instanceof Casella);
		assertTrue(t1.getCasella(11, 11) instanceof Casella);
		assertTrue(t1.getCasella(11, 6) instanceof Casella);
		
		assertTrue(t1.getCasella(6, 0) instanceof Casella);
		assertTrue(t1.getCasella(6, 1) instanceof Casella);
		assertTrue(t1.getCasella(6, 12) instanceof Casella);
		assertTrue(t1.getCasella(6, 11) instanceof Casella);
		assertTrue(t1.getCasella(6, 6) instanceof Casella);
		
		
		try{
			t1.getCasella(0, -1);
			assertTrue(false);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
		try{
			t1.getCasella(0, 13);
			assertTrue(false);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
		try{
			t1.getCasella(1, -1);
			assertTrue(false);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
		try{
			t1.getCasella(1, 13);
			assertTrue(false);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
		try{
			t1.getCasella(-1, 0);
			assertTrue(false);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
		try{
			t1.getCasella(-1, 1);
			assertTrue(false);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
		try{
			t1.getCasella(-1, -1);
			assertTrue(false);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
		try{
			t1.getCasella(-1, 12);
			assertTrue(false);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
		try{
			t1.getCasella(-1, 13);
			assertTrue(false);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
		try{
			t1.getCasella(-1, 11);
			assertTrue(false);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
		try{
			t1.getCasella(12, -1);
			assertTrue(false);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
		try{
			t1.getCasella(12, 13);
			assertTrue(false);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
		try{
			t1.getCasella(13, 0);
			assertTrue(false);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
		try{
			t1.getCasella(13, 1);
			assertTrue(false);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
		try{
			t1.getCasella(13, 12);
			assertTrue(false);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
		try{
			t1.getCasella(13, 13);
			assertTrue(false);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
		try{
			t1.getCasella(13, 11);
			assertTrue(false);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
		try{
			t1.getCasella(13, 6);
			assertTrue(false);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
		try{
			t1.getCasella(11, -1);
			assertTrue(false);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
		try{
			t1.getCasella(11, 13);
			assertTrue(false);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
		try{
			t1.getCasella(6, -1);
			assertTrue(false);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
		try{
			t1.getCasella(6, 13);
			assertTrue(false);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
		//-------------------------------------
		try
		{
			t1.getCasella(-1, 5);
			assertTrue(false);
		}catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		
		try
		{
			t1.getCasella(5, -1);
			assertTrue(false);
		}catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		
		assertNotNull(t1.getCasella(1, 1));
		
		try
		{
			t1.getCasella(13, 7);
			assertTrue(false);
		}catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		
		try
		{
			t1.getCasella(7, 13);
			assertTrue(false);
		}catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		assertNotNull(t1.getCasella(4, 8));

	}
	
	@Test
	void setMinaTest() {
		t1.setMina(0, 0);
		assertTrue(t1.getCasella(0,0).isMina());
		t1.setMina(12, 12);
		assertTrue(t1.getCasella(12,12).isMina());
		try {
			t1.setMina(-1, 13);
			assertTrue(false);
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
}
