package model;
import java.util.Random;

public class Tauler {

	private static final int MIDA = 13;
	protected Casella[][]tauler;
	int nMaxMines;
	Random myRandom; //Atribut per poder utilitzar el MockObject de Random i generar les mines a on volem.
	
	public Tauler(Random r){
		this.myRandom = r;
		tauler = new Casella[MIDA][MIDA];
		for(int i = 0; i < MIDA; i++)
			for(int j = 0; j < MIDA;j++)
				tauler[i][j] = new Casella();
		this.nMaxMines = 30;
		//Postcondicions per comporvar que s'ha inicialitzat correctament en 30 mines i el myRandom
		assert(this.nMaxMines == 30) : "Error el número màxim de mines no s'ha incialitzat correctament";
		assert(this.myRandom == r) : "Error l'atribut del tipus Random no s'ha inicialitzat correctament";
	}
	
	public void invariant() {
		assert(nMaxMines >= 0): "Error el número màxim de mines es menor a 0";
		assert(myRandom != null): "Error l'atribut del tipus Random és null";
	}
	
	public Casella getCasella(int x,int y){
		invariant();
		//Precondició dels límits del tauler.
		if(x > 12 || y > 12 || x < 0 || y < 0)
			throw new IllegalArgumentException("Error la posició que demanes està fora dels límits del tauler");
		return tauler[x][y];
	}
	
	//Utilitzem getCasella per comprovar els límits del tauler i l'invariant.
	public boolean isMina(int x, int y) {
		return getCasella(x, y).isMina();
	}
	
	//Utilitzem getCasella per comprovar els límits del tauler i l'invariant.
	public boolean isBandera(int x,int y) {
		return getCasella(x, y).isBandera();
	}
	
	//Utilitzem getCasella per comprovar els límits del tauler i l'invariant.
	public int getNumMinesVoltant(int x, int y) {
		return getCasella(x,y).getNumMinesVoltant();
	}
	
	//Utilitzem getCasella per comprovar els límits del tauler i l'invariant.
	public boolean isDestapat(int x, int y) {
		return getCasella(x,y).isDestapat();
	}
	
	public void setMaxMines(int n){
		invariant();
		nMaxMines = n;
		//Postcondició per comporvar que s´ha fet correctament el setter
		assert(nMaxMines == n) : "Error el número màxim de mines no concideix amb el paràmetre rebut al setter";
		invariant();
	}

	public void generaMinesRandom(int xPlayer, int yPlayer) {
		invariant();
		if(xPlayer > 12 || yPlayer > 12 || xPlayer < 0 || yPlayer < 0){ 
			//Precondició dels límits del tauler segons les coordenades del clic del jugador.
        	throw new IllegalArgumentException("Les coordenades del clic de l'usuari no són correctes (fora dels limits del tauler)");
        }
		
		int nMines = 0; //Número de mines al teuler.
		
		while(nMines < nMaxMines) { //Iterem fins que hi hagi 30 mines al tauler ben col·locades.
	        int xMina = myRandom.nextInt(13); //Generació de x aleatòriament del 0 al 12.
	        int yMina = myRandom.nextInt(13); //Generació de y aleatòriament del 0 al 12.
	        
	        if( Math.abs(xMina - xPlayer) <= 1 && Math.abs(yMina - yPlayer) <= 1) { 
	        	//La posició aleatòria generada de la mina està dins de les 8 del voltant on el jugador a fet el clic inicial.
	        	// La norma del buscamines diu que quan clica no pot haver-hi una mina en les 8 caselles del voltant. Per tant, tornem a generar.
	        	continue;
	        }
	        
	        if(!tauler[xMina][yMina].isMina()) {
	        	tauler[xMina][yMina].setMina(); //Posem la casella generada correctament com una mina (mina = true) però abans comprovem que la casella no té mina.
		        nMines++; //Augmentem el nombre de mines ben col·locades al tauler.
	        }
	        
		}	
		invariant();
	}
	
	public void changeBandera(int x, int y) {
		// Canviem l'atribut bandera de la casella indicada (si era true -> false, i al revés).
		//Utilitzem el mètode getCasella en comptes d'accedir a casella[][]
		//per evitar tornar a fer la precondició del límit del tauler pels valor de paràmetres x,y i més comprovem l'invariant.
		getCasella(x, y).changeBandera();
	}
	
	public void setNumMinesVoltant() {
		invariant();
		for(int i = 0; i < MIDA; i++)
			for(int j = 0; j < MIDA;j++) {
				int mines = comptarNumMines(i, j);
	            tauler[i][j].setNumMinesVoltant(mines);
			}
		invariant();
	}
	
	private int comptarNumMines(int x, int y) {
		invariant();
	    int count = 0;

	    for (int dx = -1; dx <= 1; dx++) {
	        for (int dy = -1; dy <= 1; dy++) {
	            if (dx == 0 && dy == 0) 
	                continue;
	            int nx = x + dx;
	            int ny = y + dy;
	            if (nx >= 0 && nx < MIDA && ny >= 0 && ny < MIDA) {
	                if (tauler[nx][ny].isMina()) {
	                    count++;
	                }
	            }
	        }
	    }
	    invariant();
	    return count;
	}
	
	public void destapaCasella(int x, int y) {
	    Casella c = getCasella(x, y); //Comprova els límits i l'invariant

	    // Si ja está destapada o té bandera, no fem res.
	    if (c.isDestapat() || c.isBandera()) {
	        return;
	    }

	    // Destapem la casella.
	    c.destaparCasella();

	    // Si es una mina, acabem (game over)
	    if (c.isMina()) {
	        return;
	    }
	    
	    if (c.getNumMinesVoltant() == 0) {
	    	
	        for (int dx = -1; dx <= 1; dx++) {
	            for (int dy = -1; dy <= 1; dy++) {
	                if (dx == 0 && dy == 0)
	                    continue; // no repetir la mateix acasella
	                
	                int nx = x + dx;
	                int ny = y + dy;
	                
	                if (nx >= 0 && nx < MIDA && ny >= 0 && ny < MIDA) {
	                    destapaCasella(nx, ny);
	                    
	                }
	            }
	        }
	    }
	    invariant();
	}
	
	public boolean totesDestapadesSenseMines() {
		invariant();
		// Mètode que comprova que totes les caselles que no son mines estan destapades (per comprovar que ha guanyat el jugador o no)
	    for (int i = 0; i < MIDA; i++) {
	        for (int j = 0; j < MIDA; j++) {
	            Casella c = getCasella(i, j);
	            if (!c.isMina() && !c.isDestapat()) {
	            	invariant();
	                return false;
	            }
	        }
	    }
	    invariant();
	    return true;
	}
}
