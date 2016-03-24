
## xy-inc
Repositorio para desenvolvimento de aplicação para ZUP IT Innovation

### Download aplicacao

Execute em linha de comando

	$ git clone https://github.com/hbrayres/xy-inc.git

	$ git pull

### Execucao da aplicação

acesse a pasta da aplicacao
 
	$ cd pontointeresse

execute comando maven para buildar a aplicacao
  
	$ mvn clean install

Sera gerado um arquivo pontointeresse.war na pasta {pontointeresse.dir}\target
copie para a pasta de deploy do JBoss AS 7.1.1 ({jboss.dir}\standalone\deployments)
depois execute standalone.bat(win)/.sh(linux) o jboss via comando

	$ {jboss.dir}\bin\standalone.[bat|sh]

### Uso da API: ponto de interese

 Servico 1: Salvar novo ponto de interesse (POI)
 
	 Method: POST
	 URI: /api/pontointeresses
	 data: pontointeresse {
	      nome   : String
	      coordX : number
	      coordY : number
	 }


Servico 2: Listar todos os POIS cadastrados

	Method: GET
	URI: /api/pontointeresses


Servico 3: Listar os POIs proximos a um ponto (x,y) com uma distancia maxima (d-max)
 
	 Method: GET
	 URI: /api/pontointeresses
	 params: 
	      x     : number
	      y     : number
	      d-max : number
	
	Exemplo de requisicao: http://localhost:8080/pontointeresse/api/pontointeresses?x=20&y=10&d-max=14
	
Parametros sao obrigatórios para exibir os locais proximos, caso um dos parametros seja nulo sera listado todos os registros
 

 Servico 4: Atualizar POI
 
	Method: PUT
	URI: /api/pontointeresses/{id}
	data: pontointeresse {
	      id     : number
	      nome   : String
	      coordX : number
	      coordY : number
	}
	
	
 Servico 5: Remover POI
 
	 Method: DELETE
	 URI: /api/pontointeresses/{id}
 

### Definições da aplicação

 Foi usado o JBoss Forge 2.19.2.Final para a geração do projeto
 Com modificações na parte de injecoes de codigo, optei por utilizar
 EJB3 fazendo uso de @Stateless e @Local nos Entity Beans, pois tenho maior
 vivencia com esses recursos, poderia ter utilizado @Inject usado pelo CDI, 
 mas preferir utilizar os recursos do EJB.

 Optei por utilizar AngularJS na parte WEB pela facilidade que o framework proporciona e
 por que estou utilizando mais recentemente em projetos que estou desenvolvendo.
 
 Para o layout das páginas usei Bootstrap 3 e jQuery2.1, minha escolha foi devido ser um
 framework que te dar liberdade para modificacoes nos componentes.

 Nos teste unitarios optei pelo Mockito (mockito.org) que eh integrado ao JUnit para execucao
 de testes unitarios. Te ajuda para 'mockar' os objetos em Java.

 Foram realizados cinco testes unitarios nas regras de negocio: 
 
	1. Teste salvar com sucesso;
	 
	2. Teste para validar o calculo de distancia, nao listar se estiver longe;
	 
	3. Teste para validar o calculo de distancia, listar se estiver proximo;
	 
	4. Teste salvar coordenada negativa;
	
 	5. Listar todos os POIs cadastrados.

### Estrutura da aplicação
Os pacotes na aplicacao seguiu padroes do MVC, que na camada do View foi usado
o angularJS que implementa o paradigma Model-View-Whatever

 	\pontointeresse
		\src
			\main
				\java
					\br.com.zup
						\api 		[encontra-se a implementacao de todos os recursos REST da aplicacao]
					
						\controller
							\business 	[implementacao das regras de negocio da aplicacao, injeta os dao]
							\dao 		[implementacao das consultas, updates na base de dados]
							\facade 	[responsavel por agrupar os Beans do business, injeta os business]
						
						\model
							\entity 	[entidades mapeados da base de dados]
						
					\util 		[classes utilitarias que eh utilizada pela aplicacao]
					
				\webapp
				
			\test
				\br.com.zup.test 	[implementacoes dos testes unitarios da aplicacao]
				

