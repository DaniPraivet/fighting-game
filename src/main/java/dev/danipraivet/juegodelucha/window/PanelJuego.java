package dev.danipraivet.juegodelucha.window;

import dev.danipraivet.juegodelucha.ControlesJuego;
import dev.danipraivet.juegodelucha.entities.Enemigo;
import dev.danipraivet.juegodelucha.entities.Jugador;
import dev.danipraivet.juegodelucha.map.Plataforma;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Objects;
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

        jugador = new Jugador(plataformaX + 50,plataformaY -100);
        enemigo = new Enemigo(plataformaX + plataformaAncho -80, plataformaY -100);
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

        String textoJugador = "Player 1 -> " + jugador.getVidas() + " vidas | " + jugador.getDanyo() + " daño";
        g.drawString(textoJugador, 50, 50);

        String textoEnemigo = "Player 2 -> " + enemigo.getVidas() + " vidas | " + enemigo.getDanyo() + " daño";
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

        /*
         * Límites de pantalla para Jugador/Enemigo, si alguno de estos sobrepasa los límites
         * pierde 1 vida y es teletransportado de vuelta a la plataforma.
         * Se usan paréntesis para que sea más cómodo cambiar los valores
         */

        if (jugador.getY() > (getHeight() + 100) ||
                jugador.getY() < (0 - 100) ||
                jugador.getX() > (getWidth() + 100) ||
                jugador.getX() < (0 - 100)) {
            RUNNING = jugador.perderVida(plataforma);

        }
        if (enemigo.getY() > (getHeight() + 100) ||
                enemigo.getY() < (0 - 100) ||
                enemigo.getX() > (getWidth() + 100) ||
                enemigo.getX() < (0 - 100)) {
            RUNNING = enemigo.perderVida(plataforma);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        try {
            File archivoFuente = new File(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource("/BigBlueTermPlusNerdFont-Regular.ttf")).getFile()));
            Font fuente = Font.createFont(Font.TRUETYPE_FONT, archivoFuente).deriveFont(44f);
            Graphics2D graphics2D = (Graphics2D) g;

            graphics2D.setFont(fuente);

            graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            jugador.dibujar(graphics2D);
            enemigo.dibujar(graphics2D);
            plataforma.dibujar(graphics2D);

            dibujarInterfaz(graphics2D);
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
