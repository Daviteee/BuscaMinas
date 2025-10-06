
public class Casella {

	private boolean teMina;
	private boolean teBandera;
	private boolean estaDestapat;
	
	public Casella()
	{
		
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
	
}
