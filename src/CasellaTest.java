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
		
		@Test
		void setNumMinesVoltantTest() {
			
			//Comprovem que es pot posar el nombre de mines al voltant correctament.
			//Particions equivalents: (0 - 8): Particions equivalents
			c.setNumMinesVoltant(0);
			assertEquals(0, c.getNumMinesVoltant()); //Valor frontera
			
			c.setNumMinesVoltant(8);
			assertEquals(8, c.getNumMinesVoltant()); //Valor frontera
			
			
			try {
				c.setNumMinesVoltant(-1); //Valor limit inferior
				assertTrue(false);
				
			}catch(Exception e) {
				System.err.println(e.getMessage());
			}
			
			try {
				c.setNumMinesVoltant(9); //Valor limit superior
				assertTrue(false);
				
			}catch(Exception e) {
				System.err.println(e.getMessage());
			}
			
			c.setNumMinesVoltant(1);
			assertEquals(1, c.getNumMinesVoltant()); //Valor limit superior
			
			c.setNumMinesVoltant(4);
			assertEquals(4, c.getNumMinesVoltant()); //Valor interior
			
			c.setNumMinesVoltant(7);
			assertEquals(7, c.getNumMinesVoltant()); //Valor limit inferior
			
		}
		
}
