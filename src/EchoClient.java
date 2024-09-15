import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 1234;

        try (Socket socket = new Socket(hostname, port);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Connected to the echo server");

            String messageToSend;
            System.out.println("Enter messages to send to the server (type 'exit' to quit):");

            while ((messageToSend = userInput.readLine()) != null) {
                out.println(messageToSend);

                if ("exit".equalsIgnoreCase(messageToSend)) {
                    System.out.println("Closing connection...");
                    break;
                }

                String serverResponse = in.readLine();
                System.out.println("Echoed from server: " + serverResponse);
            }
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }
}
