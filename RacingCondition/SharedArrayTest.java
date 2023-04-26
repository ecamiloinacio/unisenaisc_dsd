public class SharedArrayTest {
    private static final int NUM_THREADS = 2;
    private static final int VALUES_PER_THREAD = 5;

    public static void main(String[] args) {
        SimpleArray sharedArray = new SimpleArray(NUM_THREADS * VALUES_PER_THREAD);

        Thread[] pool = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            ClientThread client = new ClientThread((i * 10) + 1, VALUES_PER_THREAD, sharedArray);
            pool[i] = new Thread(client);
            pool[i].start();
        }
        try {
            for (int i = 0; i < NUM_THREADS; i++) {
                pool[i].join();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(sharedArray);
    } // fim do método main(String[])

} // fim da classe SharedArrayTest
