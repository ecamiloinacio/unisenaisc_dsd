import java.rmi.Remote;
import java.rmi.RemoteException;

public interface JogoDaVelha extends Remote {

    public static final String URL_SERVER = "rmi://127.0.0.1/JogoDaVelha";

    void registrar(Jogador jogador) throws RemoteException;

    boolean marcar(int linha, int coluna) throws RemoteException;

    void encerrarTurno(Simbolo simbolo) throws RemoteException;

} // fim da interface JogoDaVelha
