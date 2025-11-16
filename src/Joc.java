
public class Joc {
	
	private Tauler tauler;
    private boolean partidaAcabada;
    
    public void clicDret(int x, int y) {
        if (partidaAcabada)
            return;

        tauler.changeBandera(x, y);
    }

}
