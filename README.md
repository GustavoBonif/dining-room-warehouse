Projeto Dining-Room | Arquitetura de Microsserviços

Esta versão da aplicação Dining-Room foi desenvolvido com arquitetura de microsserviços para o meu Trabalho de Conclusão de Curso (TCC) entitulado "ANÁLISE COMPARATIVA ENTRE ARQUITETURA DE SISTEMA MONOLÍTICO E MICROSSERVIÇO: DESEMPENHO E CONSUMO DE RECURSO".

Neste repositório está o microsserviço do _Warehouse_, contendo a entidade _Stock, que trata das interações e mantém o estoque com o sistema.

Os outros microsserviços que contém a aplicação Dining-Room na arquitetura de microsserviços são:

- Eureka-Server: https://github.com/GustavoBonif/dining-room-eureka-server
- Gateway: https://github.com/GustavoBonif/dining-room-gateway
- User-Registry: https://github.com/GustavoBonif/dining-room-user-registry
- Catalog: https://github.com/GustavoBonif/dining-room-catalog
- Cart: https://github.com/GustavoBonif/dining-room-cart

O projet Dining-Room o é um _ecommerce_ simples, com apenas o _backend_, e tem como objetivo comparar o tempo do resposta e o consumo de CPU das instâncias EC2 na AWS de cada aplicação.

As tecnologias empregadas foram:
- Java 17;
- Spring 3.1.4;
- MySQL 8.0.29.

As ferramentas utilizadas: 
- _Postman_ para realização de consulta de API;
- _Apache JMeter_ para criação de plano de testes para _baseline test_ e _load test_

Além disso, foi utilizado o ambiente _Amazon Web Server_ (AWS) para hospedagem do sistema em plataforam nuvem.
