import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;

public class Server {
    private int numFromClient;
    private int v3 = -1;

    public void await() {

        try {
            boolean shutdown = false;
            ServerSocket serverSocket = new ServerSocket(8888);
            while (!shutdown) {
                Socket client = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String input = reader.readLine();
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
                int output = calculateFibDynamic(Integer.parseInt(input));
                writer.println(output);
                writer.flush();
                client.close();
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.await();
    }

    private int calculateFibDynamic(int n) {
        int v1 = 0, v2 = 1;
        for (int i = 2; i <= n; i++) {
            v3 = v1 + v2;
            v1 = v2;
            v2 = v3;
        }
        return v3;
    }

    public int getResult() {
        return v3;
    }
}
