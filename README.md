# API REST SEGUROS UNIMED

API Restful CRUD para um cadastro de clientes e endereços usando Spring Boot, JPA, Hibernate, Gson, Jackson Databind, H2 Database e [API Viacep](https://viacep.com.br)

## FUNCIONALIDADES

**1. Cadastro, listagem, atualização e exclusão de clientes.**

**2. Cadastro, listagem, atualização e exclusão de endereços.**

**3. Cadastro de endereços via busca por cep em api externa.**


## EXPLORANDO API REST

O aplicativo define as seguintes APIs CRUD.


### CLIENTE

| METODOS | URL                        | DESCRIÇÃO                       | EXEMPLO DE CORPO DE SOLICITAÇÃO |
|---------|----------------------------|---------------------------------|---------------------------------|
| GET     | /customers                 | Obter todos clientes            |                                 |
| GET     | /customers/{id}            | Obter cliente por um id         |                                 |
| GET     | /customers/name/{name}     | Obter cliente por um nome       |                                 |
| GET     | /customers/email/{email}   | Obter cliente por email         |                                 |
| GET     | /customers/gender/{gender} | Obter clientes por genero       |                                 |
| POST    | /customers                 | Cadastrar um novo cliente       | [JSON](#clientecreate)          |
| PUT     | /customers/{id}            | Atualizar um cliente existente  | [JSON](#clienteupdate)          |
| DELETE  | /customers/{id}            | Deletar um cliente existente    |                                 |
| POST    | /customers/{id}/enderecos  | Adicionar endereço a um cliente | [JSON](#addendereco)            |
| GET     | /customers/city/{city}     | Obter clientes por cidade       |                                 |
| GET     | /customers/state/{state}   | Obter clientes por estado       |                                 |

### ENDEREÇO

| METODOS | URL                             | DESCRIÇÃO                | EXEMPLO DE CORPO DE SOLICITAÇÃO |
|---------|---------------------------------|--------------------------|---------------------------------|
| GET     | /adress                         | Obter todos os endereços |                                 |
| DELETE  | /adress/{adressId}/{customerId} | Deletar um endereço      |                                 |

## EXEMPLO DE CORPO DE SOLICITAÇÃO VALIDOS

##### <a id="clientecreate">Cadastrar Cliente -> /customers</a>
```json
{
  "name": "Thanos",
  "email": "thanos@vingadores.com",
  "gender": "M"
}
```

##### <a id="clienteupdate">Atualizar Cliente -> /customers/{id}</a>
```json
{
  "name": "Hulk atualizado",
  "email": "hulk@vingadores.com",
  "gender": "M"
}
```

##### <a id="addendereco">Adiconar Endereço -> /customers/{id}/enderecos</a>
```json
{
  "cep": "65067197"
}
```