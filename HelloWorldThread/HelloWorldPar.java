/**
 * Hello World paralelo.
 */
public class HelloWorldPar implements Runnable {
    private static final int NUM_THREADS = 8;

    private int myId;

    public HelloWorldPar(int myId) {
        this.myId = myId;
    }

    public static void main(String[] args) {
        for (int i = 0; i < NUM_THREADS; i++) {
            System.out.printf("Iniciando a thread %d%n", i);
            HelloWorldPar hwp = new HelloWorldPar(i);
            Thread t = new Thread(hwp);
            t.start();
        }

        System.out.println("Finalizando a aplicação");
    }

    public void print() {
        System.out.printf("[MyId %d] Hello, World!%n", this.myId);
    }

    @Override
    public void run() {
        print();
    }
} // fim da classe HelloWorldPar
