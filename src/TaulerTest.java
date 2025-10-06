import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TaulerTest {
 
	Tauler t1,t2;
	@Test
	void ConstructorInicialTaulerTest()
	{
		//Comrpovem que el tauler inicial s'inicialitza correctament amb totes les caselles corresponents.
		t1= new Tauler();
		for(int i=0;i<12;i++)
			for(int j=0;j<12;j++)
			{
				assertFalse(t1.getCasella(i,j).isBandera());
				assertFalse(t1.getCasella(i,j).isMina());
				assertFalse(t1.getCasella(i,j).isDestapat());

			}
	}
}
