import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Jogador extends Remote {
    public void aguardarOponente() throws RemoteException;

    public void iniciarPartida(Tabuleiro tabuleiro, Simbolo simbolo) throws RemoteException;

    public void iniciarTurno(Tabuleiro tabuleiro) throws RemoteException;

    public void encerrarPartida(Tabuleiro tabuleiro) throws RemoteException;
} // fim da interface Jogador