# Sistema de Guia e Rastreador de Séries e Filmes
Aluno: Luiz Felipe Sousa Neilson

Sistema feito pela obtenção de nota em Mobile Coding

# Guia e Rastreador de Séries e Filmes

Projeto acadêmico em Kotlin que funciona pelo terminal. O sistema permite consultar séries e filmes, ver episódios e comentários, marcar episódios assistidos no dia e avaliar mídias com notas de 1 a 5.

O tempo assistido é calculado pela soma da duração dos episódios marcados. Acima de 180 minutos, o programa exibe um alerta de maratona.

Todo o código está no arquivo `main.kt`. Os dados ficam na memória somente enquanto o programa está aberto.

## Como executar

```bash
kotlinc main.kt -include-runtime -d guia.jar
java -jar guia.jar