
package controlador;

import java.util.Random;

import model.Tauler;
import vista.BuscaminesVista;

public class Joc {
	
	private Tauler tauler;
    private boolean partidaAcabada;
    private BuscaminesVista vista;
    private int banderesRestants;

    public Joc(Tauler t) { 
    	// Constructor per paràmetres on passem el tauler del joc.
    	// Precondició per comprovar que el tauler no és null.
    	assert(t != null) : "Error: el tauler és null";	
    	
    	this.partidaAcabada = false;
    	this.banderesRestants = 30;
    	this.tauler = t; 
    	this.vista = null; // Inicialitzem la vista a null al constructor.
    	
    	// Postcondicions per comprovar que el joc s’ha inicialitzat correctament.
    	assert(!this.partidaAcabada) : "Error: la partida s’ha inicialitzat com acabada (incorrecte)";
    	assert(this.banderesRestants == 30) : "Error: el nombre de banderes no s’ha inicialitzat correctament";
    	assert(this.tauler == t) : "Error: el tauler no s’ha inicialitzat correctament";
    	assert(this.vista == null) : "Error: la vista no s’ha inicialitzat a null correctament";
    }
    
    public void crearVistaDelJoc(BuscaminesVista vista) { 
    	// Funció que assigna la vista del joc al controlador. S’utilitza per poder instanciar un mock de vista.
    	// Precondició per comprovar que la vista no és null.
    	assert(vista != null): "Error: la vista és null";	
    	
    	this.vista = vista;
    	
    	// Postcondició per comprovar que la vista s’ha instanciat correctament.
    	assert(this.vista == vista): "Error: la vista no s’ha instanciat correctament";
    	
    	this.vista.actualitzar(); // Actualitza o imprimeix el tauler inicial (si és mock, no fa res).
    }
    
    public void reiniciarPartida() {
    	// Funcionalitat extra per reiniciar el joc amb la mateixa vista.
    	// Aquesta funció només la crida la vista, i actua com un "nou joc" sense necessitat de mocks,
    	// ja que sempre es farà servir un tauler real i un Random real.
    	// Les postcondicions comproven que la funció reinicia correctament l’estat del joc.
    	
    	Random r = new Random();
        this.tauler = new Tauler(r);
        this.partidaAcabada = false;
        this.banderesRestants = 30;
        
        assert(!this.partidaAcabada) : "Error: la partida s’ha reiniciat com acabada (incorrecte)";
    	assert(this.banderesRestants == 30) : "Error: el nombre de banderes no s’ha reiniciat correctament";
    }
    
    public int getBanderesRestants() {
        return this.banderesRestants;
    }

    public boolean getPartidaAcabada() {
    	return this.partidaAcabada;
    }
    
    public boolean isBandera(int x, int y) {
    	return this.tauler.isBandera(x, y);
    }
    
    public boolean isDestapat(int x, int y) {
    	return this.tauler.isDestapat(x, y);
    }
    
    public boolean isMina(int x, int y) {
    	return this.tauler.isMina(x, y);
    }
    
    public int getNumMinesVoltant(int x, int y) {
    	return this.tauler.getNumMinesVoltant(x, y);	
    }
    
    public void setPartidaAcabada() {
    	this.partidaAcabada = true;
    }
    
    public void clicDret(int x, int y) {

        // Si la partida ha acabat, no fem res.
        if (this.partidaAcabada)
            return;

        // No es poden posar banderes sobre caselles destapades.
        if (this.tauler.isDestapat(x, y))
            return;

        boolean teniaBandera = this.tauler.isBandera(x, y);

        // Si ja tenia bandera → la traiem i incrementem banderes restants.
        if (teniaBandera) {
            this.tauler.changeBandera(x, y);
            this.banderesRestants++;
        }
        else {
            // Si no queden banderes disponibles → no fem res.
            if (this.banderesRestants == 0) {
                return;
            }
            // Posem una bandera.
            this.tauler.changeBandera(x, y);
            this.banderesRestants--;
        }

    }

    
    public void clicEsquerra(int x, int y) {

    	// No podem interactuar si la partida ha acabat.
    	if (this.partidaAcabada)
            return;

    	// No podem destapar una casella amb bandera.
    	if (this.tauler.isBandera(x, y))
    		return;

    	// Determinem si és el primer clic del jugador.
    	boolean primerClic = true;
    	
    	for(int i = 0; i < 13; i++) {
    		for(int j = 0; j < 13; j++) {
    			if(this.tauler.isDestapat(i, j)) {
    				primerClic = false;
    				break;
    			}
    		}
    	}

        // Si és el primer clic: generem mines garantint que la casella clicada no tingui mina.
        if (primerClic) {
            this.tauler.generaMinesRandom(x, y);
            this.tauler.setNumMinesVoltant();
        }
    	
        // Destapem la casella indicada.
        this.tauler.destapaCasella(x, y); 

        // Si hem clicat una mina → derrota.
        if (this.tauler.isMina(x, y)) {
            this.partidaAcabada = true;
            this.tauler.destaparCasellesAmbMines(); // Mostrem totes les mines.
        }

        // Si totes les caselles sense mines han estat destapades → victòria.
        if (this.tauler.totesDestapadesSenseMines()) {
            this.partidaAcabada = true;
            this.tauler.destaparCasellesAmbMines(); // Mostrem també les mines.
        }
    }
}
