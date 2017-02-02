import javax.tools.JavaCompiler;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * A client which connects to a server.
 */
public class EchoClient {

    private final String host;
    private final int port;
    private Socket clientSocket;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void open() throws IOException {
        clientSocket = new Socket();
        clientSocket.connect(new InetSocketAddress(host, port));
        System.out.println("Client connected to server on port " + port);
    }

    /**
     * Sends a message to the server by opening a socket, writing to the input and reading from the output.
     *
     * @param message The message to send
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        // Write to the server
        OutputStream output = clientSocket.getOutputStream();
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
        InputStream input = clientSocket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String fromServer;
        while ((fromServer = reader.readLine()) == null) {
            // Wait until the server says something interesting
        }
        return fromServer;
    }

    public static void main(String[] args) throws IOException {
        //start client
        EchoClient client = new EchoClient("localhost", 7080);

        //open client
        client.open();

        //User choices
        System.out.println("Choose between\nUPPER#Hello World\nLOWER#hello world\nTRANSVERSE#dcba\nTRANSLATE#hund\nEnter your command in the console now:");

        //creating scanner object for user input
        Scanner scanner = new Scanner(System.in);
        String request = scanner.nextLine();

        //Sending message with our variable
        client.sendMessage(request);

        //outcomment the message you want to sent

        //client.sendMessage("UPPER#Hello World");

        //client.sendMessage("LOWER#Hello World");

        //client.sendMessage("TRANSVERSE#abcd");

        //client.sendMessage("TRANSLATE#hund");

        //stores answer from server and reads it
        String message = client.readMessage();

        //print the reply in console
        System.out.println(message);

    }

}
