import java.util.Random;

public class MatrixGenerator {
    public static void generate(float[][] matrix) {
        Random generator = new Random();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = generator.nextFloat(0, 100);
            }
        }
    } // fim do método generate
} // fim da classe MatrixGenerator
