import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControlesJuego implements KeyListener {

    private PanelJuego panel;

    public ControlesJuego(PanelJuego panel) {
        this.panel = panel;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            // 87 -> w
            case 87 -> {
                panel.setLocation(panel.pj1X, panel.pj1Y+10);
            }
            // 65 -> a
            case 65 -> {
                panel.setLocation(panel.pj1X-10, panel.pj1Y);
            }
            // 83 -> s
            case 83 -> {
                panel.setLocation(panel.pj1X, panel.pj1Y-10);
            }
            // 68 -> d
            case 68 -> {
                panel.setLocation(panel.pj1X+10, panel.pj1Y);
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
