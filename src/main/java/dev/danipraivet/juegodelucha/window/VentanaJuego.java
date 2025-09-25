package dev.danipraivet.juegodelucha.window;

import dev.danipraivet.juegodelucha.ControlesJuego;
import dev.danipraivet.juegodelucha.window.Controles.ControlesEnemigo;
import dev.danipraivet.juegodelucha.window.Controles.ControlesJugador;
import dev.danipraivet.juegodelucha.window.Controles.MostrarControles;

import javax.swing.*;

public class VentanaJuego extends JFrame {
    private static MenuPrincipal menu;
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
        PANEL = new PanelJuego(this, true);
        setContentPane(PANEL);
        addKeyListener(new ControlesJuego());
        revalidate();
        repaint();
        PANEL.requestFocus();
        setFocusable(true);
        requestFocusInWindow();

    }

    public void mostrarControles() {
        ControlesJugador cj = new ControlesJugador(this, menu);
        ControlesEnemigo ce = new ControlesEnemigo(this, menu);
        setVisible(true);
        JTabbedPane j = new JTabbedPane();
        j.setBounds(0, 0, ANCHO_VENTANA, ALTO_VENTANA);
        j.addTab("Player 1", cj);
        j.addTab("Player 2", ce);
        setContentPane(j);
    }

    public void mostrarMenu() {
        menu = new MenuPrincipal(this);
        setContentPane(menu);
        setVisible(true);
    }
}
