package model;

import java.util.Random;

public class Tauler {

	private static final int MIDA = 13;
	protected Casella[][] tauler;
	private int nMaxMines; // Atribut per canviar el nombre màxim de mines del tauler (útil per als tests)
	private Random myRandom; // Atribut per utilitzar MockRandom i controlar la generació de mines.
	
	public Tauler(Random r){
		// Precondició per comprovar que l'objecte Random passat per paràmetre no és null.
		assert(r != null) : "Error, el Random indicat per paràmetres és null!";
		
		this.myRandom = r;
		tauler = new Casella[MIDA][MIDA];
		
		// Inicialització de totes les caselles del tauler.
		for(int i = 0; i < MIDA; i++)
			for(int j = 0; j < MIDA; j++)
				tauler[i][j] = new Casella();
		
		this.nMaxMines = 30; // Valor per defecte.
		
		// Postcondicions per comprovar que la inicialització és correcta.
		assert(this.nMaxMines == 30) : "Error: el número màxim de mines no s'ha inicialitzat correctament.";
		assert(this.myRandom == r) : "Error: l'atribut Random no s'ha inicialitzat correctament.";
	}
	
	public void invariant() {
		// L’invariant del tauler: el nombre màxim de mines no pot ser negatiu.
		assert(nMaxMines >= 0) : "Error: el número màxim de mines és menor que 0.";
	}
	
	public Casella getCasella(int x, int y){
		invariant();
		// Precondició: comprovació dels límits del tauler.
		if(x > 12 || y > 12 || x < 0 || y < 0)
			throw new IllegalArgumentException("Error: la posició demanada està fora dels límits del tauler.");
		return tauler[x][y];
	}
	
	// Els següents mètodes utilitzen getCasella(), que ja comprova límits i invariant.
	
	public boolean isMina(int x, int y) {
		return getCasella(x, y).isMina();
	}
	
	public boolean isBandera(int x,int y) {
		return getCasella(x, y).isBandera();
	}
	
	public int getNumMinesVoltant(int x, int y) {
		return getCasella(x,y).getNumMinesVoltant();
	}
	
	public boolean isDestapat(int x, int y) {
		return getCasella(x,y).isDestapat();
	}
	
	public void setMaxMines(int n){
		invariant();
		nMaxMines = n;
		// Postcondició per comprovar que el setter ha funcionat correctament.
		assert(nMaxMines == n) : "Error: el número màxim de mines no coincideix amb el paràmetre rebut.";
		invariant();
	}

	public void generaMinesRandom(int xPlayer, int yPlayer) {
		invariant();
		
		// Precondició: les coordenades del clic inicial han de ser dins dels límits.
		if(xPlayer > 12 || yPlayer > 12 || xPlayer < 0 || yPlayer < 0){ 
        	throw new IllegalArgumentException("Les coordenades del clic de l'usuari són incorrectes (fora del tauler).");
        }
		
		int nMines = 0; // Nombre de mines col·locades fins al moment.
		
		// Itera fins que hi hagi nMaxMines mines col·locades correctament. nMaxMines serveix per quan només volem posar x mines en comptes de 30 que son les predeterminades, és a dir per a la realització dels casos de prova de Tauler.
		while(nMines < nMaxMines) {
	        int xMina = myRandom.nextInt(13); // Generació de x aleatòria (0–12) o utilitzant el mockRandom que retorna una coordenada predefinida.
	        int yMina = myRandom.nextInt(13); // Generació de y aleatòria (0–12) utilitzant el mockRandom que retorna una coordenada predefinida.
	        
	        // No es poden col·locar mines en les 8 caselles adjacents al primer clic.
	        if(Math.abs(xMina - xPlayer) <= 1 && Math.abs(yMina - yPlayer) <= 1) {
	        	continue;
	        }
	        
	        // Si la casella no té una mina, la col·loquem.
	        if(!tauler[xMina][yMina].isMina()) {
	        	tauler[xMina][yMina].setMina();
		        nMines++;
	        }
		}
		invariant();
	}
	
	public void changeBandera(int x, int y) {
		// Alternem l’estat de la bandera utilitzant getCasella(),
		// que ja assegura límits i invariant.
		getCasella(x, y).changeBandera();
	}
	
	public void setNumMinesVoltant() {
		invariant();
		// Calcula el número de mines al voltant de cada casella.
		for(int i = 0; i < MIDA; i++)
			for(int j = 0; j < MIDA; j++) {
				int mines = comptarNumMines(i, j);
	            tauler[i][j].setNumMinesVoltant(mines);
			}
		invariant();
	}
	
	private int comptarNumMines(int x, int y) {
		invariant();
		
	    int count = 0;
	    // Recorrem les 8 caselles adjacents.
	    for (int dx = -1; dx <= 1; dx++) {
	        for (int dy = -1; dy <= 1; dy++) {
	            if (dx == 0 && dy == 0)
	                continue;
	            int nx = x + dx;
	            int ny = y + dy;
	            
	            // Comprovem límits del tauler.
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
	    Casella c = getCasella(x, y); // Comprova límits i invariant.

	    // Si ja està destapada o té bandera, no fem res.
	    if (c.isDestapat() || c.isBandera()) {
	        return;
	    }

	    // Destapem la casella.
	    c.destaparCasella();

	    // Si és una mina, finalitzem (game over).
	    if (c.isMina()) {
	        return;
	    }
	    
	    // Si no hi ha mines al voltant, destapem recursivament les caselles adjacents.
	    if (c.getNumMinesVoltant() == 0) {
	        for (int dx = -1; dx <= 1; dx++) {
	            for (int dy = -1; dy <= 1; dy++) {
	                if (dx == 0 && dy == 0)
	                    continue;
	                
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
		// Comprova si totes les caselles sense mina estan destapades (condició de victòria).
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
	
	public void destaparCasellesAmbMines() {
		invariant();
		// Destapa totes les caselles que són mines.
		for (int i = 0; i < MIDA; i++) {
	        for (int j = 0; j < MIDA; j++) {
	            Casella c = getCasella(i, j);
	            if (c.isMina()) {
	            	c.destaparCasella();
	            }
	        }
		}
		invariant();
	}
}
