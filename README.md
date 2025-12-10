# Phasezero-catalog-service
The phasezero-catalog-service is a Spring Boot application that manages a product catalog.

Product Inventory Management API
A Spring Boot–based REST API for managing product inventory. This application supports product
creation, searching, filtering, sorting, pagination, and total inventory valuation. It uses a clean layered
architecture and includes proper validation, structured responses, and exception handling.
1. How to Build and Run the Application
Requirements:- Java 17+- Maven 3.8+- No external database required (uses in-memory H2)
Run Using Maven:
mvn spring-boot:run
Build JAR:
mvn clean package
java -jar target/product-api.jar
Access H2 Console:
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
2. Project Design Overview
Three-layer architecture:- Controller Layer: Handles HTTP requests and returns structured responses.- Service Layer: Business logic, validation, inventory computation, search, sorting, pagination.- Repository Layer: Spring Data JPA for DB operations.
Entities: Product (JPA), Category (Enum)
Custom Exceptions: NoRecordException
Response Wrapper: ResponseStructure
3. Why H2 Database Was Used- Lightweight & embedded- No installation required- Ideal for testing and assignment evaluation- Automatically reset on restart- Fully supported by Spring Boot- Easy to debug via H2 console
4. Schema Management
Schemas are auto-generated using JPA entities via:
spring.jpa.hibernate.ddl-auto=update
5. Available API Endpoints
POST /products - Create new product
GET /products/{id} - Fetch product by ID
GET /products/search?text= - Search by part name
GET /products/category/{category} - Filter by enum category
GET /products/sorted/price - List products sorted by price ascending
GET /products/page?page=&size;= - Paginated product list
GET /products/inventory/value - Compute total inventory value
6. Example Requests & Responses
Create Product:
{ "partNumber": "PE23E5", "partName": "Hydraulic Filter", "category": "ELECTRONICS", "price":
1200.50, "stock": 10, "brand": "Dell", "description": "High-performance laptop" }
Sorted Products Response:
{ "statusCode": 200, "message": "SUCCESS", "data": [ ... ] }
Inventory Value Response:
{ "statusCode": 200, "message": "SUCCESS", "data": 902.5 }
Invalid Category Error:
{ "statusCode": 400, "message": "Invalid category...", "data": null }
7. Assumptions & Limitations- Enum category must be valid- H2 resets each run- Double precision limitations- Pagination starts at index 0- Product uniqueness not enforced by default
8. Evaluation Criteria Alignment
 API design correctness
 Business logic and validation
 Code structure and readability
 Data handling
 Clear documentation
