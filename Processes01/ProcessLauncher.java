import java.io.IOException;

/**
 * Classe que permite iniciar processos externos no
 * Sistema Operacional.
 */
public class ProcessLauncher {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java ProcessLauncher [executavel]");
            System.exit(1);
        }

        ProcessBuilder builder = new ProcessBuilder(args[0]);
        try {
            builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Launcher finalizado!");
    }

} // fim da classe ProcessLauncher