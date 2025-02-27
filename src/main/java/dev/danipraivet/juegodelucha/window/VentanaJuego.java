package dev.danipraivet.juegodelucha.window;

import dev.danipraivet.juegodelucha.ControlesJuego;

import javax.swing.*;

public class VentanaJuego extends JFrame {
    public static PanelJuego PANEL;
    public static final int ANCHO_VENTANA = 1280;
    public static final int ALTO_VENTANA = 720;

    public VentanaJuego() {
        setTitle("Ñawlhalla");
        setSize(ANCHO_VENTANA, ALTO_VENTANA);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
        setFocusable(true);
        setResizable(false);
        setAutoRequestFocus(true);
        setLocationRelativeTo(null);

        PANEL = new PanelJuego(this);

        add(PANEL);

        addKeyListener(new ControlesJuego());
        SwingUtilities.invokeLater(this::requestFocusInWindow);
    }
}
