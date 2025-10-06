
public class Casella {

	private Posicio posCasella;
	private boolean teMina;
	private boolean teBandera;
	private boolean estaDestapat;
	
	public Casella(Posicio p)
	{
		if(p == null)
			throw new IllegalArgumentException("Error la posició de la casella no por ser null");
		
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
	public boolean isDestapat()
	{
		return estaDestapat;
	}
	
	public Posicio getPosicio() {
		return posCasella;
	}
}
