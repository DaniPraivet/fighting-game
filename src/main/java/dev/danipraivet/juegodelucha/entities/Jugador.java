package dev.danipraivet.juegodelucha.entities;

import dev.danipraivet.juegodelucha.map.Plataforma;

import java.awt.*;

public class Jugador extends Entidad{
    private int x;
    private double y;
    private final int ANCHO = 30;
    private final int ALTO = 50;
    private double velocidadY = 0;
    private double gravedad = 0.5;
    private final int velocidadDeSalto = -10;
    private boolean enElAire = true;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
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

    public double getVelocidadY() {
        return velocidadY;
    }

    public void setVelocidadY(int velocidadY) {
        this.velocidadY = velocidadY;
    }

    public double getGravedad() {
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

    public Jugador(int x, int y) {
        super(x,y,30,50,Color.BLUE);
    }
}
