package dev.danipraivet.juegodelucha.window;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Objects;

public class MenuPrincipal extends JPanel {

    public MenuPrincipal(VentanaJuego ventana) {
        setLayout(new GridBagLayout());
        setBackground(Color.DARK_GRAY); // Fondo oscuro

        try {
            File archivoFuente = new File(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource("/BigBlueTermPlusNerdFont-Regular.ttf")).getFile()));
            Font fuente = Font.createFont(Font.TRUETYPE_FONT, archivoFuente).deriveFont(44f);
            setFont(fuente);
            JButton btnJugar = crearBoton("Play", new Color(50, 205, 50), fuente); // Verde
            JButton btnSalir = crearBoton("Exit", new Color(220, 20, 60), fuente); // Rojo
            JButton btnControles = crearBoton("Controls", Color.orange, fuente);
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
        } catch (Exception e) {
            System.out.println("Error");
        }




    }

    private JButton crearBoton(String texto, Color colorFondo, Font fuente) {
        JButton boton = new JButton(texto);
        boton.setFont(fuente);
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        boton.setPreferredSize(new Dimension(300, 60));

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
