# E-Commerce Platform: Polymorphism and Overloading

## Project Description
This project implements an e-commerce platform in Java to demonstrate object-oriented programming concepts, specifically polymorphism, method overloading, and method overriding. The system manages products (digital and physical), a shopping cart, and orders, showcasing how different product types can be handled uniformly through a base class while allowing specialized behavior in derived classes.

## Technologies Used
- **Java**: Version 17 (JDK 17) for core programming.
- **Maven**: For project management and dependencies (optional, not used in this implementation for simplicity).
- **Git**: For version control and GitHub hosting.

## Implementation Details

### Polymorphism
- The `Producto` class is an abstract base class with derived classes `ProductoDigital` and `ProductoFisico`.
- Polymorphism is demonstrated in the `Pedido` class, where the `agregarProducto` method accepts any `Producto` object, regardless of whether it is `ProductoDigital` or `ProductoFisico`. The `mostrarDetalle` method is called polymorphically, displaying specific details based on the actual object type.

### Method Overloading
- The `Carrito` class implements three overloaded `agregarProducto` methods:
  1. Accepts a `Producto` object.
  2. Accepts an ID, name, price, type, and an extra parameter (file size or weight).
  3. Accepts only an ID, simulating a database lookup.
- This allows flexible addition of products to the cart based on available data.

### Method Overriding
- The `mostrarDetalle` method in the `Producto` class is overridden in `ProductoDigital` and `ProductoFisico` to provide specific details (e.g., file size for digital products, weight for physical products).

## Setup and Execution
1. Clone the repository from GitHub.
2. Ensure JDK 17 is installed.
3. Navigate to the `src` directory and compile the Java files:
   ```bash
   javac model/*.java service/*.java Main.java
   ```
4. Run the program:
   ```bash
   java Main
   ```

## Challenges and Solutions
- **Challenge**: Java’s static typing required careful design of the `agregarProducto` overloaded methods to handle different input combinations without code duplication.
  - **Solution**: Used a clear parameter structure and type checking within the `Carrito` class to create appropriate product instances based on the `tipo` parameter.
- **Challenge**: Ensuring polymorphic behavior in the `Pedido` class without type casting.
  - **Solution**: Relied on the abstract `Producto` class and its `mostrarDetalle` method, allowing the `Pedido` class to handle any product type generically.

## Screenshots
*(Note: Screenshots would be added here in a real submission, showing console output of the Main class execution. For this response, imagine console outputs displaying cart contents and order details.)*

## References
- Oracle. (2023). *The Java Tutorials: Polymorphism*. Retrieved from https://docs.oracle.com/javase/tutorial/java/IandI/polymorphism.html
- Horstmann, C. S. (2019). *Core Java, Volume I--Fundamentals* (11th ed.). Prentice Hall.

## GitHub Repository
The source code is available at: [Insert GitHub URL here]

*Note*: Ensure the repository is public for evaluation.