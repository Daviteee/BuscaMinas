package vista;

import controlador.Joc;

import javax.swing.*;
import java.awt.*;

public class BuscaminesVista extends JFrame {

    private JButton[][] botons;
    private static final int MIDA = 13;

    private JLabel contBanderes;
    private final Joc joc;

    public BuscaminesVista(Joc joc) {
        this.joc = joc;
    }

    public void initVista() {

        setTitle("Buscaminas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        contBanderes = new JLabel("Banderas restantes: 30");
        contBanderes.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        top.add(contBanderes);
        add(top, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(MIDA, MIDA));
        botons = new JButton[MIDA][MIDA];

        for (int i = 0; i < MIDA; i++) {
            for (int j = 0; j < MIDA; j++) {
                JButton b = new JButton();
                b.setPreferredSize(new Dimension(50, 50));
                botons[i][j] = b;

                final int x = i;
                final int y = j;

                b.addActionListener(e -> {
                    joc.clicEsquerra(x, y);
                    actualitzar();
                });

                b.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                            joc.clicDret(x, y);
                            actualitzar();
                        }
                    }
                });

                grid.add(b);
            }
        }

        add(grid, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        actualitzar();
    }

    public void actualitzar() {

        if (joc == null) return;

        for (int i = 0; i < MIDA; i++) {
            for (int j = 0; j < MIDA; j++) {
                try {
                    JButton b = botons[i][j];

                    if (joc.isDestapat(i, j)) {
                        if (joc.isMina(i, j)) {
                            b.setText("💣");
                        } else {
                            int n = joc.getNumMinesVoltant(i, j);
                            b.setText(n == 0 ? "" : String.valueOf(n));
                        }
                        b.setEnabled(false);
                    } else {
                        if (joc.isBandera(i, j)) {
                            b.setText("🚩");
                        } else {
                            b.setText("");
                        }
                        b.setEnabled(!joc.getPartidaAcabada());
                    }
                } catch (IllegalArgumentException ex) {
                    botons[i][j].setEnabled(false);
                    botons[i][j].setText("");
                }
            }
        }

        contBanderes.setText("🚩 " + joc.getBanderesRestants());

        // Detectar final de partida
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
