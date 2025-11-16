package vista;

import controlador.Joc;
import model.Casella;

import javax.swing.*;
import java.awt.*;

public class BuscaminesVista extends JFrame {

    private final JButton[][] botons;
    private final int mida;
    private final Joc joc;

    public BuscaminesVista(Joc joc, int mida) {
        this.joc = joc;
        this.mida = mida;

        setTitle("Buscaminas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(mida, mida));

        botons = new JButton[mida][mida];

        for (int i = 0; i < mida; i++) {
            for (int j = 0; j < mida; j++) {
                JButton b = new JButton();
                b.setPreferredSize(new Dimension(40, 40));
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

        // primer pintat segons l'estat actual del tauler
        actualitzar();
    }

    // Llegeix el tauler des de joc.getTauler() i actualitza tots els botons.
    // Detecta si la partida ha acabat i mostra missatge de victòria/derrota.
    public void actualitzar() {
        // pot ser que joc.getTauler() sigui null si no s'ha inicialitzat; protegim-nos
        if (joc == null || joc.getTauler() == null) return;

        // actualitzar la quadrícula segons el model
        for (int i = 0; i < mida; i++) {
            for (int j = 0; j < mida; j++) {
                try {
                    var c = joc.getTauler().getCasella(i, j);
                    JButton b = botons[i][j];

                    if (c.isDestapat()) {
                        // casella destapada: mostrar mina o numero i desactivar
                        if (c.isMina()) {
                            b.setText("💣");
                        } else {
                            int n = c.getNumMinesVoltant();
                            b.setText(n == 0 ? "" : String.valueOf(n));
                        }
                        b.setEnabled(false);
                    } else {
                        // casella tapada
                        if (c.isBandera()) {
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
            for (int i = 0; i < mida; i++) {
                for (int j = 0; j < mida; j++) {
                    try {
                        var c = joc.getTauler().getCasella(i, j);
                        if (c.isMina() && c.isDestapat()) {
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
