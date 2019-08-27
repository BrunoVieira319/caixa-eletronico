public enum EnumNotas {

    NOTA_DE_100(100.00),
    NOTA_DE_50(50.00),
    NOTA_DE_20(20.00),
    NOTA_DE_10(10.00);

    private Double valor;

    EnumNotas(Double valor) {
        this.valor = valor;
    }

    public Double getValor() {
        return valor;
    }
}
