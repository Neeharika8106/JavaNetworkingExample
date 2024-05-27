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
