/**
 * Classe de referência para algoritmo de convolução.
 */
public class Convolutioner implements Runnable {
    private int[][] src;
    private int[][] out;
    private int[][] filter;
    private int numProcs;
    
    public Convolutioner(int[][] src, int[][] out, int[][] filter) {
        this.src = src;
        this.out = out;
        this.filter = filter;
        this.numProcs = Runtime.getRuntime().availableProcessors();
    }

    @Override
    public void run() {
        convolute(src, out, filter);
    }

    public int[][] convolute(int[][] src, int[][] out, int[][] filter) {
        int threadId = (int) Thread.currentThread().threadId() % numProcs;
        int maxRow = src.length - filter.length;
        int maxCol = src[0].length - filter[0].length;

        for (int row = threadId; row <= maxRow; row += numProcs) {
            for (int col = threadId; col <= maxCol; col += numProcs) {
                for (int fRow = 0; fRow < filter.length; fRow++) {
                    for (int fCol = 0; fCol < filter[0].length; fCol++) {
                        out[row][col] += (filter[fRow][fCol] * src[row + fRow][col + fCol]);
                    }
                }
            }
        }

        return out;
    }
} // fim da classe Convolutioner
