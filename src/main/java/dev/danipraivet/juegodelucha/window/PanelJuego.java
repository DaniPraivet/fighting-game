package dev.danipraivet.juegodelucha.window;

import dev.danipraivet.juegodelucha.entities.Jugador;
import dev.danipraivet.juegodelucha.map.Plataforma;

import javax.swing.*;
import java.awt.*;

public class PanelJuego extends JPanel {
    private Jugador jugador;
    private Plataforma plataforma;

    public PanelJuego() {
        setBackground(Color.BLACK);
        jugador = new Jugador(100, 100);
        plataforma = new Plataforma(250, 400, 300, 20);

    }

    public Jugador getJugador() {
        return jugador;
    }

    public void actualizar() {
        jugador.actualizar();
        jugador.verificarColision(plataforma);
        
        if (jugador.getY() > getHeight()) {
            jugador = new Jugador(100,100);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        Graphics2D graphics2D = (Graphics2D) g;

        jugador.dibujar(graphics2D);
    }
}
