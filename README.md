# Hackathon TEA API

API em Spring Boot para validação e agendamento de encaminhamentos relacionados ao TEA (Transtorno do Espectro Autista). O fluxo contempla o cadastro/validação de um encaminhamento e o agendamento de uma consulta com base no protocolo informado.

## ✨ Principais funcionalidades

- **Validação de encaminhamento** com regras de domínio (protocolo, CID, médico e paciente obrigatórios, além de validação de CPF). 
- **Agendamento de encaminhamento** após validação, retornando dados do agendamento.
- **Persistência** em banco H2 em memória com schema inicial. 
- **Documentação OpenAPI** via Springdoc.

## 🧱 Tecnologias

- Java 17
- Spring Boot 3.3.x (Web + Data JPA)
- H2 Database (em memória)

## 🚀 Como executar localmente

### Pré-requisitos

- Java 17+
- Maven (ou usar `./mvnw`)

### Executar a aplicação

```bash
./mvnw spring-boot:run
```

A aplicação sobe em **http://localhost:8080**.

## 📚 Documentação e ferramentas

- **Swagger UI:** http://localhost:8080/swagger-ui/index.html
- **H2 Console:** http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:validacao`
  - User: `sa`
  - Password: *(vazio)*

## 🔗 Endpoints principais

### 1) Validar encaminhamento

`POST /encaminhamento/validacao`

**Body (JSON):**

```json
{
  "protocolo": "ABC-123",
  "cid": "F84.0",
  "medico": {
    "nome": "Dra. Maria Silva",
    "crmUf": "SP",
    "crmNumero": "123456"
  },
  "paciente": {
    "cpf": "123.456.789-09",
    "nome": "João Souza"
  }
}
```

**Resposta:** `200 OK` (sem body) quando válido.

### 2) Agendar encaminhamento

`POST /encaminhamento/agendamento/{protocolo}`

**Resposta (JSON):**

```json
{
  "protocolo": "ABC-123",
  "status": "AGENDADO",
  "dataHora": "2024-10-30T10:30:00",
  "unidade": "UBS Central",
  "especialidade": "NAO_INFORMADA"
}
```

> Caso o protocolo não tenha sido validado previamente, a API retorna erro de validação.

## 🗄️ Banco de dados

O schema é carregado automaticamente a partir de `schema.sql` e a aplicação utiliza H2 em memória com `spring.sql.init.mode=always`.

## 🧪 Testes

```bash
./mvnw test
```

---

Se quiser evoluir o projeto (ex.: regras de CID/CRM, integração com APIs externas e persistência em banco real), o código já está organizado em camadas de **API**, **Application**, **Domain** e **Infrastructure** para facilitar essa evolução.
