package dev.danipraivet.juegodelucha.entities;

import dev.danipraivet.juegodelucha.map.Plataforma;
import java.awt.*;

public abstract class Entidad {
    protected int x;
    protected double y;
    protected final int ANCHO;
    protected final int ALTO;
    protected double velocidadY = 0;
    protected double gravedad = 0.5;
    protected final int velocidadDeSalto = -10;
    protected boolean enElAire = true;
    protected Color color;

    public Entidad(int x, int y, int ancho, int alto, Color color) {
        this.x = x;
        this.y = y;
        this.ANCHO = ancho;
        this.ALTO = alto;
        this.color = color;
    }

    public void mover(int dx) {
        x += dx;
    }

    public void saltar() {
        if (!enElAire) {
            velocidadY = velocidadDeSalto;
            enElAire = true;
            gravedad = 0.5;
        }
    }

    public void actualizar() {
        if (enElAire) {
            velocidadY += gravedad;
            y += velocidadY;
        }
    }

    public void acelerarCaida() {
        if (enElAire) {
            gravedad *= 4;
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
        if ((x <= 350 || x >= 1570) && !enElAire) {
            enElAire = true;
        }
    }

    public void dibujar(Graphics2D g) {
        g.setColor(color);
        g.fillRect(x, (int) y, ANCHO, ALTO);
    }

    public void atacar(Entidad oponente) {
        int hitboxAncho = 40;
        int hitboxX = (x < oponente.getX()) ? x + ANCHO : x - hitboxAncho;

        Rectangle hitbox = new Rectangle(hitboxX, (int) y, hitboxAncho, ALTO);

        if (hitbox.intersects(new Rectangle(oponente.getX(), (int) oponente.getY(), oponente.ANCHO, oponente.ALTO))) {
            System.out.println("!!!");
        }
    }


    public int getX() { return x; }
    public double getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
}
