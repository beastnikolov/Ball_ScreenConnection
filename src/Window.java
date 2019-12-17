import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private static Viewer viewer = new Viewer();
    private Thread thread;

    public Window() {
        this.pintarVentana();
        thread = new Thread(viewer);
        thread.start();
    }

    private void pintarVentana() {
        this.getContentPane().setBackground(Color.black);
        this.setTitle("MultiScreen Balls");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(640, 480));
        this.pintarCanvas();
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }

    private void pintarCanvas() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(viewer, gbc);
    }

    public static void main(String[] args) {
        Window window = new Window();
        ServerConnection serverConnection = new ServerConnection(viewer);
        Thread thread = new Thread(serverConnection);
        thread.start();
        ClientConnection clientConnection = new ClientConnection();
        thread = new Thread(clientConnection);
        thread.start();
    }
}