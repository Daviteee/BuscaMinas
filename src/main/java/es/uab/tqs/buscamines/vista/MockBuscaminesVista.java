package es.uab.tqs.buscamines.vista;

import es.uab.tqs.buscamines.controlador.Joc;
import es.uab.tqs.buscamines.vista.Vista;

public class MockBuscaminesVista implements Vista{

	//Mock object per a quan realitzem els test de joc no ens mostri la vista, ja que no és necesària.
	public MockBuscaminesVista() {
	}
	
	//Aquesta funció no fa res ja que no ha d'iniciar cap vista
	public void initVista() {
	}
	
	//Aquesta funció no fa res perquè només volem que no es mostri la vista quan realitzem els tests de joc
	public void actualitzar() {		
	}
}
