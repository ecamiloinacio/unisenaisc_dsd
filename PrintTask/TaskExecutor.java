import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exemplo do livro Deitel & Deitel, Java: Como Programar.
 * 8a Edição.
 */
public class TaskExecutor {
    public static void main(String[] args) {
        PrintTask task1 = new PrintTask("Task1");
        PrintTask task2 = new PrintTask("Task2");
        PrintTask task3 = new PrintTask("Task3");

        System.out.println("Iniciando executor");

        ExecutorService threadExecutor = Executors.newCachedThreadPool();
        threadExecutor.execute(task1);
        threadExecutor.execute(task2);
        threadExecutor.execute(task3);

        threadExecutor.shutdown();

        System.out.println("Tasks iniciadas, main finalizado.\n");
    }
} // fim da classe TaskExecutor
