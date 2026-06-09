# ADR-001

## Título

Adoção de Arquitetura Monólito Modular

---

## Status

Aprovado

---

## Contexto

O Smart Production Planner encontra-se em fase inicial de desenvolvimento (MVP).

O sistema possui um único desenvolvedor responsável pela implementação, manutenção e evolução da solução.

O objetivo inicial do produto é validar o domínio de Planejamento e Controle da Produção (PCP), permitindo a gestão de clientes, produtos, recursos produtivos, pedidos de venda, ordens de produção e simulações básicas de capacidade.

Neste estágio, a complexidade operacional deve ser mantida a um nível mínimo para acelerar a entrega de valor e reduzir custos de desenvolvimento.

---

## Decisão

A aplicação será implementada utilizando o padrão arquitetural Monólito Modular.

O sistema será implantado como uma única aplicação Spring Boot, porém organizado internamente por domínios de negócio.

Cada domínio possuirá suas próprias entidades, serviços, repositórios e componentes de aplicação.

---

## Estrutura Proposta

```text
com.rafaelpassos.spp

├── customer
├── product
├── resource
├── salesorder
├── productionorder
├── capacity
├── infrastructure
├── config
└── shared
```

---

## Consequências Positivas

* Menor complexidade operacional
* Menor custo de infraestrutura
* Facilidade de desenvolvimento
* Facilidade de depuração
* Implantação simplificada
* Maior velocidade para validação do produto

---

## Consequências Negativas

* Escalabilidade limitada quando comparada a microsserviços
* Acoplamento maior entre módulos
* Necessidade de disciplina arquitetural para evitar dependências indevidas

---

## Alternativas Consideradas

### Microsserviços

Não adotado.

Motivos:

* Complexidade desnecessária para o MVP
* Maior custo operacional
* Necessidade de observabilidade distribuída
* Necessidade de infraestrutura adicional

### Modular Monolith

Adotado.

Permite evolução futura para microsserviços caso a necessidade de escalabilidade justifique a mudança.

---

## Revisão Futura

Esta decisão poderá ser reavaliada após a conclusão do MVP e validação das funcionalidades principais do produto.

