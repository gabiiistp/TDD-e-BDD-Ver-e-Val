# Relatório — Verificação e Validação: Abordagem Tradicional e TDD

**Disciplina:** Verificação e Validação de Software  
**Curso:** Engenharia de Software  
**Projeto:** Sistema de Cobrança e Descontos de um E-commerce

## 1. Objetivo

Este relatório apresenta os artefatos desenvolvidos para o módulo de cálculo de descontos de um e-commerce e compara duas formas de desenvolvimento: a abordagem tradicional (Code First, Test Later) e o Desenvolvimento Orientado a Testes (TDD).

O módulo recebe o valor total dos itens e a informação de que o cliente pertence ou não à categoria VIP. A partir desses dados, calcula descontos, verifica a necessidade de frete e rejeita valores inválidos.

## 2. Artefatos desenvolvidos

| Artefato | Descrição |
|---|---|
| `CalculadoraDescontoTradicional.java` | Implementação da lógica pela abordagem tradicional |
| `CalculadoraDescontoTradicionalTest.java` | Testes unitários criados depois da implementação tradicional |
| `CalculadoraDescontoTDD.java` | Implementação construída por ciclos TDD |
| `CalculadoraDescontoTDDTest.java` | Suíte final de testes desenvolvida incrementalmente |
| `pom.xml` | Configuração do projeto Maven e dependência JUnit 5 |
| `README.md` | Instruções, regras, estrutura e sequência de desenvolvimento |
| Histórico Git | Registro dos ciclos Red, Green e Refactor |

## 3. Casos de teste contemplados

| ID | Cenário | Resultado esperado |
|---|---|---|
| CT01 | Compra de R$ 300,00, cliente comum | R$ 300,00 |
| CT02 | Compra de R$ 600,00, cliente comum | R$ 540,00 |
| CT03 | Compra de exatamente R$ 500,00 | R$ 500,00 |
| CT04 | Compra de R$ 1.100,00, cliente comum | R$ 880,00 |
| CT05 | Compra de exatamente R$ 1.000,00 | R$ 900,00 |
| CT06 | Compra de R$ 1.200,00, cliente VIP | R$ 912,00 |
| CT07 | Compra de R$ 80,00, cliente comum | R$ 95,00 |
| CT08 | Compra de R$ 100,00, cliente comum | R$ 100,00 |
| CT09 | Compra de R$ 99,00, cliente VIP | R$ 109,00 |
| CT10 | Compra com valor zero | `IllegalArgumentException` |
| CT11 | Compra com valor negativo | `IllegalArgumentException` |
| CT12 | Valor de entrada nulo | `IllegalArgumentException` |

## 4. Em que momento ocorreram as atividades de Verificação e Validação na Abordagem Tradicional e na Abordagem TDD?

### 4.1 Abordagem Tradicional

Na abordagem tradicional, a implementação foi desenvolvida primeiro, considerando os requisitos apresentados. Nesse momento, a verificação ocorreu principalmente pela análise da lógica criada, observando se as condições, os cálculos e a ordem de aplicação dos descontos estavam de acordo com as regras de negócio.

Depois que o código foi concluído, foram criados os testes unitários. A execução desses testes permitiu verificar dinamicamente o comportamento do programa, identificando se os resultados produzidos correspondiam aos valores esperados para cada cenário.

A validação esteve relacionada à confirmação de que o módulo atendia à necessidade definida para o sistema de cobrança do e-commerce. Isso foi avaliado por meio dos cenários de negócio, como compras com desconto, clientes VIP, cobrança de frete e valores inválidos.

Portanto, nessa abordagem, os testes foram utilizados posteriormente para avaliar uma implementação que já estava pronta. Caso algum erro fosse encontrado, seria necessário retornar ao código para realizar os ajustes.

### 4.2 Abordagem TDD

Na abordagem TDD, as atividades de Verificação e Validação foram incorporadas ao desenvolvimento desde o início. Antes de implementar uma regra, foi escrito um teste que representava o comportamento esperado.

No ciclo Red, o teste foi executado e apresentou falha, pois a funcionalidade ainda não estava implementada. No ciclo Green, foi desenvolvido o código mínimo necessário para que o teste passasse. Por fim, no Refactor, o código foi reorganizado, mantendo os testes aprovados.

A verificação ocorreu de forma contínua, pois cada implementação era confrontada com os testes automatizados. A validação também começou mais cedo, já que os testes foram elaborados a partir dos comportamentos esperados pelo negócio.

Dessa forma, o TDD permite identificar problemas durante a construção da funcionalidade, em vez de deixar toda a avaliação para depois da implementação.

