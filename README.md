# Algoritmo do caixa eletrônico

* Para rodar os testes execute:
```
./gradlew test
```

* Descrição:

O projeto possui uma interface que representa o serviço de um caixa eletrônico.

A implementação desta interface possui a lógica e as devidas validações para o saque.

O enum de notas é responsável por determinar com quais tipos de notas o caixa eletrônico trabalha.

O saque é feito através do método efetuarSaque que recebe o valor em dinheiro e devolve um Map de nota e quantidade de notas.

Todo o código está testado com JUnit 4, o qual mostra o seu funcionamento e o valida de fato.

O gererenciador de dependências usado é o Gradle.
