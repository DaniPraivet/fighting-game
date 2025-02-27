package dev.danipraivet.juegodelucha;

import dev.danipraivet.juegodelucha.window.VentanaJuego;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControlesJuego implements KeyListener {
    private static final int VELOCIDAD = 10;
    private static boolean a, d, w, s, arrowKLeft, arrowKRight, arrowKUp, arrowKDown;

    public static void update() {
        if (a) VentanaJuego.PANEL.getJugador().mover(-VELOCIDAD);
        if (d) VentanaJuego.PANEL.getJugador().mover(VELOCIDAD);
        if (w) VentanaJuego.PANEL.getJugador().saltar();
        if (s) VentanaJuego.PANEL.getJugador().acelerarCaida();
        if (arrowKLeft) VentanaJuego.PANEL.getEnemigo().mover(-VELOCIDAD);
        if (arrowKRight) VentanaJuego.PANEL.getEnemigo().mover(VELOCIDAD);
        if (arrowKUp) VentanaJuego.PANEL.getEnemigo().saltar();
        if (arrowKDown) VentanaJuego.PANEL.getEnemigo().acelerarCaida();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            // Controles Jugador
            case 65 -> a = true; // A
            case 68 -> d = true; // D
            case 87 -> w = true; // W
            case 83 -> s = true; // S
            case 69 -> VentanaJuego.PANEL.getJugador().atacar(VentanaJuego.PANEL.getEnemigo()); // E

            // Controles Enemigo
            case 37 -> arrowKLeft = true;   // <-
            case 39 -> arrowKRight = true;  // ->
            case 38 -> arrowKUp = true;     // ↑
            case 40 -> arrowKDown = true;   // ↓
            case 80 -> VentanaJuego.PANEL.getEnemigo().atacar(VentanaJuego.PANEL.getJugador()); // P
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 65 -> a = false;
            case 68 -> d = false;
            case 87 -> w = false;
            case 83 -> s = false;
            case 37 -> arrowKLeft = false;
            case 39 -> arrowKRight = false;
            case 38 -> arrowKUp = false;
            case 40 -> arrowKDown = false;
        }
    }
}
