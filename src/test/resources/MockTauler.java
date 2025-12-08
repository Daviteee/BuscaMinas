package resources;

import java.util.Random;

public class MockTauler extends Tauler{
	
	// Mock object de la classe Tauler per facilitar els casos de prova que requereixen generar taulers específics.
	// Com que la generació de mines és aleatòria en la classe original, necessitem taulers amb mines
	// posicionades manualment per poder verificar el funcionament d'altres mètodes.
	
	public MockTauler(Random r) {
		super(r);
	}
	
	@Override
    public void generaMinesRandom(int xPlayer, int yPlayer) {
        // Override de la funció generaMinesRandom perquè no sigui aleatòria.
        // Això ens permet controlar exactament on es col·loquen les mines i així realitzar proves deterministes.
		// Utilitzem el paràmetre xPlayer per seleccionar diferents configuracions predefinides de mines.
		// En aquest cas, només fem servir la configuració 1, que genera un tauler fix de 30 mines.
		
		switch(xPlayer) {
		case 1: // Posició del tauler 1: mines predefinides per un cas de prova on el jugador pot guanyar.
			tauler[0][0].setMina();
			tauler[1][1].setMina();
			tauler[1][3].setMina();
			tauler[1][4].setMina();
			tauler[1][5].setMina();
			tauler[1][9].setMina();
			tauler[1][10].setMina();
			tauler[1][11].setMina();
			tauler[1][12].setMina();
			tauler[2][10].setMina();
			tauler[3][1].setMina();
			tauler[3][4].setMina();
			tauler[4][11].setMina();
			tauler[5][2].setMina();
			tauler[5][6].setMina();
			tauler[5][9].setMina();
			tauler[6][1].setMina();
			tauler[6][2].setMina();
			tauler[7][4].setMina();
			tauler[7][11].setMina();
			tauler[8][7].setMina();
			tauler[10][0].setMina();
			tauler[10][2].setMina();
			tauler[10][9].setMina();
			tauler[11][0].setMina();
			tauler[12][0].setMina();
			tauler[12][1].setMina();
			tauler[12][5].setMina();
			tauler[12][8].setMina();
			tauler[12][10].setMina();
			break;
			
		default:
			// Si es passa un valor no previst, no es generen mines (cas segur per defecte).
		}
	}
}
