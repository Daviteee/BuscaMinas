package es.uab.tqs.buscamines;

import es.uab.tqs.buscamines.model.Casella;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CasellaTest {
	
	Casella c;
	
	@BeforeEach
	void setUp()
	{
		// Inicialitzem una casella nova abans de cada test
		c = new Casella();
	}
		
	@Test
	void PosarMinaTest() {
		// Comprovem que la col·locació d'una mina en una casella funciona correctament
		c.setMina();
		assertTrue(c.isMina());
		
		try {
			c.setMina(); // Intentem tornar a posar una mina en una casella que ja en té una
		} catch(AssertionError e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	void PosarAndTreureBanderaTest() {
		// Comprovem que es poden posar i treure banderes correctament
		c.changeBandera();
		assertTrue(c.isBandera()); // S'ha col·locat la bandera
		
		c.changeBandera();
		assertFalse(c.isBandera()); // S'ha retirat la bandera
	}
		
	@Test
	void DestaparCasellaTest(){
		// Comprovem que es pot destapar una casella tapada correctament
		assertFalse(c.isDestapat()); // Inicialment ha d'estar tapada
		
		c.destaparCasella();
		assertTrue(c.isDestapat()); // Ara ha d'estar destapada
		
		try {
			c.destaparCasella(); // Intentem destapar-la de nou, cosa que ha de fallar per l'assert
		} catch(AssertionError e) {
			System.err.println(e.getMessage());
		}
	}
		
	@Test
	void setNumMinesVoltantTest() {
			
		// Comprovem que es pot assignar el nombre de mines al voltant correctament
		// Particions equivalents del domini: 0 - 8
		
		c.setNumMinesVoltant(0);
        // Valor frontera inferior vàlid
		assertEquals(0, c.getNumMinesVoltant());
			
		c.setNumMinesVoltant(8);
		// Valor frontera superior vàlid
		assertEquals(8, c.getNumMinesVoltant());
				
		try {
			c.setNumMinesVoltant(-1); // Valor inferior no vàlid
			assertTrue(false); // Si arriba aquí, hi ha un error
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
			
		try {
			c.setNumMinesVoltant(9); // Valor superior no vàlid
			assertTrue(false); // Si arriba aquí, hi ha un error
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
			
		c.setNumMinesVoltant(1);
		assertEquals(1, c.getNumMinesVoltant()); // Valor interior vàlid
		
		c.setNumMinesVoltant(4);
		assertEquals(4, c.getNumMinesVoltant()); // Valor central vàlid
			
		c.setNumMinesVoltant(7);
		assertEquals(7, c.getNumMinesVoltant()); // Valor proper al límit superior vàlid
	}
		
}
