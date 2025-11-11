
public class MockTauler extends Tauler{
	
	// Mock object de la classe Tauler per facilitar els casos de prova que requereixen generació de taulers especifics.
	// Com la generació de mines és random, necessitem taulers amb les mines posicionades manualment per nosaltres.
	
	public MockTauler() {
		super();
	}
	
	
	@Override
    public void generaMinesRandom(int xPlayer, int yPlayer) {
        //Fem un override de la funció generaMinesRandom perquè no sigui random i per tant tenir un control d'on es possan i poder realitzar comprovacions a altres mètodes.
		//Utilitzarem els paràmetres de xPlayer i yPlayer per realitzar els diferents casos.
		switch(xPlayer) {
		case 1: // Posició del tauler 0,0
			switch(yPlayer) {
			case 0:
				// No hiha cap mina al voltant de la posició 0,0
				break;
			case 1: // Cas on al voltant de la posició 0,0 hiha 1 mina.
				tauler[0][1].setMina();
				break;
			case 2: // Cas on al voltant de la posició 0,0 hi han 2 mines.
				tauler[0][1].setMina();
				tauler[1][0].setMina();
				break;
			case 3: // Cas on al voltant de la posició 0,0 hi han 3 mines (max).
				tauler[0][1].setMina();
				tauler[1][0].setMina();
				tauler[1][1].setMina();
				break;
			}
			break;
		case 2: // Posició del tauler 6,0
			switch(yPlayer) {
			case 0:
				// No hi ha cap miina al voltant de la posició 6,0
				break;
			case 1: // Cas on al voltant de la posició 6,0 hi ha 1 mina
				tauler[5][0].setMina();
				break;
			case 2: // Cas on al voltant de la posició 6,0 hi han 2 mines
				tauler[5][0].setMina();
				tauler[5][1].setMina();
				break;
			case 3: // Cas on al voltant de la posició 6,0 hi han 3 mines
				tauler[5][0].setMina();
				tauler[5][1].setMina();
				tauler[6][1].setMina();
				break;
			case 4: // Cas on al voltant de la posició 6,0 hi han 4 mines
				tauler[5][0].setMina();
				tauler[5][1].setMina();
				tauler[6][1].setMina();
				tauler[7][0].setMina();
				break;
			case 5: // Cas on al voltant de la posició 6,0 hi han 5 mines (max)
				tauler[5][0].setMina();
				tauler[5][1].setMina();
				tauler[6][1].setMina();
				tauler[7][0].setMina();
				tauler[7][1].setMina();
				break;
			}
			break;
		case 3: // Posició del tauer 12,0
			switch(yPlayer) {
			case 0:
				// No hi ha cap miina al voltant de la posició 12,0
				break;
			case 1: // Cas on al voltant de la posició 12,0 hiha 1 mina.
				tauler[12][1].setMina();
				break;
			case 2: // Cas on al voltant de la posició 12,0 hi han 2 mines.
				tauler[12][1].setMina();
				tauler[11][0].setMina();
				break;
			case 3: // Cas on al voltant de la posició 12,0 hi han 3 mines.
				tauler[12][1].setMina();
				tauler[11][0].setMina();
				tauler[11][1].setMina();
				break;
			}
			break;
		case 4: // Posició del tauler 12,6
			switch(yPlayer) {
			case 0:
				// No hiha cap mina al voltant de la posició 12,6
				break;
			case 1: // Cas on al voltant de la posició 12,6 hi han 1 mina.
				tauler[12][5].setMina();
				break;
			case 2: // Cas on al voltant de la posició 12,6 hi han 2 mines.
				tauler[12][5].setMina();
				tauler[11][5].setMina();
				break;
			case 3: // Cas on al voltant de la posició 12,6 hi han 3 mines.
				tauler[12][5].setMina();
				tauler[11][5].setMina();
				tauler[11][6].setMina();
				break;
			case 4: // Cas on al voltant de la posició 12,6 hi han 4 mines.
				tauler[12][5].setMina();
				tauler[11][5].setMina();
				tauler[11][6].setMina();
				tauler[11][7].setMina();
				break;
			case 5: // Cas on al voltant de la posició 12,6 hi han 5 mines.
				tauler[12][5].setMina();
				tauler[11][5].setMina();
				tauler[11][6].setMina();
				tauler[11][7].setMina();
				tauler[12][7].setMina();
				break;
			}
			break;
		case 5: // Posició del tauler 12,12
			switch(yPlayer) {
			case 0:
				// No hi ha cap miina al voltant de la posició 12,12
				break;
			case 1: // Cas on al voltant de la posició 12,12 hiha 1 mina.
				tauler[12][11].setMina();
				break;
			case 2: // Cas on al voltant de la posició 12,12 hi han 2 mines.
				tauler[12][11].setMina();
				tauler[11][11].setMina();
				break;
			case 3: // Cas on al voltant de la posició 12,12 hi han 3 mines.
				tauler[12][11].setMina();
				tauler[11][11].setMina();
				tauler[11][12].setMina();
				break;
			}
			break;
		case 6: // Posició del tauler 6,12
			switch(yPlayer) {
			case 0:
				// No hiha cap mina al voltant de la posició 6,12
				break;
			case 1: // Cas on al voltant de la posició 6,12 hi han 1 mina.
				tauler[6][11].setMina();
				break;
			case 2: // Cas on al voltant de la posició 6,12 hi han 2 mines.
				tauler[6][11].setMina();
				tauler[5][11].setMina();
				break;
			case 3: // Cas on al voltant de la posició 6,12 hi han 3 mines.
				tauler[6][11].setMina();
				tauler[5][11].setMina();
				tauler[7][11].setMina();
				break;
			case 4: // Cas on al voltant de la posició 6,12 hi han 4 mines.
				tauler[6][11].setMina();
				tauler[5][11].setMina();
				tauler[7][11].setMina();
				tauler[5][12].setMina();
				break;
			case 5: // Cas on al voltant de la posició 6,12 hi han 5 mines.
				tauler[6][11].setMina();
				tauler[5][11].setMina();
				tauler[7][11].setMina();
				tauler[5][12].setMina();
				tauler[7][12].setMina();
				break;
			}
			break;
		case 7: // Posició del tauler 0,12
			break;
		case 8: // Posició del tauler 6,0
			break;
		case 9: // Posició del tauler 6,6
			break;
		default:
		}
	}
   
}
