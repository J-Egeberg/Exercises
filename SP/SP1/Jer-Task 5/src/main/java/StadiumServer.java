import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by jensegebergrasmussen on 03/02/17.
 */
public class StadiumServer {

    private final String host;
    private final int port;
    private int customerCounter;

    public StadiumServer(String host, int port) {
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
        ServerSocket Server = new ServerSocket();
        // Bind to a port number
        Server.bind(new InetSocketAddress(host, port));

        //what are you?
        System.out.println("I am the StadiumServer at port: " + port);

        // Wait for a connection from ClientLogin
        Socket connection;
        while ((connection = Server.accept()) != null) {
            // Handle the connection in the #handleConnection method below
            try {
                handleConnection(connection);
                customerCounter++;
                System.out.println("Connection made \ncustomers entered so far: " + customerCounter);
                //catch the socketexception and close the server
            } catch (SocketException exception) {
                Server.close();
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
        String line = "Welcome to the Stadium";
        //here we need to check what the command is so we can determin which message we should return in the writer
        //based on the credentials specified in the assignment


        // Print the new line to the client
        PrintStream writer = new PrintStream(output);
        writer.println(line);
    }

    public static void main(String[] args) throws IOException {
        StadiumServer server = new StadiumServer("localhost", 7080);

        // This method will block, forever
        server.startServer();
    }

}
