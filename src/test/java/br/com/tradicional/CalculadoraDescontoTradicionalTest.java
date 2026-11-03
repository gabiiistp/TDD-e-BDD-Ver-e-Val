package br.com.tradicional;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraDescontoTradicionalTest {

    private final CalculadoraDescontoTradicional calculadora =
            new CalculadoraDescontoTradicional();

    @Test
    void deveManterValorQuandoCompraNaoAtingeFaixaDeDesconto() {
        assertEquals(new BigDecimal("300.00"),
                calculadora.calcular(new BigDecimal("300.00"), false));
    }

    @Test
    void deveAplicarDezPorCentoQuandoCompraForMaiorQueQuinhentos() {
        assertEquals(new BigDecimal("540.00"),
                calculadora.calcular(new BigDecimal("600.00"), false));
    }

    @Test
    void compraDeQuinhentosNaoDeveReceberDescontoDeDezPorCento() {
        assertEquals(new BigDecimal("500.00"),
                calculadora.calcular(new BigDecimal("500.00"), false));
    }

    @Test
    void deveAplicarVintePorCentoQuandoCompraForMaiorQueMil() {
        assertEquals(new BigDecimal("880.00"),
                calculadora.calcular(new BigDecimal("1100.00"), false));
    }

    @Test
    void compraDeMilDeveReceberDezPorCentoENaoVinte() {
        assertEquals(new BigDecimal("900.00"),
                calculadora.calcular(new BigDecimal("1000.00"), false));
    }

    @Test
    void deveAplicarDescontoVipSobreValorJaDescontado() {
        assertEquals(new BigDecimal("912.00"),
                calculadora.calcular(new BigDecimal("1200.00"), true));
    }

    @Test
    void deveCobrarFreteQuandoValorAposDescontosForInferiorACem() {
        assertEquals(new BigDecimal("95.00"),
                calculadora.calcular(new BigDecimal("80.00"), false));
    }

    @Test
    void naoDeveCobrarFreteQuandoValorAposDescontosForCem() {
        assertEquals(new BigDecimal("100.00"),
                calculadora.calcular(new BigDecimal("100.00"), false));
    }

    @Test
    void deveCobrarFreteAposAplicarDescontoVipSeValorFicarAbaixoDeCem() {
        assertEquals(new BigDecimal("109.05"),
                calculadora.calcular(new BigDecimal("99.00"), true));
    }

    @Test
    void deveLancarExcecaoParaValorZero() {
        assertThrows(IllegalArgumentException.class,
                () -> calculadora.calcular(new BigDecimal("0.00"), false));
    }

    @Test
    void deveLancarExcecaoParaValorNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> calculadora.calcular(new BigDecimal("-10.00"), false));
    }

    @Test
    void deveLancarExcecaoParaValorNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> calculadora.calcular(null, false));
    }
}
