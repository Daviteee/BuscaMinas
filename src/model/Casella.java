package model;
public class Casella {

	private boolean teMina; // Indica si la casella té una mina o no
	private boolean teBandera; // Indica si la casella té una bandera o no
	private boolean estaDestapat; // Indica si la casella està destapada o no
	private int numMinesVoltant; // Número de mines al voltant de la casella
	
	
	public Casella(){
		// Constructor per defecte de la classe Casella
		teMina = false;
		teBandera = false;
		estaDestapat = false;
		numMinesVoltant = 0;
		
		// Postcondició del constructor per comprovar que els atributs s'han inicialitzat correctament
		assert(teMina == false) : "Error: la casella s'ha iniciat amb una mina";
		assert(teBandera == false) : "Error: la casella s'ha iniciat amb una bandera";
		assert(estaDestapat == false) : "Error: la casella s'ha iniciat destapada";
		assert(numMinesVoltant == 0) : "Error: la casella s'ha iniciat amb un número de mines al voltant incorrecte";

	}
	
	public boolean isMina(){
		// Getter de l'atribut teMina
		return teMina;
	}
	
	public void setMina(){
		// Setter de l'atribut teMina
		// Precondició per assegurar que la casella no té una mina abans de col·locar-ne una
		assert(teMina == false) : "Error: vols posar una mina en una casella que ja en té una";
		teMina = true;	
		
		// Postcondició del setter per comprovar que la mina s'ha col·locat correctament
		assert(teMina == true) : "Error: la mina no s'ha col·locat correctament a la casella";

	}
	
	public boolean isBandera(){
		// Getter de l'atribut teBandera
		return teBandera;
	}
	
	public void changeBandera(){
		// Mètode per canviar l'atribut teBandera (de true a false o viceversa)
		if(teBandera)
		{
			teBandera = false;
			// Postcondició per comprovar que la bandera s'ha tret correctament de la casella
			assert(teBandera == false) : "Error: la bandera no s'ha tret correctament de la casella";

		}
		else
		{
			teBandera = true;
			// Postcondició per comprovar que la bandera s'ha col·locat correctament a la casella
			assert(teBandera == true) : "Error: la bandera no s'ha col·locat correctament a la casella";
		}
	}
	
	public void destaparCasella(){
		// Mètode per destapar la casella
		// Precondició per assegurar que la casella encara està tapada
		assert(estaDestapat == false) : "Error: vols destapar una casella que ja està destapada";
		estaDestapat = true;
		
		// Postcondició per comprovar que la casella s'ha destapat correctament
		assert(estaDestapat == true) : "Error: la casella no s'ha destapat correctament";
	}
	
	public boolean isDestapat(){
		// Getter de l'atribut estaDestapat
		return estaDestapat;
	}
	
	public int getNumMinesVoltant() {
		// Getter de l'atribut numMinesVoltant
		return numMinesVoltant;
	}
	
	public void setNumMinesVoltant(int num) {
		// Setter de l'atribut numMinesVoltant
		// Precondició per assegurar que el número de mines és correcte (entre 0 i 8)
		if(num < 0) {
			throw new IllegalArgumentException("Nombre de mines menor que 0!");
		}
		if(num > 8) {
			throw new IllegalArgumentException("Nombre de mines major que 8!");
		}
		
		this.numMinesVoltant = num;
		
		// Postcondició per comprovar que el número de mines s'ha assignat correctament
		assert(numMinesVoltant == num) : "Error: el número de mines al voltant no s'ha assignat correctament";
	}
	
}
