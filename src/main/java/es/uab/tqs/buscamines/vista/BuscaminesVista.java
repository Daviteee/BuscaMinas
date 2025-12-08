package es.uab.tqs.buscamines.vista;

import es.uab.tqs.buscamines.controlador.Joc;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class BuscaminesVista extends JFrame implements Vista{

    private JButton[][] caselles;
    private static final int MIDA = 13;
    private JLabel contBanderes;
    private final Joc joc;
    private int minaX = -1;
    private int minaY = -1;
    private boolean mensajeMostrado = false;

    private final Color COLOR_TAPADA = new Color(192, 192, 192);
    private final Color COLOR_DESTAPADA = new Color(240, 240, 240);
    private final Color COLOR_BANDERA = new Color(255, 220, 140);
    private final Color COLOR_HOVER = new Color(210, 210, 200);
    private final Color COLOR_MINA_EXPLOTADA = new Color(255, 60, 60);

    public BuscaminesVista(Joc joc) {
        this.joc = joc;
    }

    public void initVista() {
        setTitle("Buscaminas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contBanderes = new JLabel("🚩 30");
        contBanderes.setFont(new Font("Segoe UI Emoji", Font.BOLD, 18));
        top.add(contBanderes);
        add(top, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(MIDA, MIDA));
        caselles = new JButton[MIDA][MIDA];

        for (int i = 0; i < MIDA; i++) {
            for (int j = 0; j < MIDA; j++) {
                JButton b = crearCasilla(i, j);
                caselles[i][j] = b;
                grid.add(b);
            }
        }

        add(grid, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        actualitzar();
    }

    private JButton crearCasilla(int x, int y) {
        JButton b = new JButton();
        b.setPreferredSize(new Dimension(45, 45));
        b.setFont(new Font("Segoe UI Emoji", Font.BOLD, 24));
        b.setMargin(new Insets(0, 0, 0, 0));
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setBackground(COLOR_TAPADA);
        b.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        b.addActionListener(e -> {
            if (joc.getPartidaAcabada()) return;

            joc.clicEsquerra(x, y);
            if (joc.isMina(x, y) && joc.isDestapat(x, y)) {
                minaX = x;
                minaY = y;
                mostrarTableroFinal();
            } else if (joc.getPartidaAcabada()) {
                actualitzar();
            } else {
                actualizarConOnda(x, y);
            }
        });

        b.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!joc.isDestapat(x, y) && !joc.isBandera(x, y) && !joc.getPartidaAcabada()) {
                    b.setBackground(COLOR_HOVER);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!joc.isDestapat(x, y) && !joc.isBandera(x, y) && !joc.getPartidaAcabada()) {
                    b.setBackground(COLOR_TAPADA);
                } else if (joc.isBandera(x, y) && !joc.getPartidaAcabada()) {
                    b.setBackground(COLOR_BANDERA);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e) && !joc.getPartidaAcabada()) {
                    joc.clicDret(x, y);
                    actualizarCasilla(x, y);
                    contBanderes.setText("🚩 " + joc.getBanderesRestants());
                }
            }
        });

        return b;
    }

    private Color getNumeroColor(int n) {
        return switch (n) {
            case 1 -> Color.BLUE;
            case 2 -> new Color(0, 128, 0);
            case 3 -> Color.RED;
            case 4 -> new Color(128, 0, 128);
            case 5 -> new Color(128, 64, 0);
            case 6 -> new Color(0, 128, 128);
            case 7 -> Color.BLACK;
            case 8 -> Color.DARK_GRAY;
            default -> Color.BLACK;
        };
    }

    private void actualizarCasilla(int i, int j) {
        JButton b = caselles[i][j];

        if (joc.getPartidaAcabada() && joc.isMina(i, j)) {
            b.setText("💣");
            b.setForeground(Color.WHITE);
            b.setBackground((i == minaX && j == minaY) ? COLOR_MINA_EXPLOTADA : Color.DARK_GRAY);
        } else if (joc.isDestapat(i, j)) {
            int n = joc.getNumMinesVoltant(i, j);
            b.setBackground(COLOR_DESTAPADA);
            if (n > 0) {
                b.setText(String.valueOf(n));
                b.setForeground(getNumeroColor(n));
            } else {
                b.setText("");
            }
        } else {
            if (joc.isBandera(i, j)) {
                b.setText("🚩");
                b.setForeground(Color.RED.darker());
                b.setBackground(COLOR_BANDERA);
            } else {
                b.setText("");
                b.setBackground(COLOR_TAPADA);
            }
        }
    }

    public void actualitzar() {
        for (int i = 0; i < MIDA; i++) {
            for (int j = 0; j < MIDA; j++) {
                actualizarCasilla(i, j);
            }
        }

        contBanderes.setText("🚩 " + joc.getBanderesRestants());

        if (joc.getPartidaAcabada() && !mensajeMostrado) {
            mensajeMostrado = true;
            mostrarMensajeFin();
        }
    }

    private void mostrarTableroFinal() {
        actualitzar();
    }

    private void mostrarMensajeFin() {
        boolean partidaPerdida = (minaX != -1);
        String mensajeResultado = partidaPerdida ? "¡Has perdido!" : "¡Has ganado!";
        String titulo = partidaPerdida ? "Fin de la Partida" : "¡Victoria!";

        int opcion = JOptionPane.showConfirmDialog(
                BuscaminesVista.this,
                mensajeResultado + "\n¿Quieres jugar otra partida?",
                titulo,
                JOptionPane.YES_NO_OPTION,
                partidaPerdida ? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE
        );

        if (opcion == JOptionPane.YES_OPTION) {
            mensajeMostrado = true;

            joc.reiniciarPartida();
            minaX = minaY = -1;

            mensajeMostrado = false;
            actualitzar();
        } else {
            System.exit(0);
        }
    }

    private void actualizarConOnda(int startX, int startY) {
        List<List<int[]>> capas = new ArrayList<>();
        int maxDist = 0;

        for (int d = 0; d < MIDA + MIDA; d++) {
            capas.add(new ArrayList<>());
        }

        boolean[][] yaActualizada = new boolean[MIDA][MIDA];

        for (int i = 0; i < MIDA; i++) {
            for (int j = 0; j < MIDA; j++) {
                if (joc.isDestapat(i, j)) {
                    int dist = Math.abs(i - startX) + Math.abs(j - startY);
                    if (dist < capas.size()) {
                        capas.get(dist).add(new int[]{i, j});
                        if (dist > maxDist) maxDist = dist;
                    }
                }
            }
        }

        final int finalMaxDist = maxDist;

        if (finalMaxDist == 0 && joc.getNumMinesVoltant(startX, startY) > 0) {
            actualitzar();
            return;
        }

        Timer timer = new Timer(40, null);
        final int[] capaIndex = {0};

        timer.addActionListener(e -> {
            if (capaIndex[0] > finalMaxDist) {
                timer.stop();
                actualitzar();
                return;
            }

            if (capaIndex[0] < capas.size()) {
                for (int[] pos : capas.get(capaIndex[0])) {
                    int x = pos[0], y = pos[1];
                    if (!yaActualizada[x][y]) {
                        yaActualizada[x][y] = true;
                        JButton b = caselles[x][y];
                        int n = joc.getNumMinesVoltant(x, y);
                        b.setBackground(COLOR_DESTAPADA);
                        b.setText(n > 0 ? String.valueOf(n) : "");
                        b.setForeground(getNumeroColor(n));
                    }
                }
            }

            capaIndex[0]++;
        });

        timer.start();
    }
}
