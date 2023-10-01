# Minha Aplicação Spring Boot com PostgreSQL

Esta é uma aplicação Spring Boot que utiliza um banco de dados PostgreSQL. A aplicação está configurada para ser executada em um ambiente Docker usando Docker Compose.

## Requisitos

- Docker
- Docker Compose

## Como Executar

1. Clone este repositório:

```bash
git clone https://github.com/seu-usuario/sismap-vr.git

2. Navegue até o diretório do projeto:

```bash
cd sismap-vr


3. Inicie os contêineres com Docker Compose:

```bash
docker-compose up -d


Isso iniciará os contêineres para a aplicação Spring Boot e o banco de dados PostgreSQL.

A aplicação estará acessível em http://localhost:8080.

Configurações
Banco de Dados
Host: localhost
Porta: 5432
Nome do Banco de Dados: postgres
Usuário: postgres
Senha: mysecretpassword
Outras Configurações
A aplicação Spring Boot pode ser configurada no arquivo application.properties ou application.yml no diretório src/main/resources.

Este é o controlador responsável por gerenciar as operações relacionadas aos cartões no sistema Sismap.

Funcionalidades
1. Criar Cartão
Endpoint: POST /cartoes

Cria um novo cartão com base nos dados fornecidos no corpo da requisição.

Exemplo de Requisição
json

{
    "senha": "1234",
    "numeroCartao": "1234 5678 9012 3456"
}
curl --location 'http://localhost:8080/cartoes' \
--header 'Content-Type: application/json' \
--data '{
    "senha": "1234",
    "numeroCartao": "1234 5678 9012 3456"
}'


Exemplo de Resposta (Sucesso)
Status: 201 Created

json
{
    "senha": "1234",
    "numeroCartao": "1234 5678 9012 3456"
}

Exemplo de Resposta (Erro - Cartão Já Existe)
Status: 422 Unprocessable Entity

json
{
    "senha": "1234",
    "numeroCartao": "1234 5678 9012 3456"
}
2. Obter Dados do Cartão
Endpoint: GET /cartoes/{numeroCartao}

Obtém os dados do cartão correspondente ao número informado.


Exemplo de Requisição

GET /cartoes/1234 5678 9012 3456

Exemplo de Resposta (Sucesso)
Status: 200 OK

json
{
  "saldo": 470.0
}

curl --location 'http://localhost:8080/cartoes/1234 5678 9012 3456'

Exemplo de Resposta (Erro - Cartão Não Encontrado)
Status: 404 Not Found


Funcionalidades
Realizar Compra
Endpoint: POST /transacoes

Realiza uma nova transação de compra com base nos dados fornecidos no corpo da requisição.

Exemplo de Requisição
json
{
  "numeroCartao": "1234 5678 9012 3456",
  "valorCompra": 150.00,
  "senhaCartao": "1234"
}

curl --location 'http://localhost:8080/transacoes' \
--header 'Content-Type: application/json' \
--data '{
  "numeroCartao": "1",
  "senhaCartao": "1234",
  "valor": 30.0
}'

