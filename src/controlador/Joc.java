package controlador;
import model.Tauler;
import vista.BuscaminesVista;

public class Joc {
	
	private Tauler tauler;
    private boolean partidaAcabada;
    private BuscaminesVista vista;

    public Joc(Tauler t) { //Constructor per paràmetres on li pasem el tauler del joc.
    	//Precondició per comprovar que hi ha tauler.
    	assert(t != null):"Error el tauler és null";	
    	
    	this.partidaAcabada = false;
    	this.tauler = t; 
    	this.vista = null; //Inicialitzem la vista a null al constructor.
    	
    	//Postcondicions per comprovar que s'ha iniciat correctament el joc abans de generar la vista.
    	assert(!this.partidaAcabada):"Error la partida s'ha inicialitzat acabada(incorrecte)";
    	assert(this.tauler == t):"Error el tauler no s'ha iniciat correctament amb el tauler per paràmetre";
    	assert(this.vista == null):"Error la vista no s'ha iniciat a null correctament";
    }
    
    public void crearVistaDelJoc(BuscaminesVista vista) { //Funció que asigna la vista del joc al controlador joc, s'utilitza per poder instanciar un mock de vista correctament.
    	//Precondició per comprovar que hi ha vista.
    	assert(vista != null):"Error la vista és null";	
    	this.vista = vista;
    	//Postcondició per comprovar que s'ha instanciat correctament la vista.
    	assert(this.vista == vista):"Error la vista no s'ha instanciat correctament amb la vista per paràmetres";	
    	this.vista.actualitzar(); //Printem el tauler inicial (si és mock no farà res).
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
