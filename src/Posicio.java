
public class Posicio {

	private int x;
	private int y;
	public Posicio(int x,int y)
	{
		if(x<0 || x>12 || y<0 || y>12)
		{
			throw new IllegalArgumentException("Els valors dels parametres de poscio son incorrectes");
		}
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY()
	{
		return y;
	}
}
