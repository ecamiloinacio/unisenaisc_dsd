public enum Simbolo {
    VAZIO(' ', -1),
    X('X', 0),
    O('O', 1);

    public final char rotulo;
    public final int valor;

    private Simbolo(char rotulo, int valor) {
        this.rotulo = rotulo;
        this.valor = valor;
    } // fim do construtor(char, int)
} // fim do enum Simbolo
