import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private Viewer viewer;

    public ClientHandler(Socket clientSocket, Viewer viewer) {
        this.clientSocket = clientSocket;
        this.viewer = viewer;
    }

    @Override
    public void run() {
        String mensaje = "";
        System.err.println("Connection from : " + clientSocket.getInetAddress().getHostName() + " successfull!");
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            while (!mensaje.equalsIgnoreCase("disconnect")) {
                mensaje = in.readLine();
                System.out.println("Client: " + mensaje);
                if (mensaje.contains("createship")) {
                    createShip(mensaje);
                }
            }
            System.err.println("Connection closed: " + clientSocket.getInetAddress().getHostName());
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createShip(String mensaje) {
        try {
            String[] stringArray = mensaje.split(" ");
            if (stringArray.length < 6 || stringArray.length > 6) {
                throw new Exception("IncorrectUsageException");
            }
            Color color = new Color(Integer.valueOf(stringArray[3]),Integer.valueOf(stringArray[4]),Integer.valueOf(stringArray[5]));
            SimpleShip simpleShip = new SimpleShip(clientSocket.getInetAddress().getHostAddress(),Integer.valueOf(stringArray[1]),Integer.valueOf(stringArray[2]),color);
            viewer.createClientShip(simpleShip);
            out.println("Server: Ship created successfully!");
            System.err.println("Created ship | X: " + simpleShip.getShipX() + " Y: " + simpleShip.getShipY() + " Color: " + color.getRGB());
        } catch (Exception e) {
            System.err.println("Ship creation failed");
            out.println("Server: Incorrect command. Correct usage: create ball 'X' 'Y' 'Rcolor' 'Gcolor' 'Bcolor'");
        }

    }
}