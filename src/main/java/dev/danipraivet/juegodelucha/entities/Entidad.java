package dev.danipraivet.juegodelucha.entities;

import dev.danipraivet.juegodelucha.map.Plataforma;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Entidad {
    protected final int ANCHO;
    protected final int ALTO;
    protected final int velocidadDeSalto = -10;
    protected int x;
    protected double y;
    protected double velocidadY = 0;
    protected double gravedad = 0.5;
    protected boolean enElAire = true;
    protected Color color;
    protected boolean congelado = false;
    protected int vida = 100;

    protected Rectangle hitbox;
    private boolean mostrarHitbox = false;

    public Entidad(int x, int y, int ancho, int alto, Color color) {
        this.x = x;
        this.y = y;
        this.ANCHO = ancho;
        this.ALTO = alto;
        this.color = color;
    }

    public void mover(int dx) {
        if (!congelado) {
            x += dx;
        }

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
        dibujarBarraVida(g);

        if (mostrarHitbox && hitbox != null) {
            g.setColor(new Color(255,255,0,150));
            g.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
            g.setColor(Color.YELLOW);
            g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        }
    }

    public void atacar(Entidad oponente) {
        if (congelado) return;

        congelado = true;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                congelado = false;
            }
        }, 750);

        int hitboxAncho = 100;
        int hitboxAlto = 20;
        int hitboxX = (x < oponente.getX()) ? x + ANCHO : x - hitboxAncho;
        int hitboxY = (int) y + (ALTO / 3);

        hitbox = new Rectangle(hitboxX, hitboxY, hitboxAncho, hitboxAlto);
        mostrarHitbox = true;

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mostrarHitbox = false;
            }
        }, 750);

        if (hitbox.intersects(new Rectangle(oponente.getX(), (int) oponente.getY(), oponente.ANCHO, oponente.ALTO))) {
            oponente.recibirDanio(20);
            oponente.saltar();
        }



    }

    public void recibirDanio (int cantidad) {
        vida -= cantidad;
        if (vida < 0) vida = 0;
    }

    public void dibujarBarraVida (Graphics2D g) {
        int barraAncho = ANCHO;
        int barraAlto = 5;
        int barraX = x;
        int barraY = (int) y - 10;

        g.setColor(Color.RED);
        g.fillRect(barraX, barraY, barraAncho, barraAlto);

        g.setColor(Color.GREEN);
        g.fillRect(barraX, barraY, (int) (barraAncho * (vida / 100)), barraAlto);

        g.setColor(Color.BLACK);
        g.drawRect(barraX, barraY, barraAncho, barraAlto);
    }



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
}
