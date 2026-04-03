****The Beehive API****
Um projeto de API REST de Gerenciamento de Usuários (CRUD) desenvolvida com Java e Spring Boot.

Projeto que implementa autenticação baseada em JWT, controle de acesso por ROLES, 
uso do DTO para isolamento de camadas de dados, tratamento global de exceções,
documentação com Swagger e configuração de CORS para permitir integração com aplicações Front-End.

Objetivo é fornecer uma base sólida e escalável para sistemas que necessitam de autenticação segura e gerenciamento de usuários.

**Tecnologias Utilizadas**
*  Java
*  Spring Boot
*  Spring Security
*  JWT (JSON Web Token)
*  Swagger/OpenAPI
*  Pattern
*  Global Exception Handler
*  CORS Config

**🔐 Autenticação com JWT**
Passo para a autenticação:

1.  O usuário realiza login enviando email e senha.
2.  O sistema valida as crendencias.
3.  Um Token JWT é gerado
4.  O cliente deve enviar o token no header das próximas requisições

Isso garante que apenas usuários autenticados possam acessar os endpoints protegidos.

**Controle de Acesso por ROLES**
O sistema implementar Role Based Access Control (RBAC)
ROLE existente:
* ROLE_USER
* ROLE_ADMIN

Algumas operações podem exigir permissões específicas:
