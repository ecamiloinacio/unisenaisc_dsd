import java.util.Arrays;
import java.util.Random;

public class SimpleArray {
    private final int[] array;
    private int nextIndex;
    private final Random generator;

    public SimpleArray(int size) {
        this.array = new int[size];
        this.nextIndex = 0;
        this.generator = new Random();
    } // fim do construtor(int)

    public synchronized void add(int value) {
        int index = nextIndex;

        try {
            // simula a realização de algum processamento
            Thread.sleep(generator.nextInt(500));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.array[index] = value;
        System.out.printf("%s escreveu %d no índice %d.\n", Thread.currentThread().getName(), value, index);
        this.nextIndex++;
        System.out.printf("Próximo índice: %d\n", this.nextIndex);
    } // fim do método add(int)

    @Override
    public String toString() {
        return String.format("\nConteúdo do SimpleArray:\n%s", Arrays.toString(this.array));
    } // fim do método toString()

} // fim da classe SimpleArray