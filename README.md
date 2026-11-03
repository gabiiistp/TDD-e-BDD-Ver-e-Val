# Sistema de Cobrança e Descontos de um E-commerce

Atividade prática da disciplina **Verificação e Validação de Software**, do curso de Engenharia de Software. O objetivo é implementar e comparar duas abordagens de desenvolvimento: Tradicional (Code First, Test Later) e TDD (Test Driven Development).

## 1. Objetivo

Desenvolver um módulo Java responsável por calcular o valor final de um pedido, aplicando as regras de desconto, benefício VIP, frete e validação de valores.

## 2. Regras de negócio

1. Compras acima de R$ 500,00 recebem 10% de desconto.
2. Compras acima de R$ 1.000,00 recebem 20% de desconto.
3. Clientes VIP recebem 5% adicional sobre o valor já descontado.
4. Se o valor após todos os descontos for inferior a R$ 100,00, acrescenta-se R$ 15,00 de frete.
5. Valores de itens iguais a zero ou negativos devem lançar `IllegalArgumentException`.

**Interpretação dos limites:** “acima de” significa estritamente maior que. Assim, R$ 500,00 não recebe 10%; R$ 1.000,00 recebe 10%, e somente valores maiores que R$ 1.000,00 recebem 20%. O frete é avaliado depois dos descontos e antes de ser acrescentado ao total.

## 3. Tecnologias

- Java 17
- Maven
- JUnit 5
- IntelliJ IDEA
- Git e GitHub

Foi utilizado `BigDecimal` para cálculos monetários, evitando imprecisões comuns em operações com `double`.

## 4. Estrutura do projeto

```text
src/
├── main/java/br/com/tdd/
│   ├── tradicional/CalculadoraDescontoTradicional.java
│   └── tdd/CalculadoraDescontoTDD.java
└── test/java/br/com/tdd/
    ├── tradicional/CalculadoraDescontoTradicionalTest.java
    └── tdd/CalculadoraDescontoTDDTest.java
docs/
└── RELATORIO_VV.md
pom.xml
README.md
```

## 5. Etapa 1 — Abordagem Tradicional (Code First, Test Later)

Nesta abordagem, a implementação foi desenvolvida antes dos testes.

### Passos realizados

1. Criação da classe `CalculadoraDescontoTradicional`.
2. Implementação da validação do valor total.
3. Implementação do desconto de 10% para compras acima de R$ 500,00.
4. Implementação do desconto de 20% para compras acima de R$ 1.000,00.
5. Implementação do desconto VIP de 5% sobre o valor já descontado.
6. Implementação do frete de R$ 15,00 quando o valor após descontos é inferior a R$ 100,00.
7. Criação posterior da classe `CalculadoraDescontoTradicionalTest`, com testes unitários para as regras e limites.

### Testes da abordagem tradicional

```bash
mvn -Dtest=CalculadoraDescontoTradicionalTest test
```

## 6. Etapa 2 — Abordagem TDD (Test Driven Development)

Nesta abordagem, o desenvolvimento deve seguir o ciclo **Red → Green → Refactor**.

- **Red:** escrever um teste para uma regra ainda não implementada e observar sua falha.
- **Green:** escrever somente o código necessário para fazer o teste passar.
- **Refactor:** melhorar a organização do código sem alterar o comportamento, mantendo todos os testes aprovados.

### Sequência dos ciclos e commits

Os commits abaixo representam a sequência que deve ser registrada durante o desenvolvimento. Cada ciclo deve ser executado e testado antes do próximo.

| Ordem | Ciclo | Alteração | Mensagem sugerida |
|---:|---|---|---|
| 1 | Red 01 | Teste de compra sem desconto | `test: adiciona teste para compra sem desconto` |
| 2 | Green 01 | Implementa cálculo básico | `feat: implementa calculo inicial do pedido` |
| 3 | Red 02 | Testes do desconto de 10% e limite de R$ 500 | `test: adiciona testes para desconto de 10 por cento` |
| 4 | Green 02 | Implementa desconto de 10% | `feat: implementa desconto de 10 por cento` |
| 5 | Red 03 | Testes do desconto de 20% e limite de R$ 1.000 | `test: adiciona testes para desconto de 20 por cento` |
| 6 | Green 03 | Implementa desconto de 20% | `feat: implementa desconto de 20 por cento` |
| 7 | Red 04 | Teste do desconto VIP | `test: adiciona teste para desconto VIP` |
| 8 | Green 04 | Implementa desconto VIP | `feat: implementa desconto adicional VIP` |
| 9 | Red 05 | Testes de cobrança e limite do frete | `test: adiciona testes para regra de frete` |
| 10 | Green 05 | Implementa frete | `feat: implementa cobranca de frete` |
| 11 | Red 06 | Testes para zero e valores negativos | `test: adiciona testes para valores invalidos` |
| 12 | Green 06 | Implementa exceção de argumento inválido | `feat: valida valor total do pedido` |
| 13 | Refactor | Organiza métodos e constantes e executa regressão | `refactor: organiza calculadora e mantem testes` |

