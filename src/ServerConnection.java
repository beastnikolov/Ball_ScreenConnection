import java.io.IOException;
import java.net.ServerSocket;

public class ServerConnection implements Runnable {
    private static final int PORT_NUMBER = 5000;
    private static ServerSocket serverSocket;
    private static ClientHandler clientHandler;
    private static Thread thread;
    private Viewer viewer;

    public ServerConnection(Viewer viewer) {
        this.viewer = viewer;
    }

    public void runServer() {
        try {
            serverSocket = new ServerSocket(PORT_NUMBER);

            while (true) {
                System.err.println("Server waiting connection...");
                clientHandler = new ClientHandler(serverSocket.accept(),viewer);
                thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
           // e.printStackTrace();
        }
    }

    @Override
    public void run() {
        runServer();
    }
}
