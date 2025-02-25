package dev.danipraivet.juegodelucha.window;

import dev.danipraivet.juegodelucha.entities.Enemigo;
import dev.danipraivet.juegodelucha.entities.Jugador;
import dev.danipraivet.juegodelucha.map.Plataforma;

import javax.swing.*;
import java.awt.*;

public class PanelJuego extends JPanel {
    private final Jugador jugador;
    private final Enemigo enemigo;
    private final Plataforma plataforma;

    public PanelJuego() {
        setBackground(Color.BLACK);
        jugador = new Jugador(1400, 100);
        enemigo = new Enemigo( 600, 100);
        plataforma = new Plataforma(360, 400, 1200, 100);

        new Timer(16, e -> {
            actualizar();
            repaint();
        }).start();
    }

    public Jugador getJugador() {
        return jugador;
    }
    public Enemigo getEnemigo() {
        return enemigo;
    }

    public void actualizar() {
        jugador.actualizar();
        enemigo.actualizar();
        jugador.verificarColision(plataforma);
        enemigo.verificarColision(plataforma);
        
        if (jugador.getY() > getHeight()) {
            jugador.setY(250);
            jugador.setX(1400);
        }
        if (enemigo.getY() > getHeight()) {
            enemigo.setY(250);
            enemigo.setX(600);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        Graphics2D graphics2D = (Graphics2D) g;

        jugador.dibujar(graphics2D);
        enemigo.dibujar(graphics2D);
        plataforma.dibujar(graphics2D);
    }
}
