import java.util.Random;

public class Producer implements Runnable {
    private final static Random generator = new Random();
    private final Buffer sharedBuffer;
    private final int initialValue;
    private final int numValues;

    public Producer(Buffer buffer, int initialValue, int numValues) {
        this.sharedBuffer = buffer;
        this.initialValue = initialValue;
        this.numValues = numValues;
    } // fim do construtor(Buffer, int, int)

    @Override
    public void run() {
        int value;
        int sum = 0;

        try {
            for (int i = 0; i < this.numValues; i++) {
                Thread.sleep(generator.nextInt(3000));
                value = initialValue + i;
                this.sharedBuffer.set(value);
                sum += value;
                System.out.printf("Soma do Produtor %s: %d\n", Thread.currentThread().getName(), sum);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Produtor %s finalizado.\n", Thread.currentThread().getName());
    } // fim do método run()

} // fim da classe Producer
