package br.com.tdd;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculadoraDescontoTDD {

    public BigDecimal calcular(BigDecimal valorItens, boolean clienteVip) {
        return valorItens.setScale(2, RoundingMode.HALF_UP);
    }
}