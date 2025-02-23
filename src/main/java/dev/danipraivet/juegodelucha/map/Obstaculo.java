package dev.danipraivet.juegodelucha.map;

import java.awt.*;

public interface Obstaculo {
    void dibujar (Graphics g);
    int getX();
    int getY();
    int getAncho();
    int getAlto();
}
