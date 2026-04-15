# ProductStoreApp - Cadastro de Produtos

Este é um aplicativo Android simples desenvolvido como parte de uma avaliação prática. O objetivo do app é auxiliar uma loja de eletrônicos a organizar seu estoque, substituindo anotações em papel por um sistema digital local.

## Funcionalidades

- **Cadastro de Produtos**: Permite inserir nome, código alfanumérico, preço e quantidade em estoque.
- **Validação de Dados**: Garante que nenhum campo fique em branco e que valores numéricos sejam válidos e positivos.
- **Listagem de Produtos**: Exibe uma lista com os produtos cadastrados (nome, código e preço).
- **Armazenamento Local**: Utiliza a biblioteca Room para persistência de dados em um banco de dados SQLite interno.

## Tecnologias Utilizadas

- **Linguagem**: Kotlin
- **Persistência**: Room Database
- **Componentes de UI**: Material Components (TextInputLayout, MaterialCardView), RecyclerView, ScrollView.
- **Concorrência**: Kotlin Coroutines (lifecycleScope).

## Como utilizar

1. Na tela inicial, preencha os campos do produto.
2. Clique em "Salvar Produto" para persistir os dados.
3. Clique em "Ver Lista de Produtos" para visualizar os itens cadastrados.
4. Na tela de listagem, use o botão "Voltar" para retornar ao formulário.
