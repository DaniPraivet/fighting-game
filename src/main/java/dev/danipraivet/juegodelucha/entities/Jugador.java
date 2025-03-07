package dev.danipraivet.juegodelucha.entities;

import java.awt.*;

public class Jugador extends Entidad {
    public Jugador(int x, int y) {
        super(x, y,  Color.BLUE);
        setSprite("/entities/sprites/player-right.png");
    }
}
