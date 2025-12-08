package es.uab.tqs.buscamines;
import java.util.Random;

import es.uab.tqs.buscamines.controlador.Joc;
import es.uab.tqs.buscamines.model.Tauler;
import es.uab.tqs.buscamines.vista.BuscaminesVista;
import es.uab.tqs.buscamines.vista.Vista;

public class Main {
    public static void main(String[] args) {

        //Classe Main per realitzar el joc real ja una vegada tot esta testejat correctament.
        Random r = new Random();
        Tauler t = new Tauler(r);
        // Crear joc amb vista incorporada
        Joc joc = new Joc(t);
        Vista vista  = new BuscaminesVista(joc); //Creem la vista real
        vista.initVista(); //La vista és genera.
        joc.crearVistaDelJoc(vista); 
    }
}
