# Projeto Parque do Saber 

O Parque do Saber é um projeto desenvolvido em Spring Boot, com foco na criação de uma API REST para permitir operações de CRUD relacionadas às entidades Diretor, Turma e Aluno.
O sistema utiliza jQuery para consumir os endpoints e gerar dinamicamente as tabelas exibidas nas páginas HTML.

A proposta desse projeto é demonstrar como uma aplicação web moderna integra backend, banco de dados e frontend dinâmico por meio de requisições AJAX.

## Visão Geral do Funcionamento

### A arquitetura do sistema funciona da seguinte forma:
- O backend (Spring Boot) expõe endpoints REST seguindo o padrão /api/.
- O banco de dados H2 é carregado em memória, facilitando testes e desenvolvimento.
- O frontend utiliza jQuery para buscar dados da API e renderizar tabelas HTML automaticamente.
- As exibições das páginas HTML são atualizadas dinamicamente a partir dos dados recebidos da API.

## Tecnologias Utilizadas
- Java 17+
- Spring Boot
- Banco de Dados H2
- HTML5
- CSS3
- Bootstrap 5
- Thymeleaf
- JavaScript
- jQuery
- VSCode

### Cada tecnologia pode ser visualizada no projeto ao abrir seus respectivos diretórios:
- Controllers Java:
- >/src/main/java/.../controller/
- Templates HTML:
  >/src/main/resources/templates/
- Scripts JavaScript:
  >/src/main/resources/static/js/
- Configurações Spring Boot:
  >application.properties
- Banco H2: acessível via navegador ao rodar o projeto

## Funcionalidades Implementadas
### API REST
#### Foram criados três controllers:
- DiretorApiController
- TurmaApiController
- AlunoApiController

### Métodos Disponíveis
- GET — listar registros
- POST — criar registro
- PUT — atualizar registro
- DELETE — remover registro

### Atualizações no Front-end
- Páginas HTML consumindo dados via jQuery
- Tabelas carregadas dinamicamente
- Scripts organizados individualmente por entidade
- Testes realizados utilizando banco H2

## Como Executar o Projeto
- Baixe o arquivo ParqueDoSaber_API_REST.zip.
- Extraia a pasta para seu computador na Área de Trabalho.
- Abra o projeto no VSCode (ou IDE desejada).
- Execute a classe principal:
```
DemoApplication.java
```
- No navegador, acesse:
_http://localhost:8088/_ ou _http://localhost:8088/ParqueDoSaber.html_
- Teste as funcionalidades de cadastro, edição e exclusão diretamente pelas tabelas exibidas.
