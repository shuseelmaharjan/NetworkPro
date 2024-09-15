import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.Date;

public class SingleFileHTTPServer {

    private File file;
    private int port;
    private String encoding;

    public SingleFileHTTPServer(File file, int port, String encoding) {
        this.file = file;
        this.port = port;
        this.encoding = encoding;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Serving " + file.getName() + " on port " + port);

        while (true) {
            try (Socket clientSocket = serverSocket.accept()) {
                serveFile(clientSocket);
            } catch (IOException e) {
                System.err.println("Connection error: " + e.getMessage());
            }
        }
    }

    private void serveFile(Socket clientSocket) throws IOException {
        OutputStream rawOutput = clientSocket.getOutputStream();
        Writer out = new OutputStreamWriter(rawOutput, encoding);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String requestLine = in.readLine();
        if (requestLine != null && requestLine.startsWith("GET")) {
            sendHeader(out, "HTTP/1.0 200 OK", "text/html; charset=" + encoding, file.length());

            Files.copy(file.toPath(), rawOutput);
            rawOutput.flush();
        } else {
            sendHeader(out, "HTTP/1.0 400 Bad Request", "text/plain", 0);
            out.write("Bad Request");
            out.flush();
        }

        out.close();
        in.close();
    }

    private void sendHeader(Writer out, String responseCode, String contentType, long contentLength) throws IOException {
        out.write(responseCode + "\r\n");
        out.write("Date: " + new Date() + "\r\n");
        out.write("Server: SingleFileHTTPServer 1.0\r\n");
        out.write("Content-length: " + contentLength + "\r\n");
        out.write("Content-type: " + contentType + "\r\n\r\n");
        out.flush();
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java SingleFileHTTPServer <file> <port> <encoding>");
            return;
        }

        File file = new File(args[0]);
        int port = Integer.parseInt(args[1]);
        String encoding = args[2];

        if (!file.exists()) {
            System.err.println("File not found: " + file.getAbsolutePath());
            return;
        }

        try {
            SingleFileHTTPServer server = new SingleFileHTTPServer(file, port, encoding);
            server.start();
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }
}
