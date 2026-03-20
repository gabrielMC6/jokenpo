# Projeto JoKenPo - FATEC
**Aluno:** Gabriel de Mendonça Costa

## Lógica do Jogo
O aplicativo utiliza a classe `Random` para gerar a jogada da CPU entre três opções (Pedra, Papel ou Tesoura). 

### Regras Implementadas:
* **Vitória do Jogador:** Pedra > Tesoura | Tesoura > Papel | Papel > Pedra.
* **Empate:** Quando a escolha do usuário é igual à da CPU.
* **Melhor de 3:** O placar é atualizado a cada rodada, comparando os resultados até que um lado vença a série.
* **Reset:** O botão de reiniciar limpa as `ImageViews` e zera as variáveis do placar para `-- x --`.
