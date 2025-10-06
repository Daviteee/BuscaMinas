import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PosicioTest {

	@Test
	void constructorTest()
	{
		//Test partició d'equivalència amb x

		Posicio p = new Posicio(4,7);
		assertEquals(4,p.getX());
		assertEquals(7,p.getY());
		p = new Posicio(0,6);
		assertEquals(0,p.getX());
		assertEquals(6,p.getY());
		p = new Posicio(12,6);
		assertEquals(12,p.getX());
		assertEquals(6,p.getY());
		try
		{
			p = new Posicio(-1,6);
			assertTrue(false);
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		p = new Posicio(1,6);
		assertEquals(1,p.getX());
		assertEquals(6,p.getY());
		p = new Posicio(11,6);
		assertEquals(11,p.getX());
		assertEquals(6,p.getY());
		try
		{
			p = new Posicio(13,6);
			assertTrue(false);
		}catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		
		//Test partició d'equivalència amb y
		
		p = new Posicio(10,0);
		assertEquals(10,p.getX());
		assertEquals(0,p.getY());
		p = new Posicio(10,12);
		assertEquals(10,p.getX());
		assertEquals(12,p.getY());
		try
		{
			p = new Posicio(10,-1);
			assertTrue(false);
		}catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		
		p = new Posicio(10,1);
		assertEquals(10,p.getX());
		assertEquals(1,p.getY());
		p = new Posicio(10,11);
		assertEquals(10,p.getX());
		assertEquals(11,p.getY());
		try
		{
			p = new Posicio(10,13);
			assertTrue(false);
		}catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}

}
