public class HeatConduction {
    private static final float THD_SILVER = 8.418e-5f;

    private int nrows;
    private int ncols;
    private int niter;

    public static void main(String[] args) {
        HeatConduction app = new HeatConduction(args);
        app.start();

        System.out.println("Encerrando a aplicação");
        System.exit(0);
    } // fim do método main

    public HeatConduction(String[] args) {
        if (args.length < 3) {
            System.err.printf("Usage: java %s [NROWS] [NCOLS] [NITER]%n",
                    this.getClass().getSimpleName());
            System.exit(1);
        }

        this.nrows = Integer.parseInt(args[0]);
        this.ncols = Integer.parseInt(args[1]);
        this.niter = Integer.parseInt(args[2]);
    } // fim do construtor

    public void start() {
        System.out.println("Iniciando a simulação");
        
        float[][] tempIn, tempOut, tempTmp;

        tempIn = new float[this.nrows][this.ncols];
        tempOut = new float[this.nrows][this.ncols];
        tempTmp = new float[this.nrows][this.ncols];

        MatrixGenerator.generate(tempIn);

        int i = 0;
        while (i < this.niter) {
            heatConductionKernel(tempIn, tempOut);

            tempTmp = tempIn;
            tempIn = tempOut;
            tempOut = tempTmp;
            i++;
        }

        System.out.println("Encerrando a simulação");
    } // fim do método start

    public void heatConductionKernel(float[][] tempIn, float[][] tempOut) {
        float d2tdx2, d2tdy2;

        for (int i = 1; i < this.nrows - 1; i++) {
            for (int j = 1; j < this.ncols - 1; j++) {
                d2tdx2 = tempIn[i][j - 1] - 2 * tempIn[i][j] + tempIn[i][j + 1];
                d2tdy2 = tempIn[i - 1][j] - 2 * tempIn[i][j] + tempIn[i + 1][j];

                tempOut[i][j] = tempIn[i][j] + THD_SILVER * (d2tdx2 + d2tdy2);
            }
        }
    } // fim do método heatConductionKernel

} // fim da classe HeatConduction
