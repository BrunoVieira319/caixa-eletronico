# Algoritmo do caixa eletrônico

### Problema
Desenvolva um programa que simule a entrega de notas quando um cliente efetuar um saque em um caixa eletrônico. Os requisitos básicos são os seguintes:
* Entregar o menor número de notas;
* É possível sacar o valor solicitado com as notas disponíveis;
* Saldo do cliente infinito;
* Quantidade de notas infinito (pode-se colocar um valor finito de cédulas para aumentar a dificuldade do problema);
* Notas disponíveis de R$ 100,00; R$ 50,00; R$ 20,00 e R$ 10,00

Exemplos:
* Valor do Saque: R$ 30,00 – Resultado Esperado: Entregar 1 nota de R$20,00 e 1 nota de R$ 10,00.
* Valor do Saque: R$ 80,00 – Resultado Esperado: Entregar 1 nota de R$50,00 1 nota de R$ 20,00 e 1 nota de R$ 10,00.

### Resolução
O projeto possui uma interface que representa o serviço de um caixa eletrônico. Esta aponta os métodos que devem ser implementados caso um caixa eletrônico venha a ser criado.

A classe responsável pela lógica do caixa eletrônico implementa a interface e faz as devidas validações no momento do saque. Esta já deve ser instanciada com alguma quantidade de notas que é passada no construtor.

O enum de notas é responsável por determinar com quais tipos de notas o caixa eletrônico trabalha.

O saque é feito através do método <code>efetuarSaque()</code> que recebe o valor em dinheiro e devolve um Map de nota e quantidade de notas. O fluxo deste método é o seguinte:
```
efetuarSaque ( recebe o valor do saque no parâmetro )
  valida o valor {
    - Verifica se é negativo
    - Verifica se é possível fornecer a quantia solicitada (se foi solicitado moedas ou notas de 2 e 5 reais)
    - Verifica se o caixa possui todo o valor solicitado
  }
  entra em um loop buscando as notas mais altas disponíveis no caixa {
    - Cada vez que uma nota é encontrada, ela é colocada em um Map que tem seu valor 
        no enum de notas como Key e a quantidade de notas já encontradas como Value
    - Subtrai o valor da nota do valor solicitado no saque
    - Condição de parada é o valor do saque igual 0
  }
  retorna o Map contendo a quantidade de notas
```
A reposição das notas no caixa é feito através do método <code>colocarNotasNoCaixa()</code> que recebe o tipo da nota e a quantidade de notas colocadas.

Todo o código está testado com JUnit 4, o qual mostra o seu funcionamento e o valida de fato.

O gerenciador de dependências usado é o Gradle, e a única dependência usada é o framework de testes JUnit.

### Execução
Para rodar os testes entre na pasta do projeto e execute:
```
./gradlew test
```
