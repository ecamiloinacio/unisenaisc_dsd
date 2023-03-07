/**
 * Classe de referência para algoritmo de convolução.
 */
public class Convolutioner {
    public static int[][] convolute(int[][] src, int[][] out, int[][] filter) {
        int maxRow = src.length - filter.length;
        int maxCol = src[0].length - filter[0].length;

        for (int row = 0; row <= maxRow; row++) {
            for (int col = 0; col <= maxCol; col++) {
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
