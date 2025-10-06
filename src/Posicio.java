
public class Posicio {

	private int x;
	private int y;
	public Posicio(int x,int y)
	{
		if(x<0)
		{
			throw new IllegalArgumentException("El valor del paràmetre de posció X es menor a 0");
		}
		
		if(x>12)
		{
			throw new IllegalArgumentException("El valor del paràmetre de posció X es major a 12");
		}
		
		if(y<0)
		{
			throw new IllegalArgumentException("El valor del paràmetre de posció Y es menor a 0");
		}
		
		if(y>12)
		{
			throw new IllegalArgumentException("El valor del paràmetre de posició Y es major a 12");
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
