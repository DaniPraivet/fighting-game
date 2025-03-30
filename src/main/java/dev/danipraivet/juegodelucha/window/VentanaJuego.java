package dev.danipraivet.juegodelucha.window;

import dev.danipraivet.juegodelucha.ControlesJuego;

import javax.swing.*;

public class VentanaJuego extends JFrame {
    private MenuPrincipal menu;
    private MostrarControles controles;
    public static PanelJuego PANEL;
    public static final int ANCHO_VENTANA = 1920;
    public static final int ALTO_VENTANA = 1080;

    public VentanaJuego() {
        setTitle("Ñawlhalla");
        setSize(ANCHO_VENTANA, ALTO_VENTANA);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        menu = new MenuPrincipal(this);
        setContentPane(menu);

        setVisible(true);
    }

    public void iniciarJuego() {
        PANEL = new PanelJuego(this);
        setContentPane(PANEL);
        addKeyListener(new ControlesJuego());
        revalidate();
        repaint();
        PANEL.requestFocus();
        setFocusable(true);
        requestFocusInWindow();

    }

    public void mostrarControles() {
        controles = new MostrarControles(this);
        setContentPane(controles);
        setVisible(true);
    }
}
