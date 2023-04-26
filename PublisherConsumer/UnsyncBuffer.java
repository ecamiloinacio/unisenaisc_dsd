public class UnsyncBuffer implements Buffer {
    private int value = -1;

    public void set(int value) throws InterruptedException {
        System.out.printf("Produtor %s escreveu: %d\n", Thread.currentThread().getName(), value);
        this.value = value;
    } // fim do método set(int)

    public int get() throws InterruptedException {
        System.out.printf("Consumidor %s leu: %d\n", Thread.currentThread().getName(), this.value);
        return this.value;
    } // fim do método get()

} // fim da classe UnsyncBuffer
