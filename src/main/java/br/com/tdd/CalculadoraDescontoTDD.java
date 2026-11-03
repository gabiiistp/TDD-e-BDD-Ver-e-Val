package br.com.tdd;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculadoraDescontoTDD {

    public BigDecimal calcular(BigDecimal valorItens, boolean clienteVip) {
        BigDecimal valor = valorItens;

        if (valor.compareTo(new BigDecimal("500.00")) > 0) {
            valor = valor.multiply(new BigDecimal("0.90"));
        }

        return valor.setScale(2, RoundingMode.HALF_UP);
    }



}