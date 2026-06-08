# Smart Production Planner

Sistema de apoio ao Planejamento e Controle da Produção (PCP), desenvolvido para auxiliar analistas de produção, logística e operações industriais na tomada de decisões relacionadas à capacidade produtiva, programação de recursos e atendimento da demanda.

---

## Sobre o Projeto

Em muitas indústrias, atividades de planejamento ainda dependem de planilhas complexas, controles paralelos e análises manuais que consomem tempo e aumentam o risco de erros operacionais.

O Smart Production Planner nasce com o objetivo de centralizar informações relevantes para o planejamento da produção, permitindo uma visão mais clara da capacidade produtiva, recursos disponíveis, pedidos em carteira e necessidades de manufatura.

O projeto foi idealizado com base em experiências reais adquiridas em ambientes industriais dos segmentos metalúrgico e automotivo, buscando reproduzir desafios encontrados diariamente por profissionais de PCP, logística, manutenção e operações.

---

## Problemas que o sistema pretende resolver

- Falta de visibilidade da capacidade produtiva disponível;
- Dificuldade na priorização de pedidos;
- Dependência excessiva de planilhas;
- Baixa integração das informações de produção;
- Tempo elevado para análises operacionais;
- Suporte limitado à tomada de decisão.

---

## Objetivos do Projeto

- Centralizar informações relevantes para o PCP;
- Simular capacidade produtiva;
- Apoiar o planejamento da produção;
- Facilitar a programação de recursos produtivos;
- Disponibilizar indicadores operacionais;
- Reduzir atividades manuais e repetitivas;
- Servir como laboratório de estudo para tecnologias Java aplicadas à indústria.

---

## Público-Alvo

- Analistas PCP
- Planejadores de Produção
- Analistas de Processos
- Analistas de Operações
- Consultores ERP
- Profissionais de Logística
- Gestores Industriais

---

## Funcionalidades Planejadas

### MVP 1

- Cadastro de Produtos
- Cadastro de Recursos Produtivos
- Cadastro de Clientes
- Cadastro de Pedidos

### MVP 2

- Geração de Ordens de Produção
- Controle de Capacidade
- Programação de Recursos

### MVP 3

- Simulação de Cenários
- Dashboard Operacional
- Indicadores de Planejamento

---

## Arquitetura Inicial

Entidades principais:

- Product
- Resource
- Customer
- SalesOrder
- ProductionOrder

---

## Tecnologias Planejadas

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Docker
- REST API
- GitHub Actions

---

## Roadmap

### Sprint 0 *(Fase atual)*

- [ ] Modelagem do domínio
- [ ] Diagrama UML
- [ ] Estrutura inicial Spring Boot
- [ ] Configuração do banco de dados

### Sprint 1

- [ ] CRUD de Produtos
- [ ] CRUD de Recursos
- [ ] CRUD de Clientes

### Sprint 2

- [ ] CRUD de Pedidos
- [ ] Relacionamentos entre entidades
- [ ] Persistência com JPA

### Sprint 3

- [ ] Geração de Ordens de Produção
- [ ] Regras básicas de capacidade

### Sprint 4

- [ ] Dashboard inicial
- [ ] Indicadores operacionais

---

## Motivação

Este projeto faz parte da minha jornada de evolução profissional na interseção entre operações industriais e tecnologia.

Meu objetivo é unir conhecimentos adquiridos em Planejamento e Controle da Produção (PCP), ERP, indicadores de desempenho e melhoria de processos com desenvolvimento de software utilizando Java e Spring Boot.

---

## Status

🚧 Em desenvolvimento
