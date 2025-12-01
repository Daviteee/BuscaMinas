package model;

import java.util.Random;

public class MockRandom extends Random{
	public int nextInt(int x) {
		// Retornar els valors que nosaltres volguem entre (0 - x)
		return -1;
	}
}
