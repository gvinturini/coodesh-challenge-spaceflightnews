# Back-end Challenge 🏅 2021 - Space Flight News Coodesh

Projeto para replicar o CRUD de artigos da API Space Flight News, com um CRON job para sincronização do banco de dados com as novas informações da API original.

## Tecnologias utilizadas
- **Spring Boot Java**
- **Banco de dados MySQL**

## Pré-requisitos
- Java 11
- Maven na versão 3.6.3 ou superior

## Instruções
- Com uma instância do MySQL 8, criar o schema db_spacenews.
- Acessar o repositório do [Gerador de SQL](https://github.com/gvinturini/coodesh-challenge-spaceflightnews-sqlgenerator).
- Executar o script de criação das tabelas no banco presente nele: table_creation.sql.
- Executar o projeto do Gerador de SQL e gerar os scripts para inserção dos últimos dados da API original nas tabelas.
- Executar os três scripts gerados a partir da linha de comando ou do MySQL workbench.
- Para executar este projeto, execute o seguinte comando no terminal:
```shell script
mvn spring-boot:run 
```
- Após a execução do projeto, ao acessar o endereço abaixo, a rota raiz da API estará disponível e a partir dela todas as outras rotas do CRUD:
```
http://localhost:8080/
```


>  This is a challenge by [Coodesh](https://coodesh.com/)
