package dev.danipraivet.juegodelucha.window.Controles;

import dev.danipraivet.juegodelucha.window.MenuPrincipal;
import dev.danipraivet.juegodelucha.window.VentanaJuego;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Objects;

public class ControlesEnemigo extends MostrarControles{
    public ControlesEnemigo(VentanaJuego ventana, MenuPrincipal menu) {
        super(ventana, menu);
        JTextArea player2Bindings = new JTextArea("""
        Movement:
        Jump: UP arrow
        Move Left: LEFT arrow
        Move Right: RIGHT arrow
        Fast Fall: DOWN arrow (while in air)

        Attacks:
        Neutral Light: P
        Side Light: LEFT/RIGHT arrow + P
        Down Light: DOWN arrow + P (grounded)
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
        getjTextArea(player2Bindings, fuente);
    }
}
