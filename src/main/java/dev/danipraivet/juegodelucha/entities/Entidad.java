package dev.danipraivet.juegodelucha.entities;

import dev.danipraivet.juegodelucha.map.Plataforma;
import dev.danipraivet.juegodelucha.math.Velocity;
import dev.danipraivet.juegodelucha.window.VentanaJuego;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public abstract class Entidad implements Personaje {
    private static int ANCHO = 40;
    public static int ALTO = 50;
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
    protected boolean canJump = true;

    protected Rectangle hitbox;
    private boolean mostrarHitbox = false;

    static {
        calcularTamanyoPersojes(VentanaJuego.ANCHO_VENTANA, VentanaJuego.ALTO_VENTANA);
    }

    public Entidad(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    public static void calcularTamanyoPersojes(int RES_ANCHO, int RES_ALTO) {
        int resBaseAncho = 1280;
        int resBaseAlto = 720;

        ANCHO = (ANCHO * RES_ANCHO) / resBaseAncho;
        ALTO = (ALTO * RES_ALTO) / resBaseAlto;
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
        canJump = false;

        if (saltosRestantes > 0) {
            velocity.setVelocityY(velocidadDeSalto);
            enElAire = true;
            velocity.setDefaultGravity();
            saltosRestantes--;
        }

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                canJump = true;
            }
        }, 250);
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
        } else if (!enElAire) {
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

    /*
    Metodo nLight temporal hasta que funcione bien el nuevo

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

        if (hitbox.intersects(new Rectangle(oponente.getX(), (int) oponente.getY(), ANCHO, ALTO))) { // TODO: Make ANCHO and ALTO independent for every entity.
            oponente.aumentarDanyo(25);
            oponente.retroceso(this, 10);
            oponente.congelado = true;
        }
    }
     */

    public void nLight(Entidad oponente) {
        if (congelado) return;

        congelado = true;
        Timer timer = new Timer();
        int[] golpes = {3, 3, 3, 10}; // Daño * ataque

        for (int i = 0; i < golpes.length; i++) {
            int golpeIndex = i;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (golpeIndex == 3 && !hitbox.intersects(new Rectangle(oponente.getX(), (int) oponente.getY(), ANCHO, ALTO))) {
                        congelado = false;
                        return; // Si el 3er golpe no conecta, el 4o no ocurre
                    }

                    ejecutarGolpe(oponente, golpes[golpeIndex]);

                    // Si es el último golpe, desbloquear al jugador
                    if (golpeIndex == golpes.length - 1) {
                        congelado = false;
                    }
                }
            }, golpeIndex * 200);
        }
    }

    private void ejecutarGolpe(Entidad oponente, int danyo) {
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
        }, 100);

        if (hitbox.intersects(new Rectangle(oponente.getX(), (int) oponente.getY(), ANCHO, ALTO))) {
            oponente.aumentarDanyo(danyo);
            oponente.retroceso(this, danyo / 2); // Pequeño retroceso
        }
    }




    public void aumentarDanyo(int cantidad) {
        danyo += cantidad;
        if (danyo > 999) danyo = 999;
    }

    public void retroceso(Entidad enemy, int baseRetroceso) {
        int direccion = (x < enemy.getX()) ? -1 : 1;
        double retrocesoFinal = 5 + (Math.pow(danyo, 2)) / (4 * 500);


        velocity.setVelocityX(retrocesoFinal * direccion);
        velocity.setVelocityY(-retrocesoFinal / 1.5);
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

        g.setColor(new Color(60, 0, 0));
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

    public boolean canJump() {
        return canJump;
    }

    public void setCanJump(boolean canJump) {
        this.canJump = canJump;
    }
}
