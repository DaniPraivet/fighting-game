package dev.danipraivet.juegodelucha.entities;

import dev.danipraivet.juegodelucha.map.Plataforma;
import dev.danipraivet.juegodelucha.math.Velocity;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public abstract class Entidad implements Personaje{
    protected final int ANCHO;
    public final int ALTO;
    protected final int velocidadDeSalto = -10;
    protected int x;
    protected double y;
    public Velocity velocity;
    public boolean enElAire = true;
    protected Color color;
    protected boolean congelado = false;
    protected int danyo = 0;
    protected int vidas = 3;
    protected int saltosRestantes = 2;

    protected Rectangle hitbox;
    private boolean mostrarHitbox = false;

    public Entidad(int x, int y, int ancho, int alto, Color color) {
        this.x = x;
        this.y = y;
        this.ANCHO = ancho;
        this.ALTO = alto;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    public int getVidas() {
        return vidas;
    }

    public int getDanyo() {
        return danyo;
    }

    public void mover(int dx) {
        if (!congelado) {
            velocity.addVelocityX(dx);
        }
    }

    public void saltar() {
        if (saltosRestantes > 0) {
            velocity.setVelocityY(velocidadDeSalto);
            enElAire = true;
            velocity.setDefaultGravity();
            saltosRestantes--;
        }
    }

    public void actualizar() {
        velocity.update(this);
    }

    public void acelerarCaida() {
        if (enElAire) {
            velocity.setGravity(velocity.getGravity() * 1.15);
        }
    }

    public void verificarColision(Plataforma plataforma) {
        boolean sobrePlataforma =
                y + ALTO >= plataforma.getY() &&
                x + ANCHO > plataforma.getX() &&
                x < plataforma.getX() + plataforma.getAncho();

        if (sobrePlataforma) {
            y = plataforma.getY() - ALTO;
            enElAire = false;
            velocity.setVelocityY(0);
            velocity.setDefaultGravity();
            saltosRestantes = 2;
        } else if (!enElAire){
            enElAire = true;
        }
    }

    public void dibujar(Graphics2D g) {
        if (sprite != null) {
            g.drawImage(sprite, x, (int) y, ANCHO, ALTO, null);
        } else {
            g.setColor(color);
            g.fillRect(x, (int) y, ANCHO, ALTO);
        }

        dibujarBarraVida(g);

        if (mostrarHitbox && hitbox != null) {
            g.setColor(new Color(255, 255, 0, 150));
            g.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
            g.setColor(Color.YELLOW);
            g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        }
    }


    public void nLight(Entidad oponente) {
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
                oponente.congelado = false;
            }
        }, 500);

        if (hitbox.intersects(new Rectangle(oponente.getX(), (int) oponente.getY(), oponente.ANCHO, oponente.ALTO))) {
            oponente.aumentarDanyo(10);
            oponente.retroceso(this, 10);
            oponente.congelado = true;
        }
    }

    public void aumentarDanyo(int cantidad) {
        danyo += cantidad;
        if (danyo > 999) danyo = 999;
    }

    public void retroceso(Entidad enemy, int baseRetroceso) {
        int direccion = (x < enemy.getX()) ? -1 : 1;
        double retrocesoFinal = 5+(Math.pow(danyo, 2)) / (4 * 500);



        velocity.setVelocityX(retrocesoFinal * direccion);
        velocity.setVelocityY(-retrocesoFinal/1.5);
        enElAire = true;
    }

    public boolean perderVida(Plataforma plataforma) {
        vidas--;
        danyo = 0;

        if (vidas <= 0) {
            vidas = 0;
            return false;
        }
        if (this instanceof Jugador) {
            setX(plataforma.getX() + plataforma.getAncho() / 3);
        } else if (this instanceof Enemigo) {
            setX(plataforma.getX() + (plataforma.getAncho() * 2) / 3);
        }

        setY(plataforma.getY() - ALTO);
        return true;
    }

    public void dibujarBarraVida(Graphics2D g) {
        int barraAncho = ANCHO;
        int barraAlto = 5;
        int barraX = x;
        int barraY = (int) y - 10;

        Color colorBarra = DamageColors.getColor(danyo);

        g.setColor(new Color (60,0,0));
        g.fillRect(barraX, barraY, barraAncho, barraAlto);

        g.setColor(colorBarra);
        g.fillRect(barraX, barraY, barraAncho, barraAlto);

        g.setColor(Color.BLACK);
        g.drawRect(barraX, barraY, barraAncho, barraAlto);
    }

    protected Image sprite;

    public void setSprite(String rutaImagen) {
        try {
            sprite = new ImageIcon(Objects.requireNonNull(getClass().getResource(rutaImagen))).getImage();
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen: " + rutaImagen);
        }
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
