# Coleção Postman - Hackathon TEA API

Este guia foi preparado para apoiar a **entrega acadêmica da pós-graduação**, com um roteiro completo de execução e evidências de teste da API.

A coleção cobre os fluxos de:

- validação de encaminhamento;
- agendamento de encaminhamento;
- cenários positivos e negativos com validação de payload e regras de negócio.

## 1) Arquivo da coleção

- **Arquivo principal:** `fiap-hackathon-collection.json`
- **Formato:** Postman Collection v2.1

---

## 2) Pré-requisitos

1. Subir a API Spring Boot:

```bash
./mvnw spring-boot:run
```

2. Subir os mocks de integração (WireMock):

```bash
docker compose up --build
```

Serviços esperados:

- API principal: `http://localhost:8080`
- WireMock Agendamento: `http://localhost:8081`
- WireMock CFM: `http://localhost:8089`

---

## 3) Como importar no Postman

1. Abra o Postman.
2. Clique em **Import**.
3. Selecione o arquivo `fiap-hackathon-collection.json` na raiz do repositório.
4. Execute a coleção pelo **Collection Runner** na ordem indicada neste documento.

---

## 4) Variáveis da coleção

| Variável | Finalidade | Exemplo |
|---|---|---|
| `baseUrl` | URL base da API | `http://localhost:8080` |
| `protocoloAtual` | Protocolo transitório para requests de validação | gerado em runtime |
| `protocoloSucesso` | Protocolo criado no cenário de validação com sucesso | gerado em runtime |
| `protocoloAgendamentoSucesso` | Protocolo preparado para agendamento bem-sucedido | gerado em runtime |
| `protocoloInexistente` | Protocolo inexistente para testar `404` | gerado em runtime |
| `protocoloSemVagas` | Protocolo para cenário de indisponibilidade (`409`) | gerado em runtime |

---

## 5) Estratégia de testes da coleção

A coleção foi estruturada em **2 pastas**:

1. **Validação de Encaminhamento**
2. **Agendamento de Encaminhamento**

Cada request já contém scripts em **Tests** com validações automáticas de:

- status HTTP esperado;
- presença e conteúdo das mensagens de erro;
- validação de campos de resposta em cenários de sucesso.

---

## 6) Matriz de cobertura de casos

### 6.1 Validação de encaminhamento

| ID | Cenário | Resultado esperado                                |
|---|---|---------------------------------------------------|
| 01 | Validação com payload válido | `200 OK`                                          |
| 02 | Protocolo duplicado | `400` + `pendencias`            |
| 03 | Protocolo ausente | `400` + `pendencias`  |
| 04 | Especialidade ausente | `400` + `pendencias`                              |
| 05 | Motivo da solicitação ausente | `400` + `pendencias`                              |
| 06 | CID ausente | `400` + `pendencias`                              |
| 07 | CID fora da família TEA | `400` + `pendencias`                              |
| 08 | Médico ausente | `400` + `pendencias`                              |
| 09 | Nome do médico ausente | `400` + `pendencias`                              |
| 10 | UF do CRM ausente | `400` + `pendencias`                              |
| 11 | Número do CRM ausente | `400` + `pendencias`                              |
| 12 | Paciente ausente | `400` + `pendencias`                              |
| 13 | Nome do paciente ausente | `400` + `pendencias`                              |
| 14 | CPF ausente | `400` + `pendencias`                              |
| 15 | CPF inválido | `400` + `pendencias`                              |
| 16 | CRM inexistente no CFM (`404` externo) | `400` + `pendencias`                              |
| 17 | CRM inválido no CFM (`400` externo) | `400` + `pendencias`                              |
| 18 | Múltiplas pendências em um único payload | `400` + array com múltiplos itens                 |

### 6.2 Agendamento

| ID | Cenário | Resultado esperado |
|---|---|---|
| 19 | Preparar protocolo válido para agendamento | `200 OK` |
| 20 | Agendamento com sucesso | `200 OK` + corpo com protocolo e especialidade |
| 21 | Protocolo inexistente | `404 Not Found` |
| 22 | Preparar protocolo para cenário sem vagas | `200 OK` |
| 23 | Agendamento sem vagas | `409 Conflict` |

---

## 7) Ordem recomendada de execução

Execute a coleção em ordem crescente (01 → 23), pois alguns cenários dependem de variáveis geradas em requests anteriores.

---

## 8) Troubleshooting

- Se todos os requests falharem com conexão: verifique se a API está em `localhost:8080`.
- Se houver falha em CRM: confirme WireMock CFM em `localhost:8089`.
- Se houver falha em agendamento: confirme WireMock Agendamento em `localhost:8081`.
- Para reiniciar os cenários de vagas do WireMock:

```bash
docker compose restart
```
