public class SyncBuffer implements Buffer {
    private int value = -1;
    private boolean occupied = false;

    public synchronized void set(int value) throws InterruptedException {
        while (this.occupied) {
            System.out.printf("Buffer ocupado. Produtor %s aguardando.\n", Thread.currentThread().getName());
            wait();
        }
        System.out.printf("Produtor %s escreveu: %d\n", Thread.currentThread().getName(), value);
        this.value = value;
        this.occupied = true;
        notifyAll();
    } // fim do método set(int)

    public synchronized int get() throws InterruptedException {
        while (!this.occupied) {
            System.out.printf("Buffer vazio. Consumidor %s aguardando.\n", Thread.currentThread().getName());
            wait();
        }
        System.out.printf("Consumidor %s leu: %d\n", Thread.currentThread().getName(), this.value);
        this.occupied = false;
        notifyAll();
        return this.value;
    } // fim do método get()

} // fim da classe SyncBuffer
