import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientConnection implements Runnable {
    private static final int PORT = 5000;
    private static final String HOST = "localhost";
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Scanner input = new Scanner(System.in);


        public ClientConnection() {
        }

        public void connectServer() {
            String mensaje = "";


            try {
                socket = new Socket(HOST,PORT);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(),true);
                System.out.println("Connected to server");
                while (!mensaje.equalsIgnoreCase("disconnect")) {
                    mensaje = input.nextLine();
                    out.println(mensaje);
                    if (mensaje.contains("create") & mensaje.contains("ball")) {
                        System.err.println(in.readLine());
                    }
                }
                System.err.println("Connection closed from server: " + socket.getInetAddress().getHostName());
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    @Override
    public void run() {
        connectServer();
    }
}
