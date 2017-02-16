import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by jensegebergrasmussen on 03/02/17.
 */
public class ClientLogIn {


    private final String host;
    private final int port;
    private Socket turnstile;


    public ClientLogIn(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void open() throws IOException {
        turnstile = new Socket();
        turnstile.connect(new InetSocketAddress(host, port));
        System.out.println("Turnstile connected to Stadium Servers on " + port);
    }

    /**
     * Sends a message to the server by opening a socket, writing to the input and reading from the output.
     *
     * @param message The message to send
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        // Write to the server
        OutputStream output = turnstile.getOutputStream();
        PrintWriter writer = new PrintWriter(output);
        writer.println(message);
        writer.flush();
    }

    /**
     * Reads a message from the server, if connected.
     *
     * @return A message from the server.
     * @throws IOException
     */
    public String readMessage() throws IOException {
        // Read from the server
        InputStream input = turnstile.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String fromServer;
        while ((fromServer = reader.readLine()) == null) {
            // Wait until the server says something interesting
        }
        System.out.println(fromServer);
        return fromServer;
    }

    public static void main(String[] args) throws IOException {
        //start client
        ClientLogIn client = new ClientLogIn("localhost", 7080);

        //open client
        client.open();
    }

}
