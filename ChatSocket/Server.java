import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private final int PORT = 5000;
    private List<ServerThread> threads;

    public Server() {
        threads = new ArrayList<>();
    } // fim do construtor()

    public static void main(String[] args) {
        new Server().start();
    } // fim do método main(String[])

    public void broadcast(MessagePacket messagePacket) throws IOException {
        for (ServerThread thread : threads) {
            thread.send(messagePacket);
        }
    } // fim do método broadcast(MessagePacket)

    private void start() {
        System.out.println("Iniciando o servidor...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado");
            listen(serverSocket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // fim do método start()

    private void listen(ServerSocket serverSocket) throws IOException {
        while (true) {
            System.out.println("Aguardando conexão...");
            Socket socket = serverSocket.accept();
            System.out.println("Conexão aceita");

            ServerThread serverThread = new ServerThread(socket, this);
            threads.add(serverThread);
            new Thread(serverThread).start();
        }
    } // fim do método listen(ServerSocket)

} // fim da classe Server
