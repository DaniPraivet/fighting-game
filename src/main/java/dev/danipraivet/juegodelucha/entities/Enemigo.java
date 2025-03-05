package dev.danipraivet.juegodelucha.entities;

import java.awt.*;

public class Enemigo extends Entidad {
    public Enemigo(int x, int y) {
        super(x, y, 30, 50, Color.RED);
        setSprite("/entities/sprites/enemy-left.png");
        // Sprite para Enemigo
    }
}
