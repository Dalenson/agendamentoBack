
# Serviço de autenticação utilizando JWT
Serviço desenvolvido para praticar o desenvolvimento de aplicação utilizando JWT. E criação de usuarios via API.
## Stack utilizada

**Back-end:** Spring Boot, MySql, Heroku, Eureka, JWT.

## Documentação da API
### Para que seja possivel realizar os requests das pesquisas, é preciso do token quando é realizado o login.
#### Retorna todos os usuários

```
  GET /api/usuarios
```

#### Retorna um usuário por ID

```
  GET /api/usuarios/{id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. ID do produto |

#### Adiciona um usuário

```
  POST /api/usuarios
```
```
{
    "nome":"usuario",
    "email":"tt.tt@hotmail.com",
    "senha":"123456"
}
```
#### Exclui um usuário por ID

```
  DELETE /api/usuarios/{id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. ID do produto |

#### Faz o login do usuário

```
  POST /api/login
```
```
{
    "email":"tt.tt@hotmail.com",
    "senha":"123456"
}
```




![image](https://user-images.githubusercontent.com/108817919/197065973-31f94531-74d6-485d-b97a-27cded844109.png)

