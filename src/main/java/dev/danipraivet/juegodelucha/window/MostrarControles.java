package dev.danipraivet.juegodelucha.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MostrarControles extends JPanel {
    private MenuPrincipal menu;

    public MostrarControles(VentanaJuego ventana, MenuPrincipal menu) {
        setLayout(new GridBagLayout());
        setBackground(Color.DARK_GRAY);

        /*
         * Add Player 1 bindings
         */
        JLabel player1Title = new JLabel("Player 1");
        player1Title.setForeground(Color.WHITE);
        player1Title.setFont(new Font("Arial", Font.BOLD, 50));


        JTextArea player1Bindings = getjTextArea("""
                Movement:
                Jump: W
                Move Left: A
                Move Right: D
                Fast Fall: S (while in air)

                Attacks:
                Neutral Light: E
                Side Light: A/D + E
                Down Light: S + E (grounded)
                """);

        /*
         * Add Player 2 bindings
         */
        JLabel player2Title = new JLabel("Player 2");
        player2Title.setForeground(Color.WHITE);
        player2Title.setFont(new Font("Arial", Font.BOLD, 50));

        JTextArea player2Bindings = getjTextArea("""
                Movement:
                Jump: UP arrow
                Move Left: LEFT arrow
                Move Right: RIGHT arrow
                Fast Fall: DOWN arrow (while in air)

                Attacks:
                Neutral Light: P
                Side Light: LEFT/RIGHT arrow + P
                Down Light: DOWN arrow + P (grounded)
                """);

        JButton btnSalir = crearBoton("Return", Color.GRAY);
        btnSalir.addActionListener(e -> ventana.mostrarMenu());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15,15,15,15);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(player1Title, gbc);

        gbc.gridy = 2;
        add(player1Bindings, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        add(btnSalir, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        add(player2Title, gbc);

        gbc.gridy = 2;
        add(player2Bindings, gbc);


    }

    private static JTextArea getjTextArea(String text) {
        JTextArea player1Bindings = new JTextArea(text);
        player1Bindings.setFont(new Font("Arial", Font.PLAIN, 24));
        player1Bindings.setForeground(Color.WHITE);
        player1Bindings.setBackground(Color.DARK_GRAY);
        return player1Bindings;
    }

    private JButton crearBoton(String texto, Color colorFondo) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 24));
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        boton.setPreferredSize(new Dimension(200, 60));

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
