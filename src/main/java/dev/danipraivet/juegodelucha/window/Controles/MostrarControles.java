package dev.danipraivet.juegodelucha.window.Controles;

import dev.danipraivet.juegodelucha.window.MenuPrincipal;
import dev.danipraivet.juegodelucha.window.VentanaJuego;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Objects;

public abstract class MostrarControles extends JPanel {
    private MenuPrincipal menu;

    public MostrarControles(VentanaJuego ventana, MenuPrincipal menu) {
        setLayout(new GridBagLayout());
        setBackground(Color.DARK_GRAY);


        File archivoFuente = new File(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource("/BigBlueTermPlusNerdFont-Regular.ttf")).getFile()));
        Font fuente = null;
        try {
            fuente = Font.createFont(Font.TRUETYPE_FONT, archivoFuente).deriveFont(44f);
        } catch (Exception e) {
            System.out.println(e);
        }
        setFont(fuente);


        if (this.getClass().getName().equalsIgnoreCase("dev.danipraivet.juegodelucha.window.Controles.ControlesJugador")) {
            /*
             * Add Player 1 bindings
             */
            JLabel player1Title = new JLabel("Player 1");
            player1Title.setForeground(Color.WHITE);
            player1Title.setFont(fuente);


            JTextArea player1Bindings = new JTextArea("""
                Movement:
                    Jump: W
                    Move Left: A
                    Move Right: D
                    Fast Fall: S (while in air)
               \s
                Attacks:
                   \s
                     - Grounded
                         Neutral Light: E (grounded)
                         Side Light: A/D + E (grounded)
                         Down Light: S + E (grounded)
               \s
                     - Aerial
                         Neutral Air: E (On air)
                         Side Air: A/D + E (On air)
               \s""");
            getjTextArea(player1Bindings, fuente);
            player1Bindings.setEditable(false);


            JButton btnSalir = crearBoton("Return", Color.GRAY, fuente);
            btnSalir.addActionListener(e -> ventana.mostrarMenu());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(15, 15, 15, 15);

            gbc.gridx = 0;
            gbc.gridy = 1;
            add(player1Title, gbc);

            gbc.gridy = 2;
            add(player1Bindings, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            add(btnSalir, gbc);


        } if (this.getClass().getName().equalsIgnoreCase("dev.danipraivet.juegodelucha.window.Controles.ControlesEnemigo")) {
            /*
             * Add Player 2 bindings
             */
            JLabel player2Title = new JLabel("Player 2");
            player2Title.setForeground(Color.WHITE);
            player2Title.setFont(fuente);

            JButton btnSalir = crearBoton("Return", Color.GRAY, fuente);
            btnSalir.addActionListener(e -> ventana.mostrarMenu());

            JTextArea player2Bindings = new JTextArea("""
                Movement:
                    Jump: UP arrow
                    Move Left: LEFT arrow
                    Move Right: RIGHT arrow
                    Fast Fall: DOWN arrow (while in air)

                Attacks:
               \s
                    - Grounded
                        Neutral Light: P
                        Side Light: LEFT/RIGHT arrow + P
                        Down Light: DOWN arrow + P (grounded)
               \s
                        - Aerial
                        Neutral Air: P (On air)
                        Side Air: LEFT/RIGHT arrow + P (On air)
               \s""");
            getjTextArea(player2Bindings, fuente);
            player2Bindings.setEditable(false);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(15, 15, 15, 15);

            gbc.gridx = 0;
            gbc.gridy = 1;
            add(player2Title, gbc);

            gbc.gridy = 2;
            add(player2Bindings, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            add(btnSalir, gbc);
        } else {
            System.out.println("Error");
        }
    }

    public void getjTextArea(JTextArea ta, Font fuente) {
        JTextArea player1Bindings = new JTextArea();
        ta.setFont(fuente.deriveFont(14f));
        ta.setForeground(Color.WHITE);
        ta.setBackground(Color.DARK_GRAY);
    }

    private JButton crearBoton(String texto, Color colorFondo, Font fuente) {
        JButton boton = new JButton(texto);
        boton.setFont(fuente);
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        boton.setPreferredSize(new Dimension(300, 80));

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorFondo.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorFondo);
            }
        });

        return boton;
    }
}
