import java.util.Random;

public class Consumer implements Runnable {
    private final static Random generator = new Random();
    private final Buffer sharedBuffer;
    private final int numValues;

    public Consumer(Buffer buffer, int numValues) {
        this.sharedBuffer = buffer;
        this.numValues = numValues;
    } // fim do construtor(Buffer, int)

    @Override
    public void run() {
        int sum = 0;

        try {
            for (int i = 0; i < this.numValues; i++) {
                Thread.sleep(generator.nextInt(3000));
                sum += this.sharedBuffer.get();
                System.out.printf("Soma do Consumidor %s: %d\n", Thread.currentThread().getName(), sum);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Consumidor %s finalizado.\n", Thread.currentThread().getName());
    } // fim do método run()

} // fim da classe Consumer