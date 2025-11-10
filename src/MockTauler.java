
public class MockTauler extends Tauler{
	
	// Mock object de la classe Tauler per facilitar els casos de prova que requereixen generació de taulers especifics.
	// Com la generació de mines és random, necessitem taulers amb les mines posicionades manualment per nosaltres.
	
	public MockTauler() {
		super();
	}
	
	
	@Override
    public void generaMinesRandom(int xPlayer, int yPlayer) {
        
    }
    }

}
	
