package vista;

import controlador.Joc;

public class MockBuscaminesVista extends BuscaminesVista{

	//Mock object per a quan realitzem els test de joc no ens mostri la vista, ja que no és necesària.
	public MockBuscaminesVista(Joc joc) {
		super(joc);
	}
	//Aquesta funció no fa res ja que no ha d'iniciar cap vista
	public void initVista() {
		// NO FA RES
	}
	//Aquesta funció no fa res perquè només volem que no es mostri la vista quan realitzem els tests de joc
	public void actualitzar() {		
	}
}

