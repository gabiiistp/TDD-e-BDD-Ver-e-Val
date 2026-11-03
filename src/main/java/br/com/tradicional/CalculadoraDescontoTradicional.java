package br.com.tradicional;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Implementação da abordagem tradicional (Code First, Test Later).
 * A lógica é desenvolvida antes da criação dos testes unitários.
 */
public class CalculadoraDescontoTradicional {

    private static final BigDecimal ZERO = new BigDecimal("0.00");
    private static final BigDecimal LIMITE_DESCONTO_10 = new BigDecimal("500.00");
    private static final BigDecimal LIMITE_DESCONTO_20 = new BigDecimal("1000.00");
    private static final BigDecimal LIMITE_FRETE = new BigDecimal("100.00");
    private static final BigDecimal FRETE = new BigDecimal("15.00");

    /**
     * Calcula o valor final do pedido, aplicando desconto por faixa,
     * desconto VIP e, quando necessário, taxa de frete.
     *
     * @param valorItens valor total dos itens do pedido
     * @param clienteVip indica se o cliente pertence à categoria VIP
     * @return valor final do pedido, com duas casas decimais
     * @throws IllegalArgumentException quando o valor é zero ou negativo
     */
    public BigDecimal calcular(BigDecimal valorItens, boolean clienteVip) {
        validarValor(valorItens);

        BigDecimal valor = valorItens;

        if (valor.compareTo(LIMITE_DESCONTO_20) > 0) {
            valor = aplicarDesconto(valor, new BigDecimal("0.20"));
        } else if (valor.compareTo(LIMITE_DESCONTO_10) > 0) {
            valor = aplicarDesconto(valor, new BigDecimal("0.10"));
        }

        if (clienteVip) {
            valor = aplicarDesconto(valor, new BigDecimal("0.05"));
        }

        // A regra considera o valor depois dos descontos e antes do frete.
        if (valor.compareTo(LIMITE_FRETE) < 0) {
            valor = valor.add(FRETE);
        }

        return valor.setScale(2, RoundingMode.HALF_UP);
    }

    private void validarValor(BigDecimal valorItens) {
        if (valorItens == null || valorItens.compareTo(ZERO) <= 0) {
            throw new IllegalArgumentException("O valor total dos itens deve ser maior que zero.");
        }
    }

    private BigDecimal aplicarDesconto(BigDecimal valor, BigDecimal percentual) {
        BigDecimal fator = BigDecimal.ONE.subtract(percentual);
        return valor.multiply(fator).setScale(2, RoundingMode.HALF_UP);
    }
}
