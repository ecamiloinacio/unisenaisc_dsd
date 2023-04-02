import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Programa cliente.
 */
public class Client {
    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.getRegistry("127.0.0.1");
            Hello stub = (Hello) reg.lookup("MyHelloStub");
            String msg = stub.sayHello();
            System.out.println("[CLIENT] Message: " + msg);
        } catch (Exception e) {
            System.err.println("[CLIENT ERROR]" + e.toString());
            e.printStackTrace();
        }
    } // fim do método main(String[])
} // fim da classe Client
