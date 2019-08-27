import java.util.Map;

public interface CaixaEletronico {

    Map<EnumNotas, Integer> efetuarSaque(double valor);
    Map<EnumNotas, Integer> getNotasDisponiveis();
    void colocarNotasNoCaixa(EnumNotas nota, Integer quantidade);
    Double getQuantiaDisponivel();
}
