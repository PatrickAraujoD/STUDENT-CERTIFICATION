# STUDENT-CERTIFICATION

Este projeto é um backend desenvolvido em Java para gerenciar o processo de certificação de alunos. Projeto desenvolvido na semana do evento nlw da [rocketseat](https://www.rocketseat.com.br).

### Funcionalidades
-
-
-
-

### Tecnologias Utilizadas

- Java
- Spring Framework
- Hibernate
- PostgreSQL
- Maven (gereciador de dependências)
- Git

### Requisitos

- Java JDK 17
- Maven
- PostgreSQL

### Configuração
1. Clonar o repositório
   
   ```bash
   git clone https://github.com/PatrickAraujoD/STUDENT-CERTIFICATION

2. Configurar o banco de dados
   
- Configure as informações de conexão com o banco de dados no arquivo `application.properties`.

  Configurações no `application.propeties`

  ```bash
  server.port=8085 (porta opcional)
  spring.datasource.url=jdbc:user://server:port/nome_do_banco_de_dados
  spring.datasource.password= password (a senha do seu banco de dados)
  spring.datasource.username=postgres (user do seu banco de dados)
  spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
  spring.jpa.hibernate.ddl-auto=update (fazer atualizações na base de dados)
  spring.jpa.show-sql=true (serve para olhar os comandos sql - opcional)

3. Executar o programa
   - Instale o intellij
   - Navegue até a página `CertificationNlwApplication`
   - Clique em executar a aplicação

      []()
   

### Endpoints da API

A API expõe os seguintes endpoints:

- `POST /students/certification/answer`: Receber um novo certificado
- `GET /questions//technology/{technology}`: lista questões da tecnologia especifica
- `GET /ranking/top10`: lista os alunos com as melhores notas 

### Licença 

Este projeto está licenciado sob a [Licença MIT](https://mit-license.org/)
