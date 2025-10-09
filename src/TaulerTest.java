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
		//Comprovem que el get casella controla correctament els limits del tauler.
		
		assertNotNull(t1.getCasella(0, 0));
		assertNotNull(t1.getCasella(12, 12));
		
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
}
