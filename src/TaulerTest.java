import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TaulerTest {
 
	Tauler [][] t1,t2;
	@Test
	void ConstructorInicialTaulerTest()
	{
		for(int i=0;i<13;i++)
			for(int j=0;j<13;j++)
			{
				t1[i][j] = new Casella();
			}
		t2 = new Tauler();
		t1.equals(t2);
		
	}

}
