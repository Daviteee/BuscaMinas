package model;

import java.util.Random;

public class MockRandom extends Random{
	int opcio1,opcio2,n_mina;
	boolean is_x;
	MockRandom(int opcio1,int opcio2){
		this.opcio1 = opcio1; //Per saber quins valors de x o y retornar
		this.opcio2 = opcio2;
		this.n_mina  = 0;
		this.is_x = true;
	}
	public int nextInt(int x) {
		// Retornar els valors que nosaltres volguem entre (0 - x)
		int[][][][] valor = {
				//Casella 0-0 
				{	
					{{0,1}}, //1mina
					{{0,1},{1,0}}, //2mines
					{{0,1},{1,0},{1,1}} //3mines
				},
				//Casella 6-0
				{	
					{{5,0}}, //1mina
					{{5,0},{5,1}}, //2mines
					{{5,0},{5,1},{6,1}},	//3mines
					{{5,0},{5,1},{6,1},{7,0}},	//4mines
					{{5,0},{5,1},{6,1},{7,0},{7,1}}	//5mines
				},
				// Casella 12, 0
				{
					{{12, 1}}, // 1 mina
					{{12, 1},{11, 0}}, // 2 mines
					{{12, 1},{11, 0},{11, 1}} // 3 mines
				},
				// Casella 12, 6
				{
					{{12, 5}}, // 1 mina
					{{12, 5}, {11, 5}}, // 2 mines
					{{12, 5}, {11, 5}, {11, 6}}, // 3 mines
					{{12, 5}, {11, 5}, {11, 6}, {11, 7}}, // 4 mines
					{{12, 5}, {11, 5}, {11, 6}, {11, 7}, {12, 7}} // 5 mines
				},
				// Casella 12, 12
				{
					{{12, 11}}, // 1 mina
					{{12, 11}, {11, 11}}, // 2 mines
					{{12, 11},{11, 11},{11, 12}} // 3 mines
				},
				// Casella 6, 12
				{
					{{6, 11}}, // 1 mina
					{{6, 11}, {5, 11}}, // 2 mines
					{{6, 11},{5, 11},{7, 11}}, // 3 mines
					{{6, 11},{5, 11},{7, 11}, {5, 12}}, // 4 mines
					{{6, 11},{5, 11},{7, 11}, {5, 12}, {7, 12}} // 5 mines
				},
				// Casella 0, 12
				{
					{{0, 11}}, // 1 mina
					{{0, 11}, {1, 11}}, // 2 mines
					{{0, 11},{1, 11},{1, 12}} // 3 mines
				},
				// Casella 0, 6
				{
					{{0, 5}}, // 1 mina
					{{0, 5}, {1, 5}}, // 2 mines
					{{0, 5}, {1, 5}, {1, 6}}, // 3 mines
					{{0, 5}, {1, 5}, {1, 6}, {1, 7}}, // 4 mines
					{{0, 5}, {1, 5}, {1, 6}, {1, 7}, {0, 7}} // 5 mines
				},
				// Casella 6, 6
				{
					{{5, 5}}, // 1 mina
					{{5, 5}, {5, 6}}, // 2 mines
					{{5, 5}, {5, 6}, {5, 7}}, // 3 mines
					{{5, 5}, {5, 6}, {5, 7}, {6, 5}}, // 4 mines
					{{5, 5}, {5, 6}, {5, 7}, {6, 5}, {6, 7}}, // 5 mines
					{{5, 5}, {5, 6}, {5, 7}, {6, 5}, {6, 7}, {7, 5}}, // 6 mines
					{{5, 5}, {5, 6}, {5, 7}, {6, 5}, {6, 7}, {7, 5}, {7, 6}}, // 7 mines
					{{5, 5}, {5, 6}, {5, 7}, {6, 5}, {6, 7}, {7, 5}, {7, 6}, {7, 7}} // 8 mines
				}
		};
		if(is_x) {
			is_x = false;
			
			return valor[opcio1][opcio2][n_mina][0];
		}else
		{
			is_x = true;
			return valor[opcio1][opcio2][n_mina++][1];
		}
		
	}
		
}
