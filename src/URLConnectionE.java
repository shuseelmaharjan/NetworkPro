import java.net.*;
import java.io.*;


public class URLConnectionE {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java URLConnectionExample <URL>");
            return;
        }

        BufferedReader reader = null;

        try {
            URL url = new URL(args[0]);

            URLConnection connection = url.openConnection();

            connection.connect();

            System.out.println("Document Properties:");
            System.out.println("Content Type: " + connection.getContentType());
            System.out.println("Content Length: " + connection.getContentLength());
            System.out.println("Content Encoding: " + connection.getContentEncoding());
            System.out.println("Date: " + connection.getDate());
            System.out.println("Last Modified: " + connection.getLastModified());
            System.out.println("Expiration: " + connection.getExpiration());

            System.out.println("\nDocument Content:");

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (MalformedURLException e) {
            System.out.println("The URL provided is malformed: " + args[0]);
        } catch (IOException e) {
            System.out.println("Error reading from the URL: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Error closing the reader: " + e.getMessage());
                }
            }
        }
    }
}
