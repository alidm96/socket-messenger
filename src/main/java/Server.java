import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        ServerSocket serverSocket = null;

        System.out.println("S: Server is started");

        serverSocket = new ServerSocket(6666);

        System.out.println("S: Server is waiting for client request");

        while (true) {

            try {

                socket = serverSocket.accept();

                System.out.println("S: Client is connected");

                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                while (true) {

                    String messageFromClient = bufferedReader.readLine();

                    System.out.println("Client: " + messageFromClient);

                    bufferedWriter.write("Message received.");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    if (messageFromClient.equalsIgnoreCase("bye")) break;

                }

                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
