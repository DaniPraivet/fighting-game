package dev.danipraivet.juegodelucha;

import dev.danipraivet.juegodelucha.window.VentanaJuego;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControlesJuego implements KeyListener {
    private static final int VELOCIDAD = 5;
    private static boolean a, d, w, s, e, arrowKLeft, arrowKRight, arrowKUp, arrowKDown, p;

    public static void update() {
        if (a|d && e) {
            VentanaJuego.PANEL.getJugador().sLight(VentanaJuego.PANEL.getEnemigo(), d);
        }
        if (s && e) {
            VentanaJuego.PANEL.getJugador().dLight(VentanaJuego.PANEL.getEnemigo());
        }

        if (a) {VentanaJuego.PANEL.getJugador().mover(-VELOCIDAD);
            VentanaJuego.PANEL.getJugador().setSprite("/entities/sprites/player-left.png");
        }
        if (d) {VentanaJuego.PANEL.getJugador().mover(VELOCIDAD);
            VentanaJuego.PANEL.getJugador().setSprite("/entities/sprites/player-right.png");
        }
        if (w && VentanaJuego.PANEL.getJugador().canJump()) {
            VentanaJuego.PANEL.getJugador().saltar();
        }
        if (s) VentanaJuego.PANEL.getJugador().acelerarCaida();
        if (arrowKLeft) {VentanaJuego.PANEL.getEnemigo().mover(-VELOCIDAD);
            VentanaJuego.PANEL.getEnemigo().setSprite("/entities/sprites/enemy-left.png");
        }
        if (e && !s) VentanaJuego.PANEL.getJugador().nLight(VentanaJuego.PANEL.getEnemigo());

        if (arrowKRight) {VentanaJuego.PANEL.getEnemigo().mover(VELOCIDAD);
            VentanaJuego.PANEL.getEnemigo().setSprite("/entities/sprites/enemy-right.png");
        }
        if (arrowKUp && VentanaJuego.PANEL.getEnemigo().canJump()) {
            VentanaJuego.PANEL.getEnemigo().saltar();
        }
        if (arrowKDown) VentanaJuego.PANEL.getEnemigo().acelerarCaida();
        if (arrowKLeft|arrowKRight && p) VentanaJuego.PANEL.getEnemigo().sLight(VentanaJuego.PANEL.getJugador(),arrowKRight);
        if (arrowKDown && p) VentanaJuego.PANEL.getEnemigo().dLight(VentanaJuego.PANEL.getJugador());
        if (p && !s) VentanaJuego.PANEL.getEnemigo().nLight(VentanaJuego.PANEL.getJugador());
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            // Controles Jugador
            case 65 -> a = true; // A
            case 68 -> d = true; // D
            case 87 -> w = true; // W
            case 83 -> s = true; // S
            case 69 -> e = true; // E

            // Controles Enemigo
            case 37 -> arrowKLeft = true;   // <-
            case 39 -> arrowKRight = true;  // ->
            case 38 -> arrowKUp = true;     // ↑
            case 40 -> arrowKDown = true;   // ↓
            case 80 -> p = true; // P
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case 65 -> a = false;
            case 68 -> d = false;
            case 87 -> w = false;
            case 83 -> s = false;
            case 69 -> e = false;
            case 37 -> arrowKLeft = false;
            case 39 -> arrowKRight = false;
            case 38 -> arrowKUp = false;
            case 40 -> arrowKDown = false;
            case 80 -> p = false;
        }
    }
}
