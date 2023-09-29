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
