import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CasellaTest {
	
	Casella c;
	
	@BeforeEach
	void setUp()
	{
		c = new Casella();
	}
	
	@Test
	void ConstructorCasellaTest() {
		//Comprovem que el constructor genera correctament la casella sense mina, sense bandera i tapada.
		assertFalse(c.isMina());
		assertFalse(c.isBandera());
		assertFalse(c.isDestapat());
	}
		@Test
		void PosarMinaTest() {
			//Comprovem que l'assingació de la mina a una casella es fa correctament.
			c.setMina();
			assertTrue(c.isMina());
			
		}
		@Test
		void PosarAndTreureBanderaTest() {
			//Comprovem que es pot posar banderas i treure correctament en una casella.
			c.changeBandera();
			assertTrue(c.isBandera());
			c.changeBandera();
			assertFalse(c.isBandera());
		}
		
		@Test
		void DestaparCasellaTest()
		{
			//Comprovem que es pot destapar una casella tapada correctament.
			assertFalse(c.isDestapat());
			c.destaparCasella();
			assertTrue(c.isDestapat());
		}
		
		
}
