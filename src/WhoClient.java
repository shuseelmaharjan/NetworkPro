import java.io.*;
import java.net.*;

public class WhoClient {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java WhoisClient <domain>");
            return;
        }

        String domain = args[0];
        String whoisServer = "whois.iana.org";

        try {
            Socket socket = new Socket(whoisServer, 43);

            OutputStream out = socket.getOutputStream();
            out.write((domain + "\r\n").getBytes());
            out.flush();

            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            System.out.println("Whois information for: " + domain);
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            socket.close();
        } catch (IOException e) {
            System.err.println("Error connecting to the Whois server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
