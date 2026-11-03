package br.com.tdd;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraDescontoTDDTest {
    @Test
    void deveManterValorSemDesconto() {
        CalculadoraDescontoTDD calculadora =
                new CalculadoraDescontoTDD();

        assertEquals(new BigDecimal("300.00"),
                calculadora.calcular(
                        new BigDecimal("300.00"), false));
    }
}
