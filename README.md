# 🐝 The Beehive API 

## 💬 Descrição
Um projeto de API REST de Gerenciamento de Usuários (CRUD) desenvolvida com Java e Spring Boot.

Projeto que implementa autenticação baseada em JWT, controle de acesso por ROLES, 
uso do DTO para isolamento de camadas de dados, tratamento global de exceções,
documentação com Swagger e configuração de CORS para permitir integração com aplicações Front-End.

Objetivo é fornecer uma base sólida e escalável para sistemas que necessitam de autenticação segura e gerenciamento de usuários.

## 🎥 Vídeo
 <img src="/src/main/resources/videos/cadastrando Usuário.mp4"/>


## 🛠️ Tecnologias Utilizadas
*  Java
*  Spring Boot
*  Spring Security
*  JWT (JSON Web Token)
*  Swagger/OpenAPI
*  Pattern
*  Global Exception Handler
*  CORS Config
*  Validation
*  PostgreSQL

## 🔐 Autenticação com JWT
Passo para a autenticação:

1.  O usuário realiza login enviando email e senha.
2.  O sistema valida as crendencias.
3.  Um Token JWT é gerado
4.  O cliente deve enviar o token no header das próximas requisições

Isso garante que apenas usuários autenticados possam acessar os endpoints protegidos.

## 👮🛡️ Controle de Acesso por ROLES
O sistema implementar Role Based Access Control (RBAC)

ROLE existente:
* ROLE_USER
* ROLE_ADMIN

Algumas operações podem exigir permissões específicas:
*  GET -> ADMIN
*  POST -> Public
*  DELETE -> Autenticaded
*  PUT -> Autenticaded

## ⚙️ Configurações de Ambiente
**Configuração sobre JWT**
```properties
api.security.token.secret=${Sua_chave_secreta_vindo_do_abiente_das_variavéis}
api.security.token.expiration=900000 
```
***Define o tempo que durará o seu Token***

**Configuração sobre o Administrador**
```properties
admin.email=${ADMIN_EMAIL}
admin.password=${ADMIN_PASSWORD}
admin.name=${ADMIN_NAME}
```
***Defina seu ADMIN pela variavéis de ambiente. Obs: haverá apenas um único Administrador nesse projeto***


**Configuração do PostgreSQL**
```properties
spring.datasource.url=jdbc:postgresql://${DB_HOST}:${DB_PORT:5432}/userSystem
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
```
***Defina seu `${DBs}` em suas variavéis de ambiente, senha, usuário, porta e seu localhost***

 ## 📚 Documentação do (Swagger)
 Com o Swagger, você pode acessar a interface para vizualizar todos os endpoints, os DTOs de entrada/saída e testar as requisições diretamente pelo navegador.

 **Acesse:** (http://localhost:8080/swagger-ui.html)

## 🔑📍 Autenticação e Endpoints
Para acessar os endpoints protegidos, é necessário primeiro realizar o login para obter o Token JWT.

1. `POST: /auth/login` Nesse endpoint você enviará as crendenciais ao login e retonará o Token JWT.
2. Copie o token gerado.
3. Com esse token as requisições subsequentes deverão está no cabeçalho da requisição: `Authorization: Bearer "__TOKEN__"`

**Endpoints de Usuário**
* `POST /usuario` - Cria um novo usuário
* `POST /auth/login` - Login do usuário e a geração do Token JWT
* `GET /usuario` - Lista todos os usuários (Apenas para o ADMIN)
* `PUT /usuario/{id}` - Atualiza os dados do usuário (Requer autenticação)
* `DELETE /usuario/{id}` - Deleta o usuário (Requer autenticação)
