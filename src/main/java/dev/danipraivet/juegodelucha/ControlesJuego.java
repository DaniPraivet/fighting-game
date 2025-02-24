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

            // 87 -> w
            case 87 -> {
                panel.getJugador().saltar();
                panel.repaint();
            }

            // arrowKLeft -> 37
            case 37 -> {
                panel.getEnemigo().mover(-VELOCIDAD);
                panel.repaint();
            }

            // arrowKRight -> 39
            case 39 -> {
                panel.getEnemigo().mover(VELOCIDAD);
                panel.repaint();
            }

            // arrowKUp -> 38
            case 38 -> {
                panel.getEnemigo().saltar();
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
