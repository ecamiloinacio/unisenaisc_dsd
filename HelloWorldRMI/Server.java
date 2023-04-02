import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Programa servidor.
 */
public class Server {
    public static void main(String[] args) {
        try {
            HelloImpl obj = new HelloImpl();
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);

            Registry reg = LocateRegistry.getRegistry("127.0.0.1");
            reg.bind("MyHelloStub", stub);

            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("[SERVER ERROR] " + e.toString());
            e.printStackTrace();
        }
    } // fim do método main(String[])
} // fim da classe Server
