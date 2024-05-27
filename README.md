# Java Networking Example

This project demonstrates a simple client-server communication using Java networking. The client sends a message to the server, which reads and prints it, and then the server responds.

## Code Explanation

### Dynamic_SimpleServer.java

```java
import java.io.*;
import java.net.*;

class Dynamic_SimpleServer {
    public static void main(String[] args) {
        int port = 12345; // Port number on which the server will listen

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected");

                // Set up input and output streams
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Received message from client: " + message);
                    if (message.equalsIgnoreCase("stop")) {
                        System.out.println("Client has terminated the connection.");
                        break;
                    }

                    System.out.print("Enter message to send to client: ");
                    String response = stdIn.readLine();
                    out.println(response);
                    if (response.equalsIgnoreCase("stop")) {
                        System.out.println("Server has terminated the connection.");
                        break;
                    }
                }

                in.close();
                out.close();
                clientSocket.close();
                if (message != null && message.equalsIgnoreCase("stop")) {
                    break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


Explanation:

Import Statements: The code imports the necessary Java I/O and networking classes.
Port Number: Defines the port number on which the server will listen for incoming connections.
ServerSocket: Creates a ServerSocket instance to listen for incoming client connections.
Accept Connections: Waits for a client to connect and accepts the connection.
Input/Output Streams: Sets up input and output streams for reading from and writing to the client.
Message Loop: Reads messages from the client and responds. The loop continues until the client sends "stop".
Close Resources: Closes the input/output streams and the client socket.


### Dynamic_SimpleServer.java

import java.io.*;
import java.net.*;

public class Dynamic_SimpleClient {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Server address
        int port = 12345; // Server port

        try (Socket socket = new Socket(serverAddress, port)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            String message;
            while (true) {
                System.out.print("Enter message to send to server: ");
                message = stdIn.readLine();
                out.println(message);
                if (message.equalsIgnoreCase("stop")) {
                    System.out.println("Client has terminated the connection.");
                    break;
                }

                String response = in.readLine();
                System.out.println("Received message from server: " + response);
                if (response.equalsIgnoreCase("stop")) {
                    System.out.println("Server has terminated the connection.");
                    break;
                }
            }

            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

Explanation:

Import Statements: The code imports the necessary Java I/O and networking classes.
Server Address and Port: Specifies the server's address and port number.
Socket: Creates a Socket instance to connect to the server.
Input/Output Streams: Sets up input and output streams for reading from and writing to the server.
Message Loop: Prompts the user to enter a message to send to the server, then reads and prints the server's response. The loop continues until the user types "stop".
Close Resources: Closes the input/output streams and the socket.

#############How to Run
Step 1: Compile the Code
Open your terminal or command prompt and navigate to the directory where your Java files are located.
javac SimpleServer.java
javac SimpleClient.java

Step 2: Run the Server
Start the server by running the following command:
java SimpleServer
You should see a message indicating that the server is listening on the specified port.

Step 3: Run the Client
In a new terminal or command prompt window, start the client by running the following command:
java SimpleClient
Step 4: Communicate
From Client: Enter a message in the client terminal and press Enter. The message will be sent to the server.
From Server: The server terminal will display the received message and prompt you to enter a response. Type your response and press Enter. The response will be sent back to the client.
Step 5: Terminate the Connection
To end the communication, type stop in either the client or server terminal. This will terminate the connection and close the sockets on both sides.
