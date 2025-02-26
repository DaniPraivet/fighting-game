package dev.danipraivet.juegodelucha.window;

import dev.danipraivet.juegodelucha.ControlesJuego;

import javax.swing.*;

public class VentanaJuego extends JFrame {
    public static PanelJuego PANEL;

    public VentanaJuego() {
        setTitle("Ñawlhalla");
        setSize(1920, 1080);
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
