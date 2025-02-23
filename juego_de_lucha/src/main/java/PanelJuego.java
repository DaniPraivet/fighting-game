import javax.swing.*;
import java.awt.*;

public class PanelJuego extends JPanel {
    int pj1X;
    int pj1Y;
    public PanelJuego () {
        setBackground(Color.BLACK);
        pj1X = 100;
        pj1Y = 400;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(pj1X,pj1Y,50,50);
    }
}
