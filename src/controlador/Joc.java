
package controlador;

import java.util.Random;

import model.Tauler;
import vista.BuscaminesVista;

public class Joc {
	
	private Tauler tauler;
    private boolean partidaAcabada;
    private BuscaminesVista vista;
    private int banderesRestants;

    public Joc(Tauler t) { //Constructor per paràmetres on li pasem el tauler del joc.
    	//Precondició per comprovar que hi ha tauler.
    	assert(t != null) : "Error el tauler és null";	
    	
    	this.partidaAcabada = false;
    	this.banderesRestants = 30;
    	this.tauler = t; 
    	this.vista = null; //Inicialitzem la vista a null al constructor.
    	
    	//Postcondicions per comprovar que s'ha iniciat correctament el joc abans de generar la vista.
    	assert(!this.partidaAcabada) : "Error la partida s'ha inicialitzat acabada(incorrecte)";
    	assert(this.banderesRestants == 30) : "Error, el numero de banderes no s'ha inicialitzat correctament";
    	assert(this.tauler == t) : "Error el tauler no s'ha iniciat correctament amb el tauler per paràmetre";
    	assert(this.vista == null) : "Error la vista no s'ha iniciat a null correctament";
    }
    
    public void crearVistaDelJoc(BuscaminesVista vista) { //Funció que asigna la vista del joc al controlador joc, s'utilitza per poder instanciar un mock de vista correctament.
    	//Precondició per comprovar que hi ha vista.
    	assert(vista != null):"Error la vista és null";	
    	this.vista = vista;
    	//Postcondició per comprovar que s'ha instanciat correctament la vista.
    	assert(this.vista == vista):"Error la vista no s'ha instanciat correctament amb la vista per paràmetres";	
    	this.vista.actualitzar(); //Printem el tauler inicial (si és mock no farà res).
    }
    
    public void reiniciarPartida(){
    	//Funcionalitat extra per reinciar el joc una altre vegada al joc final amb la mateixa vista.
    	//Aquesta funció només es demana desde la vista a l'usuari i és com una mena de constructor del tauler 
    	//com que només és demana per la vista no fa falta que tingui la posibiliatat de mocks, ja que sempre 
    	//utilitzarà un tauler real i un random real. Amb les postcondicions comprovem que la funció funciona 
    	//correctament
    	Random r = new Random();
        this.tauler = new Tauler(r);
        this.partidaAcabada = false;
        this.banderesRestants = 30;
        assert(!this.partidaAcabada) : "Error la partida s'ha inicialitzat acabada(incorrecte)";
    	assert(this.banderesRestants == 30) : "Error, el numero de banderes no s'ha inicialitzat correctament";
    	
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

        if (this.partidaAcabada)
            return;

        if (this.tauler.isDestapat(x, y))
            return;

        boolean teniaBandera = this.tauler.isBandera(x, y);

        if (teniaBandera) {
            this.tauler.changeBandera(x, y);
            this.banderesRestants++;
        }
        else {
            if (this.banderesRestants == 0) {
                return;
            }
            this.tauler.changeBandera(x, y);
            this.banderesRestants--;
        }

    }

    
    public void clicEsquerra(int x, int y) {
    	if (this.partidaAcabada)
            return;
    	if(this.tauler.isBandera(x,y))
    		return;

    	// Si és el primer clic → generar mines al voltant
    	boolean primerClic = true;
    	
    	for(int i = 0; i < 13; i++) {
    		for(int j = 0; j < 13; j++) {
    			if(this.tauler.isDestapat(i,j)) {
    				primerClic = false;
    				break;
    			}
    		}
    	}

        if (primerClic) {
            this.tauler.generaMinesRandom(x, y);
            this.tauler.setNumMinesVoltant();
        }
    	
        this.tauler.destapaCasella(x, y); // Destapem la casella indicada

        if (this.tauler.isMina(x,y)) {
            this.partidaAcabada = true;
            this.tauler.destaparCasellesAmbMines();
            // Mostrar explosió i actualitzar tauler
        }

        if (this.tauler.totesDestapadesSenseMines()) {
            this.partidaAcabada = true;
            // Mostrar victoria i actulaitzar tauler
            this.tauler.destaparCasellesAmbMines();
        }
    }
}
