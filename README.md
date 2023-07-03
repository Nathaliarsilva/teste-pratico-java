# Custumer Service

## Requisitos
- JDK 8
- Maven 3

## Rodando
- Clone o projeto: https://github.com/Nathaliarsilva/teste-pratico-java.git
- Entre na pasta `practical-test` e execute: `mvn spring-boot:run`
- Acesse: http://localhost:8080/swagger-ui.html
- Abra a aba customer-controller
- Pode testar os endpoints referente a Customer

## API endpoints
Esses endpoints permitem que você faça operações de criação, leitura, atualização e exclusão de clientes

|Método | EndPoint | Descrição| PathVariable| RequestParam| BodyRequest| BodyResponse|  
|--|--|--|--|--|--|--|  
| POST| /customers |Cria um novo cliente| | | `{"emails":[{"email":"string"}],"name":"string"}`|`{"emails":[{"email":"string","id":0}],"id":0,"name":"string"}`|  
| GET | /customers |Retorna todos os clientes (com paginação)| |pageNo= Número de páginas; pageSize= Tamanho da página; sortBy= Como deve ser ordenado | | |  
| GET | /customers/{id} |Retorna um cliente pelo ID| id = identificador do cliente | | | |  
| UPDATE| /customers/{id} |Altera um cliente pelo ID |id = identificador do cliente | | `{"emails":[{"email":"string","id":0}],"id":0,"name":"string"}`| `{"emails":[{"email":"string","id":0}],"id":0,"name":"string"}`|  
| DELETE| /customers/{id} |Exclui um cliente pelo ID| id = identificador do cliente | | | |  

## Validações De Dados do Cliente
### Email
- Deve ter um '@';
- Deve  ter um ponto '.<alguma_coisa>';

|Exemplos de Email| Resultado da Validação  |
|--|--|
|teste@email.com  | POSITIVO  |
|testeemail.com  | NEGATIVO  |
|teste@emailcom  | NEGATIVO |

### Nome
- Não pode ter números;
- Não pode ter caracteres especiais;
- Deve começar com uma letra maiúscula;
- Respeitar regra de um espaço;

|Exemplos de Nomes| Resultado da Validação  |
|--|--|
|Teste  | POSITIVO  |
|Teste Silva | POSITIVO  |
|teste  | NEGATIVO  |
|t3ste  | NEGATIVO |
|t#ste  | NEGATIVO |
|Teste silva | NEGATIVO|
|Teste Silva | NEGATIVO (levar em consideração mais de um espaço entre as palavras)|

