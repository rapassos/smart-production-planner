# ADR-002

## Título

Adoção do PostgreSQL como Banco de Dados Relacional

---

## Status

Aprovado

---

## Contexto

O Smart Production Planner necessita de um banco de dados relacional para armazenar informações relacionadas a clientes, produtos, recursos produtivos, pedidos de venda, ordens de produção e cenários de planejamento de capacidade.

O sistema será desenvolvido utilizando Java 21, Spring Boot e Spring Data JPA, exigindo uma solução robusta, confiável e amplamente utilizada pelo mercado.

Além dos requisitos atuais do MVP, a arquitetura deverá suportar futuras evoluções como integração com ERP, MRP simplificado, dashboards operacionais e simulações avançadas de capacidade produtiva.

---

## Decisão

Foi adotado o PostgreSQL como banco de dados relacional oficial do Smart Production Planner.

O PostgreSQL será utilizado em ambiente de desenvolvimento, homologação e produção.

A integração com a aplicação será realizada através do Spring Data JPA.

---

## Justificativa

O PostgreSQL foi escolhido pelos seguintes motivos:

* Banco de dados Open Source amplamente utilizado pelo mercado;
* Excelente integração com Spring Boot;
* Suporte nativo a UUID;
* Alta confiabilidade e estabilidade;
* Forte aderência a padrões SQL;
* Ótimo desempenho para aplicações corporativas;
* Grande comunidade e ampla documentação;
* Facilidade de execução em containers Docker.

---

## Consequências Positivas

* Padronização tecnológica;
* Facilidade de desenvolvimento local;
* Facilidade de integração com ferramentas do ecossistema Java;
* Boa escalabilidade para o porte esperado do sistema;
* Suporte adequado para futuras evoluções do produto.

---

## Consequências Negativas

* Necessidade de conhecimento específico para administração e otimização;
* Maior consumo de recursos quando comparado a bancos embarcados;
* Dependência de infraestrutura adicional para execução local.

---

## Alternativas Consideradas

### MySQL

Não adotado.

Motivos:

* Menor aderência ao uso extensivo de UUID;
* Menor alinhamento com experiências anteriores do projeto;
* Menor interesse técnico para evolução futura da solução.

### H2 Database

Não adotado como banco principal.

Motivos:

* Adequado apenas para testes ou prototipação;
* Não representa adequadamente cenários reais de produção;
* Diferenças comportamentais em relação a bancos utilizados em ambiente corporativo.

### PostgreSQL

Adotado.

Atende plenamente aos requisitos atuais e futuros previstos para o produto.

---

## Revisão Futura

Esta decisão poderá ser revisada caso surjam requisitos específicos de escalabilidade, distribuição geográfica ou necessidades técnicas que justifiquem a adoção de outra tecnologia de persistência.

