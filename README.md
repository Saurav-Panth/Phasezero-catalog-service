Absolutely! Here’s your polished **README.md** in proper Markdown format, complete with all sections, usage examples, and enum documentation:

```markdown
# Phasezero Catalog Service

The **Phasezero Catalog Service** is a Spring Boot application designed to manage a product catalog through a clean and well‑structured REST API. It provides comprehensive product inventory management features including creation, searching, filtering, sorting, pagination, and total inventory value calculation.

The project follows a **clean layered architecture** with validation, structured responses, and centralized exception handling.

---

## 1. Build and Run Instructions

### Requirements
- **Java** 17+
- **Maven** 3.8+
- **Database**: No external database required (uses in‑memory **H2**)

### Steps
1. Clone the repository.
2. Navigate to the project root directory.
3. Run the application using:
   ```bash
   mvn spring-boot:run
   ```
4. Access the API at:  
   `http://localhost:8080`

---

## 2. Project Design Overview

This project is structured into three distinct layers:

### 🔹 Controller Layer
- Handles incoming HTTP requests.
- Returns responses wrapped inside `ResponseStructure<T>`.
- Maps endpoints to corresponding service methods.

### 🔹 Service Layer
- Contains all business logic:
  - Search and filter operations.
  - Validation (enum category, empty results, invalid input).
  - Inventory value computation.
  - Sorting and pagination.
- Implements error handling using custom exceptions (e.g., `NoRecordException`).

### 🔹 Repository Layer
- Uses **Spring Data JPA** for persistence.
- Supports derived queries and JPQL queries.

### Entities
- **Product** – JPA entity representing catalog items.
- **Category** – Enum defining product categories.

### Custom Components
- **ResponseStructure<T>** – Uniform API response wrapper.
- **NoRecordException** – Thrown when no data is found.

---

## 3. Why H2 Database?

The **H2 in‑memory database** was chosen for the following reasons:
- ✔ Lightweight and embedded.
- ✔ No installation required.
- ✔ Resets automatically on each application restart.
- ✔ Ideal for testing and evaluation.
- ✔ Fast startup with zero configuration.
- ✔ Easy debugging via H2 Console.

These qualities make H2 optimal for demonstrating API functionality in development and evaluation environments.

---

## 4. API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| **POST** | `/products` | Create a new product |
| **GET** | `/products/{id}` | Fetch product by ID |
| **GET** | `/products` | Fetch all products |
| **GET** | `/products/search?name=` | Search products by part name |
| **GET** | `/products/category/{category}` | Filter products by category (enum) |
| **GET** | `/products/sorted/price` | List products sorted by price (ascending) |
| **GET** | `/products/page?page=&size=` | Paginated product list |
| **GET** | `/products/inventory/value` | Compute total inventory value |

---

## 5. Custom Exceptions

The application defines several custom exceptions to ensure robust error handling:

1. **IdNotFoundException**  
   - Thrown when a requested product ID does not exist.  
   - **HTTP 404 NOT FOUND**

2. **DuplicatePartNumberException**  
   - Thrown when attempting to create a product with an existing `partNumber`.  
   - **HTTP 409 CONFLICT**

3. **NegativePriceException**  
   - Thrown when `price < 0` during creation or update.  
   - **HTTP 400 BAD REQUEST**

4. **NegativeStockException**  
   - Thrown when `stock < 0` during creation or update.  
   - **HTTP 400 BAD REQUEST**

5. **NoRecordException**  
   - Thrown when no products are found, or when search/filter/pagination results are empty.  
   - **HTTP 404 NOT FOUND**

6. **PartNameNotFoundException**  
   - Thrown when the required field `partName` is missing or empty.  
   - **HTTP 400 BAD REQUEST**

7. **MissingFieldException**  
   - Thrown when any required field (other than `partName`) is missing.  
   - **HTTP 400 BAD REQUEST**

8. **HttpMessageNotReadableException** *(Spring default)*  
   - Thrown when incoming JSON contains an invalid enum value.  
   - Example: `"category": "SA"`  
   - **HTTP 400 BAD REQUEST**  
   - Message:  
     ```
     Invalid category. Allowed values: [ELECTRONICS, FURNITURE, ACCESSORIES, CLOTHING]
     ```

---

## 6. Usage Examples

### Create a Product

**Request (POST `/products`)**
```json
{
  "partNumber": "PE23EJ5",
  "partName": "Hydraulic Filter",
  "category": "ELECTRONICS",
  "price": 1200.50,
  "stock": 78,
  "brand": "Dell",
  "description": "High-performance laptop"
}
```

**Successful Response**
```json
{
  "status": "SUCCESS",
  "message": "Product created successfully",
  "data": {
    "id": 1,
    "partNumber": "PE23EJ5",
    "partName": "Hydraulic Filter",
    "category": "ELECTRONICS",
    "price": 1200.50,
    "stock": 78,
    "brand": "Dell",
    "description": "High-performance laptop"
  }
}
```

---

### Fetch Product by ID

**Request (GET `/products/1`)**
```http
GET /products/1
```

**Response**
```json
{
  "status": "SUCCESS",
  "message": "Product retrieved successfully",
  "data": {
    "id": 1,
    "partNumber": "PE23EJ5",
    "partName": "Hydraulic Filter",
    "category": "ELECTRONICS",
    "price": 1200.50,
    "stock": 78,
    "brand": "Dell",
    "description": "High-performance laptop"
  }
}
```

---

### Enum: Category

The `category` field is validated against the following **enum values**:

- `ELECTRONICS`
- `FASHION`
- `GROCERY`
- `SPORTS`
- `OTHER`

If an invalid category is provided, the API responds with:

```json
{
  "status": "ERROR",
  "message": "Invalid category. Allowed values: [ELECTRONICS, FURNITURE, ACCESSORIES, CLOTHING]"
}
```

---

### Example: Compute Inventory Value

**Request (GET `/products/inventory/value`)**
```http
GET /products/inventory/value
```

**Response**
```json
{
  "status": "SUCCESS",
  "message": "Total inventory value computed successfully",
  "data": 93639.00
}
```

---

## 7. Key Highlights
- ✅ Clean layered architecture (Controller, Service, Repository).  
- ✅ Centralized exception handling with meaningful error responses.  
- ✅ In‑memory H2 database for simplicity and fast evaluation.  
- ✅ Support for search, filter, sort, pagination, and inventory value computation.  
- ✅ Uniform API response structure for consistency.  

---

## 8. Conclusion

The **Phasezero Catalog Service** provides a robust and extensible product inventory management API with clean architecture, centralized exception handling, and developer‑friendly responses. Its use of an in‑memory H2 database ensures quick setup and evaluation, while the layered design makes it easy to extend for production environments.
```

---

