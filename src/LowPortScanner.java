import java.io.IOException;
import java.net.Socket;

public class LowPortScanner {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java LowPortScanner <host>");
            return;
        }

        String host = args[0];
        int startPort = 1;
        int endPort = 1024;

        System.out.println("Scanning ports " + startPort + " to " + endPort + " on host: " + host);

        for (int port = startPort; port <= endPort; port++) {
            try {
                Socket socket = new Socket(host, port);

                System.out.println("Port " + port + " is open.");

                socket.close();
            } catch (IOException e) {
                System.out.println("Port " + port + " is closed.");
            }
        }

        System.out.println("Port scan completed.");
    }
}
