# datingwebsite

Skills demonstrated in this project:

- Spring Boot
- Hibernate 
  - CRUD operations
  - Batch updates
  - Sequences
- JPQL
- Big O
  - Maps used extensively for methods. This is to aid performance as the get operations for LinkedHashMap and HashMap are both O(1).

- SQL 
  - Check constraint on Answer table. For weighted answers, a weight must always be selected. The check constraint ensures that this weight cannot be null for 
    weighted answers (AnswerWeightedImpls) and thus solves the potential data integrity prroblem that using the Single Table inheritance strategy presents. 
   
- Testing
  -  Junit 5 and Mockito
  -  SQL database for production and H2 database for testing
  
 -  Maven
 
- Design patterns
  - Visitor pattern
  - Dependency Injection
  - Service-DAO
  - Composite
