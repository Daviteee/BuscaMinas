
public class Casella {

	private Posicio posCasella;
	private boolean teMina;
	private boolean teBandera;
	private boolean estaDestapat;
	
	public Casella(Posicio p)
	{
		if(p == null)
			throw new IllegalArgumentException("Error la posició de la casella no pot ser null");
		
		posCasella = p;
		teMina = false;
		teBandera = false;
		estaDestapat = false;
	}
	
	public boolean isMina()
	{
		return teMina;
	}
	
	public void setMina()
	{
		teMina = true;
	}
	
	public boolean isBandera()
	{
		return teBandera;
	}
	
	public void changeBandera()
	{
		if(teBandera)
			teBandera = false;
		else
			teBandera = true;
	}
	
	public void destaparCasella()
	{
		estaDestapat= true;
	}
	public boolean isDestapat()
	{
		return estaDestapat;
	}
	
	public Posicio getPosicio() {
		return posCasella;
	}
}
