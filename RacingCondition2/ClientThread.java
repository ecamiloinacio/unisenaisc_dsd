public class ClientThread implements Runnable {
    private final SimpleArray sharedArray;
    private final int startValue;
    private final int numValues;

    public ClientThread(int startValue, int numValues, SimpleArray sharedArray) {
        this.sharedArray = sharedArray;
        this.startValue = startValue;
        this.numValues = numValues;
    } // fim do construtor(int, SimpleArray)

    @Override
    public void run() {
        for (int i = 0; i < numValues; i++) {
            this.sharedArray.add(this.startValue + i);
        }
    } // fim do método run()

} // fim da classe ClientThread
