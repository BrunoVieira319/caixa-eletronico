import java.util.*;
import java.util.stream.Stream;

public class CaixaEletronicoImpl implements CaixaEletronico {

    private Map<EnumNotas, Integer> notasDisponiveis;
    private Double quantiaDisponivel;

    public CaixaEletronicoImpl(Map<EnumNotas, Integer> notasDisponiveis) {
        this.notasDisponiveis = notasDisponiveis;
        atualizarValorDisponivelNoCaixa();
    }

    @Override
    public Map<EnumNotas, Integer> efetuarSaque(double valor) {
        validarValorDoSaque(valor);

        Map<EnumNotas, Integer> saque = new HashMap<>();
        Double restante = valor;
        while (restante != 0) {
            Optional<EnumNotas> notaMaisAltaDisponivel = encontrarNotaMaisAltaParaDeterminadoValor(restante);
            if (notaMaisAltaDisponivel.isPresent()) {
                saque.merge(notaMaisAltaDisponivel.get(), 1, Integer::sum);
                notasDisponiveis.compute(notaMaisAltaDisponivel.get(), (nota, qtd) -> qtd -= 1);
                restante -= notaMaisAltaDisponivel.get().getValor();
            }
        }
        atualizarValorDisponivelNoCaixa();
        return saque;
    }

    private void validarValorDoSaque(double valor) {
        if (valor > quantiaDisponivel) {
            throw new IllegalArgumentException("Quantia indisponível no caixa");
        }
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor inválido");
        }
        if (valor < 10) {
            throw new IllegalArgumentException("Valor mínimo para saque é R$ 10,00");
        }
        if (valor % 10 != 0) {
            throw new IllegalArgumentException("Valor inválido. O caixa não possui moedas, notas de 2 e notas 5 reais!");
        }
    }

    private Optional<EnumNotas> encontrarNotaMaisAltaParaDeterminadoValor(Double valor) {
        return Stream.of(EnumNotas.values())
                .filter(enumNotas -> notasDisponiveis.containsKey(enumNotas))
                .filter(enumNotas -> valor >= enumNotas.getValor() && notasDisponiveis.get(enumNotas) > 0)
                .max(Comparator.comparingDouble(EnumNotas::getValor));
    }

    private void atualizarValorDisponivelNoCaixa() {
        Double valorTotal = 0.0;
        for (Map.Entry<EnumNotas, Integer> entry : notasDisponiveis.entrySet()) {
            EnumNotas nota = entry.getKey();
            Integer qtd = entry.getValue();
            valorTotal += nota.getValor() * qtd;
        }
        quantiaDisponivel = valorTotal;
    }

    @Override
    public void colocarNotasNoCaixa(EnumNotas nota, Integer quantidade) {
        notasDisponiveis.merge(nota, quantidade, Integer::sum);
        atualizarValorDisponivelNoCaixa();
    }

    @Override
    public Map<EnumNotas, Integer> getNotasDisponiveis() {
        return Collections.unmodifiableMap(notasDisponiveis);
    }

    @Override
    public Double getQuantiaDisponivel() {
        return quantiaDisponivel;
    }
}