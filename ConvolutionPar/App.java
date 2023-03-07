import java.util.Random;

public class App {
    private static final int[][] BORDER_FILTER = {
        { -1, -1, -1},
        { -1,  4, -1},
        { -1, -1, -1}
    };

    private int numProcs;

    public App() {
        this.numProcs = Runtime.getRuntime().availableProcessors();
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java App [nrows] [ncols] [seed]");
            System.exit(1);
        }

        int nrows = Integer.parseInt(args[0]);
        int ncols = Integer.parseInt(args[1]);
        int seed = Integer.parseInt(args[2]);

        App app = new App();
        app.start(nrows, ncols, seed);
    }

    private void start(int nrows, int ncols, int seed) {
        int[][] src = init(nrows, ncols, seed);
        int[][] out = new int[nrows - (BORDER_FILTER.length - 1)][ncols - (BORDER_FILTER[0].length - 1)];

        Thread[] threadPool = new Thread[numProcs];
        for (int i = 0; i < numProcs; i++) {
            threadPool[i] = new Thread(new Convolutioner(src, out, BORDER_FILTER));
            threadPool[i].start();
        }

        //printMatrix(out);
    }

    private int[][] init(int nrows, int ncols, int seed) {
        Random generator = new Random(seed);
        
        int[][] m = new int[nrows][ncols];
        for (int i = 0; i < nrows; i++) {
            for (int j = 0; j < ncols; j++) {
                m[i][j] = generator.nextInt(256);
            }
        }

        return m;
    }

    private void printMatrix(int[][] m) {
        String cellFormat = " %5d |";

        int nrows = m.length;
        int ncols = m[0].length;

        for (int i = 0; i < nrows; i++) {
            System.out.print("\n|");
            for (int j = 0; j < ncols; j++) {
                System.out.printf(cellFormat, m[i][j]);
            }
        }

        System.out.println();
    }
} // fim da classe App
