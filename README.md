# Project ToDo List

Este projeto é uma aplicação desenvolvida com Spring Boot e JPA para gerenciamento de tarefas.
O sistema permite criar, atualizar, listar e excluir tarefas, além de organizá-las por categorias e vinculá-las a usuários.

## Tecnologias usadas:
- Java 21
- Spring boot
- Spring Web
- Spring Data JPA / Hibernet
- MySQL
- SQL
- Maven
- Flyway

## Funcionalidades
- CRUD de usuários, tarefas e categorias
- Flyway para população do banco de dados

## Configuração e execução
  1. Clone o repositório:
     
          git@github.com:LucasLimaDevx/Project_ToDo_List.git


  3. No arquivo application.yml configure o banco de dados com usuário e senha
     

          spring:
          application:
            name: MedicalAppointmentSystem
     
          datasource:
            driver-class-name: com.mysql.cj.jdbc.Driver
            url: jdbc:mysql://localhost:3306/todo_list?useTimezone=true&serverTimezone=UTC
            username: nome_do_usuario
            password: senha_do_usuário
        
          jpa:
            hibernate:
               ddl-auto: update
            properties:
                hibernate:
            show-sql: false
            open-in-view: false
     
    
 4. Use o seguinte comando para entrar na pasta:

        cd Project_ToDo_List/

    
 7. Use o segunte comando na raíz da pasta:

        mvn spring-boot:run

     
# Rotas da API
## Gerenciamento (/api/user)

| Método | Rota                     | Descrição                                |
|--------|--------------------------|------------------------------------------|
| POST   | /api/user                | Cria um novo usuário                     |
| GET    | /api/user                | Lista todos os usuários                  |
| GET    | /api/user/{id}           | Busca um usuário pelo ID                 |
| PUT    | /api/user/{id}           | Atualiza todos os dados de um usuário    |
| DELETE | /api/user/{id}           | Remove um usuário                        |

## Gerenciamento (api/tasks)

| Método | Rota                       | Descrição                                |
|--------|----------------------------|------------------------------------------|
| POST   | /api/task                  | Cria uma nova tarefa                     |
| GET    | /api/task                  | Lista todas as tarefas                   |
| GET    | /api/task/{id}             | Busca uma tarefa pelo ID                 |
| PUT    | /api/task/{id}             | Atualiza todos os dados de uma tarefa    |
| DELETE | /api/task/{id}             | Remove uma tarefa                        |

## Gerenciamento (api/category)

| Método | Rota                           | Descrição                                   |
|--------|--------------------------------|---------------------------------------------|
| POST   | /api/category                  | Cria uma nova categoria                     |
| GET    | /api/category                  | Lista todas as categorias                   |
| GET    | /api/category/{id}             | Busca uma categoria pelo ID                 |
| PUT    | /api/category/{id}             | Atualiza todos os dados de uma categoria    |
| DELETE | /api/category/{id}             | Remove uma categoria                        |


## Acesse a aplicação usando o POSTMAN

Aplicação estará disponível em http//:localhost:8080

## Autor

Lucas Lima Silva: https://www.linkedin.com/in/lucaslimaitz/
