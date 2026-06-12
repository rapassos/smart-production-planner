# Contribuindo para o Smart Production Planner 🚀

Primeiramente, obrigado por demonstrar interesse em contribuir para o nosso planejador inteligente de produção! Este é um projeto de código aberto que visa resolver problemas reais de PCP e manufatura utilizando engenharia de software de alta performance.

Para manter a consistência e a qualidade do ecossistema, seguimos algumas diretrizes obrigatórias.

## 🛠️ Stack Tecnológica
- **Java 21** (LTS)
- **Spring Boot 3.x**
- **PostgreSQL** (via Docker)
- **Flyway** (Migrações de banco de dados)

## 📐 Padrões de Arquitetura e Código
- **Monólito Modular:** O projeto é dividido estritamente por contextos de negócio (atualmente `manufacturing` e `sales`). Comunicação intermodular deve ser mínima e desacoplada.
- **Records como DTOs:** Sempre utilize `record` para a entrada e saída de dados na camada de controle.
- **Validação de Payload:** Use anotações do `jakarta.validation` para garantir a integridade dos dados antes que cheguem ao serviço.
- **Tratamento Global de Erros:** Exceções de negócio devem lançar erros controlados capturados pelo nosso `GlobalExceptionHandler`.

## 🌿 Fluxo de Trabalho do Git (Git Flow)
1. Faça um **Fork** do repositório.
2. Crie uma branch para a sua feature/correção:
   ```bash
   git checkout -b feat/nome-da-sua-feature
   # ou
   git checkout -b fix/nome-da-sua-correcao
   ```
3. Siga o padrão de **Conventional Commits**:
   - `feat(sales): ...` para novas funcionalidades.
   - `fix(manufacturing): ...` para correção de bugs.
   - `chore(infra): ...` para atualizações de build, gitignore ou dependências.

## 🐳 Como Executar o Ambiente Local
Certifique-se de ter o Docker instalado e execute o banco de dados:
```bash
docker compose up -d
```
Rode a aplicação utilizando o Maven Wrapper:
```bash
./mvnw spring-boot:run
```

Se tiver dúvidas, abra uma Issue ou comente nos cartões da nossa Sprint!