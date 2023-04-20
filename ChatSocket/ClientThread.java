import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientThread implements Runnable {
    private ObjectInputStream in;
    private String name;

    public ClientThread(Socket socket, String name) throws IOException {
        this.in = new ObjectInputStream(socket.getInputStream());
        this.name = name;
    } // fim do construtor(Socket)

    @Override
    public void run() {
        try {
            while (true) {
                MessagePacket inMessage = (MessagePacket) this.in.readObject();
                if (!inMessage.getName().equals(this.name)) {
                    System.out.printf("\n[%s] %s\n", inMessage.getName(), inMessage.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("[ERRO] Thread cliente finalizada");
    } // fim do método run()

} // fim da classe ClientThread
