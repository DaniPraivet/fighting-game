import javax.swing.*;

public class VentanaJuego extends JFrame {
    public VentanaJuego() {
        setTitle("Ñawlhalla");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        PanelJuego panelJuego = new PanelJuego();
            add(panelJuego);
            addKeyListener(new ControlesJuego(panelJuego));
            setFocusable(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->{
            VentanaJuego ventana = new VentanaJuego();
            ventana.setVisible(true);
        });
    }
}
