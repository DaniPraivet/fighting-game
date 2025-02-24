package dev.danipraivet.juegodelucha.entities;

import dev.danipraivet.juegodelucha.map.Plataforma;

import java.awt.*;

public class Enemigo implements Personaje{
    private int x;
    private int y;
    private final int ANCHO = 10;
    private final int ALTO = 10;
    private int velocidadY = 0;
    private final int gravedad = 1;
    private final int velocidadDeSalto = -15;
    private boolean enElAire = true;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getANCHO() {
        return ANCHO;
    }

    public int getALTO() {
        return ALTO;
    }

    public int getVelocidadY() {
        return velocidadY;
    }

    public void setVelocidadY(int velocidadY) {
        this.velocidadY = velocidadY;
    }

    public int getGravedad() {
        return gravedad;
    }

    public int getVelocidadDeSalto() {
        return velocidadDeSalto;
    }

    public boolean isEnElAire() {
        return enElAire;
    }

    public void setEnElAire(boolean enElAire) {
        this.enElAire = enElAire;
    }

    public Enemigo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void mover(int dx) {
        x += dx;
    }

    @Override
    public void saltar() {
        if (!enElAire) {
            velocidadY = velocidadDeSalto;
            enElAire = true;
        }
    }

    @Override
    public void actualizar() {
        if (enElAire) {
            velocidadY += gravedad;
            y += velocidadY;
        }
    }

    public void verificarColision(Plataforma plataforma) {
        if (y + ALTO >= plataforma.getY() &&
                x + ANCHO > plataforma.getX() &&
                x < plataforma.getX() + plataforma.getAncho()) {

            y = plataforma.getY() - ALTO;
            enElAire = false;
            velocidadY = 0;
        }
        if ( x >= 550 || x <= 240 && !enElAire) {
            enElAire = true;
        }
    }

    public void dibujar(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, ANCHO, ALTO);
    }
}
