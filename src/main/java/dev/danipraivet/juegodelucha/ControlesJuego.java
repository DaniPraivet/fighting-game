package dev.danipraivet.juegodelucha;

import dev.danipraivet.juegodelucha.window.VentanaJuego;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControlesJuego implements KeyListener {
    private static final int VELOCIDAD = 10;
    private static boolean a, d, w, s;

    public static void update() {
        if (a) VentanaJuego.PANEL.getJugador().mover(-VELOCIDAD);
        if (d) VentanaJuego.PANEL.getJugador().mover(VELOCIDAD);
        if (w) VentanaJuego.PANEL.getJugador().saltar();
        if (s) VentanaJuego.PANEL.getJugador().acelerarCaida();
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
            case 37 -> VentanaJuego.PANEL.getEnemigo().mover(-VELOCIDAD); // ←
            case 39 -> VentanaJuego.PANEL.getEnemigo().mover(VELOCIDAD);  // →
            case 38 -> VentanaJuego.PANEL.getEnemigo().saltar();          // ↑
            case 40 -> VentanaJuego.PANEL.getEnemigo().acelerarCaida();   // ↓
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
        }
    }
}
