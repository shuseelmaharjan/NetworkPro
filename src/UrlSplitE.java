
import java.net.URL;
import java.net.MalformedURLException;


public class UrlSplitE {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java URLSplitter <URL>");
            return;
        }

        try {
            URL url = new URL(args[0]);

            System.out.println("Full URL: " + url.toString());
            System.out.println("Protocol: " + url.getProtocol());
            System.out.println("Host: " + url.getHost());
            System.out.println("Port: " + (url.getPort() == -1 ? "Default Port" : url.getPort()));
            System.out.println("Path: " + url.getPath());
            System.out.println("File: " + url.getFile());
            System.out.println("Query: " + (url.getQuery() == null ? "No Query" : url.getQuery()));
            System.out.println("Reference (Fragment): " + (url.getRef() == null ? "No Fragment" : url.getRef()));

        } catch (MalformedURLException e) {
            System.out.println("The URL provided is malformed: " + args[0]);
        }
    }

}
