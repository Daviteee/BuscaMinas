package model;

import java.util.Random;

public class MockRandom extends Random{

	int opcio1, opcio2, n_mina;
	boolean is_x;

	public MockRandom(int opcio1, int opcio2){
		// Guardem quins valors volem que nextInt retorni segons el cas de prova.
		this.opcio1 = opcio1; // Indica quina casella base volem utilitzar
		this.opcio2 = opcio2; // Indica quina configuració de nombre de mines utilitzarem
		this.n_mina  = 0;     // Comptador per iterar entre les coordenades de mines
		this.is_x = true;     // Controla si retornem la coordenada X o la Y
	}

	public int nextInt(int x) {
		// Retorna els valors que nosaltres volem entre (0 - x)
		// Es fa servir per controlar exactament on es generen les mines durant els tests
		
		int[][][][] valor = {
				// Casella 0,0
				{	
					{{0,1}},                 // 1 mina
					{{0,1},{1,0}},           // 2 mines
					{{0,1},{1,0},{1,1}}      // 3 mines
				},
				// Casella 6,0
				{	
					{{5,0}},                                // 1 mina
					{{5,0},{5,1}},                          // 2 mines
					{{5,0},{5,1},{6,1}},                    // 3 mines
					{{5,0},{5,1},{6,1},{7,0}},              // 4 mines
					{{5,0},{5,1},{6,1},{7,0},{7,1}}         // 5 mines
				},
				// Casella 12,0
				{
					{{12, 1}},                      // 1 mina
					{{12, 1},{11, 0}},              // 2 mines
					{{12, 1},{11, 0},{11, 1}}       // 3 mines
				},
				// Casella 12,6
				{
					{{12, 5}},                                 // 1 mina
					{{12, 5}, {11, 5}},                        // 2 mines
					{{12, 5}, {11, 5}, {11, 6}},               // 3 mines
					{{12, 5}, {11, 5}, {11, 6}, {11, 7}},      // 4 mines
					{{12, 5}, {11, 5}, {11, 6}, {11, 7}, {12, 7}} // 5 mines
				},
				// Casella 12,12
				{
					{{12, 11}},                          // 1 mina
					{{12, 11}, {11, 11}},                // 2 mines
					{{12, 11},{11, 11},{11, 12}}         // 3 mines
				},
				// Casella 6,12
				{
					{{6, 11}},                             // 1 mina
					{{6, 11}, {5, 11}},                    // 2 mines
					{{6, 11},{5, 11},{7, 11}},             // 3 mines
					{{6, 11},{5, 11},{7, 11}, {5, 12}},    // 4 mines
					{{6, 11},{5, 11},{7, 11}, {5, 12}, {7, 12}} // 5 mines
				},
				// Casella 0,12
				{
					{{0, 11}},                     // 1 mina
					{{0, 11}, {1, 11}},            // 2 mines
					{{0, 11},{1, 11},{1, 12}}      // 3 mines
				},
				// Casella 0,6
				{
					{{0, 5}},                            // 1 mina
					{{0, 5}, {1, 5}},                    // 2 mines
					{{0, 5}, {1, 5}, {1, 6}},            // 3 mines
					{{0, 5}, {1, 5}, {1, 6}, {1, 7}},    // 4 mines
					{{0, 5}, {1, 5}, {1, 6}, {1, 7}, {0, 7}} // 5 mines
				},
				// Casella 6,6
				{
					{{5, 5}},                                      // 1 mina
					{{5, 5}, {5, 6}},                               // 2 mines
					{{5, 5}, {5, 6}, {5, 7}},                       // 3 mines
					{{5, 5}, {5, 6}, {5, 7}, {6, 5}},               // 4 mines
					{{5, 5}, {5, 6}, {5, 7}, {6, 5}, {6, 7}},       // 5 mines
					{{5, 5}, {5, 6}, {5, 7}, {6, 5}, {6, 7}, {7, 5}}, // 6 mines
					{{5, 5}, {5, 6}, {5, 7}, {6, 5}, {6, 7}, {7, 5}, {7, 6}}, // 7 mines
					{{5, 5}, {5, 6}, {5, 7}, {6, 5}, {6, 7}, {7, 5}, {7, 6}, {7, 7}} // 8 mines
				},
				// Cas particular: totes les caselles són mines menys 0,0 i les del voltant
				{
					// Llista extensa de coordenades per omplir quasi tota la graella
					{{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},{0,8},{0,9},{0,10},{0,11},{0,12},
                    {1,2},{1,3},{1,4},{1,5},{1,6},{1,7},{1,8},{1,9},{1,10},{1,11},{1,12},
                    {2,0},{2,1},{2,2},{2,3},{2,4},{2,5},{2,6},{2,7},{2,8},{2,9},{2,10},{2,11},{2,12},
                    {3,0},{3,1},{3,2},{3,3},{3,4},{3,5},{3,6},{3,7},{3,8},{3,9},{3,10},{3,11},{3,12},
                    {4,0},{4,1},{4,2},{4,3},{4,4},{4,5},{4,6},{4,7},{4,8},{4,9},{4,10},{4,11},{4,12},
                    {5,0},{5,1},{5,2},{5,3},{5,4},{5,5},{5,6},{5,7},{5,8},{5,9},{5,10},{5,11},{5,12},
                    {6,0},{6,1},{6,2},{6,3},{6,4},{6,5},{6,6},{6,7},{6,8},{6,9},{6,10},{6,11},{6,12},
                    {7,0},{7,1},{7,2},{7,3},{7,4},{7,5},{7,6},{7,7},{7,8},{7,9},{7,10},{7,11},{7,12},
                    {8,0},{8,1},{8,2},{8,3},{8,4},{8,5},{8,6},{8,7},{8,8},{8,9},{8,10},{8,11},{8,12},
                    {9,0},{9,1},{9,2},{9,3},{9,4},{9,5},{9,6},{9,7},{9,8},{9,9},{9,10},{9,11},{9,12},
                    {10,0},{10,1},{10,2},{10,3},{10,4},{10,5},{10,6},{10,7},{10,8},{10,9},{10,10},{10,11},{10,12},
                    {11,0},{11,1},{11,2},{11,3},{11,4},{11,5},{11,6},{11,7},{11,8},{11,9},{11,10},{11,11},{11,12},
                    {12,0},{12,1},{12,2},{12,3},{12,4},{12,5},{12,6},{12,7},{12,8},{12,9},{12,10},{12,11},{12,12}}
				}
		};

		// Aquest mecanisme fa que el mock retorni primer la coordenada X i després la Y
		if(is_x) {
			is_x = false;
			return valor[opcio1][opcio2][n_mina][0];
		}else{
			is_x = true;
			return valor[opcio1][opcio2][n_mina++][1];
		}
	}
}
