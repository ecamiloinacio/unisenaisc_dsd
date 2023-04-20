import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread implements Runnable {
    private final String EXIT_MSG = "exit";
    private Server server;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ServerThread(Socket socket, Server server) throws IOException {
        this.server = server;
        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());
    } // fim do construtor(Socket, Server)

    @Override
    public void run() {
        try {
            while (true) {
                MessagePacket inMessage = (MessagePacket) this.in.readObject();
                System.out.printf("Mensagem recebida: %s\n", inMessage);
                if (inMessage.getMessage().toLowerCase().equals(EXIT_MSG)) {
                    break;
                }
                this.server.broadcast(inMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // fim do método run()

    public void send(MessagePacket outMessage) throws IOException {
        this.out.writeObject(outMessage);
    } // fim do método send(MessagePacket)

} // fim da classe ServerThread
