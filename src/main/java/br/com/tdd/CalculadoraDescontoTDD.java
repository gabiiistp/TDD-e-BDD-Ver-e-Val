package br.com.tdd;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculadoraDescontoTDD {

    public BigDecimal calcular(BigDecimal valorItens, boolean clienteVip) {
        BigDecimal valor = valorItens;

        if (valor.compareTo(new BigDecimal("1000.00")) > 0) {
            valor = valor.multiply(new BigDecimal("0.80"));
        } else if (valor.compareTo(new BigDecimal("500.00")) > 0) {
            valor = valor.multiply(new BigDecimal("0.90"));
        }
        if (clienteVip) {
            valor = valor.multiply(new BigDecimal("0.95"));
        }
        if (valor.compareTo(new BigDecimal("100.00")) < 0) {
            valor = valor.add(new BigDecimal("15.00"));
        }
        if (valorItens == null ||
                valorItens.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "O valor total deve ser maior que zero.");
        }
        return valor.setScale(2, RoundingMode.HALF_UP);
    }
    private BigDecimal aplicarDesconto(
            BigDecimal valor, BigDecimal percentual) {
        return valor.multiply(
                        BigDecimal.ONE.subtract(percentual))
                .setScale(2, RoundingMode.HALF_UP);
    }
}