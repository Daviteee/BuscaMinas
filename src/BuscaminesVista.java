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

}
