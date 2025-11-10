
public class Casella {

	private boolean teMina; //Indica si la casella té m una mina o no
	private boolean teBandera; //Indica si la casella té bandera o no
	private boolean estaDestapat; //Indica si la casella esta destapada o no
	private int numMinesVoltant; //Numero de mines al voltant de la casella
	
	
	public Casella(){
		//Constructor per defecte de la classe Casella
		teMina = false;
		teBandera = false;
		estaDestapat = false;
		numMinesVoltant = 0;
		
		//Postcondició del constructor per comprovar que s'han creat els objectes correctament
		assert(teMina == false) : "Error la casella s'ha iniciat amb mina";
		assert(teBandera == false) : "Error la casella s'ha iniciat amb bandera";
		assert(estaDestapat == false) : "Error la casella s'ha iniciat destapada";
		assert(numMinesVoltant == 0) : "Error la casella s'ha iniciat amb un número de mines al seu voltant";

	}
	
	public boolean isMina(){
		//Getter de l'atribut teMina
		return teMina;
	}
	
	public void setMina(){
		//Setter de l'altribut teMina
		//Precondició per veure si la casella on vols posar la mina no hi ha mina.
		assert(teMina == false) : "Error vols posar una mina a una casella que ja té una";
		teMina = true;	
		//Postcondició del setter per comprovar que la mina ha sigut col·locada correctament
		assert(teMina == true) : "Error la mina no s'ha col·locat correctament a la casella";

	}
	
	public boolean isBandera(){
		//Getter de l'altribut teBandera
		return teBandera;
	}
	
	public void changeBandera(){
		//Mètode per canviar l'atribut teBnadera (true a false i viceversa)
		if(teBandera)
		{
			teBandera = false;
			//Postcondició per comprovar que la bandera s'ha tret correctament de la casella
			assert(teBandera == false) : "Error la bandera no s'ha tret correctament a la casella";

		}
		else
		{
			teBandera = true;
			//Postcondició per comprovar que la bandera s'ha col·locat correctament de la casella
			assert(teBandera == true) : "Error la bandera s'ha col·locat correctament a la casella";
		}
	}
	
	public void destaparCasella(){
		//Mètode per destapar la casella
		//Precondició per veure si la casella on vols destapar està tapada.
		assert(estaDestapat == false) : "Error vols destapar una casella que ja està destapada";
		estaDestapat = true;
		
		//Postcondició de destapar la casella per comprovar que la casella s'ha destapat correctament
		assert(estaDestapat == true) : "Error la casella no s'han destapat correctament";
	}
	
	public boolean isDestapat(){
		//Getter de l'atribut estaDestapat
		return estaDestapat;
	}
	
	public int getNumMinesVoltant() {
		//Getter de l'atribut numMinesVoltant
		return numMinesVoltant;
	}
	
	public void setNumMinesVoltant(int num) {
		//Setter de l'altribut numMinesVoltant
		//Precondició per veure que el número de mines a instaurar es correcte (entre 0 y 8)
		if(num < 0) {
			throw new IllegalArgumentException("Nombre de mines menor a 0!!!");
		}
		if(num > 8) {
			throw new IllegalArgumentException("Nombre de mines major a 8!!!");
		}
		
		this.numMinesVoltant = num;
		//Post condició per veure si el número de mines al voltant s'ha instaurat correctament
		assert(numMinesVoltant == num) : "Error el número de mines al voltant no s'ha instaurat correctament";
	}
	
}
