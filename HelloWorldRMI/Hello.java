import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface remota
 */
public interface Hello extends Remote {
    String sayHello() throws RemoteException;
} // fim da interface Hello
