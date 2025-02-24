package dev.danipraivet.juegodelucha;

import dev.danipraivet.juegodelucha.window.PanelJuego;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControlesJuego implements KeyListener {
    private final PanelJuego panel;
    private final int VELOCIDAD = 10;

    public ControlesJuego(PanelJuego panel) {
        this.panel = panel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            // 65 -> a
            case 65 -> {
                panel.getJugador().mover(-VELOCIDAD);
                panel.repaint();
            }

            // 68 -> d
            case 68 -> {
                panel.getJugador().mover(VELOCIDAD);
                panel.repaint();
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
