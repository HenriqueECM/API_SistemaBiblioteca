# Sistema de Biblioteca - API REST com JDBC

Este projeto consiste na implementação de uma API de Sistema de Biblioteca utilizando operações CRUD (Create, Read, Update, Delete) com JDBC para acesso a dados. Os serviços são expostos por meio de uma API REST, permitindo o gerenciamento completo de livros, usuários e empréstimos.

## Principais Funcionalidades

- **CRUD de Livros, Usuários e Empréstimos**
- **Persistência de dados** utilizando banco de dados relacional (MySQL)
- **DAO Manual** para acesso aos dados de cada entidade
- **Service Layer** que integra regras de negócio, como controle de empréstimos e devoluções
- **Controllers REST** para exposição dos endpoints da API

## Estrutura do Projeto

```
src/
└── main/
    └── java/
        └── com.exemplo.biblioteca/
            ├── controller/
            ├── service/
            ├── dao/
            └── model/
```

## Banco de Dados

O sistema utiliza uma estrutura com as tabelas: `livro`, `usuario` e `emprestimo`, com relacionamento entre elas para controle dos empréstimos.

## Endpoints REST

- **Livros:** cadastrar, listar, buscar por id, atualizar, remover
- **Usuários:** cadastrar, listar, buscar por id, atualizar, remover
- **Empréstimos:** registrar, listar, buscar por id, atualizar, excluir, registrar devolução

## Exercícios de Fixação e Desafio Extra

- Cadastro de livros e usuários
- Criação e listagem de empréstimos
- Atualização e exclusão de registros
- Regras de negócio para controle de empréstimos ativos
- Endpoint extra para listar empréstimos de um usuário específico

---

> Implementação prática de um sistema de biblioteca, integrando JDBC, regras de negócio e API REST para operações essenciais de gerenciamento.
