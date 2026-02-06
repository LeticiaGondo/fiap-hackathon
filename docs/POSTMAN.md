# Coleção Postman - Hackathon TEA API

Este documento descreve como importar e executar a coleção Postman com **todos os cenários** de validação e agendamento do projeto.

## ✅ Pré-requisitos

1. **Aplicação Spring Boot** rodando localmente:
   ```bash
   ./mvnw spring-boot:run
   ```
2. **Mocks WireMock** rodando via Docker Compose:
   ```bash
   docker compose up --build
   ```

> Os endpoints de integração devem estar disponíveis em `http://localhost:8081` (Agendamento) e `http://localhost:8089` (CFM).

## 📦 Importar a coleção

1. Abra o Postman.
2. Clique em **Import**.
3. Selecione o arquivo `fiap-hackathon-collection.json` na raiz do repositório.

## 🌐 Variáveis da coleção

A coleção já inclui variáveis configuradas em nível de coleção:

| Variável | Descrição | Valor padrão |
| --- | --- | --- |
| `baseUrl` | URL base da API | `http://localhost:8080` |
| `protocolo` | Protocolo criado nos cenários de sucesso | gerado automaticamente |
| `protocoloSemVagas` | Protocolo para simular falta de vagas | gerado automaticamente |
| `protocoloInexistente` | Protocolo inexistente para validar 404 | gerado automaticamente |

Se você publicar a API em outra URL, atualize apenas o valor de `baseUrl`.

## 🧪 Cenários contemplados

### 1) Validação de encaminhamento

- ✅ Sucesso (protocolo dinâmico)
- ❌ Protocolo duplicado
- ❌ Campos obrigatórios (protocolo, especialidade, motivo, CID, médico, paciente)
- ❌ CPF ausente / inválido
- ❌ CID fora da família TEA
- ❌ CRM inválido (retorno 404 do CFM)
- ❌ CRM inválido (retorno 400 do CFM)

### 2) Agendamento de encaminhamento

- ✅ Sucesso
- ❌ Protocolo inexistente (404)
- ❌ Sem vagas disponíveis (409)

## ▶️ Ordem recomendada para execução

A coleção foi organizada para rodar de cima para baixo. O fluxo recomendado é:

1. **Validar encaminhamento - sucesso** (gera `{{protocolo}}`)
2. **Validar encaminhamento - protocolo duplicado**
3. Demais validações com erro
4. **Validar encaminhamento - especialidade sem vagas** (gera `{{protocoloSemVagas}}`)
5. **Agendar encaminhamento - sucesso**
6. **Agendar encaminhamento - protocolo inexistente**
7. **Agendar encaminhamento - sem vagas**

> Todos os requests possuem testes automáticos (`Tests`) verificando status HTTP e mensagens esperadas.

## 📝 Observações

- O WireMock utiliza *cenarios* para simular vagas por especialidade. Em especialidades de baixa disponibilidade, múltiplas execuções podem gerar conflitos (409) naturalmente.
- Caso precise resetar o estado do WireMock, reinicie o container com `docker compose restart`.
