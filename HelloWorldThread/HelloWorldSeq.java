/**
 * Hello World sequencial.
 */
public class HelloWorldSeq {
    public static void main(String[] args) {
        HelloWorldSeq hws = new HelloWorldSeq();
        hws.print();
    }

    public void print() {
        System.out.printf("[Thread %d] Hello, World!%n", Thread.currentThread().threadId());
    }
} // fim da classe HelloWorldSeq
