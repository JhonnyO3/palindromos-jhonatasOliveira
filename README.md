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
![Texto Alternativo](arquitetura-controllers(1).png)

- Logica de conversão da Matriz:
Para um processo inicial, é feito um processo de mapeamento nos indices da Matriz para listas de angulo, onde cada lista representa um angulo diferente, sendo eles
- Listas Horizontais
- Listas Verticais
- Listas Diagonais
  
  
  ![Texto Alternativo](arquitetura-controllers(2).png)

  - Algoritmo de limpeza por pares:
Para a identificalçao de possiveis palindromes, utilizamos um algoritmo que percorre por todas listas formatadas postariormente.
Ao percorrer, cada lista é interada por 2 principais pares, sendo eles:
- 1par: percorre lista.index(i) e concatena com lista.index(i+1);
- 2par: percorre a lista.index(f =i+1) e concatena com a lista.index(f+1);
  
  Após a montagem de pares, é comparado cada par que está se formando, obs: o par1 só altera seu index após o par2 ter percorrido e comparado toda a lista.
  caso a palavra seja impar, ou seja 3/5/7 letras, o algoritmo identifica pela contagem dos pares, e monta 1 trio e 1 par de letras.
  
  -Trabalhando com pares

  ![Texto Alternativo](arquitetura-controllers(3).png)


- Trabalhando com impares
 
 ![Texto Alternativo](arquitetura-controllers(5).png)

   
    

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

Substitua `http://localhost:8081` pela URL da sua instância da API em execução.

Isso conclui a descrição da API e suas principais funcionalidades. Para mais detalhes, consulte a documentação gerada pelo Swagger UI.
