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
        Rectangle hitboxEntidad = new Rectangle(x, (int) y, ANCHO, ALTO);
        Rectangle hitboxPlataforma = new Rectangle(plataforma.getX(), plataforma.getY(), plataforma.getAncho(), plataforma.getAlto());

        // Colisión con la parte superior de la plataforma
        boolean sobrePlataforma =
                y + ALTO >= plataforma.getY() &&
                        y + ALTO - velocity.getVelocityY() <= plataforma.getY() &&
                        x + ANCHO > plataforma.getX() &&
                        x < plataforma.getX() + plataforma.getAncho();

        if (sobrePlataforma) {
            y = plataforma.getY() - ALTO;
            enElAire = false;
            velocity.setVelocityY(0);
            velocity.setDefaultGravity();
            saltosRestantes = 2;
            return;
        }

        // Colisión con los lados de la plataforma
        if (hitboxEntidad.intersects(hitboxPlataforma)) {
            if (x + ANCHO - 5 <= plataforma.getX()) {
                x = plataforma.getX() - ANCHO;
                velocity.setVelocityX(0);
            } else if (x >= plataforma.getX() + plataforma.getAncho() - 5) {
                x = plataforma.getX() + plataforma.getAncho();
                velocity.setVelocityX(0);
            }
        }

        if (!enElAire) {
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

    private void ejecutarGolpe(Entidad oponente, int danyo, boolean esPuente) {
        int hitboxAncho = 20;
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

        if (hitbox.intersects(new Rectangle(oponente.getX(), (int) oponente.getY(), ANCHO, ALTO))) {// TODO: Make ANCHO and ALTO independent for every entity.
            oponente.aumentarDanyo(danyo);
            oponente.retroceso(this, oponente, danyo, esPuente);
        }
    }
    public void aumentarDanyo(int cantidad) {
        danyo += cantidad;
        if (danyo > 999) danyo = 999;
    }

    public void retroceso(Entidad jugador, Entidad enemy, int baseRetroceso, boolean esPuente) {
        int direccion = (jugador.getX() < enemy.getX()) ? 1 : -1;


        if (esPuente) {
            // Retroceso fijo muy bajo si es un ataque puente
            enemy.velocity.setVelocityX(-2 * direccion);
            enemy.velocity.setVelocityY(-2);
        } else {
            // Retroceso normal
            double retrocesoFinal = baseRetroceso + (Math.pow(danyo, 2)) / (4 * 500);
            enemy.velocity.setVelocityX(retrocesoFinal * direccion);
            enemy.velocity.setVelocityY(-retrocesoFinal / 1.5);
        }

        enemy.enElAire = true;
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
            velocity.setVelocityX(0);
            velocity.setVelocityY(0);
        } else if (this instanceof Enemigo) {
            setX(plataforma.getX() + (plataforma.getAncho() * 2) / 3);
            velocity.setVelocityX(0);
            velocity.setVelocityY(0);
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

    /***
     * ATAQUES
     */

    public void nLight(Entidad oponente) {
        if (congelado) return;
        congelado = true;
        Timer timer = new Timer();
        int[] golpes = {3,3,3,10};
        boolean[] esAgarre = {true, true, true, false}; // Indica si cada golpe es un puente

        for (int i = 0; i < golpes.length; i++) {
            int golpeIndex = i;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (golpeIndex == 3 && !hitbox.intersects(new Rectangle(oponente.getX(), (int) oponente.getY(), ANCHO, ALTO))) {
                        congelado = false;
                        return; // Si el 3er golpe no conecta, el 4o no ocurre
                    }

                    ejecutarGolpe(oponente, golpes[golpeIndex], esAgarre[golpeIndex]);

                    if (golpeIndex == golpes.length - 1) {
                        congelado = false;
                    }
                }
            }, golpeIndex * 200);
        }
    }

    public void sLight(Entidad oponente, boolean haciaDerecha) {
        if (congelado) return;
        congelado = true;

        int desplazamiento = haciaDerecha ? 40 : -40;
        int hitboxAncho = 50;
        int hitboxAlto = 25;
        int danyo = 12;

        x += desplazamiento;

        int hitboxX = haciaDerecha ? x + ANCHO : x - hitboxAncho;
        int hitboxY = (int) y + (ALTO / 3);

        hitbox = new Rectangle(hitboxX, hitboxY, hitboxAncho, hitboxAlto);
        mostrarHitbox = true;

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mostrarHitbox = false;
                congelado = false;
            }
        }, 300);

        if (hitbox.intersects(new Rectangle(oponente.getX(), (int) oponente.getY(), ANCHO, ALTO))) {
            oponente.aumentarDanyo(danyo);
            oponente.retroceso(this, oponente, 10, false); // Empuje moderado
        }
    }

    public void dLight(Entidad oponente) {
        if (this.congelado) return;

        this.congelado = true;
        int desplazamiento = (x < oponente.getX()) ? 40 : -40;
        int hitboxAncho = 50;
        int hitboxAlto = 20;
        int danyo = 5;


        this.x += desplazamiento;

        int hitboxX = (x < oponente.getX()) ? this.x + ANCHO : this.x - hitboxAncho;
        int hitboxY = (int) y + ALTO - hitboxAlto;

        this.hitbox = new Rectangle(hitboxX, hitboxY, hitboxAncho, hitboxAlto);
        this.mostrarHitbox = true;

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mostrarHitbox = false;
                congelado = false;
            }
        }, 300);

        if (hitbox.intersects(new Rectangle(oponente.getX(), (int) oponente.getY(), ANCHO, ALTO))) {
            oponente.aumentarDanyo(danyo);
            oponente.retrocesoFijo(0, -5);
        }
    }

    public void retrocesoFijo(int fuerzaX, int fuerzaY) {
        this.velocity.setVelocityX(fuerzaX);
        this.velocity.setVelocityY(fuerzaY);
        this.enElAire = true;
    }

    public void nAir(Entidad oponente) {
        if (this.congelado) return;

        this.congelado = true;

        int danyo = 15;
        int hitboxAlto = ALTO + 50;
        int hitboxAncho = ANCHO + 50;
        int hitboxX = this.x - (hitboxAncho/3);
        int hitboxY = (int) this.y - (hitboxAlto/3);

        this.hitbox = new Rectangle(hitboxX,hitboxY, hitboxAncho, hitboxAlto);
        this.mostrarHitbox = true;

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mostrarHitbox = false;
                congelado = false;
            }
        }, 500);

        if (hitbox.intersects(new Rectangle(oponente.getX(), (int) oponente.getY(), ANCHO, ALTO))) {
            oponente.aumentarDanyo(danyo);
            oponente.retroceso(this, oponente, 10, false);
        }
    }

    public void sAir(Entidad oponente, boolean haciaDerecha) {
        if (congelado) return;
        congelado = true;

        int desplazamiento = haciaDerecha ? 60 : -60;
        int hitboxAncho = 40;
        int hitboxAlto = 25;
        int danyo = 17;

        x += desplazamiento;

        int hitboxX = haciaDerecha ? x + ANCHO : x - hitboxAncho;
        int hitboxY = (int) y + (ALTO / 3);

        hitbox = new Rectangle(hitboxX, hitboxY, hitboxAncho, hitboxAlto);
        mostrarHitbox = true;

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mostrarHitbox = false;
                congelado = false;
            }
        }, 300);

        if (hitbox.intersects(new Rectangle(oponente.getX(), (int) oponente.getY(), ANCHO, ALTO))) {
            oponente.aumentarDanyo(danyo);
            oponente.retroceso(this, oponente, 10, false);
        }
    }




}
