import java.io.*;
import java.net.*;
import java.util.Date;

public class DaytimeServer {
    public static void main(String[] args) {
        int port = 1313;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Daytime Server is running on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();

                String currentDateTime = new Date().toString();

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println(currentDateTime);

                clientSocket.close();
            }

        } catch (IOException e) {
            System.out.println("Error on the server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
