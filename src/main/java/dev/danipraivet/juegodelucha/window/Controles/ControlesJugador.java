package dev.danipraivet.juegodelucha.window.Controles;

import dev.danipraivet.juegodelucha.window.MenuPrincipal;
import dev.danipraivet.juegodelucha.window.VentanaJuego;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Objects;

public class ControlesJugador extends MostrarControles{
    public ControlesJugador(VentanaJuego ventana, MenuPrincipal menu) {
        super(ventana, menu);
        JTextArea player1Bindings = new JTextArea("""
                Movement:
                Jump: W
                Move Left: A
                Move Right: D
                Fast Fall: S (while in air)
                
                Attacks:
                Neutral Light: E
                Side Light: A/D + E
                Down Light: S + E (grounded)
                """);
        File archivoFuente = new File(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource("/BigBlueTermPlusNerdFont-Regular.ttf")).getFile()));
        Font fuente = null;
        try {
            fuente = Font.createFont(Font.TRUETYPE_FONT, archivoFuente).deriveFont(44f);
        } catch (Exception e) {
            System.out.println(e);
        }
        setFont(fuente);
        assert fuente != null;
        getjTextArea(player1Bindings, fuente);
    }
}
