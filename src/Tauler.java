public class Tauler {

	private Casella[][]tauler;
	public Tauler()
	{
		tauler = new Casella[13][13];
		 		
	}
	
	public Casella getCasella(int x,int y)
	{
		return tauler[x][y];
	}
}
