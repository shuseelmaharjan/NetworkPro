import java.net.*;
import java.io.*;
public class HTTPUrlE {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java HttpURLConnectionExample <URL>");
            return;
        }

        try {
            URL url = new URL(args[0]);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            connection.connect();

            int responseCode = connection.getResponseCode();
            String responseMessage = connection.getResponseMessage();

            System.out.println("Response Code: " + responseCode);
            System.out.println("Response Message: " + responseMessage);

            connection.disconnect();

        } catch (MalformedURLException e) {
            System.out.println("The URL provided is malformed: " + args[0]);
        } catch (IOException e) {
            System.out.println("Error during connection: " + e.getMessage());
        }
    }
        }
