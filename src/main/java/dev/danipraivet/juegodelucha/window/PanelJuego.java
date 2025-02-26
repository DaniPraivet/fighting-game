package dev.danipraivet.juegodelucha.window;

import dev.danipraivet.juegodelucha.entities.Enemigo;
import dev.danipraivet.juegodelucha.entities.Jugador;
import dev.danipraivet.juegodelucha.map.Plataforma;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class PanelJuego extends JPanel {
    private static final int FPS = 60;
    private static boolean RUNNING = true;
    
    private final Jugador jugador;
    private final Enemigo enemigo;
    private final Plataforma plataforma;

    public PanelJuego(JFrame frame) {
        setBackground(Color.BLACK);
        setSize(frame.getSize());
        setVisible(true);
        jugador = new Jugador(1400, 100);
        enemigo = new Enemigo( 600, 100);
        plataforma = new Plataforma(360, 400, 1200, 100);

        new Thread(() -> {
            while (RUNNING) {
                long time =  System.currentTimeMillis();

                actualizar();
                repaint();

                long elapsed = System.currentTimeMillis() - time;
                long frameTime = 1000 / FPS;
                long sleepTime = frameTime - elapsed;

                try {
                    TimeUnit.MILLISECONDS.sleep(sleepTime);
                } catch (InterruptedException e) {
                    System.out.println("Error generating next frame.");
                }
            }
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
        Graphics2D graphics2D = (Graphics2D) g;

        jugador.dibujar(graphics2D);
        enemigo.dibujar(graphics2D);
        plataforma.dibujar(graphics2D);
    }
}
