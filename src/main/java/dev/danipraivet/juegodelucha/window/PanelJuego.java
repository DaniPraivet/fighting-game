package dev.danipraivet.juegodelucha.window;

import dev.danipraivet.juegodelucha.entities.Enemigo;
import dev.danipraivet.juegodelucha.entities.Jugador;
import dev.danipraivet.juegodelucha.map.Plataforma;

import javax.swing.*;
import java.awt.*;

public class PanelJuego extends JPanel {
    private Jugador jugador;
    private Enemigo enemigo;
    private final Plataforma plataforma;

    public PanelJuego() {
        setBackground(Color.BLACK);
        jugador = new Jugador(300, 100);
        enemigo = new Enemigo( 400, 100);
        plataforma = new Plataforma(250, 400, 300, 20);

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
            jugador.setX(300);
        }
        if (enemigo.getY() > getHeight()) {
            enemigo.setY(250);
            enemigo.setX(400);
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
