import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CaixaEletronicoImplTest {

    private CaixaEletronico caixaEletronico;

    @Before
    public void setup() {
        HashMap<EnumNotas, Integer> notas = new HashMap<>();
        notas.put(EnumNotas.NOTA_DE_100, 10);
        notas.put(EnumNotas.NOTA_DE_50, 20);
        notas.put(EnumNotas.NOTA_DE_20, 25);
        notas.put(EnumNotas.NOTA_DE_10, 40);

        this.caixaEletronico = new CaixaEletronicoImpl(notas);
    }

    @Test
    public void deveVerificarValorDisponivelNoCaixa() {
        assertEquals(Double.valueOf(2900.00), caixaEletronico.getQuantiaDisponivel());
    }

    @Test
    public void deveVerificarNotasDisponiveisNoCaixa() {
        Map<EnumNotas, Integer> notasDisponiveis = caixaEletronico.getNotasDisponiveis();
        assertEquals(4, notasDisponiveis.size());
        assertEquals(Integer.valueOf(10), notasDisponiveis.get(EnumNotas.NOTA_DE_100));
        assertEquals(Integer.valueOf(20), notasDisponiveis.get(EnumNotas.NOTA_DE_50));
        assertEquals(Integer.valueOf(25), notasDisponiveis.get(EnumNotas.NOTA_DE_20));
        assertEquals(Integer.valueOf(40), notasDisponiveis.get(EnumNotas.NOTA_DE_10));
    }

    @Test
    public void deveEfetuarSaque() {
        Map<EnumNotas, Integer> notas = caixaEletronico.efetuarSaque(1400.00);
        assertEquals(2, notas.size());
        assertEquals(Integer.valueOf(10), notas.get(EnumNotas.NOTA_DE_100));
        assertEquals(Integer.valueOf(8), notas.get(EnumNotas.NOTA_DE_50));
    }

    @Test
    public void deveAtualizarQuantiaDisponivelAposEfetuarSaque() {
        caixaEletronico.efetuarSaque(1400.00);
        assertEquals(Double.valueOf(1500.00), caixaEletronico.getQuantiaDisponivel());
    }

    @Test
    public void deveColocarNotasNoCaixa() {
        caixaEletronico.colocarNotasNoCaixa(EnumNotas.NOTA_DE_100, 20);
        assertEquals(Integer.valueOf(30), caixaEletronico.getNotasDisponiveis().get(EnumNotas.NOTA_DE_100));
    }

    @Test
    public void deveAtualizarQuantiaDisponivelAposColocarNotasNoCaixa() {
        caixaEletronico.colocarNotasNoCaixa(EnumNotas.NOTA_DE_100, 20);
        assertEquals(Double.valueOf(4900.00), caixaEletronico.getQuantiaDisponivel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDeveRealizarSaqueSeCaixaNaoTiverDinheiroSuficiente() {
        caixaEletronico.efetuarSaque(20000.00);
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDeveRealizarSaqueSeOValorNaoPuderSerPagoComAsNotasPresentesNoCaixa() {
        caixaEletronico.efetuarSaque(18.82);
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDeveRealizarSaqueSeOValorForMenorQueDezReais() {
        caixaEletronico.efetuarSaque(5.00);
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDeveRealizarSaqueSeOValorForNegativo() {
        caixaEletronico.efetuarSaque(-5.00);
    }
}