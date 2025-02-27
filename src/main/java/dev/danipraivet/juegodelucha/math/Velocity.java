package dev.danipraivet.juegodelucha.math;

import dev.danipraivet.juegodelucha.entities.Entidad;

public class Velocity {
    private static final double DEFAULT_GRAVITY = 0.5;
    private static final double AIR_FRICTION = 0.25;
    private static final double PLATFORM_FRICTION = 1.25;
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

    public void setVelocityY(double y) {
        this.y = y;
    }

    public double getGravity() {
        return gravity;
    }
    
    public void setDefaultGravity() {
        this.gravity = DEFAULT_GRAVITY;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }
}
