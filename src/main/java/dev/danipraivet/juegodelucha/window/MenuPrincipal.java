package dev.danipraivet.juegodelucha.window;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JPanel {
    private VentanaJuego ventana;

    public MenuPrincipal(VentanaJuego ventana) {
        this.ventana = ventana;
        setLayout(new GridBagLayout());
        setBackground(Color.DARK_GRAY); // Fondo oscuro

        JButton btnJugar = crearBoton("Jugar", new Color(50, 205, 50)); // Verde
        JButton btnSalir = crearBoton("Salir", new Color(220, 20, 60)); // Rojo
        JButton btnControles = crearBoton("Controles", Color.orange);

        btnJugar.addActionListener(e -> ventana.iniciarJuego());
        btnControles.addActionListener(e -> ventana.mostrarControles());
        btnSalir.addActionListener(e -> System.exit(0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(btnJugar, gbc);

        gbc.gridy = 1;
        add(btnControles, gbc);

        gbc.gridy = 2;
        add(btnSalir, gbc);
    }

    private JButton crearBoton(String texto, Color colorFondo) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 24));
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        boton.setPreferredSize(new Dimension(200, 60));

        // Efecto de hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorFondo.darker()); // Oscurece al pasar el mouse
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorFondo);
            }
        });

        return boton;
    }
}