### Comandos de teste e commits por ciclo

Em cada ciclo, primeiro adicione somente o novo teste, execute-o e confirme a falha esperada. Depois implemente o mínimo necessário, execute novamente e confirme a aprovação. Faça um commit em cada etapa.

O comando de teste da classe TDD é:

```bash
mvn -Dtest=CalculadoraDescontoTDDTest test
```

Repita este fluxo para cada ciclo:

| Ciclo | Primeiro: teste (Red) | Depois: implementação (Green) |
|---|---|---|
| 01 — compra sem desconto | Adicione o teste `ciclo01_deveManterValorSemDesconto`, execute o comando Maven e faça commit `test: adiciona teste para compra sem desconto` | Implemente o cálculo básico, execute o comando novamente e faça commit `feat: implementa calculo inicial do pedido` |
| 02 — desconto de 10% | Adicione os testes `ciclo02_*`, execute e faça commit `test: adiciona testes para desconto de 10 por cento` | Implemente a faixa de 10%, execute novamente e faça commit `feat: implementa desconto de 10 por cento` |
| 03 — desconto de 20% | Adicione os testes `ciclo03_*`, execute e faça commit `test: adiciona testes para desconto de 20 por cento` | Implemente a faixa de 20%, execute novamente e faça commit `feat: implementa desconto de 20 por cento` |
| 04 — VIP | Adicione o teste `ciclo04_*`, execute e faça commit `test: adiciona teste para desconto VIP` | Implemente os 5% adicionais, execute novamente e faça commit `feat: implementa desconto adicional VIP` |
| 05 — frete | Adicione os testes `ciclo05_*`, execute e faça commit `test: adiciona testes para regra de frete` | Implemente o frete, execute novamente e faça commit `feat: implementa cobranca de frete` |
| 06 — valores inválidos | Adicione os testes `ciclo06_*`, execute e faça commit `test: adiciona testes para valores invalidos` | Implemente a validação, execute novamente e faça commit `feat: valida valor total do pedido` |
| Refactor | Revise nomes, métodos e constantes; execute a suíte completa | Faça commit `refactor: organiza calculadora e mantem testes` |

Para cada commit, use:

```bash
git add .
git commit -m "MENSAGEM_DO_CICLO"
```

Após o Refactor, rode a suíte completa:

```bash
mvn clean test
```

**Atenção:** o arquivo de testes entregue representa a suíte final completa. Para que o histórico comprove TDD, execute os ciclos incrementalmente: não adicione todos os testes de uma vez. Em cada etapa Red, confirme que o teste falha porque a funcionalidade ainda não existe (e não por erro de compilação ou configuração). Em cada etapa Green, confirme que ele passa.

### Testes da abordagem TDD

```bash
mvn -Dtest=CalculadoraDescontoTDDTest test
```

## 7. Executar todos os testes

Para executar a suíte completa:

```bash
mvn clean test
```

Para executar somente os testes tradicionais:

```bash
mvn -Dtest=CalculadoraDescontoTradicionalTest test
```

Para executar somente os testes TDD:

```bash
mvn -Dtest=CalculadoraDescontoTDDTest test
```

O Maven deve estar instalado e configurado no PATH para que o comando `mvn` funcione no terminal. Também é possível executar `clean` e `test` pelo painel Maven do IntelliJ IDEA.

## 8. Resultados esperados

As duas implementações devem apresentar o mesmo comportamento para os mesmos valores de entrada. A diferença está no processo de desenvolvimento:

- Tradicional: primeiro a lógica, depois os testes.
- TDD: primeiro o teste, depois a implementação mínima e a refatoração.
  testes verificam também os limites das regras, como R$ 500,00, R$ 1.000,00 e R$ 100,00, além de valores inválidos.

## 9. Relatório reflexivo

O documento com a análise das atividades de Verificação e Validação, a comparação entre as abordagens e a discussão sobre verificação estática e dinâmica está em [`docs/RELATORIO_VV.md`](docs/RELATORIO_VV.md).

## 10. Observação sobre os registros da atividade

O tempo gasto e as dificuldades da etapa tradicional devem ser preenchidos com base na experiência real durante a execução. Da mesma forma, os commits devem ser registrados no Git conforme os ciclos forem realizados. Não foram atribuídos tempos ou históricos fictícios.

Os 
