# Documentação da API

Esta documentação descreve a API criada usando o Spring Boot e outras dependências listadas no arquivo `pom.xml`.

## Dependências

As seguintes dependências foram utilizadas neste projeto:

- **Spring Boot Starter Data JPA**: Fornece suporte ao Spring Data JPA para acesso a bancos de dados.
- **H2 Database (Runtime)**: Um banco de dados em memória usado para desenvolvimento e testes.
- **Lombok (Opcional)**: Uma biblioteca que ajuda a reduzir a verbosidade do código Java.
- **Spring Boot Starter Test**: Fornece suporte para testes de unidade no Spring Boot.
- **Spring Boot Starter Web**: Configura o Spring Boot para desenvolvimento de aplicativos web.
- **ModelMapper**: Uma biblioteca para mapeamento de objetos.
- **Logback Classic**: Um framework de logging para o Java.
- **SLF4J (Simple Logging Facade for Java)**: Uma API de logging que fornece abstração para diferentes frameworks de logging.
- **JUL to SLF4J**: Redireciona as mensagens de log do JUL (Java Util Logging) para o SLF4J.
- **Log4J over SLF4J**: Redireciona as mensagens de log do Log4j para o SLF4J.
- **Springdoc OpenAPI Starter WebMVC UI**: Uma biblioteca para geração de documentação OpenAPI (Swagger) para APIs Spring.

## Uso

Para usar a API, siga as instruções de instalação e execução abaixo:

### Requisitos

- JDK (Java Development Kit) instalado (versão compatível com o Spring Boot).
- Maven ou Gradle instalado (dependendo da ferramenta de build utilizada no projeto).

### Instalação

1. Clone este repositório:

    ```bash
    git clone https://github.com/seu-usuario/seu-projeto.git
    ```

Após clonar o repositório, você pode acessar a documentação da API via Swagger, utilizando a seguinte URL: [Swagger UI](http://localhost:8081/swagger-ui/index.html#/)

### Ambientes e Configurações

Este projeto inclui um perfil de ambiente que acessa exclusivamente o banco de dados H2. Caso existam outros ambientes/bancos, é possível criar perfis no `pom.xml`.

Os logs da aplicação são personalizados com um template do **Logback Classic**, um framework de logging para o Java. A **SLF4J (Simple Logging Facade for Java)** é usada como API de logging, fornecendo abstração para diferentes frameworks de logging. Isso facilita a análise de erros e horários.

Variáveis de ambiente são configuradas no arquivo `application.properties`, permitindo atribuição a qualquer objeto.

### Arquitetura e Funcionalidades

O projeto segue uma arquitetura onde as camadas são organizadas da seguinte forma:

- Controller -> Service -> Model

É utilizado DTOs com records e Mappers para mapeamento das Models. São utilizadas anotações transacionais (`@Transactional`), `@Service`, e `@Repository` para injeção de dependências.

Para persistência de dados, é utilizado o `JPARepository` que provê transações.

### Principais Serviços

Para utilizar a API, existem 2 serviços principais:

#### Inserção de Palíndromos

- Método: POST
- Endpoint: `/api/insert/palindrome`
- Payload de exemplo:

    ```json
    {
      "columns": [
        {
          "lines": ["A", "O", "S", "S", "O", "A"]
        },
        {
          "lines": ["E", "R", "Z", "B", "L", "A"]
        },
        {
          "lines": ["S", "S", "A", "P", "M", "M"]
        },   
        {
          "lines": ["S", "B", "P", "R", "Z", "A"]
        },
        {
          "lines": ["E", "L", "E", "R", "A", "M"]
        },
        {
          "lines": ["E", "L", "E", "R", "A", "R"]
        }
              ]
    }
    ```
- Response body esperado:
    ```json
    [
      {
      "id": 1,
      "palindrome": "OSSO"
      },
    {
        "id": 2,
        "palindrome": "ESSE"
    },
    {
        "id": 3,
        "palindrome": "REVER"
    }
  ]
     ```

#### Obtenção de Palíndromos

- Método: GET
- Endpoint: `/api/get/palindromes`
- Parâmetros:
  - `id` (Opcional): ID do palíndromo a ser recuperado. Se não for especificado, retornarão todos os cadastros.

Substitua `http://localhost:8081` pela URL da sua instância da API em execução.

Isso conclui a descrição da API e suas principais funcionalidades. Para mais detalhes, consulte a documentação gerada pelo Swagger UI.
