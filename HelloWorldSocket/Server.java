import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 5000;

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    } // fim do método main(String[])

    private void start() {
        System.out.println("[SERVER] Iniciando o servidor...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("[SERVER] Servidor iniciado");

            while (true) {
                System.out.println("[SERVER] Aguardando conexão...");
                Socket socket = serverSocket.accept();
                System.out.println("[SERVER] Conexão aceita");

                answer(socket);
            }
        } catch (Exception e) {
            System.err.println("[SERVER] Erro no start: " + e.getMessage());
            e.printStackTrace();
        }
    } // fim do método start()

    private void answer(Socket socket) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String name = in.readLine();

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.printf("Servidor respondeu: Hello, %s!\n", name);
        } catch (Exception e) {
            System.err.println("[SERVER] Erro no answer: " + e.getMessage());
            e.printStackTrace();
        }
    } // fim do método answer(Socket)

} // fim da classe Server
