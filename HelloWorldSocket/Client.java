import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 5000;

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    } // fim do método main(String[])

    private void start() {
        try (Socket socket = new Socket(HOST, PORT)) {
            send(socket);
        } catch (Exception e) {
            System.err.println("[CLIENT] Erro no start: " + e.getMessage());
            e.printStackTrace();
        }
    } // fim do método start()

    private void send(Socket socket) {
        try {
            String name = askName();
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(name);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = in.readLine();
            System.out.println(response);
        } catch (Exception e) {
            System.err.println("[CLIENT] Erro no send: " + e.getMessage());
            e.printStackTrace();
        }
    } // fim do método send()

    private String askName() {
        String name;
        boolean valid = true;

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Informe seu nome: ");
            name = scanner.nextLine();

            valid = !name.isBlank();
            if (!valid) {
                System.out.println("[ERRO] Nome não pode ser vazio.");
            }
        } while (!valid);

        return name;
    } // fim do método askName()

} // fim da classe Client
