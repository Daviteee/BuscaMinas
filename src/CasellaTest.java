import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CasellaTest {

	@Test
	void Constructor() {
		Posicio p1 = new Posicio(3,2);
		Casella c1 = new Casella(p1);	
		assertEquals(p1,c1.getPosicio());
		assertFalse(c1.isMina());
		assertFalse(c1.isBandera());
		assertFalse(c1.isDestapat());
		try
		{
			c1 = new Casella(new Posicio(null));
			assertTrue(false);
		}catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}

}

