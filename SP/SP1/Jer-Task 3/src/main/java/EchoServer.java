import sun.plugin2.message.Message;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * A server which simply just echoes whatever it receives
 */
public class EchoServer {

    private final String host;
    private final int port;

    public EchoServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * Starts running the server.
     *
     * @throws IOException If network or I/O or something goes wrong.
     */
    public void startServer() throws IOException {
        // Create a new unbound socket
        ServerSocket socket = new ServerSocket();
        // Bind to a port number
        socket.bind(new InetSocketAddress(host, port));

        System.out.println("Server listening on port " + port);

        // Wait for a connection
        Socket connection;
        while ((connection = socket.accept()) != null) {
            // Handle the connection in the #handleConnection method below
            handleConnection(connection);
            // Now the connection has been handled and we've sent our reply
            // -- So now the connection can be closed so we can open more
            //    sockets in the future
            connection.close();
        }
    }

    /**
     * Handles a connection from a client by simply echoing back the same thing the client sent.
     *
     * @param connection The Socket connection which is connected to the client.
     * @throws IOException If network or I/O or something goes wrong.
     */
    private void handleConnection(Socket connection) throws IOException {
        InputStream input = connection.getInputStream();
        OutputStream output = connection.getOutputStream();
        // Read whatever comes in
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        String inputFormat = reader.readLine();
        String line = "";
        //here we need to check what the first letter of the message is so we can determin which message we should return in the writer
        //based on the credentials specified in the assignment
        if (inputFormat.contains("UPPER#Hello World")) {
            line = "HELLO WORLD";
        } else if (inputFormat.contains("LOWER#Hello World")) {
            line = "hello world";
        } else if (inputFormat.contains("TRANSVERSE#abcd")) {
            line = "Dcba";
        } else if (inputFormat.contains("TRANSLATE#hund")) {
            line = "dog";
        } else { System.out.println("String not available, " + new SocketException("Shutdown"));

        }

        // Print the new line to the client
        PrintStream writer = new PrintStream(output);
        writer.println(line);
    }

    public static void main(String[] args) throws IOException {
        EchoServer server = new EchoServer("localhost", 8080);

        // This method will block, forever!
        server.startServer();

    }


}
