
package vista;

import controlador.Joc;

import javax.swing.*;
import java.awt.*;

public class BuscaminesVista extends JFrame {

    private JButton[][] botons;
    private static final int MIDA = 13;   
    private final Joc joc;

    public BuscaminesVista(Joc joc) {
    	this.joc = joc;
    }
    
    public void  initVista() {

        setTitle("Buscaminas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(MIDA, MIDA));

        botons = new JButton[MIDA][MIDA];

        for (int i = 0; i < MIDA; i++) {
            for (int j = 0; j < MIDA; j++) {
                JButton b = new JButton();
                b.setPreferredSize(new Dimension(50, 50));
                botons[i][j] = b;

                final int x = i;
                final int y = j;

                // Clic esquerre -> destapar casella
                b.addActionListener(e -> {
                    joc.clicEsquerra(x, y);
                    actualitzar();
                });


                // Clic dret -> posar/treure bandera
                b.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                            joc.clicDret(x, y);
                            actualitzar();
                        }
                    }
                });

                add(b);
            }
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
        actualitzar();
    }

    // Llegeix el tauler des de joc.getTauler() i actualitza tots els botons.
    // Detecta si la partida ha acabat i mostra missatge de victòria/derrota.
    public void actualitzar() {
        // pot ser que joc.getTauler() sigui null si no s'ha inicialitzat; protegim-nos
        if (joc == null) return;

        // actualitzar la quadrícula segons el model
        for (int i = 0; i < MIDA; i++) {
            for (int j = 0; j < MIDA; j++) {
                try {
                    JButton b = botons[i][j];

                    if (joc.isDestapat(i, j)) {
                        // casella destapada: mostrar mina o numero i desactivar
                        if (joc.isMina(i, j)) {
                            b.setText("💣");
                        } else {
                            int n = joc.getNumMinesVoltant(i, j);
                            b.setText(n == 0 ? "" : String.valueOf(n));
                        }
                        b.setEnabled(false);
                    } else {
                        // casella tapada
                        if (joc.isBandera(i, j)) {
                            b.setText("🚩");
                        } else {
                            b.setText("");
                        }
                        b.setEnabled(!joc.getPartidaAcabada()); // Si partida acabada, no es pot clicar
                    }
                } catch (IllegalArgumentException ex) {
                    // Si getCasella lança excepció (clic fora dels límits) simplement ignorem
                    botons[i][j].setEnabled(false);
                    botons[i][j].setText("");
                }
            }
        }

        // Si la partida està acabada, detectem si s'ha perdut (alguna mina destapada) o s'ha guanyat
        if (joc.getPartidaAcabada()) {
            boolean minaDestapada = false;
            outer:
            for (int i = 0; i < MIDA; i++) {
                for (int j = 0; j < MIDA; j++) {
                    try {
                        if (joc.isMina(i, j) && joc.isDestapat(i, j)) {
                            minaDestapada = true;
                            break outer;
                        }
                    } catch (IllegalArgumentException ignored) {}
                }
            }
            if (minaDestapada) {
                JOptionPane.showMessageDialog(this, "¡Has perdido!");
            } else {
                JOptionPane.showMessageDialog(this, "¡Has ganado!");
            }
        }
    }
}