## 5. Como a inversão na ordem de escrita dos testes afeta o papel da verificação estática (análise de requisitos/design) e dinâmica (execução de testes)?

A inversão da ordem de desenvolvimento altera principalmente o momento em que os problemas podem ser identificados e a maneira como os requisitos são utilizados durante a implementação.

Na abordagem tradicional, o desenvolvedor normalmente analisa os requisitos, planeja a solução e implementa o código. A verificação estática pode ocorrer durante essa análise, por meio da revisão dos requisitos, do projeto e do próprio código, sem precisar executar o programa. Entretanto, como os testes são escritos somente depois da implementação, alguns comportamentos incorretos podem permanecer no código até a fase de testes.

Na abordagem TDD, os testes são escritos antes do código. Isso faz com que o desenvolvedor precise interpretar cada requisito e transformá-lo em um comportamento verificável antes de implementar a funcionalidade. Assim, a análise dos requisitos e do design ganha maior importância, porque um teste mal elaborado pode representar incorretamente a regra de negócio.

A verificação dinâmica passa a ocorrer em ciclos menores. Cada teste é executado logo após sua criação e novamente depois da implementação. A falha inicial demonstra que o teste consegue identificar a ausência do comportamento esperado, enquanto a aprovação posterior indica que a implementação atende àquele cenário específico.

É importante destacar que o TDD não elimina a verificação estática. A revisão de requisitos, a análise do design, a leitura do código e a inspeção continuam necessárias. Da mesma forma, um teste aprovado não garante sozinho que todos os requisitos foram atendidos: é necessário criar cenários suficientes, incluindo valores-limite e situações inválidas.

No caso deste projeto, por exemplo, testar somente uma compra de R$ 600,00 não seria suficiente para garantir que a regra de desconto está correta. Também é necessário verificar o limite exato de R$ 500,00. O mesmo raciocínio se aplica aos limites de R$ 1.000,00 e R$ 100,00.

Assim, a inversão da ordem aproxima a verificação dinâmica da etapa de implementação e incentiva uma análise antecipada dos requisitos. O resultado é um processo mais incremental, no qual os erros podem ser encontrados mais cedo.

## 6. Comparação entre as abordagens

| Critério | Tradicional | TDD |
|---|---|---|
| Ordem de desenvolvimento | Código e depois testes | Testes e depois código |
| Primeiro contato com os testes | Após a implementação | Antes da implementação |
| Identificação de erros | Pode ocorrer mais tarde | Ocorre em ciclos menores |
| Verificação dinâmica | Após a implementação e durante ajustes | A cada ciclo de desenvolvimento |
| Foco inicial | Construir a funcionalidade | Definir o comportamento esperado |
| Refatoração | Pode ocorrer após os testes | Faz parte do ciclo de desenvolvimento |
| Histórico de desenvolvimento | Registra a implementação e os testes posteriores | Registra a evolução incremental dos testes e do código |

## 7. Tempo gasto e dificuldades encontradas

**Preencher após a execução real da atividade.** Não foram atribuídos tempos fictícios.

### Abordagem Tradicional

- Tempo gasto na implementação: [preencher]
- Tempo gasto na criação dos testes: [preencher]
- Principais dificuldades: [descrever as dificuldades observadas]

Pontos que podem ser avaliados durante a execução: compreender um código já implementado, identificar quais cenários precisam ser testados, verificar os limites das condições e criar testes que cubram as regras sem depender da implementação interna.

### Abordagem TDD

- Tempo gasto nos ciclos Red, Green e Refactor: [preencher]
- Principais dificuldades: [descrever as dificuldades observadas]

Pontos que podem ser avaliados durante a execução: escrever primeiro um teste que falhe pelo motivo esperado, implementar apenas o necessário, evitar antecipar funcionalidades e manter os testes anteriores aprovados.

## 8. Conclusão

A realização das duas abordagens permitiu observar diferenças importantes no processo de desenvolvimento e na aplicação das atividades de Verificação e Validação.

Na abordagem tradicional, os testes foram utilizados depois que a lógica estava pronta, funcionando como uma forma de avaliar o comportamento da implementação. Já no TDD, os testes participaram da construção da solução desde o início, orientando o desenvolvimento de cada regra.

Na minha avaliação, o TDD contribui para um desenvolvimento mais organizado, pois exige que o comportamento esperado seja definido antes da implementação. Além disso, a execução frequente dos testes ajuda a identificar erros mais cedo e oferece maior segurança durante as alterações no código.

Entretanto, as duas abordagens dependem de requisitos bem compreendidos e de testes adequados. O TDD não substitui a análise estática nem garante a ausência de defeitos, mas torna a verificação dinâmica parte constante do desenvolvimento.
