import java.util.Random;

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
	
	public void setMina(int x, int y) {

		getCasella(x, y).setMina();
	}
	
	public void generaMinesRandom(int xPlayer, int yPlayer) {
		
		if(xPlayer > 12 || yPlayer > 12 || xPlayer < 0 || yPlayer < 0){ 
			//Comprobació dels limits del tauler segons les coordenades del clic del jugador.
        	throw new IllegalArgumentException("Les coordenades del clic de l'usuari no són correctes (fora dels limits del tauler)");
        }
		
		int nMines = 0; //Numero de mines al teuler.
		
		while(nMines < 10) { //Iterem fins que hi hagi 10 mines al tauler ben col·locades.
			Random random = new Random();
	        int xMina = random.nextInt(13); //Generació de x aleatòriament del 0 al 12.
	        int yMina = random.nextInt(13); //Generació de y aleatòriament del 0 al 12.
	        
	        if( Math.abs(xMina - xPlayer) <= 1 && Math.abs(yMina - yPlayer) <= 1) { 
	        	//La posició aleatòria generada de la mina esta dins de les 8 del voltant on el jugador a fet el clic inicial.
	        	// La norma del buscamines diu que quan clica no pot haber-hi una mina en les 8 caselles del voltant. Per tant, tornem a generar.
	        	continue;
	        }
	        
	        getCasella(xMina, yMina).setMina(); //Posem la casella generada correctament com una mina (mina = true)
	        nMines++; //Augmentem el nombre de mines ben col·locades al tauler.
		}
		
	}
}
