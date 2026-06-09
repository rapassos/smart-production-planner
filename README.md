# Smart Production Planner

![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)
![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-blue)

---

## Visão Geral

O Smart Production Planner é um sistema de apoio ao Planejamento e Controle da Produção (PCP), desenvolvido para auxiliar analistas de produção, logística e operações industriais na tomada de decisões relacionadas à capacidade produtiva, programação de recursos e atendimento da demanda.

O objetivo principal do sistema é responder perguntas como:

* Existe capacidade produtiva suficiente para atender a demanda?
* Qual recurso produtivo está sobrecarregado?
* Quais pedidos correm risco de atraso?
* Qual será a ocupação dos recursos produtivos em determinado período?

O projeto foi inspirado em experiências reais adquiridas em ambientes industriais dos segmentos metalúrgico, automotivo e manufatureiro.

---

## 🚧 Status do Projeto

### Sprint 0 — Concluída ✅

Artefatos entregues:

* [x] Visão Geral do Sistema
* [x] Modelagem de Domínio
* [x] Diagrama UML
* [x] Modelo Lógico de Dados
* [x] ADR-001 — Monólito Modular
* [x] ADR-002 — PostgreSQL

### Próxima Etapa

Sprint Foundation

* [ ] Criação do Projeto Spring Boot
* [ ] Configuração PostgreSQL
* [ ] Docker Compose
* [ ] Flyway
* [ ] Estrutura Modular da Aplicação
* [ ] Configuração Inicial do Ambiente

### Status Geral

| Área           | Status             |
| -------------- | ------------------ |
| Documentação   | ✅ Concluída        |
| Arquitetura    | ✅ Concluída        |
| Infraestrutura | 🚧 Em Planejamento |
| Implementação  | ⏳ Não Iniciada     |
| Testes         | ⏳ Não Iniciados    |

---

## 📊 Modelo de Domínio

O diagrama abaixo representa as principais entidades do domínio e seus relacionamentos.

![Modelo de Domínio](docs/diagrams/domain-model.png)

---

## Arquitetura de Alto Nível

```text
+----------------------+
|      Frontend        |
| React + TypeScript   |
+----------+-----------+
           |
           | REST API
           |
+----------v-----------+
|       Backend        |
|    Spring Boot 3     |
+----------+-----------+
           |
           |
+----------v-----------+
|      PostgreSQL      |
+----------------------+
```

---

## Stack Tecnológica

### Backend

* Java 21
* Spring Boot 3
* Spring Data JPA
* Bean Validation

### Banco de Dados

* PostgreSQL

### Testes

* JUnit 5
* Mockito

### Infraestrutura

* Docker
* Docker Compose

---

## Estilo Arquitetural

### Monólito Modular

A aplicação será organizada por domínios de negócio, permanecendo implantada como uma única aplicação durante a fase inicial do produto.

Benefícios:

* Menor complexidade operacional
* Maior velocidade de desenvolvimento
* Facilidade de manutenção durante o MVP
* Possibilidade de evolução futura para microsserviços

---

## Principais Domínios de Negócio

* Gestão de Clientes
* Gestão de Produtos
* Gestão de Recursos Produtivos
* Gestão de Pedidos de Venda
* Gestão de Ordens de Produção
* Planejamento de Capacidade

---

## Roadmap

### Sprint 0 — Concluída

* Modelagem de Domínio
* Diagrama UML
* Modelo Lógico de Dados
* ADR-001 — Monólito Modular
* ADR-002 — PostgreSQL
* Estrutura Inicial de Documentação

### Sprint Foundation

* Projeto Spring Boot
* PostgreSQL
* Docker Compose
* Flyway
* Estrutura Modular

### Sprint 1

* CRUD Product
* CRUD Resource
* CRUD Customer

### Sprint 2

* CRUD SalesOrder
* CRUD SalesOrderItem
* Relacionamentos JPA

### Sprint 3

* CRUD ProductionOrder
* Motor Inicial de Capacidade

### Sprint 4

* Dashboard Operacional
* Indicadores de Capacidade
* Ocupação de Recursos

---

## 📚 Documentação

```text
docs
├── adr
│   ├── ADR-001-monolito-modular.md
│   └── ADR-002-postgresql.md
│
├── architecture
│   └── system-overview.md
│
├── database
│   └── logical-data-model.md
│
└── diagrams
    ├── domain-model.puml
    └── domain-model.png
```

---

## Evoluções Futuras

* MRP Simplificado
* Controle de Estoque
* Sequenciamento Automático
* Integração ERP
* Integração SAP PP
* Simulação Multi-Recursos
* Dashboard Executivo
* Inteligência Artificial para Apoio ao Planejamento
* Geração Automática de Cenários Produtivos

---

## Público-Alvo

* Analistas de PCP
* Programadores de Produção
* Coordenadores de Operações
* Supervisores Industriais
* Gestores de Manufatura

---

## Autor

Rafael Passos

---

## Licença

Projeto desenvolvido para fins de estudo, portfólio e evolução profissional na área de Arquitetura e Desenvolvimento de Software.

