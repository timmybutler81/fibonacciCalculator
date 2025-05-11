import java.io.IOException;
import java.net.Socket;

public class Test {
    public static void main(String[] args) {
        String serverName = "localhost";
        int port = 8080;

        try {
            Socket socket = new Socket(serverName, port);
            System.out.println("Connected to " + serverName + " on port " + port);
            socket.close();
        } catch (IOException e) {
            System.err.println("Error connecting to " + serverName + " on port " + port + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}
