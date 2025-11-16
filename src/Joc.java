
public class Joc {
	
	private Tauler tauler;
    private boolean partidaAcabada;
    
    public Joc(){ // Constructor per defecte
    	this.partidaAcabada = false;
    	this.tauler = null;
    }
    
    public Joc(Tauler t) { // Constructor per parametres
    	this.partidaAcabada = false;
    	this.tauler = t;
    }
    
    public Tauler getTauler() {
    	return this.tauler;
    }
    
    public boolean getPartidaAcabada() {
    	return this.partidaAcabada;
    }
    
    public void clicDret(int x, int y) {
        if (partidaAcabada)
            return;

        tauler.changeBandera(x, y);
    }

}
