package ecomerce;
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