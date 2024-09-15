import java.io.*;
import java.net.*;


public class URLReaderE {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java URLReaderE <URL>");
            return;
        }

        BufferedReader reader = null;

        try {
            String inputUrl = args[0];
            if (!inputUrl.startsWith("http://") && !inputUrl.startsWith("https://")) {
                inputUrl = "http://" + inputUrl;
            }

            URL url = new URL(inputUrl);

            URLConnection connection = url.openConnection();
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
