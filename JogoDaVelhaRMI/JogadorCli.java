import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class JogadorCli extends UnicastRemoteObject implements Jogador {
    private JogoDaVelha server;
    private Simbolo simbolo;

    public JogadorCli() throws RemoteException {
        try {
            this.server = (JogoDaVelha) Naming.lookup(JogoDaVelha.URL_SERVER);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    } // fim do construtor

    public static void main(String[] args) {
        System.out.println("=== Bem-vindo ao Jogo da Velha com RMI ===\n");
        try {
            JogadorCli client = new JogadorCli();
            client.executar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // fim do metodo main(String[])

    @Override
    public void aguardarOponente() throws RemoteException {
        System.out.println("Aguardando oponente...\n");
    } // fim do metodo aguardarOponente()

    @Override
    public void iniciarPartida(Tabuleiro tabuleiro, Simbolo simbolo) throws RemoteException {
        this.simbolo = simbolo;
        System.out.printf("Partida Iniciada. Você será o \"%c\".\n\n", this.simbolo.rotulo);

        iniciarTurno(tabuleiro);
    } // fim do metodo iniciarPartida(Tabuleiro, Simbolo)

    @Override
    public void iniciarTurno(Tabuleiro tabuleiro) throws RemoteException {
        System.out.println(tabuleiro + "\n");
        if (this.simbolo == tabuleiro.getTurno()) {
            realizarJogada();
            encerrarTurno();
        } else {
            System.out.println("Oponente jogando...");
        }
    } // fim do metodo iniciarTurno(Tabuleiro, Simbolo)

    @Override
    public void encerrarPartida(Tabuleiro tabuleiro) throws RemoteException {
        if (tabuleiro.getVencedor() == this.simbolo) {
            System.out.println("Parabéns! Você venceu!\n\n");
        } else {
            System.out.println("Que pena, você perdeu.\n\n");
        }
    } // fim do metodo encerrarPartida(Tabuleiro)

    private void executar() {
        try {
            this.server.registrar(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // fim do metodo executar()

    private void realizarJogada() {
        try {
            boolean valido = true;
            do {
                int linha = pedirPosicao(true);
                int coluna = pedirPosicao(false);

                valido = this.server.marcar(linha, coluna);
                if (!valido) {
                    System.out.printf("Posição %d, %d já está marcada!\n\n", linha, coluna);
                }
            } while (!valido);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // fim do metodo realizarJogada()

    private int pedirPosicao(boolean linha) {
        int posicao = -1;

        Scanner scanner = new Scanner(System.in);
        boolean valido = true;
        String eixo = (linha) ? "linha" : "coluna";
        do {
            System.out.printf("Informe a %s (entre 1 e 3): ", eixo);
            posicao = scanner.nextInt();

            valido = posicao >= 1 && posicao <= 3;
            if (!valido) {
                System.out.printf("Posição %d é inválida!\n\n", posicao);
            }
        } while (!valido);

        return posicao;
    } // fim do metodo pedirPosicao(boolean)

    private void encerrarTurno() {
        try {
            this.server.encerrarTurno(this.simbolo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // fim do metodo encerrarTurno()

} // fim da classe JogadorCli
