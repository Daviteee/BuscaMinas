
package model;

import java.util.Random;

public class MockTauler extends Tauler{
	
	// Mock object de la classe Tauler per facilitar els casos de prova que requereixen generació de taulers especifics.
	// Com la generació de mines és random, necessitem taulers amb les mines posicionades manualment per nosaltres.
	
	public MockTauler(Random r) {
		super(r);
	}
	
	
	@Override
    public void generaMinesRandom(int xPlayer, int yPlayer) {
        //Fem un override de la funció generaMinesRandom perquè no sigui random i per tant tenir un control d'on es possan i poder realitzar comprovacions a altres mètodes.
		//Utilitzarem els paràmetres de xPlayer i yPlayer per realitzar els diferents casos (2 casos). Realitzem un tauler predefinit de 30 mines per poder fer la simulació del joc
		//i comprovar que funciona correctament tot.
		switch(xPlayer) {
		case 1: // Posició del tauler 1 de les mines predefinides per guanyar.
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

			
		default:
		}
	}
   
}
