import java.util.Random;

/**
 * Exemplo do livro Deitel & Deitel, Java: Como Programar.
 * 8a Edição.
 */
public class PrintTask implements Runnable {
    private final int sleepTime;
    private final String taskName;
    private final static Random generator = new Random();

    public PrintTask(String name) {
        taskName = name;
        sleepTime = generator.nextInt(5000);
    }

    public void run() {
        try {
            System.out.printf("%s vai dormir por %d milisegundos.\n", taskName, sleepTime);
            Thread.sleep(sleepTime);
        } catch (InterruptedException exception) {
            System.out.printf("%s %s\n", taskName, "terminou prematuramente devido à interrupção");
        } 
    }
} // fim da classe PrintTask
