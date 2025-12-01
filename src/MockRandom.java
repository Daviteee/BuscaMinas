package model;

import java.util.Random;

public class MockRandom extends Random{
	int opcio1,opcio2,n_mines;
	boolean is_x;
	MockRandom(int opcio1,int opcio2,int n_mines){
		this.opcio1 = opcio1; //Per saber quins valors de x o y retornar
		this.opcio2 = opcio2;
		this.n_mines  = n_mines;
		this.is_x = true;
	}
	public int nextInt(int x) {
		// Retornar els valors que nosaltres volguem entre (0 - x)
		int[][][][] valor = {
				//Casella 0-0 
				{	
					{{}},
					{{0,1}}, //1mina
					{{0,1},{1,0}}, //2mines
					{{0,1},{1,0},{1,1}} //3mines
				},
				//Casella 6-0
				{	
					{{}},
					{{5,0}}, //1mina
					{{5,0},{5,1}}, //2mines
					{{5,0},{5,1},{6,1}},	//3mines
					{{5,0},{5,1},{6,1},{7,0}},	//4mines
					{{5,0},{5,1},{6,1},{7,0},{7,1}}	//5mines
				}		
		};
		if(is_x) {
			is_x = false;
			return valor[opcio1][opcio2][n_mines][0];
		}else
		{
			is_x = true;
			return valor[opcio1][opcio2][n_mines][1];
		}
		
	}
		
}
