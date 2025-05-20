package ecommerce4;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<Producto> productos;

    public Carrito() {
        productos = new ArrayList<>();
    }

    // Overloaded method: Add product by Producto object
    public String agregarProducto(Producto producto) {
        productos.add(producto);
        return "Producto " + producto.getNombre() + " agregado al carrito.";
    }

    // Overloaded method: Add product by ID, name, price, and type
    public String agregarProducto(String idProducto, String nombre, double precio, String tipo, double parametroExtra) {
        Producto producto;
        if (tipo.equalsIgnoreCase("digital")) {
            producto = new ProductoDigital(idProducto, nombre, precio, parametroExtra);
        } else {
            producto = new ProductoFisico(idProducto, nombre, precio, parametroExtra);
        }
        productos.add(producto);
        return "Producto " + nombre + " agregado al carrito.";
    }

    // Overloaded method: Add product by ID only (mock database lookup)
    public String agregarProducto(String idProducto) {
        // Simulate database lookup
        Producto producto;
        if (idProducto.startsWith("D")) {
            producto = new ProductoDigital(idProducto, "Ebook " + idProducto, 10.99, 50.0);
        } else {
            producto = new ProductoFisico(idProducto, "Libro " + idProducto, 20.99, 1.0);
        }
        productos.add(producto);
        return "Producto con ID " + idProducto + " agregado al carrito.";
    }

    public String mostrarContenido() {
        if (productos.isEmpty()) {
            return "El carrito está vacío.";
        }
        StringBuilder contenido = new StringBuilder("Contenido del carrito:\n");
        for (Producto producto : productos) {
            contenido.append(producto.mostrarDetalle()).append("\n");
        }
        return contenido.toString();
    }

    public double calcularTotal() {
        return productos.stream().mapToDouble(Producto::getPrecio).sum();
    }
}