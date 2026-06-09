# Smart Production Planner

## Visão Geral do Sistema

### Objetivo

O Smart Production Planner é um sistema de apoio ao Planejamento e Controle da Produção (PCP), desenvolvido para auxiliar analistas de produção, logística e operações industriais na avaliação da capacidade produtiva, geração de ordens de produção e identificação de cenários de sobrecarga dos recursos produtivos.

Seu principal objetivo é fornecer visibilidade sobre a capacidade de fabricação da empresa e apoiar a tomada de decisões operacionais.

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

### Frontend

* React
* TypeScript
* Material UI

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

* Menor complexidade operacional;
* Maior velocidade de desenvolvimento;
* Facilidade de manutenção durante o MVP;
* Possibilidade de evolução futura para microsserviços, caso necessário.

---

## Principais Domínios de Negócio

* Gestão de Clientes;
* Gestão de Produtos;
* Gestão de Recursos Produtivos;
* Gestão de Pedidos de Venda;
* Gestão de Ordens de Produção;
* Planejamento de Capacidade.

---

## Requisitos Não Funcionais

### Desempenho

O sistema deverá suportar pelo menos 10 usuários simultâneos durante a fase MVP.

### Disponibilidade

Disponibilidade mínima esperada de 99%.

### Manutenibilidade

As regras de negócio deverão permanecer isoladas das camadas de infraestrutura e persistência.

### Escalabilidade

A arquitetura deverá permitir futura integração com sistemas ERP, módulos de planejamento avançado e ferramentas analíticas.

---

## Evoluções Futuras Previstas

* Controle de Estoque;
* MRP Simplificado;
* Sequenciamento da Produção;
* Integração com ERP;
* Integração com SAP PP;
* Simulação Avançada de Capacidade;
* Dashboards Executivos;
* Inteligência Artificial aplicada ao Planejamento da Produção.

---

## Público-Alvo

O sistema é destinado a:

* Analistas de PCP;
* Programadores de Produção;
* Coordenadores de Operações;
* Supervisores Industriais;
* Gestores de Manufatura.

---

## Contexto de Negócio

O Smart Production Planner foi concebido com base em experiências práticas adquiridas em ambientes industriais dos segmentos metalúrgico, automotivo e de manufatura.

O projeto busca transformar conhecimentos reais de Planejamento e Controle da Produção em uma solução tecnológica capaz de apoiar decisões operacionais e estratégicas relacionadas à capacidade produtiva.

