package ecomerce;

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