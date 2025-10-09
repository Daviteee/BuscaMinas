
public class Tauler {

	private Casella[][]tauler;
	public Tauler()
	{
		tauler = new Casella[13][13];
		for(int i = 0; i < 13; i++)
			for(int j = 0; j < 13;j++)
				tauler[i][j] = new Casella();
		 		
	}
	
	public Casella getCasella(int x,int y)
	{
		return tauler[x][y];
	}
}
