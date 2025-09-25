package dev.danipraivet.juegodelucha;

import dev.danipraivet.juegodelucha.entities.Enemigo;
import dev.danipraivet.juegodelucha.entities.Jugador;
import dev.danipraivet.juegodelucha.window.VentanaJuego;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The objective of this class is to detect user inputs and translate it to the game actions
 * @author danipraivet
 * @see java.awt.event.KeyListener
 * @see dev.danipraivet.juegodelucha.entities.Enemigo
 * @see dev.danipraivet.juegodelucha.entities.Jugador
 */
public class ControlesJuego implements KeyListener {
    private static final int VELOCIDAD = 5;
    private static boolean a, d, w, s, e, q, arrowKLeft, arrowKRight, arrowKUp, arrowKDown, p, o;
    private static final Enemigo enemy = VentanaJuego.PANEL.getEnemigo();
    private static final Jugador player = VentanaJuego.PANEL.getJugador();

    /**
     * Checks all the keybindings from the two entities
     */
    public static void update() {
        playerKeybindsListener();
        enemyKeybindsListener();
    }

    /**
     * Method that listens when a key is pressed
     * @param ke the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent ke) {
        checkPressedKey(ke);
    }

    /**
     * This method is empty because it makes an accel curve, for dummies: when you press a key it types a few keys per second and then it goes up drastically
     * @param ke the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent ke) {}

    /**
     * Method that listens when a key is released
     * @param ke the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent ke) {
        checkReleasedKey(ke);
    }

    /**
     * Method that receives the event when a key is pressed and check if the game uses that key to take a specific action
     * @param ke the event to be processed
     */
    public static void checkPressedKey(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            // Player keybinds
            case 65 -> a = true; // A
            case 68 -> d = true; // D
            case 87 -> w = true; // W
            case 83 -> s = true; // S
            case 69 -> e = true; // E
            case 81 -> q = true;

            // Enemy keybinds
            case 37 -> arrowKLeft = true;   // <-
            case 39 -> arrowKRight = true;  // ->
            case 38 -> arrowKUp = true;     // ↑
            case 40 -> arrowKDown = true;   // ↓
            case 80 -> p = true; // P
            case 79 -> o = true;
        }
    }

    /**
     * Method that receives the event when a key is released and check if the game uses that key to take a specific action
     * @param ke the event to be processed
     */
    public static void checkReleasedKey(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case 65 -> a = false;
            case 68 -> d = false;
            case 87 -> w = false;
            case 83 -> s = false;
            case 69 -> e = false;
            case 81 -> q = false;
            case 37 -> arrowKLeft = false;
            case 39 -> arrowKRight = false;
            case 38 -> arrowKUp = false;
            case 40 -> arrowKDown = false;
            case 80 -> p = false;
            case 79 -> o = false;
        }
    }

    /**
     * Main listener for the player
     */
    public static void playerKeybindsListener() {
        playerMovementListener();
        playerAttackListener();
    }

    /**
     * Main listener for the enemy
     */
    public static void enemyKeybindsListener() {
        enemyMovementListener();
        enemyAttackListener();
    }

    /**
     * Listener for player movement
     */
    public static void playerMovementListener() {
        if (a) {player.mover(-VELOCIDAD);
            player.setSprite("/entities/sprites/player-left.png");
        }
        if (d) {player.mover(VELOCIDAD);
            player.setSprite("/entities/sprites/player-right.png");
        }
        if (w && player.canJump()) {
            player.saltar();
        }
        if (s) player.acelerarCaida();
        if (q && (a||d)) {
            player.dash(d);
        }
    }

    /**
     * Listener for player attacks
     */
    public static void playerAttackListener() {
        if ((a||d) && e) {
            if (!player.enElAire) {
                player.sLight(enemy,d);
            } else {
                player.sAir(enemy,d);
            }
        }
        if (s && e) {
            player.dLight(enemy);
        }
        if (e && !s) {
            if (!player.enElAire) {
                player.nLight(enemy);
            } else player.nAir(enemy);
        }
    }


    /**
     * Listener for enemy movement
     */
    public static void enemyMovementListener() {
        if (arrowKLeft) {enemy.mover(-VELOCIDAD);
            enemy.setSprite("/entities/sprites/enemy-left.png");
        }
        if (arrowKRight) {enemy.mover(VELOCIDAD);
            enemy.setSprite("/entities/sprites/enemy-right.png");
        }
        if (arrowKUp && enemy.canJump()) {
            enemy.saltar();
        }
        if (arrowKDown && enemy.enElAire) enemy.acelerarCaida();
        if (o && (arrowKRight||arrowKLeft)) {
            enemy.dash(arrowKRight);
        }
    }

    /**
     * Listener for enemy attacks
     */
    public static void enemyAttackListener() {
        if ((arrowKLeft||arrowKRight) && p) {
            if (!enemy.enElAire) {
                enemy.sLight(player,arrowKRight);
            } else {
                enemy.sAir(player,arrowKRight);
            }
        }
        if (arrowKDown && p) enemy.dLight(player);
        if (p && !s) {
            if (!enemy.enElAire) {
                enemy.nLight(player);
            } else enemy.nAir(player);
        }
    }

}
