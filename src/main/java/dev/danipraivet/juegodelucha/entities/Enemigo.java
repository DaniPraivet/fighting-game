package dev.danipraivet.juegodelucha.entities;

import dev.danipraivet.juegodelucha.window.VentanaJuego;

import java.awt.*;

public class Enemigo extends Entidad {
    protected boolean playable;
    protected Robot robot;

    public Enemigo(int x, int y) {
        super(x, y, Color.RED);
        setSprite("/entities/sprites/enemy-left.png");
        try {
            if (!playable) {
                robot = new Robot();
            }
        } catch (AWTException e) {
            System.out.println("Error al crear el bot.");
        }
    }

    public int posicionActual() {
        return this.getX();
    }

    public void volverAPlataforma() {
        while (this.enElAire && (posicionActual() > VentanaJuego.ANCHO_VENTANA / 4) ||
                (posicionActual() > (VentanaJuego.ANCHO_VENTANA - VentanaJuego.ANCHO_VENTANA / 4))) {

            if (this.saltosRestantes > 0) {
                presionarTecla('u');
                if (this.saltosRestantes == 1) {
                    presionarTecla('u');
                }
            }
            if (posicionActual() > VentanaJuego.ANCHO_VENTANA / 4) {
                robot.keyPress(39);
            }
        }
    }

    public void presionarTecla(char tecla) {
        try {
            int teclaAPresionar;
            switch (tecla) {
                case 'u' -> teclaAPresionar = 38;
                case 'd' -> teclaAPresionar = 40;
                case 'l' -> teclaAPresionar = 37;
                case 'r' -> teclaAPresionar = 39;
                default -> teclaAPresionar = 40;
            }
            robot.keyPress(teclaAPresionar);
            robot.wait(100);
            robot.keyRelease(teclaAPresionar);
        } catch (InterruptedException e) {
            System.out.println("Error al esperar para volver a la plataforma.");
        }
    }
}
