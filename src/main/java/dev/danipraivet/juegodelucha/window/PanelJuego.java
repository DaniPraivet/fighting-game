package dev.danipraivet.juegodelucha.window;

import dev.danipraivet.juegodelucha.ControlesJuego;
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
    public final Plataforma plataforma;

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public PanelJuego(JFrame frame) {
        setBackground(Color.BLACK);
        setSize(frame.getSize());

        setVisible(true);
        setFocusable(true);

        int plataformaX = VentanaJuego.ANCHO_VENTANA / 4;
        int plataformaY = VentanaJuego.ALTO_VENTANA -200;
        int plataformaAncho = VentanaJuego.ANCHO_VENTANA / 2;
        int plataformaAlto = 100;

        jugador = new Jugador(plataformaX + 50,plataformaY -50);
        enemigo = new Enemigo(plataformaX + plataformaAncho -80, plataformaY -50);
        plataforma = new Plataforma(
                plataformaX,
                plataformaY,
                plataformaAncho,
                plataformaAlto);


        new Thread(() -> {
            while (RUNNING) {
                long time = System.currentTimeMillis();
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

    private void dibujarInterfaz(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.BOLD, 20));

        String textoJugador = "Jugador -> " + jugador.getVidas() + " vidas | " + jugador.getDanyo() + " daño";
        g.drawString(textoJugador, 50, 50);

        String textoEnemigo = "Enemigo -> " + enemigo.getVidas() + " vidas | " + enemigo.getDanyo() + " daño";
        int anchoTexto = g.getFontMetrics().stringWidth(textoEnemigo);
        g.drawString(textoEnemigo, VentanaJuego.ANCHO_VENTANA - anchoTexto - 50, 50);
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Enemigo getEnemigo() {
        return enemigo;
    }

    public void actualizar() {
        ControlesJuego.update();
        jugador.actualizar();
        enemigo.actualizar();
        jugador.verificarColision(plataforma);
        enemigo.verificarColision(plataforma);

        if (jugador.getY() > getHeight()) {
            RUNNING = jugador.perderVida(plataforma);

        }
        if (enemigo.getY() > getHeight()) {
            RUNNING = enemigo.perderVida(plataforma);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        jugador.dibujar(graphics2D);
        enemigo.dibujar(graphics2D);
        plataforma.dibujar(graphics2D);

        dibujarInterfaz(graphics2D);
    }
}
