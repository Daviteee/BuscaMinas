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
    
    public void clicDret(int x, int y) {
        if (partidaAcabada)
            return;

        tauler.changeBandera(x, y);
    }
    
    public void clicEsquerra(int x, int y) {
    	if (partidaAcabada)
            return;

        tauler.destapaCasella(x, y); // Destapem la casella indicada

        if (tauler.getCasella(x, y).isMina()) {
            partidaAcabada = true;
            // Mostrar explosió i actualitzar tauler
            return;
        }

        if (tauler.totesDestapadesSenseMines()) {
            partidaAcabada = true;
            // Mostrar victoria i actulaitzar tauler
            return;
        }
    }

}
