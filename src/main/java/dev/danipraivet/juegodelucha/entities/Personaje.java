package dev.danipraivet.juegodelucha.entities;

public interface Personaje {
    void mover(int dx);
    void saltar();
    void actualizar();
    void acelerarCaida();
}
