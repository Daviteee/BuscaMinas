
public class Tauler {

	private static final int MIDA = 13;
	private Casella[][]tauler;
	public Tauler()
	{
		tauler = new Casella[MIDA][MIDA];
		for(int i = 0; i < MIDA; i++)
			for(int j = 0; j < MIDA;j++)
				tauler[i][j] = new Casella();
		 		
	}
	
	public Casella getCasella(int x,int y)
	{
		if(x > 12 || y > 12 || x < 0 || y < 0)
			throw new IllegalArgumentException("Error la posició que demanes està fora dels límits del tauler");
		return tauler[x][y];
	}
}
