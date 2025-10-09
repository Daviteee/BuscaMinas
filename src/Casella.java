
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
	}
	
	public boolean isMina(){
		//Getter de l'atribut teMina
		return teMina;
	}
	
	public void setMina(){
		//Setter de l'altribut teMina
		teMina = true;
	}
	
	public boolean isBandera(){
		//Getter de l'altribut teBandera
		return teBandera;
	}
	
	public void changeBandera(){
		//Mètode per canviar l'atribut teBnadera (true a false i viceversa)
		if(teBandera)
			teBandera = false;
		else
			teBandera = true;
	}
	
	public void destaparCasella(){
		//Metode per destapar la casella
		estaDestapat = true;
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
		if(num < 0) {
			throw new IllegalArgumentException("Nombre de mines menor a 0!!!");
		}
		if(num > 8) {
			throw new IllegalArgumentException("Nombre de mines major a 8!!!");
		}
		
		this.numMinesVoltant = num;
	}
	
}
