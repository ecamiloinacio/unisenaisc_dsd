import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final String HOST = "localhost";
    private final int PORT = 5000;
    private final String EXIT_MSG = "exit";

    private Socket socket;
    private Scanner in;
    private ObjectOutputStream out;
    private String name;
    private Thread thread;

    public Client() {
        this.in = new Scanner(System.in);
        this.name = askName();
    } // fim do construtor()

    public static void main(String[] args) {
        new Client().start();
    } // fim do método main(String[])

    private void start() {
        connect();
        run();
        disconnect();
    } // fim do método start()

    private void connect() {
        System.out.println("Conectando ao servidor...");
        try {
            this.socket = new Socket(HOST, PORT);
            System.out.println("Cliente conectado");

            this.out = new ObjectOutputStream(socket.getOutputStream());
            ClientThread clientThread = new ClientThread(socket, this.name);
            this.thread = new Thread(clientThread);
            this.thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // fim do método connect()

    private void run() {
        try {
            String message;
            do {
                message = askMessage();
                if (message.toLowerCase().equals(EXIT_MSG)) {
                    break;
                }
                send(message);
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // fim do método run()

    private void disconnect() {
        try {
            this.thread.interrupt();
            this.socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // fim do método disconnect()

    private void send(String message) throws IOException {
        MessagePacket messagePacket = new MessagePacket(this.name, message);
        this.out.writeObject(messagePacket);
    } // fim do método send(String)

    private String askName() {
        boolean valid = true;
        String name;

        do {
            System.out.print("Informe seu nome: ");
            name = in.nextLine();

            valid = (!name.isBlank());
            if (!valid) {
                System.out.println("Nome inválido!");
            }
        } while (!valid);

        return name;
    } // fim do método askName()

    private String askMessage() {
        String message;
        System.out.printf("Informe sua mensagem: ");
        message = this.in.nextLine();
        return message;
    } // fim do método askMessage()

} // fim da classe Client
