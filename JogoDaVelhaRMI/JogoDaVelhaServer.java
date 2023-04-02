import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class JogoDaVelhaServer extends UnicastRemoteObject implements JogoDaVelha {
    private ArrayList<Jogador> jogadores;
    private Tabuleiro tabuleiro;

    public JogoDaVelhaServer() throws RemoteException {
        this.jogadores = new ArrayList<>();
        this.tabuleiro = new Tabuleiro();
    } // fim do construtor

    public static void main(String[] args) {
        try {
            JogoDaVelhaServer server = new JogoDaVelhaServer();
            server.iniciar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // fim do metodo main(String[])

    @Override
    public void registrar(Jogador jogador) throws RemoteException {
        System.out.println("[SERVER] Registrando jogador...");

        if (this.jogadores.size() >= 2) {
            System.err.println("[SERVER] ERRO: Limite de jogadores atingido");
        } else {
            this.jogadores.add(jogador);

            System.out.println("[SERVER] Jogador registrado");

            iniciarPartida();
        }
    } // fim do metodo registrar(Jogador)

    @Override
    public boolean marcar(int linha, int coluna) throws RemoteException {
        boolean valido = this.tabuleiro.marcar(linha, coluna);
        return valido;
    } // fim do metodo marcar(int, int)

    @Override
    public void encerrarTurno(Simbolo simbolo) throws RemoteException {
        if (this.tabuleiro.getVencedor() != Simbolo.VAZIO) {
            // encerrar o jogo
            for (Jogador jogador : this.jogadores) {
                jogador.encerrarPartida(this.tabuleiro);
            }
        } else {
            // iniciar novo turno
            if (simbolo == Simbolo.X) {
                this.jogadores.get(Simbolo.X.valor).iniciarTurno(this.tabuleiro);
                this.jogadores.get(Simbolo.O.valor).iniciarTurno(this.tabuleiro);
            } else {
                this.jogadores.get(Simbolo.O.valor).iniciarTurno(this.tabuleiro);
                this.jogadores.get(Simbolo.X.valor).iniciarTurno(this.tabuleiro);
            }
        }
    } // fim do metodo encerrerTurno(Simbolo)

    private void iniciar() {
        System.out.println("[SERVER] Iniciando servidor do Jogo da Velha...");
        try {
            Naming.rebind(URL_SERVER, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("[SERVER] Servidor iniciado");
    } // fim do metodo iniciar()

    private void iniciarPartida() {
        try {
            if (this.jogadores.size() == 1) {
                this.jogadores.get(Simbolo.X.valor).aguardarOponente();
            } else {
                // 2 jogadores; iniciar o jogo
                this.jogadores.get(Simbolo.O.valor).iniciarPartida(this.tabuleiro, Simbolo.O);
                this.jogadores.get(Simbolo.X.valor).iniciarPartida(this.tabuleiro, Simbolo.X);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // fim do metodo iniciarPartida()

} // fim da classe JogoDaVelhaServer