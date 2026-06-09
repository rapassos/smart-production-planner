# Smart Production Planner

# Modelo Lógico de Dados

## Objetivo

Este documento descreve a estrutura lógica do banco de dados do Smart Production Planner.

O modelo foi derivado diretamente do domínio de negócio e servirá como base para:

* Implementação JPA
* Scripts SQL
* Migrações Flyway
* Testes de integração

---

# CUSTOMER

## Descrição

Armazena os clientes responsáveis pelos pedidos.

| Campo   | Tipo         | Obrigatório | Observação      |
| ------- | ------------ | ----------- | --------------- |
| id      | UUID         | Sim         | Chave Primária  |
| code    | VARCHAR(20)  | Sim         | Código único    |
| name    | VARCHAR(150) | Sim         | Nome do cliente |
| city    | VARCHAR(100) | Não         | Cidade          |
| state   | VARCHAR(50)  | Não         | Estado          |
| country | VARCHAR(50)  | Sim         | País            |
| active  | BOOLEAN      | Sim         | Cliente ativo   |

### Restrições

* code deve ser único

---

# PRODUCT

## Descrição

Armazena os produtos fabricados.

| Campo                       | Tipo         | Obrigatório | Observação               |
| --------------------------- | ------------ | ----------- | ------------------------ |
| id                          | UUID         | Sim         | Chave Primária           |
| code                        | VARCHAR(20)  | Sim         | Código único             |
| description                 | VARCHAR(200) | Sim         | Descrição                |
| family                      | VARCHAR(50)  | Sim         | Família do produto       |
| standard_cycle_time_seconds | INTEGER      | Sim         | Tempo padrão em segundos |

### Restrições

* code deve ser único
* standard_cycle_time_seconds > 0

---

# RESOURCE

## Descrição

Representa recursos produtivos da fábrica.

| Campo                   | Tipo         | Obrigatório | Observação        |
| ----------------------- | ------------ | ----------- | ----------------- |
| id                      | UUID         | Sim         | Chave Primária    |
| code                    | VARCHAR(20)  | Sim         | Código único      |
| description             | VARCHAR(150) | Sim         | Descrição         |
| type                    | VARCHAR(30)  | Sim         | Enum ResourceType |
| available_hours_per_day | INTEGER      | Sim         | Horas disponíveis |

### Restrições

* code deve ser único
* available_hours_per_day > 0

---

# SALES_ORDER

## Descrição

Representa pedidos de venda.

| Campo        | Tipo        | Obrigatório | Observação       |
| ------------ | ----------- | ----------- | ---------------- |
| id           | UUID        | Sim         | Chave Primária   |
| order_number | VARCHAR(30) | Sim         | Número do pedido |
| order_date   | DATE        | Sim         | Data do pedido   |
| due_date     | DATE        | Sim         | Data prometida   |
| status       | VARCHAR(30) | Sim         | Enum OrderStatus |
| customer_id  | UUID        | Sim         | FK CUSTOMER      |

### Restrições

* order_number deve ser único

---

# SALES_ORDER_ITEM

## Descrição

Itens pertencentes aos pedidos.

| Campo          | Tipo          | Obrigatório | Observação     |
| -------------- | ------------- | ----------- | -------------- |
| id             | UUID          | Sim         | Chave Primária |
| quantity       | INTEGER       | Sim         | Quantidade     |
| unit_price     | DECIMAL(15,2) | Sim         | Valor unitário |
| sales_order_id | UUID          | Sim         | FK SALES_ORDER |
| product_id     | UUID          | Sim         | FK PRODUCT     |

### Restrições

* quantity > 0
* unit_price >= 0

---

# PRODUCTION_ORDER

## Descrição

Ordens de produção.

| Campo                   | Tipo        | Obrigatório | Observação                 |
| ----------------------- | ----------- | ----------- | -------------------------- |
| id                      | UUID        | Sim         | Chave Primária             |
| production_order_number | VARCHAR(30) | Sim         | Número da OP               |
| quantity                | INTEGER     | Sim         | Quantidade                 |
| start_date              | DATE        | Sim         | Início planejado           |
| end_date                | DATE        | Sim         | Fim planejado              |
| status                  | VARCHAR(30) | Sim         | Enum ProductionOrderStatus |
| product_id              | UUID        | Sim         | FK PRODUCT                 |
| resource_id             | UUID        | Sim         | FK RESOURCE                |
| sales_order_id          | UUID        | Sim         | FK SALES_ORDER             |
| capacity_plan_id        | UUID        | Não         | FK CAPACITY_PLAN           |

### Restrições

* production_order_number deve ser único
* quantity > 0

---

# CAPACITY_PLAN

## Descrição

Cenários de planejamento de capacidade.

| Campo      | Tipo          | Obrigatório | Observação      |
| ---------- | ------------- | ----------- | --------------- |
| id         | UUID          | Sim         | Chave Primária  |
| name       | VARCHAR(100)  | Sim         | Nome do cenário |
| start_date | DATE          | Sim         | Início          |
| end_date   | DATE          | Sim         | Fim             |
| notes      | VARCHAR(1000) | Não         | Observações     |

---

# Relacionamentos

CUSTOMER (1) -------- (N) SALES_ORDER

SALES_ORDER (1) ----- (N) SALES_ORDER_ITEM

PRODUCT (1) --------- (N) SALES_ORDER_ITEM

PRODUCT (1) --------- (N) PRODUCTION_ORDER

RESOURCE (1) -------- (N) PRODUCTION_ORDER

SALES_ORDER (1) ----- (N) PRODUCTION_ORDER

CAPACITY_PLAN (1) --- (N) PRODUCTION_ORDER

---

# Índices Recomendados

## CUSTOMER

* UK_CUSTOMER_CODE

## PRODUCT

* UK_PRODUCT_CODE

## RESOURCE

* UK_RESOURCE_CODE

## SALES_ORDER

* UK_ORDER_NUMBER
* IDX_SALES_ORDER_CUSTOMER

## SALES_ORDER_ITEM

* IDX_SOI_ORDER
* IDX_SOI_PRODUCT

## PRODUCTION_ORDER

* UK_PRODUCTION_ORDER_NUMBER
* IDX_PO_PRODUCT
* IDX_PO_RESOURCE
* IDX_PO_SALES_ORDER

---

# Próxima Evolução

Na Sprint 2 será avaliada a substituição do relacionamento:

PRODUCTION_ORDER → SALES_ORDER

por

PRODUCTION_ORDER → SALES_ORDER_ITEM

para maior aderência aos processos industriais reais.

