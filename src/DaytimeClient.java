import java.io.*;
import java.net.*;

public class DaytimeClient {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java DaytimeClient <server_ip>");
            return;
        }

        String serverAddress = args[0];
        int port = 1313;

        try (Socket socket = new Socket(serverAddress, port)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String serverResponse = in.readLine();
            System.out.println("Current date and time: " + serverResponse);

        } catch (IOException e) {
            System.out.println("Error on the client: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
