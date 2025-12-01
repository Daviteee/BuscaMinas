package controlador;
import model.Tauler;
import vista.BuscaminesVista;

public class Joc {
	
	private Tauler tauler;
    private boolean partidaAcabada;
    private BuscaminesVista vista;
    
    public Joc(){ // Constructor per defecte
    	this.partidaAcabada = false;
    	this.tauler = null;
    	this.vista = null;
    }
    
    public Joc(Tauler t, int mida) { // Constructor per parametres
    	this.partidaAcabada = false;
    	this.tauler = t;
    	this.vista = new BuscaminesVista(this, mida); // Inicialitzem la vista amb el Joc i la mida del tauler.
    	vista.actualitzar(); // Pintem el tauler amb l'estat inicial.
    }
    
    public Tauler getTauler() {
    	return this.tauler;
    }
    
    public BuscaminesVista getVista() {
    	return this.vista;
    }
    
    public void setVista(BuscaminesVista v) {
    	this.vista = v;
    }
    
    public boolean getPartidaAcabada() {
    	return this.partidaAcabada;
    }
    
    public void setPartidaAcabada(boolean game) {
    	this.partidaAcabada = game;
    }
    
    public void clicDret(int x, int y) {
        if (partidaAcabada)
            return;

        tauler.changeBandera(x, y);
        vista.actualitzar();
    }
    
    public void clicEsquerra(int x, int y) {
    	if (partidaAcabada)
            return;
    	if(tauler.getCasella(x, y).isBandera())
    		return;

    	// Si és el primer clic → generar mines al voltant
    	boolean primerClic = true;
    	
    	for(int i = 0; i < 13; i++) {
    		for(int j = 0; j < 13; j++) {
    			if(tauler.getCasella(i, j).isDestapat()) {
    				primerClic = false;
    				break;
    			}
    		}
    	}

        if (primerClic) {
            tauler.generaMinesRandom(x, y);
            tauler.setNumMinesVoltant();
        }
    	
    	
        tauler.destapaCasella(x, y); // Destapem la casella indicada

        if (tauler.getCasella(x, y).isMina()) { // ESTA MAL
            partidaAcabada = true;
            // Mostrar explosió i actualitzar tauler
            return;
        }

        if (tauler.totesDestapadesSenseMines()) {
            partidaAcabada = true;
            // Mostrar victoria i actulaitzar tauler
            return;
        }
        vista.actualitzar();
    }

}
