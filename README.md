# Projeto Localizador de Museus

Uma API onde a principal funcionalidade é facilitar a busca por museus baseado na sua localização.
Os dados foram retirados [desta](http://dados.cultura.gov.br/dataset/series-historicas-cadastro-nacional-de-museus)
série histórica.

Projeto parte do módulo **Eletiva Java** do curso de Desenvolvimento Web da Trybe.
O objetivo foi trabalhar as seguintes habilidades:

* Criar classes de controle e suas rotas
* Criar classes de serviço
* Utilizar injeção de dependências
* Trabalhar com exceções customizadas
* Tratar exceções da API através de gerenciadores de erros
* Implementar testes unitários para cobertura de código
* Criar uma configuração Docker para a aplicação


### Stacks
> Java, Spring Framework, Spring Web, JUnit, Spring Boot Actuator


### Como usar
1. Clonar repositório;
2. Entrar na pasta clonada e instalar as dependências digitando ˋmvn installˋ;
3. Para testar: ˋmvn testˋ
4. Checkstyle: ˋmvn checkstyle:checkˋ


### Resumo dos requisitos pedidos no projeto
1. Crie as classes de Modelo e DTO;
2. Habilite o Spring Boot Actuator;
3. Implemente o método createMuseum da camada de serviço;
4. Implemente o método getClosestMuseum da camada de serviço;
5. Criar classe _controller_ para `/museums` com rota POST;
6. Criar rota GET `/museums/closest`;
7. Criar um ControllerAdvice para tratar erros;
8. Implemente testes para as classes CollectionTypeController e CollectionTypeService para atingir
cobertura de 80% das linhas;
9. Implemente controller, service e testes para rota GET `/museums/{id}`;
10. Crie um Dockerfile para sua aplicação;


### Créditos
Estrutura do projeto desenvolvidos pelo time da Trybe, sendo os seguintes diretórios de minha
autoria:
* src/main/java/com.betrybe.museumfinder.advice, controller, dto, exception, model, service
* test/java/com.betrybe.museumfinder/solution
* Dockerfile