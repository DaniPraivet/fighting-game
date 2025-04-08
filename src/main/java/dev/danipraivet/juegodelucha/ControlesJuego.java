package dev.danipraivet.juegodelucha;

import dev.danipraivet.juegodelucha.window.VentanaJuego;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControlesJuego implements KeyListener {
    private static final int VELOCIDAD = 5;
    private static boolean a, d, w, s, e, q, arrowKLeft, arrowKRight, arrowKUp, arrowKDown, p, o;

    public static void update() {
        if (a|d && e) {
            if (!VentanaJuego.PANEL.getJugador().enElAire) {
                VentanaJuego.PANEL.getJugador().sLight(VentanaJuego.PANEL.getEnemigo(),d);
            } else {
                VentanaJuego.PANEL.getJugador().sAir(VentanaJuego.PANEL.getEnemigo(),d);
            }
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
        if (e && !s) {
            if (!VentanaJuego.PANEL.getJugador().enElAire) {
                VentanaJuego.PANEL.getJugador().nLight(VentanaJuego.PANEL.getEnemigo());
            } else VentanaJuego.PANEL.getJugador().nAir(VentanaJuego.PANEL.getEnemigo());
        }
        if (q && a|d) {
            VentanaJuego.PANEL.getJugador().dash(d);
        }




        if (arrowKRight) {VentanaJuego.PANEL.getEnemigo().mover(VELOCIDAD);
            VentanaJuego.PANEL.getEnemigo().setSprite("/entities/sprites/enemy-right.png");
        }
        if (arrowKUp && VentanaJuego.PANEL.getEnemigo().canJump()) {
            VentanaJuego.PANEL.getEnemigo().saltar();
        }
        if (arrowKDown) VentanaJuego.PANEL.getEnemigo().acelerarCaida();
        if (arrowKLeft|arrowKRight && p) {
            if (!VentanaJuego.PANEL.getEnemigo().enElAire) {
                VentanaJuego.PANEL.getEnemigo().sLight(VentanaJuego.PANEL.getJugador(),arrowKRight);
            } else {
                VentanaJuego.PANEL.getEnemigo().sAir(VentanaJuego.PANEL.getJugador(),arrowKRight);
            }
        }

            else
        if (arrowKDown && p) VentanaJuego.PANEL.getEnemigo().dLight(VentanaJuego.PANEL.getJugador());
        if (p && !s) {
            if (!VentanaJuego.PANEL.getEnemigo().enElAire) {
                VentanaJuego.PANEL.getEnemigo().nLight(VentanaJuego.PANEL.getJugador());
            } else VentanaJuego.PANEL.getEnemigo().nAir(VentanaJuego.PANEL.getJugador());
        }
        if (o && arrowKRight|arrowKLeft) {
            VentanaJuego.PANEL.getEnemigo().dash(arrowKRight);
        }

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
            case 81 -> q = true;

            // Controles Enemigo
            case 37 -> arrowKLeft = true;   // <-
            case 39 -> arrowKRight = true;  // ->
            case 38 -> arrowKUp = true;     // ↑
            case 40 -> arrowKDown = true;   // ↓
            case 80 -> p = true; // P
            case 79 -> o = true;
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
            case 81 -> q = false;
            case 37 -> arrowKLeft = false;
            case 39 -> arrowKRight = false;
            case 38 -> arrowKUp = false;
            case 40 -> arrowKDown = false;
            case 80 -> p = false;
            case 79 -> o = false;
        }
    }
}
