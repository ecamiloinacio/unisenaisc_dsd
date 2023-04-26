import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PublisherConsumerTest {

    public static void main(String[] args) {
        Buffer sharedBuffer = new SyncBuffer();

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new Producer(sharedBuffer, 1, 10));
        executor.execute(new Consumer(sharedBuffer, 10));
        executor.shutdown();
    } // fim do método main(String[])

} // fim da classe PublisherConsumerTest