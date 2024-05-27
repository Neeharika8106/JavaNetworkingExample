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
