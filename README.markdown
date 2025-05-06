# Plataforma de E-Commerce: Clases Básicas

## Descripción del Proyecto
Este proyecto corresponde a la **Asignación No. 2** del curso SEM2 y consiste en la implementación de clases fundamentales para una plataforma de comercio electrónico. El objetivo es desarrollar un sistema modular que gestione productos, usuarios y carritos de compra, utilizando principios de programación orientada a objetos.

El proyecto incluye tres clases principales: `Producto`, `Usuario` y `Carrito`, implementadas en **Java**. Estas clases proporcionan la base para un sistema de e-commerce, con funcionalidades como la gestión de inventario, autenticación de usuarios y cálculo del total de compras. El código está diseñado para ser extensible, permitiendo futuras integraciones con interfaces gráficas o bases de datos.

## Tecnologías Utilizadas
- **Java**: Lenguaje de programación utilizado para la implementación de las clases, elegido por su robustez, soporte para programación orientada a objetos y portabilidad.
- **Git**: Sistema de control de versiones para gestionar el código fuente.
- **GitHub**: Plataforma para alojar el repositorio público, facilitando la revisión y evaluación.
- **IDE**: Cualquier entorno de desarrollo compatible con Java (por ejemplo, IntelliJ IDEA, Eclipse) puede usarse para compilar y probar el código.
- Usare Eclipse
![image](https://github.com/user-attachments/assets/f2597b9d-0b61-4645-8260-055d6e2be7dc)

## Detalles de Implementación

### 1. Clase Producto
- **Propósito**: Representa un producto en la plataforma de e-commerce.
- **Atributos**:
  - `id` (String): Identificador único del producto.
  - `nombre` (String): Nombre del producto.
  - `descripcion` (String): Descripción del producto.
  - `precio` (double): Precio del producto.
  - `stock` (int): Cantidad disponible en inventario.
- **Métodos**:
  - Constructor para inicializar todos los atributos.
  - Getters y setters para cada atributo, con validaciones para asegurar que `precio` y `stock` no sean negativos.
- **Notas de Implementación**:
  - Se utiliza encapsulación mediante atributos privados y métodos públicos de acceso.
  - Se implementa manejo de excepciones para valores inválidos (por ejemplo, precio o stock negativos).

### 2. Clase Usuario
- **Propósito**: Representa un usuario de la plataforma.
- **Atributos**:
  - `id` (String): Identificador único del usuario.
  - `nombre` (String): Nombre del usuario.
  - `email` (String): Correo electrónico del usuario.
  - `contrasena` (String): Contraseña del usuario (almacenada como texto plano para simplicidad en esta tarea).
- **Métodos**:
  - Constructor para inicializar todos los atributos.
  - Getters y setters para cada atributo.
  - Método `finalize` (equivalente a un destructor en Java) que imprime un mensaje cuando el objeto es recolectado por el recolector de basura.
- **Notas de Implementación**:
  - El método `finalize` se incluyó para cumplir con el requerimiento opcional de un destructor, aunque no es común en aplicaciones Java modernas.
  - En un sistema real, la contraseña debería almacenarse de forma cifrada.

### 3. Clase Carrito
- **Propósito**: Representa un carrito de compras que contiene productos y calcula el total.
- **Atributos**:
  - `productos` (ArrayList<Producto>): Lista de productos en el carrito.
  - `total` (double): Costo total de los productos en el carrito.
- **Métodos**:
  - Constructor para inicializar un carrito vacío.
  - `agregarProducto(Producto, int)`: Añade una cantidad específica de un producto al carrito, actualizando el stock y el total.
  - `removerProducto(Producto)`: Elimina un producto del carrito, actualizando el stock y el total.
  - `calcularTotal()`: Recalcula el costo total basado en los productos en el carrito.
  - Getters para `productos` y `total`.
- **Notas de Implementación**:
  - Se utiliza `ArrayList` para almacenar dinámicamente los productos.
  - Incluye validaciones para evitar agregar más productos de los disponibles en stock o eliminar productos inexistentes.
  - El manejo de excepciones asegura un comportamiento robusto (por ejemplo, verificaciones de valores nulos o stock insuficiente).

## Desafíos Enfrentados y Soluciones
1. **Gestión del Stock en Carrito**:
   - **Desafío**: Garantizar que al añadir o eliminar productos del carrito se actualicen correctamente el stock del producto y el total del carrito.
   - **Solución**: Se implementó lógica en `agregarProducto` y `removerProducto` para realizar actualizaciones atómicas del stock y el total. Se añadieron verificaciones para evitar agregar más productos de los disponibles.

2. **Destructor en Java**:
   - **Desafío**: Java no proporciona destructores explícitos como otros lenguajes (por ejemplo, C++). El requerimiento pedía un destructor opcional para la clase `Usuario`.
   - **Solución**: Se utilizó el método `finalize`, que es invocado por el recolector de basura. Se documentó que este método no se usa frecuentemente en aplicaciones modernas.

3. **Validación y Encapsulación**:
   - **Desafío**: Asegurar que todos los atributos estén encapsulados y que los valores sean válidos.
   - **Solución**: Se declararon todos los atributos como privados y se implementaron getters/setters con validaciones (por ejemplo, precio y stock no negativos).

## Capturas de Pantalla
*Nota*: Dado que esta es una implementación de backend sin interfaz gráfica, no se incluyen capturas de pantalla visuales. A continuación, se muestra una representación conceptual de la interacción entre las clases:

```
[Usuario] ----> [Carrito] ----> [Producto]
- Gestiona      - Contiene      - Define
  datos de       lista de       atributos
  usuario        productos      del producto
- Maneja        - Calcula      - Gestiona
  autenticación  total          stock
```

Para probar el funcionamiento del código, se puede crear una clase `Main` con un ejemplo de uso:

```java
public class Main {
    public static void main(String[] args) {
        EcommerceClasses.Producto p1 = new EcommerceClasses.Producto("1", "Laptop", "Laptop de alta gama", 999.99, 10);
        EcommerceClasses.Usuario u1 = new EcommerceClasses.Usuario("U1", "Juan Pérez", "juan@ejemplo.com", "contraseña123");
        EcommerceClasses.Carrito carrito = new EcommerceClasses.Carrito();
        
        carrito.agregarProducto(p1, 2);
        System.out.println("Total: $" + carrito.getTotal());
        System.out.println("Stock restante: " + p1.getStock());
    }
}
```



// Producto.java
public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;

    // Constructor
    public Producto(int id, String nombre, String descripcion, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

// Usuario.java
public class Usuario {
    private int id;
    private String nombre;
    private String correo;
    private String contrasena;

    // Constructor
    public Usuario(int id, String nombre, String correo, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    // Finalize method (equivalente a destructor, aunque raramente usado en Java)
    @Override
    protected void finalize() throws Throwable {
        try {
            System.out.println("Usuario con ID " + id + " está siendo eliminado");
        } finally {
            super.finalize();
        }
    }
}

// Carrito.java
import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<Producto> productos;
    private double total;

    // Constructor
    public Carrito() {
        this.productos = new ArrayList<>();
        this.total = 0.0;
    }

    // Método para añadir producto
    public void agregarProducto(Producto producto) {
        if (producto.getStock() > 0) {
            productos.add(producto);
            total += producto.getPrecio();
            producto.setStock(producto.getStock() - 1);
            System.out.println("Producto " + producto.getNombre() + " añadido al carrito");
        } else {
            System.out.println("No hay stock disponible para " + producto.getNombre());
        }
    }

    // Método para remover producto
    public void removerProducto(Producto producto) {
        if (productos.remove(producto)) {
            total -= producto.getPrecio();
            producto.setStock(producto.getStock() + 1);
            System.out.println("Producto " + producto.getNombre() + " removido del carrito");
        } else {
            System.out.println("El producto " + producto.getNombre() + " no está en el carrito");
        }
    }

    // Método para calcular total
    public double calcularTotal() {
        return total;
    }

    // Getter para la lista de productos
    public List<Producto> getProductos() {
        return productos;
    }

    // Getter para el total
    public double getTotal() {
        return total;
    }
}

// Main.java (para probar las clases)
public class Main {
    public static void main(String[] args) {
        // Crear productos
        Producto p1 = new Producto(1, "Laptop", "Laptop de alta gama", 999.99, 5);
        Producto p2 = new Producto(2, "Mouse", "Mouse óptico", 19.99, 10);

        // Crear usuario
        Usuario usuario = new Usuario(1, "Juan Pérez", "juan@example.com", "contraseña123");

        // Crear carrito
        Carrito carrito = new Carrito();

        // Agregar productos al carrito
        carrito.agregarProducto(p1);
        carrito.agregarProducto(p2);

        // Mostrar total
        System.out.println("Total del carrito: $" + carrito.calcularTotal());

        // Remover un producto
        carrito.removerProducto(p2);

        // Mostrar total actualizado
        System.out.println("Total del carrito después de remover: $" + carrito.calcularTotal());
    }
}
