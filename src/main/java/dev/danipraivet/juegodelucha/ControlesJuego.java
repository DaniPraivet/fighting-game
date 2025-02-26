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
            // Controles Jugador
            case 65 -> panel.getJugador().mover(-VELOCIDAD); // A
            case 68 -> panel.getJugador().mover(VELOCIDAD);  // D
            case 87 -> panel.getJugador().saltar();          // W
            case 83 -> panel.getJugador().acelerarCaida();   // S
            case 69 -> panel.getJugador().atacar(panel.getEnemigo()); // E

            // Controles Enemigo
            case 37 -> panel.getEnemigo().mover(-VELOCIDAD); // ←
            case 39 -> panel.getEnemigo().mover(VELOCIDAD);  // →
            case 38 -> panel.getEnemigo().saltar();          // ↑
            case 40 -> panel.getEnemigo().acelerarCaida();   // ↓
            case 80 -> panel.getEnemigo().atacar(panel.getJugador()); // P
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
