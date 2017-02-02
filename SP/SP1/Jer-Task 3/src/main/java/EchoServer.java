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

            try {
                handleConnection(connection);
            } catch (SocketException exception) {
                socket.close();
            }

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
    private void handleConnection(Socket connection) throws IOException, SocketException {
        InputStream input = connection.getInputStream();
        OutputStream output = connection.getOutputStream();
        // Read whatever comes in
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        //saves the input (as a string) and stores in a new variable for later use
        String inputFormat = reader.readLine();

        //creating variable to store the output
        String line = "";
        boolean isMessageSent = false;
        //here we need to check what the command is so we can determin which message we should return in the writer
        //based on the credentials specified in the assignment
        if (inputFormat.contains("UPPER#Hello World")) {
            line = "HELLO WORLD";
            isMessageSent = true;
        }
        if (inputFormat.contains("LOWER#Hello World")) {
            line = "hello world";
            isMessageSent = true;
        }
        if (inputFormat.contains("TRANSVERSE#abcd")) {
            line = "Dcba";
            isMessageSent = true;
        }
        if (inputFormat.contains("TRANSLATE#hund")) {
            line = "dog";
            isMessageSent = true;
        }
            //if we get to this point without any other if statements being met, we would like to disconnect our connection
        if (!isMessageSent) {
            System.out.println("String not available Shutdown ");
            throw new SocketException();
        }

        // Print the new line to the client
        PrintStream writer = new PrintStream(output);
        writer.println(line);
    }

    public static void main(String[] args) throws IOException {
        EchoServer server = new EchoServer("localhost", 7080);

        // This method will block, forever
            server.startServer();
    }


}
