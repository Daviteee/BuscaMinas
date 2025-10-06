import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CasellaTest {
	
	Posicio p;
	Casella c;
	@Test
	void Constructor() {
		//Comprovem que el constructor genera correctament la casella amb la posició assingada, sense mina, sense bandera i tapada.
		p = new Posicio(3,2);
		c = new Casella(p);	
		assertEquals(p,c.getPosicio());
		assertFalse(c.isMina());
		assertFalse(c.isBandera());
		assertFalse(c.isDestapat());
		//Comprovem que dona una excepció si pasem null la posició.
		try
		{
			c = new Casella(null);
			assertTrue(false);
		}catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
		@Test
		void PosarMinaTest() {
			//Comprovem que l'assingació de la mina a una casella es fa correctament.
			p = new Posicio(10,7);
			c = new Casella(p);	
			c.setMina();
			assertTrue(c.isMina());
			
		}
		@Test
		void PosarAndTreureBanderaTest() {
			//Comprovem que es pot posar banderas i treure correctament en una casella.
			p = new Posicio(3,3);
			c = new Casella(p);
			c.changeBandera();
			assertTrue(c.isBandera());
			c.changeBandera();
			assertFalse(c.isBandera());
		}
		
		@Test
		void DestaparCasellaTest()
		{
			//Comprovem que es pot destapar una casella tapada correctament.
			p = new Posicio(7,10);
			c = new Casella(p);
			assertFalse(c.isDestapat());
			c.destaparCasella();
			assertTrue(c.isDestapat());
		}
}
