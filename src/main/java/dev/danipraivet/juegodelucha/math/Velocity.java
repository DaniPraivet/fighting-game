package dev.danipraivet.juegodelucha.math;

import dev.danipraivet.juegodelucha.entities.Entidad;
import dev.danipraivet.juegodelucha.window.VentanaJuego;

public class Velocity {
    private static final double DEFAULT_GRAVITY = 0.5;
    private static final double AIR_FRICTION = 0.2;
    private static final double PLATFORM_FRICTION = 1.25;
    private static final int MAX_VELOCITY = (int) Math.round((double) (VentanaJuego.ANCHO_VENTANA / 1280) * 5);
    private double x;
    private double y;
    private double gravity = 0.5;

    public Velocity(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void update(Entidad entidad) {
        int x = (int) (entidad.getX() + this.x);
        int y = (int) (entidad.getY() + this.y);

        if (entidad.enElAire) {
            this.y += this.gravity;
        }

        double friction = entidad.enElAire ? AIR_FRICTION : PLATFORM_FRICTION;

        if (this.x < 0) {
            this.x += friction;

            if (this.x > 0) this.x = 0;
        } else if (this.x > 0) {
            this.x -= friction;
            if (this.x < 0) this.x = 0;
        }


        entidad.setX(x);
        entidad.setY(y);
    }

    public void setVelocityX(double x) {
        this.x = x;
    }

    public void addVelocityX(double x) {
        if (this.x > 0) {
            this.x -= x / Math.min(this.x, -1) / 2;
        } else if (this.x < 0) {
            this.x += x / Math.max(this.x, 1) / 2;
        } else {
            this.x += x;
        }

        if (this.x > MAX_VELOCITY) {
            this.x = MAX_VELOCITY;
        } else if (this.x < -MAX_VELOCITY) {
            this.x = -MAX_VELOCITY;
        }
    }

    public void setVelocityY(double y) {
        this.y = y;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public void setDefaultGravity() {
        this.gravity = DEFAULT_GRAVITY;
    }

    public double getVelocityY() {
        return this.y;
    }
}
